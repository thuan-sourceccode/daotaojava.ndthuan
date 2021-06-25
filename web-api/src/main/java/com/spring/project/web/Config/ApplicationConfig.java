package com.spring.project.web.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import com.spring.project.web.utility.passwd.service.PasswordService;

import javax.sql.DataSource;


@Configuration
@ComponentScan
public class ApplicationConfig {

    @Autowired
    private PasswordService passwordService;
    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.user}")
    private String databaseUser;
    @Value("${database.driver.class.name}")
    private String databaseClassName;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

 
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        String passWord = passwordService.getPassword("mysql.password");
        driverManagerDataSource.setDriverClassName(databaseClassName);
        driverManagerDataSource.setUrl(databaseUrl);
        driverManagerDataSource.setUsername(databaseUser);
        driverManagerDataSource.setPassword(passWord);

        return driverManagerDataSource;
    }

   
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
