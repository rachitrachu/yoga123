/*package com.yugma.school.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yugma.school.ResponseDto;
import com.yugma.school.service.EmployeeService;
import com.yugma.school.service.ParentService;

import io.swagger.annotations.Api;

@Api(tags = { "Login"},description = "Login")
@RestController
public class LoginController {

	
	@Autowired
	ParentService parentService;
	
	@Autowired
	TokenStore tokenStore;
	
	@Autowired
	EmployeeService employeeService;
	
	public static final String AUTH_SERVER_URI = "http://localhost:8080/oauth/token";
    //public static final String AUTH_SERVER_URI = "https://school-yugma.appspot-preview.com/oauth/token";

	private static HttpHeaders getHeaders(){
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	return headers;
    }
	
	 private static HttpHeaders getHeadersWithClientCredentials(){
	    	String plainClientCredentials="fooClientIdPassword:secret";
	    	String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
	    	
	    	HttpHeaders headers = getHeaders();
	    	headers.add("Authorization", "Basic " + base64ClientCredentials);
	    	headers.add("Content-Type", "application/json");
	    	return headers;
	} 

	@CrossOrigin
	@RequestMapping(value = {"/login/parent/{contactNo}"},
			method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loginParent(
						@PathVariable String contactNo,){
		ResponseDto response = new ResponseDto();
		try{
				long parentId = parentService.fetchIdByContact(contactNo);
				if(parentId==0){
					response.setDeveloperMessage("Contact Number is not registered");
					response.setError("Wrong Contact Number");
					response.setMessage("Contact Number is not registered with us.");
					response.setStatus("400");
					return new ResponseEntity<String>(
							new ObjectMapper().writeValueAsString(response),
							HttpStatus.BAD_REQUEST);
				}else{
					int row;
					if(contactNo.equals("9806155360")|| contactNo.equals("8347357810") 
							|| contactNo.equals("1234567890") || contactNo.equals("9876543210")
							|| contactNo.equals("8295579900") || contactNo.equals("7859904428"))
						row=1;
					else
						row =parentService.sendOspForLogin(contactNo);
					
					if(row>0){
							response.setDeveloperMessage("Osp has been sent on your contact successfully."
												+ "Please enter osp for proceed");
							response.setMessage("Osp has been sent on your contact successfully."
									+ "Please enter osp for proceed");
							response.setStatus("200");
							return new ResponseEntity<String>(
									new ObjectMapper().writeValueAsString(response)
									,HttpStatus.OK);
						}else{
							response.setStatus("204");
							response.setError("No Content");
							response.setMessage("Something went wrong. Please try again");
							response.setDeveloperMessage("Contact number not found");
							return new ResponseEntity<String>(
									new ObjectMapper().writeValueAsString(response),
									HttpStatus.NO_CONTENT);
						}
				}
			}catch(Exception e){
				System.out.println(e);
				response.setError("Bad Request");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login",
			method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> parentLogin(@RequestBody Map<String,Object> map){
		if(map.isEmpty() || map.containsKey("id"))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else{
			try{
				RestTemplate restTemplate = new RestTemplate();     
		        HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		        
		        List<OAuth2AccessToken> accessTokens  = 
		        		(List<OAuth2AccessToken>) tokenStore.findTokensByClientIdAndUserName("fooClientIdPassword", 
		        		map.get("username").toString());
		        //System.out.println(accessTokens);
		       
		        ResponseEntity<Object> response = 
		        		restTemplate.exchange(AUTH_SERVER_URI+"?grant_type=password&username="
		        				+map.get("username")+"&password="+map.get("password"),
		        				HttpMethod.POST, request, Object.class);
		        System.out.println("Url : "+AUTH_SERVER_URI+"?grant_type=password&username="
		        				+map.get("username")+"&password="+map.get("password"));
		        System.out.println("response : "+response);
		        System.out.println("accessTokens : "+accessTokens);
		        for(OAuth2AccessToken accessToken : accessTokens){
		        	tokenStore.removeAccessToken(accessToken);
		        }
		        
		        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>)response.getBody();
				if(result==null){
					return new ResponseEntity<String>(
							new ObjectMapper().writeValueAsString("User not exist"),HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(result),HttpStatus.OK);
			}catch(Exception e){
				System.out.println(e);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/forgot-password",
						method = RequestMethod.PUT,
							consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> forgotEmployeePassword(
			@RequestBody Map<String,Object> username_password){
		if(username_password==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else{
			try{				
				int row = employeeService.changePasswordByUsername(username_password);
				if(row>0){
					return new ResponseEntity<String>(
							new ObjectMapper().writeValueAsString("Done"),HttpStatus.OK);
				}else{
					return new ResponseEntity<String>(
							new ObjectMapper().writeValueAsString(
									"You have entered wrong username"),HttpStatus.BAD_REQUEST);
				}
			}catch(Exception e){
				System.out.println(e);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}
}
*/