/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name="ADDRESSES")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
//    @Column(name="Address_id")
    private  Integer id;
    private String postcode;
    private String town;
    private String street;
    
}
