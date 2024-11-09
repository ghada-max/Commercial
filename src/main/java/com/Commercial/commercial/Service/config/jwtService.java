package com.Commercial.commercial.Service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Builder
@Service
public class jwtService {
    private static final String SECRET_KEY="ktohrWT3dx9s9fAlE6K2GPTYTiQx7oC54bdiXhwGpKqifrAGVCvNtCtjVYl+M+u7pt5vh/sDtU86qWi8uFkfTL1XEBHHYgNDhs3xAxgDyHBNX72FLIIArbV03fKyNKwow6SkcQYHsrfJp1Ygz0LeEakIyX8XYkahSDYVGZASYvmIZIin2z2NSHjc8507BgGKN/Z18J5tOdwIovXf4Rxreve0PTzSNAD4dyNuZlQ+IG50oS4/wZSQeb8j0pnDj8PhP4fcBBHD/qdtbaPxFOYmb3FJH7poEpGyinSm17Cvlr3TJR7sB/kUA3tswWYJnUxxJqMkrJqNukqIWB/Yl0CTE8n1d/bFvRL0h07R6L34Vr8=\n";
    public String extractUsrName(String token) {
        return extractClaims(token,Claims::getSubject);
    }

     public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
     }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String,Object> extraClaims,
                                UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignKey())
                .compact();

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsrName(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExprired(token));
    }

    private boolean isTokenExprired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }

}













