package com.spring.project.web.utility.passwd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "passwdset")
@XmlAccessorType(XmlAccessType.FIELD)
public class PasswordSet {
    @XmlElement(name = "passwd")
    List<Password> passwordList;

	public List<Password> getPasswordList() {
		return passwordList;
	}

	public void setPasswordList(List<Password> passwordList) {
		this.passwordList = passwordList;
	}
    
    
}
