package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void save() {
        User user = new User();
        user.setFullName("Veli");
        user.setEmail("veli@gmail.com");
        user.setPassword("1234");
        user.setPhone("555");
        user.setAddress("Ankara");
        userRepository.save(user);
        verify(userRepository).save(user);
    }
    @Test
    void delete() {
        User user = new User();
        user.setFullName("Veli");
        user.setEmail("veli@gmail.com");
        user.setPassword("1234");
        user.setPhone("555");
        user.setAddress("Ankara");
        userRepository.delete(user);
        verify(userRepository).delete(user);
    }

    @Test
    void findById() {
        User user = new User();
        user.setFullName("Veli");
        user.setEmail("veli@gmail.com");
        user.setPassword("1234");
        user.setPhone("555");
        user.setAddress("Ankara");
        userRepository.findById(user.getId());
        verify(userRepository).findById(user.getId());
    }

    @Test
    void findAll() {
        User user = new User();
        user.setFullName("Veli");
        user.setEmail("veli@gmail.com");
        user.setPassword("1234");
        user.setPhone("555");
        user.setAddress("Ankara");
        userRepository.save(user);
        userRepository.findAll();
        verify(userRepository).findAll();
    }
    @Test
    void loadUserByUsername() {

    }

    @Test
    void update() {
    }
}