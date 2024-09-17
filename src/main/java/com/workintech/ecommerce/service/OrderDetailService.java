package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.OrderDetails;

import java.util.List;

public interface OrderDetailService {
    OrderDetails save(OrderDetails orderDetails);
    OrderDetails update(long id);
    OrderDetails delete(OrderDetails orderDetails);
    OrderDetails findById(long id);
    List<OrderDetails> findAll();
}
