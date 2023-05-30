package com.projects.oms.dto;

import com.projects.oms.models.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {

    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getCustomerNumber(),
                customer.getName(),
                customer.getTelephoneNumber());
    }
}
