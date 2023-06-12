package com.projects.oms.services;

import com.projects.oms.dto.ItemDTOMapper;
import com.projects.oms.repositories.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


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
}