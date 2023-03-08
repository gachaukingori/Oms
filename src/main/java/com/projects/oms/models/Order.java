/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.projects.oms.services.OrderItemService;
import lombok.*;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor_ = @ConstructorProperties({"orderNumber", "customerid", "orderItems"}))
public class Order {
    public static final int DEFAULT_ODRDER_NUMBER = 1;

    private  static final Item DEFAULT_ITEM = null;
    private static final OrderItem DEFAULT_ORDER_ITEM = null;
    private int orderNumber;
    private Date orderDate;
    private Customer customer;
    private int customerid;


    private ArrayList <Integer> orderItems;

     public double getTotalOrderValue(){
         Iterator<Integer> iterator = orderItems.iterator();
        double total = 0;
         while(iterator.hasNext()){
             Integer  orderNumber = iterator.next();
             OrderItem item = OrderItemService.orderItemHashMap.get(orderNumber);
             total += (item.calculateItemPrice() * item.getOrderQuantity());
         }
         return total ;
    }
     public double getTotalProfit(){
         Iterator<Integer> iterator = orderItems.iterator();
        double total = 0;
         while(iterator.hasNext()){

             Integer  orderNumber = iterator.next();
             OrderItem item = OrderItemService.orderItemHashMap.get(orderNumber);
             total += item.calculateProfit();
         }
         return total;
    }
}
