/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

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
//@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonTypeName("PrivateCustomer")

@Entity
@DiscriminatorValue("PRIVATE_CUSTOMER")
public class PrivateCustomer extends Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


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


    @Override
    public String getName() {
        return firstName.concat(lastName);
    }
    
    
            
}
