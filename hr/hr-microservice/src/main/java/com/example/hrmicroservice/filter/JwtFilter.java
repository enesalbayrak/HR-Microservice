package com.example.hrmicroservice.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class JwtFilter extends GenericFilterBean {
    private String secret;
    private final UserDetailsService userDetailsService;

    public JwtFilter(String secret, UserDetailsService userDetailsService) {
        this.secret = secret;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var authorizationValue =((HttpServletRequest)servletRequest).getHeader("Authorization");
        if (Objects.nonNull(authorizationValue) && authorizationValue.startsWith("Bearer")){
            var jwtToken = authorizationValue.replace("Bearer","").trim();
            System.err.println(jwtToken);
            var claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
            var username = claims.getSubject();
            var userDetails = userDetailsService.loadUserByUsername(username);
            Date expiration = claims.getExpiration();
            Date now = new Date();
            if (userDetails.getUsername().equals(username) && expiration.after(now)){
                Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
                filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
