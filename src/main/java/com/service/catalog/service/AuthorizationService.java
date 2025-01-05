package com.service.catalog.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.service.catalog.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

   
    private final UserRepository repository;
    public  AuthorizationService(UserRepository repository){
       this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}
