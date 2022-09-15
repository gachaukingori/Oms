package com.projects.oms.services;

import com.projects.oms.models.Item;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;

public interface ItemServiceInterface {
    void addNewItem(ArrayList<Item> itemList);
    Collection<Item> getAllItems();
    Item findItem(int itemId);
    void deleteItem (int itemId);

}

