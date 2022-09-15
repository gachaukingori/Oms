/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

/**
 *
 */
public class Item {
    
    private static final double DEFAULT_PERCENTAGE_PROFIT = 10;
    private int itemNumber;
    private String itemDesc;
    private double purchasePrice;
    private int itemQuantity;



    public Item() {
    }

    public Item(int itemNumber, String itemDesc, double purchasePrice, int itemQuantity) {
        this.itemNumber = itemNumber;
        this.itemDesc = itemDesc;
        this.purchasePrice = purchasePrice;
        this.itemQuantity = itemQuantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
    
    /**
    
    @return  sales price to be 10% more than the purchasing price
    */
    public double salesPrice(){
        double profit = DEFAULT_PERCENTAGE_PROFIT/100 * purchasePrice;
       double salesPrice =  purchasePrice + profit  ;
        return salesPrice;


    }

    @Override
    public String toString() {
        return "Item{" +
                "itemNumber=" + itemNumber +
                ", itemDesc='" + itemDesc + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
