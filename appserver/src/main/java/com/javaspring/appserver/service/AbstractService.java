package com.javaspring.appserver.service;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaspring.appserver.dto.request.LoginRequest;
import com.javaspring.appserver.dto.response.LoginResponse;
import com.javaspring.appserver.dto.response.UserResponse;
import com.javaspring.appserver.exchange.ApiExchangeService;
import com.javaspring.appserver.utility.ObjectValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractService {
	
	@Autowired
    protected Environment env;

    @Autowired
    protected ObjectValidator validator;

    @Autowired
    protected ApiExchangeService apiExchangeService;

    protected ObjectMapper objectMapper;

    protected String backApiUrl;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        backApiUrl = env.getProperty("api.backend.url");
    }
}
