package com.service.catalog.infra.security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.service.catalog.entity.Role;
import com.service.catalog.entity.User;
import com.service.catalog.repository.UserRepository;
import com.service.catalog.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token = extractToken(request);
                var login = tokenService.validateToken(token);
                if(login!=null){
                    User user = userRepository.findByEmail(login);

                    var authorities = setAuthorities(user);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                filterChain.doFilter(request, response);
        
    }

    private String extractToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        String[] headerWords = authHeader.split(" ");
        return headerWords[1];
    }

    private List<SimpleGrantedAuthority> setAuthorities(User user) {
        Role role = user.getRole();
        switch (role) {
            case ADMIN:
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new  SimpleGrantedAuthority("ROLE_USER") )   ;
             

            default:
                return List.of( new  SimpleGrantedAuthority("ROLE_USER") )   ;
                
        }
    }
}
