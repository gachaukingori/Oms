package com.projects.oms.services;

import com.projects.oms.models.Customer;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface CustomerServiceInterface {
     void createNewCustomer(Customer customer);
    void deleteCustomer(int customerId) throws SQLException;
//    Collection<Customer> findCustomer(int customerId);
    Collection<CustomerDTO> getAllCustomers();
    void updateCustomer(int customerId, Customer customer);

}
