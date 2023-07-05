package com.projects.oms.dto;

import com.projects.oms.models.OrderItem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.function.Function;
@Service
public class ItemOrderDTOMapper implements Function<OrderItem, OrderItemDto> {
    @Override
    public OrderItemDto apply(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getOrderNumber(),
                orderItem.getItemNumber(),
                orderItem.getItem().getItemDesc(),
                orderItem.getItem().getSalePrice(),
                orderItem.getOrderQuantity()
        );
    }
}
