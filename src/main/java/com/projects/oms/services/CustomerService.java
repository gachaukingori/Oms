package com.projects.oms.services;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.models.*;
import com.projects.oms.repositories.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class CustomerService implements CustomerServiceInterface {
   static HashMap<Integer, Customer> customerHashMap = new HashMap();
    private CustomerRepository customerRepository;
    String query;

   @Autowired
   JdbcTemplate db;
    public static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(CustomerController.class);


    @Override
    public void createNewCustomer(ArrayList<Customer> customerList) {
        customerList.forEach((customer)-> customerHashMap.put(customer.getCustomerNumber(), customer));
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        customerHashMap.remove(customerId);


    }

    @Override
    public Collection<Customer> findCustomer(int customerId) {
        Customer[] tempCustomer = {null};
        HashMap<Integer,Customer > temp = new HashMap<>();

       Customer customer = db.execute((PreparedStatementCreator) con -> con.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMERNO =? ")
               , (PreparedStatementCallback<Customer>) ps -> {
                   ps.setInt(1, customerId);
                   ResultSet rs = ps.executeQuery();
                   BusinessCustomer customer1 = new BusinessCustomer();
                   if(rs.next()){
                       customer1.setCustomerNumber(rs.getInt("CUSTOMERNO"));
                       customer1.setAccount(new Account(rs.getString("ACCTNO"),0));
                       customer1.setDeliveryAddress(new Address(rs.getString("DELIVERYADDRESS"),"",""));
                       customer1.setTelephoneNumber(rs.getString("TELEPHONE"));
                       customer1.setCompanyName(rs.getString("FNAME"));


                       String accountNo = rs.getString("ACCOUNTNO");
                       double accountBal = rs.getDouble("ACCOUNTBAL");
                       String billingPostcode = rs.getString("BILLING_POSTCODE");
                       String billingTown = rs.getString("BILLING_TOWN");
                       String billingStreet = rs.getString("BILLING_STREET");
                       String deliveryStreet = rs.getString("DELIVERY_STREET");
                       String deliveryTown = rs.getString("DELIVERY_TOWN");
                       String deliveryPostcode = rs.getString("DELIVERY_POSTCODE");

                       Address deliveryAddress = new Address(deliveryPostcode,deliveryTown,deliveryStreet);
                       Address billingAddress = new Address(billingPostcode,billingTown,billingStreet);
                       Account account = new Account(accountNo, accountBal);

                       customer1.setAccount(account);
                       customer1.setDeliveryAddress(deliveryAddress);
                       customer1.setBillingAddress(billingAddress);
                   }
                   return customer1;
               });
            temp.put(1,customer);

        return temp.values();
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return customerHashMap.values();
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }


}
