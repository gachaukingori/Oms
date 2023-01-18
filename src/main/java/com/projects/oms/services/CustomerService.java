package com.projects.oms.services;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.models.*;
import com.projects.oms.repositories.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

@Service
public class CustomerService implements CustomerServiceInterface, PreparedStatementCreator {
   static HashMap<Integer, Customer> customerHashMap = new HashMap();
    private CustomerRepository customerRepository;
    String query;

   @Autowired
   JdbcTemplate db;
    public static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(CustomerController.class);


    @Override
    public void createNewCustomer(ArrayList<Customer> customerList) {
//        db.execute("CREATE TABLE CUSTOMERS ( ID INT(10), FNAME VARCHAR(255), LNAME VARCHAR(255) )");
            logger.info("Customer object " + customerList.get(0));

//        PreparedStatementCreator preparedStatement = db.execute()
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            String query = "INSERT INTO CUSTOMERS (CUSTOMERNO, ACCTNO, DELIVERYADDRESS, BILLINGADDRESS, TELEPHONE, FNAME) " +
                    "VALUES (?,?,?,?,?,?)";
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

              return  con.prepareStatement(query);
            }
        };


        customerList.forEach((customer)->{

            customerHashMap.put(customer.getCustomerNumber(), customer);


            db.execute(con -> {
              String query ;
                if(customer instanceof  BusinessCustomer){
                    query = "INSERT INTO CUSTOMERS (CUSTOMERNO, ACCTNO, DELIVERYADDRESS, BILLINGADDRESS, TELEPHONE, CUSTOMERNAME, CUSTOMERTYPE) " +
                            "VALUES (?,?,?,?,?,?,?)";
                }else{
                    query = "INSERT INTO CUSTOMERS (CUSTOMERNO, ACCTNO, DELIVERYADDRESS, BILLINGADDRESS, TELEPHONE, CUSTOMERNAME,CUSTOMERTYPE,FNAME,LNAME) " +
                            "VALUES (?,?,?,?,?,?,?,?,?)";
                }
                return con.prepareStatement(query);
            }, (PreparedStatementCallback<Boolean>) ps -> {
                ps.setInt(1,customer.getCustomerNumber());
                ps.setString(2,customer.getAccount().getAccountno());
                ps.setString(3,customer.getDeliveryAddress().getPostcode());
                ps.setString(4,customer.getBillingAddress().getPostcode());
                ps.setString(5,customer.getTelephoneNumber());
                ps.setString(6, customer.getName());
                if(customer instanceof  BusinessCustomer) {
                    ps.setString(7, "B");
                }else{
                    ps.setString(7, "P");
                    ps.setString(8,  ((PrivateCustomer) customer).getFirstName());
                    ps.setString(9,  ((PrivateCustomer) customer).getLastName());
                }
//                logger.info("PREPARED STATEMENT" + ps.toString());

                return  ps.execute();
            });

        });


    }



    @Override
    public String deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM  CUSTOMERS WHERE CUSTOMERNO =?";
        customerRepository = new CustomerRepository(query);
        Boolean deleted = db.execute(con -> con.prepareStatement(query), (PreparedStatementCallback<Boolean>) ps -> {
            ps.setInt(1, customerId);
                logger.info(ps.toString());
            return ps.execute();
        });
        if(deleted){
            customerHashMap.remove(customerId);
            return "customer " + customerId +" deleted successfully";
        }else{
            return "customer " + customerId +" does not exist";
        }



    }

    @Override
    public Collection<Customer> findCustomer(int customerId) {
        Customer[] tempCustomer = {null};
        HashMap<Integer,Customer > temp = new HashMap<>();
//        customerHashMap.forEach((count, element)->{
//            if(count == customerId){
//                tempCustomer[0] = customerHashMap.get(customerId);
//                temp.put(customerId,tempCustomer[0]);
//            }
//        });

       Customer customer = db.execute((PreparedStatementCreator) con -> con.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMERNO =? ")
               , (PreparedStatementCallback<Customer>) ps -> {
                   ps.setInt(1, customerId);
                   ResultSet rs = ps.executeQuery();
                   BusinessCustomer customer1 = new BusinessCustomer();
                   if(rs.next()){
                       customer1.setCustomerNumber(rs.getInt("CUSTOMERNO"));
                       customer1.setAccount(new Account(rs.getString("ACCTNO"),0));
                       customer1.setDeliveryAddress(new Address(rs.getString("DELIVERYADDRESS"),"",""));
                       customer1.setTelephoneNumber(rs.getString("TELEPHONE"));
                       customer1.setCompanyName(rs.getString("FNAME"));
                   }
                   return customer1;
               });
            temp.put(1,customer);

        return temp.values();
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        customerHashMap.forEach((count, element)->{
            logger.info("\n element:"+count + " : \n "+element.toString());
        });


        PreparedStatementCreator psc = new PreparedStatementCreator() {
            String query = "SELECT * FROM CUSTOMERS";
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(query);
            }
        };
        query = "SELECT * FROM CUSTOMERS";
      return  db.query(con->con.prepareStatement(query), rs -> {
          HashMap<Integer, Customer> hashMap = new HashMap<>();
          int count = 0;
          while(rs.next()){

              count++;
              Customer customer ;
//              logger.info("RESULTSET "+rs.toString());
                String customerType = rs.getString("CUSTOMERTYPE");

              if(customerType.equals("B")) {
                   customer = new BusinessCustomer();
              }else{
                   customer = new PrivateCustomer();
              }
              customer.setCustomerNumber(rs.getInt("CUSTOMERNO"));
              customer.setAccount(new Account(rs.getString("ACCTNO"),0));
              customer.setDeliveryAddress(new Address(rs.getString("DELIVERYADDRESS"),"",""));
              customer.setTelephoneNumber(rs.getString("TELEPHONE"));
//                    customer.(rs.getString("TELEPHONE"));

                    if (customer instanceof BusinessCustomer) {
                        ((BusinessCustomer) customer).setCompanyName(rs.getString("CUSTOMERNAME"));
                    }else{
                        ((PrivateCustomer) customer).setFirstName(rs.getString("FNAME"));
                        ((PrivateCustomer) customer).setLastName(rs.getString("LNAME"));
                    }



              hashMap.put(count,customer);
          }
          return hashMap;
      }).values();

//        return customerHashMap.values();
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }

    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        return null;
    }
}
