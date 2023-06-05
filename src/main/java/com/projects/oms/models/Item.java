/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import jakarta.persistence.*;
import lombok.*;
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

    @Getter(AccessLevel.NONE)
    @Transient
    private double salePrice;
    @PostLoad
    private void postLoad() {
        this.salePrice =  this.purchasePrice * 1.1;
    }

    /**
    * @return  sales price to be 10% more than the purchasing price
    */


    public double getSalePrice() {
        return this.purchasePrice * 1.1;
    }

}
