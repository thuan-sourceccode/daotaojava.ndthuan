package com.spring.project.web.dao.entity;

import org.springframework.web.multipart.MultipartFile;

public class folder {
	
	 private String des;
	 
	    private MultipartFile[] fileDatas;
	 
	    public String getDescription() {
	        return des;
	    }
	 
	    public void setDescription(String des) {
	        this.des = des;
	    }
	 
	    public MultipartFile[] getFileDatas() {
	        return fileDatas;
	    }
	 
	    public void setFileDatas(MultipartFile[] fileDatas) {
	        this.fileDatas = fileDatas;
	    }
	 
}
