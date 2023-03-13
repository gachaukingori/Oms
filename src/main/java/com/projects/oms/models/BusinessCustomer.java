/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

/**
 *
 */
//@Builder
@Data
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonTypeName("BusinessCustomer")

public class  BusinessCustomer  extends Customer{
    public BusinessCustomer() {
    }

    private String companyName;

    public BusinessCustomer(String companyName, Account account, Address deliveryAddress, int customerNumber, String telephoneNumber) {
        super(account, deliveryAddress, customerNumber, telephoneNumber);
        this.companyName = companyName;
    }

    public BusinessCustomer(String companyName, Account account, Address deliveryAddress, Address billingAddress, int customerNumber, String telephoneNumber) {
        super(account, deliveryAddress, billingAddress, customerNumber, telephoneNumber);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
    @Override
    public String getName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "BusinessCustomer{" + "companyName=" + companyName + ", Name=" +getName() + ", Address="+getBillingAddress()+ '}';
    }
    
   
}
