package com.spring.project.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.spring.project.web.dao.entity.User;
import com.spring.project.web.dao.repository.UserRepository;
import com.spring.project.web.dto.request.LoginRequest;
import com.spring.project.web.dto.response.LoginResponse;
import com.spring.project.web.exception.RequestParamInvalidException;
import com.spring.project.web.exception.TokenInvalidException;
import com.spring.project.web.service.AbstractService;
import com.spring.project.web.service.LoginService;
import com.spring.project.web.service.VerifyService;

import java.util.Optional;



@Service
public class LoginServiceImpl extends AbstractService implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerifyService verifyService;

    @Override
    public Optional<LoginResponse> login(LoginRequest request) {
        String message = validator.validateRequestThenReturnMessage(request);
        if (!StringUtils.isEmpty(message)) {
            throw new RequestParamInvalidException(message);
        }
        Optional<User> userData = userRepository.findByIdAndPassword(request.getUserName(), request.getPassword());
        return userData.map(user -> new LoginResponse(user.getId(), user.getFirstName(), user.getLastName(),
                verifyService.generateLoginToken(request.getUserName())));
    }
}
