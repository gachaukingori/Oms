package com.projects.oms.services;

import com.projects.oms.models.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface CustomerServiceInterface {
     void createNewCustomer(ArrayList<Customer> customerList);
    String deleteCustomer(int customerId) throws SQLException;
    Collection<Customer> findCustomer(int customerId);
    Collection<Customer> getAllCustomers();
    void updateCustomer(int customerId, Customer customer);

}
