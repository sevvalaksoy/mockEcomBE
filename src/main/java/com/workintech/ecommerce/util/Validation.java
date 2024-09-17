package com.workintech.ecommerce.util;

import com.workintech.ecommerce.exception.AccountException;
import org.springframework.http.HttpStatus;

public class Validation {
    public static void isIdValid(Long id) {
        if(id == null || id < 0 ){
            throw new AccountException("Id is not valid", HttpStatus.BAD_REQUEST);
        }
    }

}
