package com.projects.oms.Controllers;

import com.projects.oms.dto.OrderItemDto;
import com.projects.oms.models.Order;
import com.projects.oms.models.OrderItem;
import com.projects.oms.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orderItems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping("/orderitem")
    public ResponseEntity<String> createOrderItem(@RequestBody OrderItem orderItem) {
        String response = orderItemService.createOrderItem(orderItem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allOrderItems")
    public ResponseEntity<Collection<OrderItemDto>> getAllOrderItems() {
        return new ResponseEntity<>(orderItemService.getAllOrderItems(), HttpStatus.OK);
    }

}
