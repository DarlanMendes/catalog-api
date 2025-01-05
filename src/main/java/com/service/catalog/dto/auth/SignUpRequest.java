package com.service.catalog.dto.auth;

import com.service.catalog.entity.Role;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String password;
    private Role role;
}
