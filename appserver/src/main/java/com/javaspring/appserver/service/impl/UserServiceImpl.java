package com.javaspring.appserver.service.impl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javaspring.appserver.dto.response.UserResponse;
import com.javaspring.appserver.service.AbstractService;
import com.javaspring.appserver.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractService implements UserService

{
	 @Override
	    public Optional<UserResponse> getUser(HttpServletRequest httpServletRequest, String userName) {
	        UserResponse response
	                = apiExchangeService.get(httpServletRequest, apiExchangeService.createURL(backApiUrl, "users", userName), UserResponse.class);
	        if (response == null) {
	            return Optional.empty();
	        }
	        return Optional.of(response);
	    }

	    @Override
	    public Optional<List<UserResponse>> getUsers(HttpServletRequest httpServletRequest) {
	        UserResponse[] responses = apiExchangeService.get(httpServletRequest, apiExchangeService.createURL(backApiUrl, "users"), UserResponse[].class);
	        if (responses == null || responses.length == 0) {
	            return Optional.empty();
	        }
	        return Optional.of(Arrays.asList(responses));
	    }
	}
