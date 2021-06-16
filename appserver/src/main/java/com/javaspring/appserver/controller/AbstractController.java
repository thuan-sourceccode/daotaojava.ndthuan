package com.javaspring.appserver.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javaspring.appserver.exception.ResourceNotFoundException;

import java.util.Optional;
public abstract class AbstractController<S> {

	@Autowired
    protected S service;
    protected <T> ResponseEntity<T> response(Optional<T> response) {
        return new ResponseEntity<T>(response.orElseThrow(() -> {
            throw new ResourceNotFoundException();
        }), HttpStatus.OK);
    }
}
