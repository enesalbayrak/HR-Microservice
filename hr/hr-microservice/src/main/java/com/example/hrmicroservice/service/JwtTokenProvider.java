package com.example.hrmicroservice.service;

import com.example.hrmicroservice.dto.request.WebUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire.duration}")
    private long expirationDuration;
    public String generateToken(WebUser webUser){
        Claims claims = Jwts.claims().setSubject(webUser.getUsername());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+expirationDuration))
                .signWith(SignatureAlgorithm.ES256,secret)
                .compact();
    }
}
