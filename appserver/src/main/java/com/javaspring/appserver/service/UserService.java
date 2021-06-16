package com.javaspring.appserver.service;

import javax.servlet.http.HttpServletRequest;

import com.javaspring.appserver.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
	Optional<UserResponse> getUser(HttpServletRequest httpServletRequest, String userName);
    Optional<List<UserResponse>> getUsers(HttpServletRequest httpServletRequest);
}
