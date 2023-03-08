/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.ArrayList;

/**
 *
 * @author victor
 */
@Builder
@Data
//@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)



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
    
    
    

    public abstract String getName();

    @Override
    public String toString() {
        return "Customer{" + "account=" + account + ", deliveryAddress=" + deliveryAddress + ", billingAddress=" + billingAddress + ", customerNumber=" + customerNumber + ", telephoneNumber=" + telephoneNumber + '}';
    }

}
