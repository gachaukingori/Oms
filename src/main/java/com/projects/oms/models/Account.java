/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

/**
 *
 */
@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor(onConstructor_=@ConstructorProperties({"accountno","accountbal"}))
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;
//    public static final double DEFAULT_ACCOUNT_BALANCE = 10;
    private  String accountno ;
    private double accountbal;

//    @OneToOne(mappedBy = "accounts")
//    private Customer customer;
}
