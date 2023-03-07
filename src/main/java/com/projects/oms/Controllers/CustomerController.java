package com.projects.oms.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.oms.models.Address;
import com.projects.oms.models.Customer;
import com.projects.oms.models.JSONResponse;
import com.projects.oms.services.CustomerService;
//import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@ControllerAdvice
@RestController

public class CustomerController {

    public static final Logger logger =
            LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerService customerService;


    // replace this with record java 17
    private final JSONResponse jsonResponse = new JSONResponse();

    record SuccessResponse(String status){
    }



    @RequestMapping(path = "/newcustomer", method = RequestMethod.POST)

    public SuccessResponse createNewCustomer(@RequestBody ArrayList<Customer> customerList){

        customerService.createNewCustomer(customerList);
        jsonResponse.setMessage("Customer added succesfully");
        jsonResponse.setStatus(HttpStatus.CREATED.toString());
        return new SuccessResponse("success");
    }
    @RequestMapping(path="/allcustomers", method = RequestMethod.GET)
    public ResponseEntity<Collection<Customer>> getAllCustomers(@RequestParam(value = "customerId", required = false) Optional<Integer> customerid){
        return customerid
                .map(i -> new ResponseEntity<>(customerService.findCustomer(i), HttpStatus.OK))
                .orElseGet(() ->new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK));
    }

    @RequestMapping(path = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SuccessResponse> deleteCustomer(@PathVariable ("id") Optional <Integer> customerid) throws SQLException {
        return customerid.map((x)->{
            try {
                return new ResponseEntity<>(new SuccessResponse(customerService.deleteCustomer(x)), HttpStatus.OK );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).orElseThrow(()->new RuntimeException("Missing customerid"));

    }

    public  static void testJacksonLibrary(){
        ObjectMapper objectMapper = new ObjectMapper();
        String addressJson = "{\n" +
                "      \"postcode\":\"10104\",\n" +
                "      \"town\":\"Mweiga\",\n" +
                "      \"street\":\"Kiambu road\"\n" +
                "  }";
        try {
            Address address = objectMapper.readValue(addressJson, Address.class);
            logger.info("the address object from json is"+ address);
            Address address1 = new Address("0200","Nanyuki", "Ngobit");
            String jsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address1);
            logger.info("the json object from address object is"+ jsonObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static  void testJsonFile(){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Address address = objectMapper.readValue(new File("/home/victor/JAVA/SPRINGBOOT/oms/src/main/resources/static/customer.json"), Address.class);
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address);

            HashMap<String, Object> hashMap = new HashMap<>();
            Address address1 = objectMapper.readValue(jsonString, Address.class);
            hashMap.put("address",address1.getTown());
            hashMap.put("street",address1.getStreet());



            objectMapper.writeValue(new File("/home/victor/JAVA/SPRINGBOOT/oms/src/main/resources/static/address.json"),hashMap);

            logger.info("customer file "+jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
