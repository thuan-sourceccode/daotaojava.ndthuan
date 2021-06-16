package com.javaspring.appserver.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javaspring.appserver.service.UserService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractController<UserService>{

    @GetMapping(value = "/system-error")
    public ModelAndView getUsers(HttpServletRequest httpServletRequest) {
        return new ModelAndView("error-page");
    }
}
