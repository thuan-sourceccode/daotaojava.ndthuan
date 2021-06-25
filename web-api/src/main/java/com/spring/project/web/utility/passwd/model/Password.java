package com.spring.project.web.utility.passwd.model;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement(name = "passwd")
@XmlAccessorType(XmlAccessType.FIELD)
public class Password {
    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "expires")
    private String expires;

    @XmlElement(name = "word")
    private Word word;

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}
    
    
    
}
