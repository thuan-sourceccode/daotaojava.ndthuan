package com.javaspring.appserver.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.jaas.LoginExceptionResolver;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.javaspring.appserver.constant.SessionAttributeName;
import com.javaspring.appserver.dto.request.LoginRequest;
import com.javaspring.appserver.dto.response.LoginResponse;
import com.javaspring.appserver.exception.RequestParamInvalidException;
import com.javaspring.appserver.service.AbstractService;
import com.javaspring.appserver.service.LoginService;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
public class LoginServiceImpl extends AbstractService implements LoginService {

	 @SuppressWarnings("deprecation")
	@Override
	    public Optional<LoginResponse> login(HttpServletRequest httpServletRequest, LoginRequest request) {
	        if (request == null) {
	            throw new RequestParamInvalidException("request parameter can not be null");
	        }
	        String message = validator.validateRequestThenReturnMessage(request);
	        if (!StringUtils.isEmpty(message)) {
	            throw new RequestParamInvalidException(message);
	        }
	        LoginResponse response = apiExchangeService.post(httpServletRequest, backApiUrl + "/login", request, LoginResponse.class);
	        if (response == null) {
	            return Optional.empty();
	        }

	        httpServletRequest.getSession().setAttribute(SessionAttributeName.TOKEN, response.getToken());
	        return Optional.of(response);
	    }
}
