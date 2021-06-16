package com.javaspring.appserver.config;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestTemplate;


@EnableAutoConfiguration
@ComponentScan
@Configuration
public class ApplicationConfig {

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
