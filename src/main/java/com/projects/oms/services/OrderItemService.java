package com.projects.oms.services;

import com.projects.oms.models.Item;
import com.projects.oms.models.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class OrderItemService {
    public static HashMap<Integer, OrderItem> orderItemHashMap = new HashMap<>();
    static Logger logger = LoggerFactory.getLogger(OrderItemService.class);
    @Autowired
    ItemService itemService;
    public String createOrderItem(OrderItem orderItem){

        int itemNumber = orderItem.getItemNumber();
        if(!ItemService.itemHashMap.containsKey(itemNumber)){
            return "Item not found";
        }else {
            Item tempItem = ItemService.itemHashMap.get(itemNumber);
            logger.info("Ordered quantity " + orderItem.getOrderQuantity() +" current quantity "+ tempItem.getItemQuantity() );
            if (orderItem.getOrderQuantity() > tempItem.getItemQuantity()) {
                return "sorry! your order exceed the quantity available";
            } else {
                orderItem.setItem(tempItem);
                orderItemHashMap.put(orderItem.getOrderNumber(), orderItem);
                int remainingQuantity = tempItem.getItemQuantity() - orderItem.getOrderQuantity();
                itemService.updateItemQuantity(itemNumber,remainingQuantity );
                return "item added  successfully";
            }
        }

    }
    public Collection<OrderItem> getAllOrderItems(){
        return orderItemHashMap.values();
    }
}
