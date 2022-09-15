/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 *
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonTypeName("PrivateCustomer")
public class PrivateCustomer extends Customer {
    private String firstName;
    private String lastName;


    public PrivateCustomer() {
    }

    public PrivateCustomer(String firstName, String lastName, Account account, Address deliveryAddress, int customerNumber, String telephoneNumber) {
        super(account, deliveryAddress, customerNumber,telephoneNumber );
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PrivateCustomer(String firstName, String lastName, Account account, Address deliveryAddress, Address billingAddress, int customerNumber, String telephoneNumber) {
        super(account, deliveryAddress, billingAddress, customerNumber,telephoneNumber );
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getName() {
        
        return firstName.concat(lastName);
    }
    
    
            
}
