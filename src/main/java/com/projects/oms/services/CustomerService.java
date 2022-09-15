package com.projects.oms.services;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.models.Customer;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

@Service
public class CustomerService implements CustomerServiceInterface {
   static HashMap<Integer, Customer> customerHashMap = new HashMap();

    public static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(CustomerController.class);


    @Override
    public void createNewCustomer(ArrayList<Customer> customerList) {
        customerList.forEach((customer)->{
            customerHashMap.put(customer.getCustomerNumber(), customer);
        });


    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerHashMap.containsKey(customerId)){
            customerHashMap.remove(customerId);
            return "customer " + customerId +" deleted successfully";
        }else{
            return "customer " + customerId +" does not exist";
        }

    }

    @Override
    public Collection<Customer> findCustomer(int customerId) {
        Customer[] tempCustomer = {null};
        HashMap<Integer,Customer > temp = new HashMap<>();
        customerHashMap.forEach((count, element)->{
            if(count == customerId){
                tempCustomer[0] = customerHashMap.get(customerId);
                temp.put(customerId,tempCustomer[0]);
            }
        });
        return temp.values();
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        customerHashMap.forEach((count, element)->{
            logger.info("\n element:"+count + " : \n "+element.toString());
        });
        return customerHashMap.values();
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }
}
