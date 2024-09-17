package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.UserDTO;
import com.workintech.ecommerce.dto.UserResponse;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userService.findById(id);
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user);
    }
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable long id, @RequestBody UserDTO userDTO){
        User userToUpdate = userService.findById(id);
        userToUpdate.setFullName(userDTO.getFullName());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setAddress(userDTO.getAddress());
        userToUpdate.setPassword(userDTO.getPassword());
        userToUpdate.setPhone(userDTO.getPhone());
        userService.save(userToUpdate);
        return new UserResponse(userToUpdate.getFullName(),userToUpdate.getEmail(),userToUpdate.getPhone());
    }
    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable long id){
        User userToDelete = userService.findById(id);
        userService.delete(userToDelete);
        return new UserResponse(userToDelete.getFullName(),userToDelete.getEmail(),userToDelete.getPhone());
    }
}
