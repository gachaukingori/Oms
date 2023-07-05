package com.projects.oms.dto;

public record OrderItemDto(
        int orderNumber,
        int itemNumber,
        String itemDesc,
        double itemPrice,
        int orderQuantity
) {

}
