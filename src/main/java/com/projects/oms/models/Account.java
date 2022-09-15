/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;


/**
 *
 */
public class Account {
    public static final double DEFAULT_ACCOUNT_BALANCE = 10;
    
    private String accountno ;
    private double accountbal;
    private Customer customer;

    

    public Account(String accountno, double accountbal) {
        this.accountno = accountno;
        setAccountbal(accountbal);
    }
    

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public double getAccountbal() {
        return accountbal;
    }

    public void setAccountbal(double accountbal) {
        if(accountbal < 10){
            accountbal = DEFAULT_ACCOUNT_BALANCE;
        }
        this.accountbal = accountbal;
    }

//    public Customer getCustomer() {
//        return customer;
//    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    @Override
    public String toString() {
        return "Account{" + "accountno=" + accountno + ", accountbal=" + accountbal + ", customer=" + customer + '}';
    }
    
    
    
}
