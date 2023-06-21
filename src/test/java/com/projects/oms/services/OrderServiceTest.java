package com.projects.oms.services;

import com.projects.oms.models.Item;
import com.projects.oms.models.Order;
import com.projects.oms.models.OrderItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

class OrderServiceTest {

    OrderItem orderItem ;
    Item item;
    OrderService underTest ;
    Order order ;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        underTest = new OrderService(order);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createOrder() {
    }

    @Test
    @Disabled
    void getOrderDetails() {
    }
    @Test
    void shouldCalculateTotalAmountOfAnOrder(){


    }

}