package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.orderDetails FROM Order o WHERE o.id = :id")
    OrderDetails getOrderDetails(long id);
}
