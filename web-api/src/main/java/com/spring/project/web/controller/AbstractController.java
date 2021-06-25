package com.spring.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.project.web.exception.ResourceNotFoundException;
import com.spring.project.web.log.AppLogger;
import com.spring.project.web.log.LoggerFactory;
import com.spring.project.web.log.LoggerType;

import java.util.Optional;


public abstract class AbstractController<S> {
    @Autowired
    protected S service;

    protected static final AppLogger APP_LOGGER = LoggerFactory.getLogger(LoggerType.APPLICATION);

  
    protected <T> ResponseEntity<T> response(Optional<T> response) {
        return new ResponseEntity<T>(response.orElseThrow(() -> {
            throw new ResourceNotFoundException();
        }), HttpStatus.OK);
    }
}
