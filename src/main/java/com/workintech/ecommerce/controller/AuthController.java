package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.RegisterResponse;
import com.workintech.ecommerce.dto.RegistrationUser;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register/user")
    public RegisterResponse registerUser(@RequestBody RegistrationUser registrationUser){
        User createdUser = authenticationService
                .registerUser(registrationUser.getFullName(),registrationUser.getEmail(), registrationUser.getPassword());
        RegisterResponse response = new RegisterResponse(createdUser.getEmail(), "kayıt başarılı bir şekilde gerçekleşti.");
        return response;
    }
    @PostMapping("/register/admin")
    public RegisterResponse registerAdmin(@RequestBody RegistrationUser registrationUser){
        User createdUser = authenticationService
                .registerAdmin(registrationUser.getFullName(),registrationUser.getEmail(), registrationUser.getPassword());
        RegisterResponse response = new RegisterResponse(createdUser.getEmail(), "kayıt başarılı bir şekilde gerçekleşti.");
        return response;
    }
    @PostMapping("/register/supplier")
    public RegisterResponse registerSupplier(@RequestBody RegistrationUser registrationUser){
        User createdUser = authenticationService
                .registerSupplier(registrationUser.getFullName(),registrationUser.getEmail(), registrationUser.getPassword());
        RegisterResponse response = new RegisterResponse(createdUser.getEmail(), "kayıt başarılı bir şekilde gerçekleşti.");
        return response;
    }
}