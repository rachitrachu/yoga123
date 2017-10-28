package com.yugma.school.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yugma.school.ResponseDto;

@RestControllerAdvice
public class ExceptionControllerAdvice implements ResponseBodyAdvice<Object>{
	
	@Autowired
	HttpServletResponse httpResponse;
	
	
	@Override
	public boolean supports(MethodParameter returnType,
	        Class<? extends HttpMessageConverter<?>> converterType) {

	   int statusCode = httpResponse.getStatus();
	   if(statusCode == 400)
		   return true;
	   
	   return false;
	}


	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
	        MediaType selectedContentType,
	        Class<? extends HttpMessageConverter<?>> selectedConverterType,
	        ServerHttpRequest request, ServerHttpResponse response) {
		
		int statusCode = httpResponse.getStatus();
		ResponseDto res;
		if(statusCode == 400){
			res = new ResponseDto();
			res.setDeveloperMessage(body);
			res.setError("Bad Request");
			res.setMessage(body);
			res.setStatus("400");
			try {
				return new ObjectMapper().writeValueAsString(res);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//System.out.println(response.)
		//MethodParameter return1 = returnType.getConstructor();
	    return body;
	}
	
}
