package com.projects.oms.services;

import com.projects.oms.dto.ItemDTOMapper;
import com.projects.oms.models.Item;
import com.projects.oms.repositories.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class ItemServiceTest {
   @Mock
   private ItemRepository itemRepository;
    @Mock
    private ItemDTOMapper itemDTOMapper;
    private ItemService underTest;
    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ItemService(itemRepository, itemDTOMapper);


    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void addNewItem() {

    }

    @Test

    void getAllItems() {
        // given
        underTest.getAllItems();

        //then
        verify(itemRepository).findAll();
    }

    @Test
    @Disabled
    void findItem() {
    }

    @Test
    @Disabled
    void deleteItem() {
    }

    @Test
    void updateItemQuantity() {

    }
    @Test
    void calculateStockValue(){
        // given
        Item item1 = new Item(1,1,"Bread",40, 50,1);
        Item item2 = new Item(2,2,"Banana",5, 100,10);
        List<Item> allItems = new ArrayList<Item>();
        allItems.add(item1);
        allItems.add(item2);
        when(itemRepository.findAll()).thenReturn(allItems);

        double actualValue = underTest.getStockValue();
        assertEquals(2500.0,actualValue);
    }
}