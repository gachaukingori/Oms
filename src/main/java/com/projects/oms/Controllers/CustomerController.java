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
public class CustomerController {

    public static final Logger logger =
            LoggerFactory.getLogger(CustomerController.class);
   private final CustomerService customerService;


    // replace this with record java 17


   public record SuccessResponse(String status){
    }
    @RequestMapping(path = "/newcustomer", method = RequestMethod.POST)
    public SuccessResponse createNewCustomer(@RequestBody Customer customer){
        customerService.createNewCustomer(customer);

        return new SuccessResponse("success");
    }
    @RequestMapping(path="/allcustomers", method = RequestMethod.GET)
    public ResponseEntity<Collection<CustomerDTO>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/updatecustomer/{customerId}", method = RequestMethod.POST)
    public SuccessResponse updateCustomerIfExists(@PathVariable("customerId") int customerId, @RequestBody Customer customer){
       logger.info(customer.toString());
        customerService.updateCustomer(customerId, customer);
        return new SuccessResponse("success");
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<Customer> getCustomer(@RequestParam(value = "customerId", required = true) int customerid){
        return new ResponseEntity<>(customerService.findCustomer(customerid),HttpStatus.OK);
    }
    @RequestMapping(path = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SuccessResponse> deleteCustomer(@PathVariable ("id") int customerid) throws SQLException {
        customerService.deleteCustomer(customerid);
        return new ResponseEntity<>(HttpStatus.OK );
    }

}
