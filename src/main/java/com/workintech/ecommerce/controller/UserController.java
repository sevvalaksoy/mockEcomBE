package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.UserDTO;
import com.workintech.ecommerce.dto.UserResponse;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    private UserService userService;
    private UserDetailsService userDetailsService;
    @Autowired
    public UserController(UserService userService, UserDetailsService userDetailsService){
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userService.findById(id);
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    @GetMapping("/verify")
    public UserDetails login(@RequestBody UserDetails userDetails){
        return userDetailsService.loadUserByUsername(userDetails.getUsername());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@RequestBody UserDTO userDTO){
        User userToSave = new User();
        userToSave.setFullName(userDTO.getFullName());
        userToSave.setEmail(userDTO.getEmail());
        userToSave.setPassword(userDTO.getPassword());
        userToSave.setPhone(userDTO.getPhone());
        userToSave.setAddress(userDTO.getAddress());
        userService.save(userToSave);
        return new UserResponse(userToSave.getFullName(),userToSave.getEmail(),userToSave.getPhone());
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
