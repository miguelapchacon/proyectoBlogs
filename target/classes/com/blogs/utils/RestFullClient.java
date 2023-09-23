package com.blogs.utils;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;

public class RestFullClient 
{
	

	public static String postRestController(String endpoint,JsonObject jsonRequest)
	{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = 
			      new HttpEntity<String>(jsonRequest.toString(), headers);
			    
		ResponseEntity<String> responseEntityStr = restTemplate.
			      postForEntity(endpoint, request, String.class);
		String response=responseEntityStr.getBody();
		return response;
	}

}
