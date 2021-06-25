package com.spring.project.web.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.project.web.dto.response.ErrorResponse;
import com.spring.project.web.exception.RequestParamInvalidException;
import com.spring.project.web.exception.ResourceNotFoundException;
import com.spring.project.web.exception.TokenInvalidException;
import com.spring.project.web.log.AppLogger;
import com.spring.project.web.log.LoggerFactory;
import com.spring.project.web.log.LoggerType;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final AppLogger APP_LOGGER = LoggerFactory.getLogger(LoggerType.APPLICATION);

    @ExceptionHandler({TokenInvalidException.class})
    public ResponseEntity<ErrorResponse> handleTokenInvalidException(TokenInvalidException e) {
        APP_LOGGER.error(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(e.getCode(), e.getMessage()), null, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({RequestParamInvalidException.class})
    public ResponseEntity<ErrorResponse> handleTokenInvalidException(RequestParamInvalidException e) {
        APP_LOGGER.error(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("E11", e.getMessage()), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        APP_LOGGER.error(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("D0", e.getMessage()), null, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleCommonException(Exception e) {
        APP_LOGGER.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("E0", e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
