
package com.projects.oms.models;

import com.projects.oms.services.OrderItemService;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="ORDERS")

public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  Integer id;
    private int orderNumber;
    private int customerid;

    @OneToMany
    private List<OrderItem> orderItems;

     public double getTotalOrderValue(){
         Iterator<OrderItem> iterator = orderItems.iterator();
        double total = 0;
         while(iterator.hasNext()){
             Integer  orderNumber = iterator.next().getOrderNumber();
             OrderItem item = OrderItemService.orderItemHashMap.get(orderNumber);
             total += (item.calculateItemPrice() * item.getOrderQuantity());
         }
         return total ;
    }
     public double getTotalProfit(){
         Iterator<OrderItem> iterator = orderItems.iterator();
        double total = 0;
         while(iterator.hasNext()){

             Integer  orderNumber = iterator.next().getOrderNumber();
             OrderItem item = OrderItemService.orderItemHashMap.get(orderNumber);
             total += item.calculateProfit();
         }
         return total;
    }
}
