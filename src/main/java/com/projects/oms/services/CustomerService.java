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


            db.execute(psc, (PreparedStatementCallback<Boolean>) ps -> {
                ps.setInt(1,customer.getCustomerNumber());
                ps.setString(2,customer.getAccount().getAccountno());
                ps.setString(3,customer.getDeliveryAddress().getPostcode());
                ps.setString(4,customer.getBillingAddress().getPostcode());
                ps.setString(5,customer.getTelephoneNumber());
                ps.setString(6,customer.getName());
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
        customerHashMap.forEach((count, element)->{
            if(count == customerId){
                tempCustomer[0] = customerHashMap.get(customerId);
                temp.put(customerId,tempCustomer[0]);
            }
        });
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

      return  db.query(con->con.prepareStatement(query), rs -> {
          HashMap<Integer, Customer> hashMap = new HashMap<>();
          int count = 0;
          while(rs.next()){

              count++;
              BusinessCustomer customer = new BusinessCustomer();
              customer.setCustomerNumber(rs.getInt("CUSTOMERNO"));
              customer.setAccount(new Account(rs.getString("ACCTNO"),0));
              customer.setDeliveryAddress(new Address(rs.getString("DELIVERYADDRESS"),"",""));
              customer.setTelephoneNumber(rs.getString("TELEPHONE"));
//                    customer.(rs.getString("TELEPHONE"));
              customer.setCompanyName(rs.getString("FNAME"));
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
