package com.spring.project.web.service;

import com.spring.project.web.dao.entity.User;
import com.spring.project.web.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;



public interface UserService {
    Optional<UserResponse> getUser(String userName);
    Optional<List<UserResponse>> getUsers();
    
    
}
