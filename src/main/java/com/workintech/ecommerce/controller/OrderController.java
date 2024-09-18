package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.entity.*;
import com.workintech.ecommerce.service.ChartService;
import com.workintech.ecommerce.service.OrderDetailService;
import com.workintech.ecommerce.service.OrderService;
import com.workintech.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private OrderDetailService orderDetailService;
    private ChartService chartService;

    @Autowired
    public OrderController(OrderService orderService,UserService userService,OrderDetailService orderDetailService,ChartService chartService){
        this.orderService = orderService;
        this.userService = userService;
        this.orderDetailService = orderDetailService;
        this.chartService = chartService;
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
    @PostMapping("/{userId}")
    public Order save(@PathVariable long userId){
        User user = userService.findById(userId);
        Chart chart = chartService.findById(userId);

        OrderDetails odDetails = new OrderDetails();

        List<Product> products = new ArrayList<>(chart.getProducts());
        odDetails.setProducts(products);
        odDetails.setPrice(chart.priceCalculator(products));
        odDetails.setQuantity(products.size());

        orderDetailService.save(odDetails);

        Order orderToPost = new Order();
        orderToPost.setUser(user);
        orderToPost.setOrderDetails(odDetails);

        return orderService.save(orderToPost);
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
