package com.spring.project.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.web.dto.request.LoginRequest;
import com.spring.project.web.service.LoginService;



@RestController
@RequestMapping("/login")
public class LoginController extends AbstractController<LoginService> {

    @PostMapping(value = "")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return response(service.login(request));
    }
}
