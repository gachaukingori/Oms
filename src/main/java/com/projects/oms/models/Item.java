/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    
    private static final double DEFAULT_PERCENTAGE_PROFIT = 10;
    private int itemNumber;
    private String itemDesc;
    private double purchasePrice;
    private int itemQuantity;

    /**
    
    @return  sales price to be 10% more than the purchasing price
    */
    public double salesPrice(){
        double profit = DEFAULT_PERCENTAGE_PROFIT/100 * purchasePrice;
       double salesPrice =  purchasePrice + profit  ;
        return salesPrice;


    }

}
