package com.spring.project.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import com.spring.project.web.exception.TokenInvalidException;
import com.spring.project.web.service.VerifyService;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class TokenAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    protected ServletContext servletContext;

    @Autowired
    private VerifyService verifyService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    public static final String LOCAL = "local";
    public static final String DEV = "dev";
    private static final List<String> PUBLIC_ACCEPTED_URL = List.of(
            "/login"
    );

    @Value("${spring.profiles.active}")
    private String profileActive;



   
    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws ServletException, IOException {
        String requestURI = httpRequest.getRequestURI();
        if (isPubicRequest(requestURI)) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
        String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (StringUtils.isEmpty(token)) {
                throw new TokenInvalidException("E04", "token is empty!");
            }
            verifyService.verifyLoginToken(token);
        } catch (Exception e) {
            resolver.resolveException(httpRequest, httpResponse, null, e);
            return;
        }

        chain.doFilter(httpRequest, httpResponse);
    }

  
    private boolean isPubicRequest(String requestURI) {

        String ctxPath = servletContext.getContextPath();
        for (String req : PUBLIC_ACCEPTED_URL) {
            if (requestURI.startsWith(ctxPath + req)) {
                return true;
            }
        }

        if (profileActive.equals(LOCAL) || profileActive.equals(DEV)) {
            List<String> swaggerUriAccepted = List.of(
                    "/configuration/ui",
                    "/swagger-resources",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/v2/api-docs",
                    "/webjars",
                    "/images"
            );
            for (String req : swaggerUriAccepted) {
                if (requestURI.startsWith(ctxPath + req)) {
                    return true;
                }
            }
        }
        return false;

    }
}
