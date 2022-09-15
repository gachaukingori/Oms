package com.projects.oms.services;

import com.projects.oms.models.Customer;
import com.projects.oms.models.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
@Service
public class ItemService implements ItemServiceInterface {
    public static HashMap<Integer, Item> itemHashMap = new HashMap<>();
    static Logger logger = LoggerFactory.getLogger(ItemService.class);
    @Override
    public void addNewItem(ArrayList<Item> itemList) {
        itemList.forEach((item)->{

            itemHashMap.put(item.getItemNumber(), item);
        });

    }

    @Override
    public Collection<Item> getAllItems() {
        return itemHashMap.values();
    }

    @Override
    public Item findItem(int itemId) {
       final Item [] item = {null};
        itemHashMap.forEach((count,element)->{
            if(element.getItemNumber() == itemId){
                item[0] = element;
            }

        });
        return item[0];
    }

    @Override
    public void deleteItem(int itemId) {

    }
    public void updateItemQuantity(int itemNumber, int remainingQuantity ){
        itemHashMap.forEach((count,element)->{
                if(itemNumber == element.getItemNumber()){
                    element.setItemQuantity(remainingQuantity);
                }
        });
    }
}
