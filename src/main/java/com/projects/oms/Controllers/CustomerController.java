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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@ControllerAdvice
@RestController

public class CustomerController {

    public static final Logger logger =
            LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerService customerService;

    private JSONResponse jsonResponse = new JSONResponse();


    @RequestMapping(path = "/newcustomer", method = RequestMethod.POST)

    public ResponseEntity<JSONResponse> createNewCustomer(@RequestBody ArrayList<Customer> customerList){

        customerService.createNewCustomer(customerList);
        jsonResponse.setMessage("Customer added succesfully");
        jsonResponse.setStatus(HttpStatus.CREATED.toString());
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
    @RequestMapping(path="/", method = RequestMethod.GET)
    public ResponseEntity<Collection<Customer>> getAllCustomers(@RequestParam(value = "customerId", required = false) Optional<Integer> customerid){
        if(!customerid.isPresent()){
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(customerService.findCustomer(customerid.get()), HttpStatus.OK);
        }

    }

    @RequestMapping(path = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONResponse> deleteCustomer(@PathVariable ("id") int customerid){

   String message= customerService.deleteCustomer(customerid);
        jsonResponse.setStatus(HttpStatus.OK.toString());
        jsonResponse.setMessage(message);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
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
