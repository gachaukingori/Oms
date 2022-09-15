/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

/**
 *
 */
public class Address {
    private String postcode;
    private String town;
    private String street;

    public Address(String postcode, String town, String street) {
        this.postcode = postcode;
        this.town = town;
        this.street = street;
    }

    public Address() {
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" + "postcode=" + postcode + ", town=" + town + ", street=" + street + '}';
    }
    
    
}
