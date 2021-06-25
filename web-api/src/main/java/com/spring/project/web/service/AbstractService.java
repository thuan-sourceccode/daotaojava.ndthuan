package com.spring.project.web.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import com.spring.project.web.log.AppLogger;
import com.spring.project.web.log.LoggerFactory;
import com.spring.project.web.log.LoggerType;
import com.spring.project.web.utility.ObjectValidator;

import javax.annotation.PostConstruct;


public abstract class AbstractService {
    @Autowired
    Environment env;

    protected static final AppLogger APP_LOGGER = LoggerFactory.getLogger(LoggerType.APPLICATION);

    @Autowired
    protected ObjectValidator validator;


    protected ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
