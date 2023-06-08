package com.projects.oms.services;

import com.projects.oms.models.Item;
import com.projects.oms.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
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
    void getAllItems() {
    }

    @Test
    void findItem() {
    }

    @Test
    void deleteItem() {
    }

    @Test
    void updateItemQuantity() {
    }
}