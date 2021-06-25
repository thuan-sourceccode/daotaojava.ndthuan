package com.spring.project.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.project.web.dao.entity.User;
import com.spring.project.web.dao.repository.UserRepository;
import com.spring.project.web.dto.response.UserResponse;
import com.spring.project.web.exception.ResourceNotFoundException;
import com.spring.project.web.service.AbstractService;
import com.spring.project.web.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserResponse> getUser(String userName) {
        User user = userRepository.findById(userName).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        return Optional.of(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone()));
    }

    @Override
    public Optional<List<UserResponse>> getUsers() {
        List<User> users = userRepository.getAllUsers().orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        return Optional.of(users.stream()
                .map(user -> new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone()))
                .collect(Collectors.toList()));
    }
}
