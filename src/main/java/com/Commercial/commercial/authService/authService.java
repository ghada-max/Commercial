package com.Commercial.commercial.authService;

import com.Commercial.commercial.Constants.Role;
import com.Commercial.commercial.DAO.AUser;
import com.Commercial.commercial.Service.config.jwtService;
import com.Commercial.commercial.auth.AuthenticationRequest;
import com.Commercial.commercial.auth.AuthenticationResponse;
import com.Commercial.commercial.auth.RegisterRequest;
import com.Commercial.commercial.repository.AUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class authService {
    private final AUserRepository userRepo;
    public final PasswordEncoder passwordEncoder;
    private final jwtService jwtServ;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request)  {
        Optional<AUser> u=userRepo.findByEmail(request.getEmail());
        Optional<AUser> optionalUser = userRepo.findByEmail(request.getEmail());

        if (optionalUser.isPresent()) {
            // If user exists, throw an exception
            throw new IllegalArgumentException("User email exists, please try with another one!");
        }
        var user = AUser.builder().firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER).build();
        userRepo.save(user);

        var jwtToken = jwtServ.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();}

    public AuthenticationResponse login(AuthenticationRequest request) throws javax.naming.AuthenticationException {

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("usename not found"));
            var jwtToken = jwtServ.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw e; // Re-throw to be handled in the controller

        }
        }
    }


















