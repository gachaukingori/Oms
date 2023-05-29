package com.projects.oms.services;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.exceptions.NotFoundException;
import com.projects.oms.models.*;
import com.projects.oms.repositories.CustomerRepository;
import dto.CustomerDTO;
import dto.CustomerDTOMapper;
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
public class CustomerService implements CustomerServiceInterface {
   static HashMap<Integer, Customer> customerHashMap = new HashMap<>();

    private  final CustomerRepository customerRepository;
    @Autowired
    private  final CustomerDTOMapper customerDTOMapper;
    public static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(CustomerController.class);


    @Override
    public void createNewCustomer(Customer customer) {
        customerRepository.save(customer);
//        customerList.forEach((customer)-> customerHashMap.put(customer.getCustomerNumber(), customer));
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
//        customerHashMap.remove(customerId);
        customerRepository.deleteById(customerId);
    }


    public Customer findCustomer(int customerId) {
    return customerRepository.findById(customerId).orElseThrow(()->new NotFoundException("Customer not found"));
    }

    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {
        Customer tempCustomer = new PrivateCustomer();
        if(customer instanceof  BusinessCustomer){
            tempCustomer = new BusinessCustomer();
        }
        tempCustomer = customer;
     customerRepository
             .findByCustomerNumber(tempCustomer.getCustomerNumber())
             .ifPresent((customer1 -> logger.info("customer is "+ customer1.toString())));

    }


}
