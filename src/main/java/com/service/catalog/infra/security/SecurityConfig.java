package com.service.catalog.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter){
        this.securityFilter = securityFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
         httpSecurity.csrf(csrf->csrf.disable())
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize->
        authorize.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
        .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/product").permitAll()
        .requestMatchers(HttpMethod.POST, "/category").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/category").permitAll()
        .anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
