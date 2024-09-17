package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.OrderDetails;

import java.util.List;

public interface OrderService {
    Order save(Order order);
    Order update(long id);
    Order delete(Order order);
    Order findById(long id);
    List<Order> findAll();
    OrderDetails getOrderDetails(long id);
}
