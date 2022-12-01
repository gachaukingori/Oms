/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;

/**
 *
 * @author victor
 */
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = BusinessCustomer.class, name = "BusinessCustomer"),
//
//        @JsonSubTypes.Type(value = PrivateCustomer.class, name = "PrivateCustomer") }
//)



@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "BusinessCustomer", value = BusinessCustomer.class),
        @JsonSubTypes.Type(name = "PrivateCustomer", value = PrivateCustomer.class),
})

public abstract class Customer {
    
    private Account account;
    private Address deliveryAddress;
    private Address billingAddress;
    private int customerNumber ;
    private String telephoneNumber;
    private ArrayList<Order>orders;





    public Customer(Account account, Address deliveryAddress, int customerNumber, String telephoneNumber) {
        this.account = account;
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = deliveryAddress;
        this.customerNumber = customerNumber;
        this.telephoneNumber = telephoneNumber;
    }

    public Customer() {
    }

    public Customer(Account account, Address deliveryAddress, Address billingAddress, int customerNumber, String telephoneNumber) {
        this.account = account;
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.customerNumber = customerNumber;
        this.telephoneNumber = telephoneNumber;
    }
    
    
    

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
    
    
    
    public abstract String getName();

    @Override
    public String toString() {
        return "Customer{" + "account=" + account + ", deliveryAddress=" + deliveryAddress + ", billingAddress=" + billingAddress + ", customerNumber=" + customerNumber + ", telephoneNumber=" + telephoneNumber + '}';
    }

   
    
    
}
