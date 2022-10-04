package com.projects.oms.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.oms.configs.Config;
import com.projects.oms.models.*;
import com.projects.oms.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
@RestController
@ControllerAdvice
public class ItemController {


    @Autowired
    ItemService itemService;
    @RequestMapping(path = "/newitem", method = RequestMethod.POST)
    public ResponseEntity<String> addNewItem(@RequestBody ArrayList<Item> itemList){
        itemService.addNewItem(itemList);
        return new ResponseEntity<>("items added successfully", HttpStatus.OK);
    }
    @RequestMapping(path = "/itemquantity/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JSONResponse> updateItemQuantity(@PathVariable(name = "id") int itemid, @RequestBody Item item){
      JSONResponse jsonResponse = new JSONResponse();
       if(ItemService.itemHashMap.containsKey(itemid)){
           Item item1 = ItemService.itemHashMap.get(itemid);
           int newQuantity = item1.getItemQuantity() + item.getItemQuantity();
           itemService.updateItemQuantity(itemid, newQuantity);
           jsonResponse.setMessage("Item quantity updated successfully");
           jsonResponse.setStatus(HttpStatus.OK.toString());
       }else{
           jsonResponse.setMessage("Item not found");
           jsonResponse.setStatus(HttpStatus.OK.toString());
       }
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }



    @RequestMapping(value = "/allItems", method = RequestMethod.GET)
    public ResponseEntity<Collection<Item>> getAllItems(){
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

//    public static void testItemJson(){
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Item item1 = new Item(4,"Bread",70.00,4);
//            String itemString = objectMapper.writeValueAsString(item1);
//
//            Item item  = objectMapper.readValue(itemString,Item.class);
//            HashMap<String, Object> itemHashMap = new HashMap<>();
//            itemHashMap.put("item",item);
//            objectMapper.writeValue(new File(Config.FILE_PATH+"/item.json"), itemHashMap);
//
//            HashMap<String, OrderItem> orderItemHashMap = new HashMap<>();
//
////            OrderItem orderItem = new OrderItem(1, 2,item.getItemNumber());
//            OrderItem orderItem = new OrderItem();
//            orderItemHashMap.put("orderItem",orderItem);
//            objectMapper.writeValue(new File(Config.FILE_PATH+"/orderitem.json"), orderItemHashMap);
//            Account account = new Account("3904059/AC", 90000.00);
//            Date orderDate = new Date(1,9,2022);
//            Address address = objectMapper.readValue(new File(Config.FILE_PATH+"/address.json"),Address.class);
//
//            Customer customer = new BusinessCustomer("parity",account,address,9,"0703828949");
//            ArrayList<Integer> orderItems = new ArrayList<>();
////            Order order = new Order(1,orderDate,customer.getCustomerNumber(),orderItems);
//
//            HashMap<String, Order> orderHashMap = new HashMap<>();
//            orderHashMap.put("order",order);
//            objectMapper.writeValue(new File(Config.FILE_PATH+"/order.json"), orderHashMap);
//
//
//
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
