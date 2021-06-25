package com.spring.project.web.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;


public class AppLogger {

    private final Logger logger;
    private final LoggerType loggerType;

    AppLogger(LoggerType loggerType) {
        this.loggerType = loggerType;
        this.logger = LoggerFactory.getLogger(loggerType.getLoggerName());
    }

    public void debug(String msg, String... headerNames) {
        this.logger.debug(prepareMessage(msg, headerNames));
    }

    public void warn(String msg, String... headerNames) {
        this.logger.warn(prepareMessage(msg, headerNames));
    }

    public void error(String msg, String... headerNames) {
        this.logger.error(prepareMessage(msg, headerNames));
    }

    public void info(String msg, String... headerNames) {
        this.logger.info(prepareMessage(msg, headerNames));
    }

    public void debug(String msg, Throwable error, String... headerNames) {
        this.logger.debug(prepareMessage(msg, headerNames), error);
    }

    public void warn(String msg, Throwable error, String... headerNames) {
        this.logger.warn(prepareMessage(msg, headerNames), error);
    }

    public void error(String msg, Throwable error, String... headerNames) {
        this.logger.error(prepareMessage(msg, headerNames), error);
    }

    public void info(String msg, Throwable error, String... headerNames) {
        this.logger.info(prepareMessage(msg, headerNames), error);
    }

 
    private String prepareMessage(String msg, Object[] headerNames) {
        return Arrays.stream(headerNames)
                .map(p -> String.format("[%s]", p))
                .collect(Collectors.joining("")) +  " " + msg;
    }
}
