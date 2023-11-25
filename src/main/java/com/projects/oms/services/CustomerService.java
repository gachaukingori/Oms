package com.projects.oms.services;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.exceptions.NotFoundException;
import com.projects.oms.models.*;
import com.projects.oms.repositories.CustomerRepository;
import com.projects.oms.dto.CustomerDTO;
import com.projects.oms.dto.CustomerDTOMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor
public class CustomerService {
   static HashMap<Integer, Customer> customerHashMap = new HashMap<>();

    private  final CustomerRepository customerRepository;

    private  final CustomerDTOMapper customerDTOMapper;
    public static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(CustomerController.class);



    public void createNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    public void deleteCustomer(int customerId) throws SQLException {
        customerRepository.deleteById(customerId);
    }


    public Customer findCustomer(int customerId) {
    return customerRepository.findById(customerId).orElseThrow(()->new NotFoundException("Customer not found"));
    }


    public Collection<CustomerDTO> getAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(customerDTOMapper)
                .toList();
    }

    public void updateCustomer(int customerId, Customer customer) {
     customerRepository
             .findByCustomerNumber(customerId)
             .ifPresentOrElse((customer1 -> {
                 customer.setTelephoneNumber("0701502302");
                 customerRepository.save(customer);
             }),()->customerRepository.save(customer));

    }


}
