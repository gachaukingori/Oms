package com.projects.oms.services;

import com.projects.oms.models.Order;
import com.projects.oms.models.OrderItem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class OrderService {
    public static HashMap<Integer, Order> orderHashMap = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    public final Order order;

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

    public double getTotalProfit(){
        Iterator<OrderItem> iterator = order.getOrderItems().iterator();
        double total = 0;
        while(iterator.hasNext()){

            Integer  orderNumber = iterator.next().getOrderNumber();
            OrderItem item = OrderItemService.orderItemHashMap.get(orderNumber);
            total += item.calculateProfit();
        }
        return total;
    }

}
