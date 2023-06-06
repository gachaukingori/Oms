package com.projects.oms.services;

import com.projects.oms.dto.ItemDTO;
import com.projects.oms.dto.ItemDTOMapper;
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
public class ItemService  {
    public static HashMap<Integer, Item> itemHashMap = new HashMap<>();
    private final ItemRepository itemRepository;
    private final ItemDTOMapper itemDTOMapper;
    static Logger logger = LoggerFactory.getLogger(ItemService.class);

    public void addNewItem(Item item) {
        logger.info("item is "+item);
        itemRepository.save(item);
    }


    public Collection<ItemDTO> getAllItems() {
        return itemRepository
                .findAll()
                .stream().filter(item->item.getItemQuantity()>0)
                .map(itemDTOMapper)
                .toList();
    }


    public Item findItem(int itemId) {

        return itemRepository.findById(itemId)
                .orElseThrow(()->new NotFoundException("Item not found"));
    }


    public void deleteItem(int itemId) {
        itemRepository.deleteById(itemId);
    }
    public void updateItemQuantity(int itemNumber, int newQuantity ) {
        itemRepository.findByItemNumber(itemNumber)
                .ifPresentOrElse((item)->{
                item.setItemQuantity(item.getItemQuantity() + newQuantity);
                itemRepository.save(item);
                 },()->{
                    throw new NotFoundException("Item not found");
                });


    }
}
