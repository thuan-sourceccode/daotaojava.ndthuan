package com.javaspring.appserver.filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.javaspring.appserver.constant.SessionAttributeName;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class TokenAuthenticationFilter extends OncePerRequestFilter{

	 @Autowired
	    protected ServletContext servletContext;

	    @Autowired
	    @Qualifier("handlerExceptionResolver")
	    private HandlerExceptionResolver resolver;

	    public static final String LOCAL = "local";
	    public static final String DEV = "dev";
	    private static final List<String> PUBLIC_ACCEPTED_URL = List.of(
	            "/error",
	            "/css",
	            "/js",
	            "/img",
	            "/fonts",
	            "/login"
	    );

	    @Value("${spring.profiles.active}")
	    private String profileActive;

	    @Override
	    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws ServletException, IOException {
	        String requestUri = httpRequest.getRequestURI();
	        if (isPublicRequest(requestUri)) {
	            chain.doFilter(httpRequest, httpResponse);
	            return;
	        }
	        HttpSession session = httpRequest.getSession();
	        if (StringUtils.isEmpty(session.getAttribute(SessionAttributeName.TOKEN))) {
	            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
	            return;
	        }
	        chain.doFilter(httpRequest, httpResponse);
	    }

	    private boolean isPublicRequest(String requestURI) {

	        String ctxPath = servletContext.getContextPath();
	        for (String req : PUBLIC_ACCEPTED_URL) {
	            if (requestURI.startsWith(ctxPath + req)) {
	                return true;
	            }
	        }

	        return false;

	    }
	}