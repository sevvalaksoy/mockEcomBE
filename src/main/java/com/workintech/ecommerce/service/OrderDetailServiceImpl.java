package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.OrderDetails;
import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    public OrderDetailServiceImpl(OrderDetailsRepository orderDetailsRepository){
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    public OrderDetails update(long id) {
        Optional<OrderDetails> optional = orderDetailsRepository.findById(id);
        if(optional.isPresent()){
            return orderDetailsRepository.save(optional.get());
        } else {
            throw new AccountException("There is no order with given id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public OrderDetails delete(OrderDetails orderDetails) {
        Optional<OrderDetails> optional = orderDetailsRepository.findById(orderDetails.getId());
        if(optional.isPresent()){
            OrderDetails orderDetailsToDelete = optional.get();
            orderDetailsRepository.delete(orderDetails);
            return orderDetailsToDelete;
        } else {
            throw new AccountException("There is no such order", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public OrderDetails findById(long id) {
        Optional<OrderDetails> optional = orderDetailsRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        } else {
            throw new AccountException("There is no order with the given id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }
}
