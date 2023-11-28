package com.projects.oms.repositories;

import com.projects.oms.models.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<Item> findByItemNumber(int itemNumber);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Item SET itemQuantity = :itemQuantity WHERE itemNumber = :itemNumber ")
    void updateItemQuantity(@Param("itemQuantity") int itemQuantity, @Param("itemNumber") int itemNumber);
    @Query(value="SELECT i.id, i.itemNumber, i.itemDesc, i.purchasePrice, i.itemQuantity, i.salePrice FROM Items i ",nativeQuery = true)
    List<Item> getAllItemsWithPagination(Pageable pageable);

    @Query(value = "SELECT AVG(i.purchasePrice) as avgPurchasePrice FROM Item i")
    Double getAveragePurchasePrice();
}
