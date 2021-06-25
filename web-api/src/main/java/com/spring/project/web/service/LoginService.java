package com.spring.project.web.service;

import com.spring.project.web.dto.request.LoginRequest;
import com.spring.project.web.dto.response.LoginResponse;

import java.util.Optional;


public interface LoginService {
    Optional<LoginResponse> login(LoginRequest request);
}
