package com.javaspring.appserver.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String id;
    private String firstName;
    private String lastName;
    private String token;
	
    public String getToken() {
		return token;
	}
    
    
    
}
