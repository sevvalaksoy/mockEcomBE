package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Chart;
import com.workintech.ecommerce.entity.Role;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.RoleRepository;
import com.workintech.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";
    private static final String ROLE_SUPPLIER = "SUPPLIER";
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String fullName, String email, String password){
        if(userRepository.findByEmail(email).isPresent()){
            throw new AccountException("User with given email already exist", HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(password);
        List<Role> roles = new ArrayList<>();

        addRoleUser(roles);

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(roles);

        return userRepository.save(user);
    }
    public User registerAdmin(String fullName, String email, String password){
        if(userRepository.findByEmail(email).isPresent()){
            throw new AccountException("User with given email already exist", HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(password);
        List<Role> roles = new ArrayList<>();

        addRoleAdmin(roles);

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(roles);

        return userRepository.save(user);
    }
    public User registerSupplier(String fullName, String email, String password){
        if(userRepository.findByEmail(email).isPresent()){
            throw new AccountException("User with given email already exist", HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(password);
        List<Role> roles = new ArrayList<>();

        addRoleSupplier(roles);

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    private void addRoleUser(List<Role> roles){
        Optional<Role> optionalUser = roleRepository.findByAuthority(ROLE_USER);
        if(!optionalUser.isPresent()){
            Role roleUserEntity = new Role();
            roleUserEntity.setAuthority(ROLE_USER);
            roles.add(roleRepository.save(roleUserEntity));
        } else {
            roles.add(optionalUser.get());
        }
    }
    private void addRoleAdmin(List<Role> roles){
        Optional<Role> optionalAdmin = roleRepository.findByAuthority(ROLE_ADMIN);
        if(!optionalAdmin.isPresent()){
            Role roleUserEntity = new Role();
            roleUserEntity.setAuthority(ROLE_ADMIN);
            roles.add(roleRepository.save(roleUserEntity));
        } else {
            roles.add(optionalAdmin.get());
        }
    }
    private void addRoleSupplier(List<Role> roles){
        Optional<Role> optionalSupplier = roleRepository.findByAuthority(ROLE_SUPPLIER);
        if(!optionalSupplier.isPresent()){
            Role roleUserEntity = new Role();
            roleUserEntity.setAuthority(ROLE_SUPPLIER);
            roles.add(roleRepository.save(roleUserEntity));
        } else {
            roles.add(optionalSupplier.get());
        }
    }
}
