package com.spring.project.web.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInvalidException extends RuntimeException {
    private String code;

    public TokenInvalidException(String code, String message) {
        super(message);
    	this.code = code;
    }

    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
}
