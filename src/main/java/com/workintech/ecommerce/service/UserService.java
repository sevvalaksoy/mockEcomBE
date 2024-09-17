package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User update(long id);
    User delete(User user);
    User findById(long id);
    List<User> findAll();
}
