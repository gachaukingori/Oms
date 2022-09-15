/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.projects.oms.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class OrderItem  {
    public static final int DEFAULT_ORDER_QUANTITY = 1;
//    public static final int

    private int orderNumber;
    private int orderQuantity;
    @Autowired(required = false)
    private Item item;
    private int itemNumber;



    public OrderItem() {
    }

    public OrderItem(int orderNumber, int orderQuantity, int itemNumber) {
        this.orderNumber = orderNumber;
        this.orderQuantity = orderQuantity;
        this.item = ItemService.itemHashMap.get(itemNumber);
        this.itemNumber = itemNumber;
    }

//    public OrderItem(int orderQuantity, int itemNumber) {
//           this.item = ItemService.itemHashMap.get(itemNumber);
//             setOrderQuantity(orderQuantity);
//            setItemNumber(itemNumber);
//    }

//    public OrderItem(int orderQuantity, Item item, int itemNumber) {
//        this.orderQuantity = orderQuantity;
//        this.item = item;
//        this.itemNumber = itemNumber;
//    }


    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        if(orderQuantity < 1){
            orderQuantity = DEFAULT_ORDER_QUANTITY;
        }
        this.orderQuantity = orderQuantity;
        
    }
    
    public double calculateProfit(){
        double profit = item.salesPrice() - item.getPurchasePrice();

       
        double totalProfit = orderQuantity* profit;
        
        return totalProfit;
    }
    
     public double calculateItemPrice(){

        return item.salesPrice();
    }
    
    
    
}
