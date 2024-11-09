package com.Commercial.commercial.auth;

import lombok.*;

@Data
@Builder

public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
