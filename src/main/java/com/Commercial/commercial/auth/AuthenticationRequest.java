package com.Commercial.commercial.auth;


import lombok.*;

@Data
@Builder
public class AuthenticationRequest {
    private String email;
    private String password;
}
