/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.projects.oms.services.ItemService;
import com.projects.oms.services.OrderItemService;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 
 */
public class Order {
    public static final int DEFAULT_ODRDER_NUMBER = 1;
    
    
    private  static final Item DEFAULT_ITEM = new Item(1,"Sausage", 10,5);
    private static final OrderItem DEFAULT_ORDER_ITEM = new OrderItem(1,1, DEFAULT_ITEM.getItemNumber());
    private int orderNumber;
    private Date orderDate;
    private Customer customer;
    private int customerid;
    private ArrayList <Integer> orderItems;

    public Order() {
    }

    public Order(int orderNumber,  int customerid, ArrayList<Integer> orderItems) {
        this.orderNumber = orderNumber;

        this.customerid = customerid;
//        if(orderItems.isEmpty()){
//            orderItems.add(DEFAULT_ORDER_ITEM.getItemNumber());
//        }
        this.orderItems = orderItems;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

//    public Date getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }



    public ArrayList<Integer> getOrderItems() {
        return orderItems;
    }
    
   

    public void setOrderItems(ArrayList<Integer> orderItems) {
        if(orderItems.isEmpty()){
            orderItems.add(DEFAULT_ORDER_ITEM.getOrderNumber());
        }
        this.orderItems = orderItems;
    }
    
     public double getTotalOrderValue(){
         Iterator<Integer> iterator = orderItems.iterator();
        double total = 0;
         while(iterator.hasNext()){
//             OrderItem  item = iterator.next();
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

    @Override
    public String toString() {
        return "Order{\n " + "orderNumber= " + orderNumber + ",\n orderDate=" + orderDate + ",\n customer= " + customer
                +",\n totalOrderValue= "+getTotalOrderValue() + ",\n totalProfit= "+ getTotalProfit()+"\n}";
    }
    
    
}
