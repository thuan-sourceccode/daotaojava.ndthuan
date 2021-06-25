package com.spring.project.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.project.web.dao.entity.folder;

@Controller
public class foldercontroller {

	 
	    @RequestMapping(value = "/")
	    public String homePage() {
	 
	        return "index";
	    }
	 
	    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.GET)
	    public String uploadOneFileHandler(Model model) {
	 
	        folder myUploadForm = new folder();
	        model.addAttribute("myUploadForm", myUploadForm);
	 
	        return "uploadOneFile";
	    }
	 
	    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST)
	    public String uploadOneFileHandlerPOST(HttpServletRequest request, //
	            Model model, //
	            @ModelAttribute("myUploadForm") folder myUploadForm) {
	 
	        return this.doUpload(request, model, myUploadForm);
	 
	    }
	 
	 
	    private String doUpload(HttpServletRequest request, Model model,
	            folder myUploadForm) {
	 
	        String des = myUploadForm.getDescription();
	        System.out.println("Description: " + des);
	 
	       
	        String uploadRootPath = request.getServletContext().getRealPath("upload");
	        System.out.println("uploadRootPath=" + uploadRootPath);
	 
	        File uploadRootDir = new File(uploadRootPath);
	      
	        if (!uploadRootDir.exists()) {
	            uploadRootDir.mkdirs();
	        }
	        MultipartFile[] fileDatas = myUploadForm.getFileDatas();
	        
	        List<File> uploadedFiles = new ArrayList<File>();
	        List<String> failedFiles = new ArrayList<String>();
	 
	        for (MultipartFile fileData : fileDatas) {
	 
	            
	            String name = fileData.getOriginalFilename();
	            System.out.println("Client File Name = " + name);
	 
	            if (name != null && name.length() > 0) {
	                try {
	                
	                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
	 
	                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	                    stream.write(fileData.getBytes());
	                    stream.close();
	                    // 
	                    uploadedFiles.add(serverFile);
	                    System.out.println("Write file: " + serverFile);
	                } catch (Exception e) {
	                    System.out.println("Error Write file: " + name);
	                    failedFiles.add(name);
	                }
	            }
	        }
	        model.addAttribute("description", description);
	        model.addAttribute("uploadedFiles", uploadedFiles);
	        model.addAttribute("failedFiles", failedFiles);
	        return "uploadResult";
	    }
}
