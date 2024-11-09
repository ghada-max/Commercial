package com.Commercial.commercial.Service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTAuthentificationFilter jwtAuthFilter;  // Injected properly
    private final AuthenticationProvider authenticationProvider;  // Injected properly

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for stateless APIs
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:4200"); // Angular frontend origin
                    config.addAllowedMethod("*");  // Allow all HTTP methods
                    config.addAllowedHeader("*");  // Allow all headers
                    config.setAllowCredentials(true); // Allow cookies (if needed)
                    return config;
                }))
                .authorizeHttpRequests(auth -> auth
                       // .requestMatchers("/api/auth/register", "/api/auth/login","/company/getCompany","/company/updateCompany").permitAll()  // Public endpoints
                        .anyRequest().permitAll()  // Other requests require authentication
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
