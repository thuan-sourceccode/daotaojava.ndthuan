package com.spring.project.web.service;

import com.spring.project.web.dao.entity.User;

import java.util.Optional;


public interface VerifyService {

    String generateLoginToken(String userName);

    User verifyLoginToken(String token);
}
