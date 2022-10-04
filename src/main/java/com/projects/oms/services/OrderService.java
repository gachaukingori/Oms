package com.projects.oms.services;

import com.projects.oms.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class OrderService {
    public static HashMap<Integer, Order> orderHashMap = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(OrderService.class);

    public String createOrder(Order order){

//        logger.info("the order objcet is " + order.toString());
        if(!CustomerService.customerHashMap.containsKey(order.getCustomerid())) {
            return "Customer no "+ order.getCustomerid()+" not found";
        }
        if(!OrderItemService.orderItemHashMap.containsKey(order.getOrderNumber())){
            return " Item order  " +order.getOrderNumber() +  " not found";

        }



        orderHashMap.put(order.getOrderNumber(), order);
        return "Order created successfully";



    }

    public Collection<Order> getOrderDetails(){
        return orderHashMap.values();
    }
    public boolean checkCustomerExists(int customerId){
        return CustomerService.customerHashMap.containsKey(customerId);
    }


}
