package com.dairybilling.api.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String token; // This is the VIP Badge!
}