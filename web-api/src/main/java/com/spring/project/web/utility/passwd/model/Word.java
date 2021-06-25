package com.spring.project.web.utility.passwd.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement(name = "word")
@XmlAccessorType(XmlAccessType.FIELD)
public class Word {
    @XmlAttribute(name = "value")
    String value;
    @XmlAttribute(name = "start")
    String start;
    @XmlAttribute(name = "end")
    String end;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
    
    
}
