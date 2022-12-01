package com.projects.oms.services;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.models.Customer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

@Service
public class CustomerService implements CustomerServiceInterface {
   static HashMap<Integer, Customer> customerHashMap = new HashMap();
   @Autowired
   JdbcTemplate db;
    public static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(CustomerController.class);


    @Override
    public void createNewCustomer(ArrayList<Customer> customerList) {
//        db.execute("CREATE TABLE CUSTOMERS ( ID INT(10), FNAME VARCHAR(255), LNAME VARCHAR(255) )");




        customerList.forEach((customer)->{

            customerHashMap.put(customer.getCustomerNumber(), customer);
        PreparedStatementCreator psc =new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("INSERT INTO CUSTOMERS VALUES(?,?,?,?,?,?)");
                ps.setInt(1,customer.getCustomerNumber());
                ps.setInt(2,customer.getCustomerNumber());
                ps.setString(3,customer.getAccount().toString() );
                ps.setString(4,customer.getDeliveryAddress().toString());
                ps.setString(5,customer.getBillingAddress().toString());
                ps.setString(6,customer.getName());
//                ps.setString(7,customer.());

                return null;
            }
        };
//            db.query(psc, );
        });


    }



    @Override
    public String deleteCustomer(int customerId) {
        if(customerHashMap.containsKey(customerId)){
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
        return customerHashMap.values();
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }
}
