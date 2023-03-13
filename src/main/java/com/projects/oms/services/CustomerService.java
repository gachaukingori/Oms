package com.projects.oms.services;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.models.*;
import com.projects.oms.repositories.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class CustomerService implements CustomerServiceInterface {
   static HashMap<Integer, Customer> customerHashMap = new HashMap();
    private CustomerRepository customerRepository;
    String query;

   @Autowired
   JdbcTemplate db;
    public static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(CustomerController.class);


    @Override
    public void createNewCustomer(ArrayList<Customer> customerList) {
        customerList.forEach((customer)-> customerHashMap.put(customer.getCustomerNumber(), customer));
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        customerHashMap.remove(customerId);
    }

    @Override
    public Collection<Customer> findCustomer(int customerId) {
        HashMap<Integer,Customer > temp = new HashMap<>();
        Customer tempCustomer = customerHashMap.entrySet()
                .stream()
                .filter((customer) -> customerId == customer.getValue().getCustomerNumber())
                .findFirst()
                .orElseThrow(()->new RuntimeException("Customer not Found"))
                .getValue();

          temp.put(customerId,tempCustomer);
          return temp.values();
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return customerHashMap.values();
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }


}
