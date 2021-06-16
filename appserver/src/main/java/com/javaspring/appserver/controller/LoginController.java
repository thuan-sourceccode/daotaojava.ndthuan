package com.javaspring.appserver.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javaspring.appserver.dto.request.LoginRequest;
import com.javaspring.appserver.dto.response.LoginResponse;
import com.javaspring.appserver.service.LoginService;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController extends AbstractController<LoginService> {

	@GetMapping(value = "")
    public ModelAndView login() {
        return new ModelAndView();
    }

    @PostMapping(value = "")
    public ModelAndView login(HttpServletRequest httpServletRequest) {
       LoginRequest request = new LoginRequest(httpServletRequest.getParameter("userName"),
    	       httpServletRequest.getParameter("password"));
    
    	
        Optional<LoginResponse> response = service.login(httpServletRequest, request);
        if (response.isEmpty()) {
            return new ModelAndView("redirect:/error/system-error");
        }
        return new ModelAndView("redirect:/user/detail?user-name=" + request.getUserName());
    }
}
