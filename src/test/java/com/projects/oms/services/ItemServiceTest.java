package com.projects.oms.services;

import com.projects.oms.exceptions.NotFoundException;
import com.projects.oms.models.Item;
import com.projects.oms.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

class ItemServiceTest {
    @Autowired
    private  ItemRepository itemRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void  tearDown(){
        itemRepository.deleteAll();
    }

    @Test
    void shouldAddNewItem() {
        // Given
        Item item = new Item(
                1,
                54,
                "bananas",
                60.0,
                7,
                80) ;

        itemRepository.save(item);
        // when
        Optional<Item> exists = itemRepository.findByItemNumber(54);
        String itemDesc = exists.get().getItemDesc();

        //Then
        assertThat(itemDesc).isEqualTo(item.getItemDesc());


    }

    @Test
    void shouldCheckItemDoesNotExists(){
        //given
        Item item = new Item(
                1,
                54,
                "bananas",
                60.0,
                7,
                80) ;
        // when
        Optional<Item> exists = itemRepository.findByItemNumber(54);
        boolean itemNumber = exists.isPresent();

        //Then
        assertThat(itemNumber).isEqualTo(false);
    }


    @Test
    void updateItemQuantity() {
        // given
         Item item = new Item(
                1,
                54,
                "bananas",
                60.0,
                70,
                80) ;
         itemRepository.save(item);

         itemRepository.updateItemQuantity(100, 54);
         // when
        Optional<Item> item1 = itemRepository.findByItemNumber(54);
        int itemQuantity= item1.orElseThrow(()->new NotFoundException("item not found")).getItemQuantity();
        // then
        assertThat(itemQuantity).isEqualTo(100);

    }
}