/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ITEMS")
public class Item {
    
    private static final double DEFAULT_PERCENTAGE_PROFIT = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int itemNumber;
    private String itemDesc;
    private double purchasePrice;
    private int itemQuantity;

    /**
    
    @return  sales price to be 10% more than the purchasing price
    */
    public double salesPrice(){
        double profit = DEFAULT_PERCENTAGE_PROFIT/100 * purchasePrice;
        return purchasePrice + profit;
    }

}
