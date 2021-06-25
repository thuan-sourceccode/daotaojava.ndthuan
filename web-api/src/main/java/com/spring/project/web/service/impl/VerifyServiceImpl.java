package com.spring.project.web.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.spring.project.web.dao.entity.User;
import com.spring.project.web.dao.repository.UserRepository;
import com.spring.project.web.exception.TokenInvalidException;
import com.spring.project.web.service.AbstractService;
import com.spring.project.web.service.VerifyService;
import com.spring.project.web.utility.DateTimeUtils;
import com.spring.project.web.utility.passwd.service.PasswordService;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Optional;


@Service
public class VerifyServiceImpl extends AbstractService implements VerifyService {

    private static final String TOKEN_TYPE_PREFIX = "Bearer";
    private static final String TYPE_HEADER_NAME = "typ";
    private static final String JWT_HEADER_TYPE = "JWT";
    private static final String USER_NAME_PAYLOAD = "userName";

    private String tokenKey;
    @Value("${token.expiration}")
    private Integer tokenExpiredValue;

    @Autowired
    PasswordService passwordService;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        this.tokenKey = passwordService.getPassword("token.key");
    }

    @Override
    public String generateLoginToken(String userName) {
        return Jwts.builder()
                .setHeaderParam(TYPE_HEADER_NAME, JWT_HEADER_TYPE)
                .claim(USER_NAME_PAYLOAD, userName)
                .setIssuedAt(new Date())
                .setExpiration(DateTimeUtils.addDayToDate(new Date(), tokenExpiredValue))
                .signWith(SignatureAlgorithm.HS256, tokenKey.getBytes())
                .compact();
    }

    @Override
    public @NonNull User verifyLoginToken(@NonNull String token) {
        String userName;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenKey.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_TYPE_PREFIX, "")).getBody();
            userName = claims.get(USER_NAME_PAYLOAD, String.class);
        } catch (ExpiredJwtException e) {
            throw new TokenInvalidException("E1", "Token is Expired");
        } catch (Exception e) {
            throw new TokenInvalidException("E2", "Can't verify token: " + token);
        }

        Optional<User> user = userRepository.findById(userName);
        return user.orElseThrow(() -> {
            throw new TokenInvalidException("E3", "This user is invalidate: " + userName);
        });
    }
}
