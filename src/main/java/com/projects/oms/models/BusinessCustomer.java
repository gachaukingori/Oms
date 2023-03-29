/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.*;
import lombok.*;

/**
 *
 */
//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonTypeName("BusinessCustomer")

@Entity
@DiscriminatorValue("BUSINESS_CUSTOMER")
public class  BusinessCustomer  extends Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  Integer id;

    @Column(name = "company_name")
    private String companyName;

    public BusinessCustomer(String companyName, Account account, Address deliveryAddress, int customerNumber, String telephoneNumber) {
        super(account, deliveryAddress, customerNumber, telephoneNumber);
        this.companyName = companyName;
    }

    public BusinessCustomer(String companyName, Account account, Address deliveryAddress, Address billingAddress, int customerNumber, String telephoneNumber) {
        super(account, deliveryAddress, billingAddress, customerNumber, telephoneNumber);
        this.companyName = companyName;
    }


    
    @Override
    public String getName() {
        return companyName;
    }


   
}
