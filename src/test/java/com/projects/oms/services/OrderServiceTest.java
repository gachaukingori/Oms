package com.projects.oms.services;

import com.projects.oms.models.BusinessCustomer;
import com.projects.oms.models.Item;
import com.projects.oms.models.Order;
import com.projects.oms.models.OrderItem;
import com.projects.oms.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;
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

        //given
        underTest.createOrder(order);
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