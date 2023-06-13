package com.projects.oms.repositories;

import com.projects.oms.models.Item;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@RequiredArgsConstructor
class ItemRepositoryTest {
    @Autowired
    private  ItemRepository underTest;
    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindItemEByItemNumber() {
        // given
        Item item = new Item(
                1,
                89,
                "Pineapple",
                70,
                8,
                40
        );

        // when
        underTest.save(item);
        Optional<Item>  itemexists = underTest.findByItemNumber(89);
        int itemNumber = itemexists.get().getItemNumber();

        //Then
        assertThat(itemNumber).isEqualTo(89);
    }
    

    @Test
    void updateItemQuantity() {
        // given
        Item item = new Item(
                1,
                89,
                "Pineapple",
                70,
                8,
                40
        );

        // when
        underTest.save(item);
        underTest.updateItemQuantity(800,89);
        
        Optional<Item>  expectedItem = underTest.findById(item.getId());
        int itemQuantity = expectedItem.orElseThrow().getItemQuantity();

        //Then
        int expected = 800;
        assertThat(itemQuantity).isEqualTo(expected);
    }
}