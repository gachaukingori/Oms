package com.projects.oms.repositories;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.models.Item;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@RequiredArgsConstructor
class ItemRepositoryTest {
    Logger logger =
            LoggerFactory.getLogger(ItemRepositoryTest.class);
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

        Optional<Item>  expectedItem = underTest.findByItemNumber(item.getItemNumber());
        int itemQuantity = expectedItem.orElseThrow().getItemQuantity();

        //Then
        int expected = 800;
        assertThat(itemQuantity).isEqualTo(expected);
    }

    @Test
    void testOrderBy(){
        //given
        Item item = new Item(
                1,
                89,
                "Pineapple",
                70,
                8,
                40
        );
        Item item2 = new Item(
                2,
                90,
                "mango",
                50,
                60,
                70
        );
        Item item3= new Item(
                3,
                91,
                "banana",
                10,
                50,
                30
        );
        //when
        underTest.save(item2);
        underTest.save(item3);
        underTest.save(item);


        List<Item> orderedByName = underTest.findAll(Sort.by(Sort.Direction.DESC,"itemQuantity"));

        //then
        assertThat(orderedByName.get(0).getItemDesc()).isEqualTo("mango");
    }
    @Test
    void testAveragePrice(){
        //given
        Item item = new Item(
                1,
                89,
                "Pineapple",
                90,
                8,
                40
        );
        Item item2 = new Item(
                2,
                90,
                "mango",
                50,
                60,
                70
        );
        Item item3= new Item(
                3,
                91,
                "banana",
                10,
                50,
                30
        );

        //when
        underTest.save(item2);
        underTest.save(item3);
        underTest.save(item);

        Double avgPrice = underTest.getAveragePurchasePrice();
        //then
        assertThat(avgPrice).isEqualTo(50);
    }
    @Test
    void testPagination(){
        //given
        Pageable pageable = PageRequest.of(0,3);
        Item item = new Item(
                1,
                89,
                "Pineapple",
                70,
                8,
                40
        );
        Item item2 = new Item(
                2,
                90,
                "mango",
                50,
                60,
                70
        );
        Item item3= new Item(
                3,
                91,
                "banana",
                10,
                50,
                30
        );
        //when
        underTest.save(item2);
        underTest.save(item3);
        underTest.save(item);

        List<Item> theFirst3Items = underTest.getAllItemsWithPagination(pageable);

        //then
        assertThat(theFirst3Items.get(1).getItemDesc()).isEqualTo("banana");


    }
}