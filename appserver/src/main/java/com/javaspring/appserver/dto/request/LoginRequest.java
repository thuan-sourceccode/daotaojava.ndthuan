package com.javaspring.appserver.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotEmpty(message = "userName{validate.required}")
	@Size(max = 30, message = "userName{validate.over.length}")
	private String userName;
	@NotEmpty(message = "password{validate.required}")
	@Size(max = 30, message = "userName{validate.over.length}")
	private String password;

	public LoginRequest(String parameter, String parameter2) {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

}
