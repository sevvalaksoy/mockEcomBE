package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.OrderDetails;
import com.workintech.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.findAll();
    }
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable long id){
        return orderService.findById(id);
    }
    @GetMapping("/details/{id}")
    public OrderDetails getOrderDetails(@PathVariable long id){
        return orderService.getOrderDetails(id);
    }
    @PostMapping
    public Order save(@RequestBody Order order){
        return orderService.save(order);
    }
    @PutMapping("/{id}")
    public Order update(@PathVariable long id, @RequestBody Order order){
        Order orderToUpdate = orderService.findById(id);
        orderToUpdate.setUser(order.getUser());
        orderToUpdate.setOrderDetails(order.getOrderDetails());
        orderService.save(orderToUpdate);
        return orderToUpdate;
    }
    @DeleteMapping("/{id}")
    public Order delete(@PathVariable long id){
        Order orderToDelete = orderService.findById(id);
        return orderService.delete(orderToDelete);
    }

}
