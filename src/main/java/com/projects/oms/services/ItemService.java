package com.projects.oms.services;

import com.projects.oms.exceptions.NotFoundException;
import com.projects.oms.models.Customer;
import com.projects.oms.models.Item;
import com.projects.oms.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
@Service
@RequiredArgsConstructor
public class ItemService implements ItemServiceInterface {
    public static HashMap<Integer, Item> itemHashMap = new HashMap<>();
    private final ItemRepository itemRepository;
    static Logger logger = LoggerFactory.getLogger(ItemService.class);
    @Override
    public void addNewItem(ArrayList<Item> itemList) {
        itemRepository.saveAll(itemList);
    }

    @Override
    public Collection<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItem(int itemId) {

        return itemRepository.findById(itemId)
                .orElseThrow(()->new NotFoundException("Item not found"));
    }

    @Override
    public void deleteItem(int itemId) {
        itemRepository.deleteById(itemId);
    }
    public void updateItemQuantity(int itemNumber, int remainingQuantity ){

        itemRepository.findByItemNumber(itemNumber).ifPresent((item)->{
            item.setItemQuantity(remainingQuantity);
            itemRepository.save(item);
        });


    }
}
