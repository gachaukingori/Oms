package com.projects.oms.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.projects.oms.configs.Config;
import com.projects.oms.dto.ItemDTO;
import com.projects.oms.models.*;
import com.projects.oms.repositories.ItemRepository;
import com.projects.oms.services.ItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {
    private final ItemService itemService;
    private final JSONResponse jsonResponse;
    @PostMapping(path = "/newitem")
    public ResponseEntity<String> addNewItem(@RequestBody Item item){
        itemService.addNewItem(item);
        return new ResponseEntity<>("items added successfully", HttpStatus.OK);
    }
    @PutMapping("/itemquantity/{id}")
    public ResponseEntity<JSONResponse> updateItemQuantity(@PathVariable(name = "id") int itemid, @RequestBody Item item){
          itemService.updateItemQuantity(itemid, item.getItemQuantity());
          jsonResponse.setMessage("Item quantity updated successfully");
          jsonResponse.setStatus(HttpStatus.OK.toString());
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
    @GetMapping("/allItems")
    public ResponseEntity<Collection<ItemDTO>> getAllItems(){
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

}
