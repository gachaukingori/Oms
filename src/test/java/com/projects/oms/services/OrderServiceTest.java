package com.projects.oms.services;

import com.projects.oms.models.*;
import com.projects.oms.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class OrderServiceTest {

    OrderItem orderItem ;
    Item item;
    OrderService underTest ;
    Order order ;

    HashMap<Integer, Order> orderHashmap;
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        orderHashmap = mock(HashMap.class);
        customerRepository = Mockito.mock(CustomerRepository.class);
        underTest = new OrderService(customerRepository,order);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void createOrder() {
        Customer customer  = new BusinessCustomer();
        customer.setCustomerNumber(2);
        customer.setTelephoneNumber("+25489038475");

        // when  the findCustomer is called then return an Optional of Customer pojo
        Mockito.when(customerRepository.findByCustomerNumber(2)).thenReturn(Optional.of(customer));

        Mockito.when(orderHashmap.containsKey(order.getOrderNumber())).thenReturn(true);

        String telephone = customerRepository.findByCustomerNumber(2).orElse(customer).getTelephoneNumber();
        //given
        underTest.createOrder(order);
        boolean orderItemExists = orderHashmap.containsKey(order.getOrderNumber());

        assertEquals(telephone, "+25489038475");
        assertTrue(orderItemExists);
        // verifies that the method was called with similar arguments passed
        verify(customerRepository).findByCustomerNumber(order.getCustomerid());

    }

    @Test
    @Disabled
    void getOrderDetails() {
    }
    @Test
    void shouldCalculateTotalAmountOfAnOrder(){

    }

    @Test
    void simpleMockVerifyMethod(){
    List<String> names = mock(List.class);
    names.add("Kara");
    verify(names).add("Kara");

    }
    @Test
    void verifyNoOfTimes(){
        List<String> names = mock(List.class);
        names.add("Kara");
        verify(names, times(1)).add("Kara");
    }
    @Test
    void verifyWithNoMoreInteractions(){

        List<String> names = mock(List.class);
        names.add("Kara");
        verify(names, times(1)).add("Kara");
        verifyNoMoreInteractions(names);
        names.size();
        verify(names).size();
    }

}