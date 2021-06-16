package com.javaspring.appserver.utility;
import org.springframework.util.ObjectUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class XmlUtils {
	
	public <T> String marshal(T object) {
        if (ObjectUtils.isEmpty(object)) {
            return "";
        }
        StringWriter response = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(object, response);

        } catch (JAXBException jxbException) {
            jxbException.printStackTrace();
        }
        return response.toString();
    }
	 public <T> T unmarshal(Reader reader, Class<T> clazz) {
	        T classInstance = null;
	        try {
	            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
	            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	            Object object = unmarshaller.unmarshal(new StreamSource(reader));
	            classInstance = clazz.cast(object);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return classInstance;
	    }
	 public List<File> getFileList(String directory, String endOfFile) throws FileNotFoundException {
	        File folder = new File(directory);
	        if (!folder.exists()) {
	            throw new FileNotFoundException();
	        }
	        List<File> listFile = Arrays.stream(folder.listFiles()).filter(file -> !file.isDirectory() && file.getName().endsWith((endOfFile))).collect(Collectors.toList());
	        return listFile;
	    }
}
