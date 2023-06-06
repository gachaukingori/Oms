package com.projects.oms.dto;

import com.projects.oms.models.Item;

import java.util.function.Function;

public class ItemDTOMapper  implements Function<Item, ItemDTO> {
    @Override
    public ItemDTO apply(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getItemNumber(),
                item.getItemDesc(),
                item.getSalePrice());
    }
}
