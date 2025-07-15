package com.dio.e_commerce.dto;

import com.dio.e_commerce.model.Role;
import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private Role role;
}