package com.example.hrmicroservice.controller;

import com.example.hrmicroservice.dto.request.WebUser;
import com.example.hrmicroservice.service.JwtTokenProvider;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/login")
@RequestScope
@Validated
@CrossOrigin
public class LoginController {
    private AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public String authenticateAndCreateToken(@RequestBody @Validated WebUser webUser){
        try{
            var authentication = new UsernamePasswordAuthenticationToken(webUser.getUsername(),webUser.getPassword());
            authenticationManager.authenticate(authentication);
            return jwtTokenProvider.generateToken(webUser);
        }catch (AuthenticationException e){
            System.err.println("Wrong username/password:"+e.getMessage());
            throw e;
        }
    }
}
