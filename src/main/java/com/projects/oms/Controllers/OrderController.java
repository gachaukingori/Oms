package com.projects.oms.Controllers;

import com.projects.oms.models.Order;
import com.projects.oms.services.OrderItemService;
import com.projects.oms.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class OrderController {
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(path = "/createOrder", method = RequestMethod.POST)
    public ResponseEntity<String> createNewOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
    }

    @RequestMapping(path="/getOrders", method = RequestMethod.GET)

    public ResponseEntity<Collection<Order>>getAllOrders(){

        return new ResponseEntity<>(orderService.getOrderDetails(), HttpStatus.OK);
    }
}
