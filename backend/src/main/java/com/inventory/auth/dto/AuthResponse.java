package com.inventory.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
