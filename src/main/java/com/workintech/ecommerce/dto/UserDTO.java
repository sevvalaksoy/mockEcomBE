package com.workintech.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String password;
}
