package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    private UserRepository userRepository;
    @Autowired
    public UserRepositoryTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setFullName("Veli");
        user.setEmail("veli@gmail.com");
        user.setPassword("1234");
        user.setPhone("555");
        user.setAddress("Ankara");
        userRepository.save(user);
        Optional<User> userToGet = userRepository.findByEmail("veli@gmail.com");
    }

    @AfterEach
    void tearDown() {
        Optional<User> userToGet = userRepository.findByEmail("veli@gmail.com");
        userRepository.delete(userToGet.get());
    }

    @Test
    void findByEmail() {
        Optional<User> user = userRepository.findByEmail("veli@gmail.com");
        assertNotNull(user);
    }
}