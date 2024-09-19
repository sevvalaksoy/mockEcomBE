package com.workintech.ecommerce.util;

import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

public class Validation {
    public static void isIdValid(Long id) {
        if(id == null || id < 0 ){
            throw new AccountException("Id is not valid", HttpStatus.BAD_REQUEST);
        }
    }
    public static void existenceValidation(JpaRepository jpaRepository, long id, boolean existence){
        if(existence){
            if(jpaRepository instanceof ProductRepository){
                if(jpaRepository.findById(id).isEmpty()){
                    throw new AccountException("Product is not found with id: " + id, HttpStatus.NOT_FOUND);
                }
            }
            if(jpaRepository instanceof UserRepository){
                if(jpaRepository.findById(id).isEmpty()){
                    throw new AccountException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
                }
            }
        } else {
            if(jpaRepository instanceof ProductRepository){
                if(jpaRepository.findById(id).isPresent()){
                    throw new AccountException("Product already exists", HttpStatus.BAD_REQUEST);
                }
            }
            if(jpaRepository instanceof UserRepository){
                if(jpaRepository.findById(id).isPresent()){
                    throw new AccountException("User already exists", HttpStatus.BAD_REQUEST);
                }
            }
        }
    }
}
