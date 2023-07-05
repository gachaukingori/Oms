package com.projects.oms.services;

import com.projects.oms.dto.ItemOrderDTOMapper;
import com.projects.oms.dto.OrderItemDto;
import com.projects.oms.models.Item;
import com.projects.oms.models.OrderItem;
import com.projects.oms.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    public static HashMap<Integer, OrderItem> orderItemHashMap = new HashMap<>();
    static Logger logger = LoggerFactory.getLogger(OrderItemService.class);

    private final ItemRepository itemRepository;
    private final ItemOrderDTOMapper itemOrderDTOMapper;
    public String createOrderItem(OrderItem orderItem){


        int itemNumber = orderItem.getItemNumber();
            Item tempItem = itemRepository
                    .findByItemNumber(itemNumber)
                    .orElseThrow(()->new IllegalStateException("Item not found"));

            if (orderItem.getOrderQuantity() > tempItem.getItemQuantity()) {
                return "sorry! your order exceed the quantity available";
            }else {
                orderItem.setItem(tempItem);
                orderItemHashMap.put(orderItem.getOrderNumber(), orderItem);
                int remainingQuantity = tempItem.getItemQuantity() - orderItem.getOrderQuantity();
                logger.info("Current qtt is "+tempItem.getItemQuantity() +" order qty = "+orderItem.getOrderQuantity());
                itemRepository.updateItemQuantity(remainingQuantity,itemNumber);
                return "item added  successfully";
            }


    }
    public Collection<OrderItemDto> getAllOrderItems(){
        for(OrderItem orderItem : orderItemHashMap.values()){
            logger.info("Order Items are "+orderItem);
        }

        return orderItemHashMap.values().stream().map(itemOrderDTOMapper).toList();
//        return orderItemHashMap.values();
    }
    public void cartCheckout(){
        for(OrderItem orderItem : orderItemHashMap.values()){
            int remainingQuantity = orderItem.getItem().getItemQuantity() - orderItem.getOrderQuantity();
            itemRepository.updateItemQuantity(remainingQuantity,orderItem.getItemNumber());
        }
    }
}
