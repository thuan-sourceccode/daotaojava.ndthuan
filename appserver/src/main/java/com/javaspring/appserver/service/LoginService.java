package com.javaspring.appserver.service;
import javax.servlet.http.HttpServletRequest;

import com.javaspring.appserver.dto.request.LoginRequest;
import com.javaspring.appserver.dto.response.LoginResponse;

import java.util.Optional;

public interface LoginService {
	Optional<LoginResponse> login(HttpServletRequest httpServletRequest, LoginRequest request);
	
}
