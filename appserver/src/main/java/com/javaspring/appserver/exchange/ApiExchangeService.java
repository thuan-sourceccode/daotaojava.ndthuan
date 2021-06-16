package com.javaspring.appserver.exchange;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaspring.appserver.constant.SessionAttributeName;
import com.javaspring.appserver.log.AppLogger;
import com.javaspring.appserver.log.LoggerFactory;
import com.javaspring.appserver.log.LoggerType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;


@Component
public class ApiExchangeService {

	@Autowired
    private RestTemplate restTemplate;
	
	 private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	    private static final AppLogger API_LOGGER = LoggerFactory.getLogger(LoggerType.API);
	    
	    public String createURL(String domain, String... paths) {
	        StringBuilder builder = new StringBuilder(domain);
	        for (String path : paths) {
	            builder.append("/" + path);
	        }
	        return builder.toString();
	    }
	    

	    private HttpHeaders transfer(HttpServletRequest httpRequest) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("User-Agent", httpRequest.getHeader("User-Agent"));
	        headers.set("X-Forwarded-For", httpRequest.getRemoteAddr());
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        Object token = httpRequest.getSession().getAttribute(SessionAttributeName.TOKEN);
	        if (!StringUtils.isEmpty(token)) {
	            headers.set(HttpHeaders.AUTHORIZATION, token.toString());
	        }

	        return headers;
	    }
	    
	    private HttpEntity<Object> createEntity(HttpServletRequest httpRequest) {
	        return new HttpEntity<>(transfer(httpRequest));
	    }
	    
	    private HttpEntity<Object> createEntity(HttpServletRequest httpRequest, Object data) {
	        HttpHeaders headers = transfer(httpRequest);
	        return new HttpEntity<>(data, headers);
	    }
	    private HttpEntity<Object> createEntity(Object data) {
	        HttpHeaders headers = new HttpHeaders();
	        return new HttpEntity<>(data, headers);
	    }
	    
	    public <T> T get(HttpServletRequest httpRequest, String url, Class<T> classType) {
	        HttpEntity<?> entity = createEntity(httpRequest);
	        try {
	            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, classType);
	            return response.getBody();
	        } catch (HttpClientErrorException e) {
	            // handle http client exception here
	            throw e;
	        } catch (Exception e) {
	            throw e;
	        }
	    }
	    
	    public <T> T post(HttpServletRequest httpRequest, String url, Object request, Class<T> classType) {
	        HttpEntity<Object> requestEntity = createEntity(httpRequest, request);
	        try {
	            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, classType);
	            return response.getBody();
	        } catch (HttpClientErrorException e) {
	            throw e;
	        } catch (Exception e) {
	            throw e;
	        }
	    }

	    public <T> ResponseEntity<T> post(HttpServletRequest httpRequest, String url, MultiValueMap<String, String> requestBody, Class<T> classType) {
	        HttpHeaders headers = createHeader(httpRequest, MediaType.APPLICATION_FORM_URLENCODED);
	        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
	        try {
	            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
	            T res = mapper.readValue(response.getBody(), classType);
	            return new ResponseEntity<>(res, response.getStatusCode());
	        } catch (HttpClientErrorException e) {
	            throw e;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            throw e;
	        }
	    }

	    public <T> ResponseEntity<T> put(HttpServletRequest httpRequest, String url,
                Object request, Class<T> classType) {
HttpEntity<Object> requestEntity = createEntity(httpRequest, request);
try {
ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT,
requestEntity, classType);
return response;
} catch (HttpClientErrorException e) {
return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
} catch (Exception e) {
throw e;
}
}
	    
	    private <T> ResponseEntity<T> delete(HttpServletRequest httpRequest, String url, Object request, Class<T> classType) {
	        HttpEntity<Object> requestEntity = createEntity(httpRequest, request);
	        try {
	            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, classType);
	            return response;
	        } catch (HttpClientErrorException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            throw e;
	        }
	    }
	    
	    private String getCookieValue(HttpServletRequest req, String cookieName) {
	        Cookie[] cookies = req == null ? null : req.getCookies();
	        if (cookies == null || cookies.length == 0) {
	            return null;
	        }

	        return Arrays.stream(cookies).filter(c -> c.getName().equals(cookieName)).findFirst()
	                .map(Cookie::getValue).orElse(null);
	    }

	    
	    private HttpHeaders createHeader(HttpServletRequest httpRequest, MediaType mediaType) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("User-Agent", httpRequest.getHeader("User-Agent"));
	        headers.set("X-Forwarded-For", httpRequest.getRemoteAddr());
	        headers.setContentType(mediaType);

	        return headers;
	    }


	}
