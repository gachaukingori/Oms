/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

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
public class Address {
    private String postcode;
    private String town;
    private String street;
    
}
