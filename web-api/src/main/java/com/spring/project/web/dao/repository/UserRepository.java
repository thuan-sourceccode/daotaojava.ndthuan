package com.spring.project.web.dao.repository;

import com.spring.project.web.dao.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(String id);

    Optional<User> findByIdAndPassword(String id, String password);

    Optional<List<User>> getAllUsers();
}
