package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->{
            System.out.println("User credentials are not valid");
            return new UsernameNotFoundException("User credentials are not valid");
        });
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(long id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return userRepository.save(optional.get());
        }
        throw new AccountException("There is no user with the given id", HttpStatus.NOT_FOUND);
    }

    @Override
    public User delete(User user) {
        Optional<User> optional = userRepository.findById(user.getId());
        if(optional.isPresent()){
            User userToDelete = optional.get();
            userRepository.delete(user);
            return userToDelete;
        }
        throw new AccountException("There is no such user", HttpStatus.NOT_FOUND);
    }

    @Override
    public User findById(long id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new AccountException("There is no user with the given id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
