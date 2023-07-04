package com.projects.oms.Controllers;

import com.projects.oms.models.Order;
import com.projects.oms.models.OrderItem;
import com.projects.oms.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class OrderItemController {

   private final OrderItemService orderItemService;
    @RequestMapping(path = "/orderitem", method = RequestMethod.POST)
    public ResponseEntity<String> createOrderItem(@RequestBody OrderItem orderItem){
       String response= orderItemService.createOrderItem(orderItem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/allOrderItems", method = RequestMethod.GET)
    public ResponseEntity<Collection<OrderItem>> getAllOrderItems(){
        return new ResponseEntity<>(orderItemService.getAllOrderItems(), HttpStatus.OK);
    }

}
