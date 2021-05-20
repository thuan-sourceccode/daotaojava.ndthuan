package com.trungtamjava.helloworld.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class HelloController {
	@RequestMapping("/form")
	public String form() {
		return "login";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String id=request.getParameter("id");
		String pw=request.getParameter("password");
		if(id.equals("thuannd") && pw.equals("hanoi1")) {
			request.setAttribute("uid", id);
			request.setAttribute("pwd", pw);
			return "information";
		}
		request.setAttribute("message", "sai thông tin đăng nhập");
		return "login";
	}
	@RequestMapping(value="/nn",method = RequestMethod.GET )
	public String hello(HttpServletRequest request) {
		request.setAttribute("hello"," Hello world with spring");
		return"hello";
	}
}
