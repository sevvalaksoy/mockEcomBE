package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.OrderDetails;
import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if(optional.isPresent()){
            return orderRepository.save(optional.get());
        } else {
            throw new AccountException("There is no order with the given Id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Order delete(Order order) {
        Optional<Order> optional = orderRepository.findById(order.getId());
        if(optional.isPresent()){
            Order orderToDelete = optional.get();
            orderRepository.delete(order);
            return orderToDelete;
        } else {
            throw new AccountException("There is no such order", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Order findById(long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new AccountException("There is no such order with the given id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderDetails getOrderDetails(long id) {
        return orderRepository.getOrderDetails(id);
    }
}
