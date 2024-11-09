package com.Commercial.commercial.auth;
import com.Commercial.commercial.authService.authService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthentificationController {
private final authService authSer;
@PostMapping("/register")
public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception
{ try{
    AuthenticationResponse    response =authSer.register(request);
    return new ResponseEntity<>(response, HttpStatus.OK);}
catch (Exception e){
    throw new Exception(e);
}
}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest request)
    { try {
        AuthenticationResponse response = authSer.login(request);
        return new ResponseEntity<>(response.getToken(), HttpStatus.OK);
    } catch (AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
    }

}
