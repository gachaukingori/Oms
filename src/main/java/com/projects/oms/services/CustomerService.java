package com.projects.oms.services;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.exceptions.NotFoundException;
import com.projects.oms.models.*;
import com.projects.oms.repositories.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class CustomerService implements CustomerServiceInterface {
   static HashMap<Integer, Customer> customerHashMap = new HashMap<>();
    @Autowired
    private  CustomerRepository customerRepository;

    public static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(CustomerController.class);


    @Override
    public void createNewCustomer(Customer customer) {
        customerRepository.save(customer);
//        customerList.forEach((customer)-> customerHashMap.put(customer.getCustomerNumber(), customer));
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        customerHashMap.remove(customerId);
    }


    public Customer findCustomer(int customerId) {
    return customerRepository.findById(customerId).orElseThrow(()->new NotFoundException("Customer not found"));
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {
     customerRepository.findById(customerId).ifPresentOrElse((
             customer1 -> {
              customer1.setBillingAddress(customer.getBillingAddress());
              customer1.setAccount(customer.getAccount());
              customer1.setTelephoneNumber(customer.getTelephoneNumber());
              customerRepository.save(customer1);
             }),() -> customerRepository.save(customer));
    }


}
