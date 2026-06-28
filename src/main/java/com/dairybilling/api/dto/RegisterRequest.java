package com.dairybilling.api.dto;
import com.dairybilling.api.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}