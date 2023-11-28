package com.projects.oms.Controllers;

import com.projects.oms.models.Customer;
import com.projects.oms.models.JSONResponse;
import com.projects.oms.services.CustomerService;
//import jakarta.annotation.Resource;
import com.projects.oms.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    public static final Logger logger =
            LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    public record SuccessResponse(String status) {
    }
    @PostMapping(path = "/newcustomer")
    public SuccessResponse createNewCustomer(@RequestBody Customer customer) {
        customerService.createNewCustomer(customer);

        return new SuccessResponse("success");
    }

    @GetMapping(path = "/allcustomers")
    public ResponseEntity<Collection<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }
    @PostMapping(value = "/updatecustomer/{customerId}")
    public SuccessResponse updateCustomerIfExists(@PathVariable("customerId") int customerId, @RequestBody Customer customer) {
        logger.info(customer.toString());
        customerService.updateCustomer(customerId, customer);
        return new SuccessResponse("success");
    }

    @GetMapping(path = "/customerWithId")
    public ResponseEntity<Customer> getCustomer(@RequestParam(value = "customerId", required = true) int customerid) {
        return new ResponseEntity<>(customerService.findCustomer(customerid), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletecustomer/{id}")
    public ResponseEntity<SuccessResponse> deleteCustomer(@PathVariable("id") int customerid) throws SQLException {
        customerService.deleteCustomer(customerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
