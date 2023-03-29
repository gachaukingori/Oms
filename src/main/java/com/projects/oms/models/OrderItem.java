/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.projects.oms.services.ItemService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ORDERITEMS")
public class OrderItem  {
    public static final int DEFAULT_ORDER_QUANTITY = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  Integer id;
    private int orderNumber;
    private int orderQuantity;
    @Autowired(required = false)
    @OneToOne
    private Item item;
    private int itemNumber;
    public double calculateProfit(){
        double profit = item.salesPrice() - item.getPurchasePrice();
        return orderQuantity* profit;
    }
    
     public double calculateItemPrice(){
        return item.salesPrice();
    }
    
    
    
}
