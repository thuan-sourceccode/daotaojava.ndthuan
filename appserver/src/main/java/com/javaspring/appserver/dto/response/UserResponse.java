package com.javaspring.appserver.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
	
}
