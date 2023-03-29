/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author victor
 */

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "BusinessCustomer", value = BusinessCustomer.class),
        @JsonSubTypes.Type(name = "PrivateCustomer", value = PrivateCustomer.class),
})




@Table(name="CUSTOMERS")
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="customer_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "customer_id",
            nullable = false,
            updatable = false
    )
    private   Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    protected Account account;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    protected Address deliveryAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_id")
    protected Address billingAddress;
    @Column(name="customer_no")
    private int customerNumber ;

    @Column(name="telephone_no")
    private String telephoneNumber;

//    protected ArrayList<Order>orders;

    public Customer(Account account, Address deliveryAddress, int customerNumber, String telephoneNumber) {
        this.account = account;
        this.deliveryAddress = deliveryAddress;
//        this.billingAddress = deliveryAddress;
        this.customerNumber = customerNumber;
        this.telephoneNumber = telephoneNumber;
    }

    public Customer(Account account, Address deliveryAddress, Address billingAddress, int customerNumber, String telephoneNumber) {
        this.account = account;
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.customerNumber = customerNumber;
        this.telephoneNumber = telephoneNumber;
    }

    public abstract String getName();

}
