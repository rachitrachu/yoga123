package com.yugma.school.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yugma.school.service.StaffService;
import com.yugma.school.service.StudentsService;

import io.swagger.annotations.Api;

@Api(tags={"login"})
@RestController

public class LoginCont {

	@Autowired
	StudentsService studentService;
	
	@Autowired
	StaffService staffService;
	
	@CrossOrigin
	@RequestMapping(value="/send_otp/student/{phone}", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchStudentByPhone(@PathVariable long phone) throws JsonProcessingException{
		if(phone==0)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				long response= studentService.fetchIdByPhone(phone,0);
				if(response==0)
					return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<String>(HttpStatus.OK);
				
			}
	}
	

	@CrossOrigin
	@RequestMapping(value="/send_otp/management/{phone}", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchStaffByPhone(@PathVariable long phone) throws JsonProcessingException{
		if(phone==0)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				long response= staffService.fetchIdByPhone(phone,0);
				if(response==0)
					return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<String>(HttpStatus.OK);
				
			}
	}
	
	
	@CrossOrigin
	@RequestMapping(value="/login", 
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> verifyUserByPassword(@RequestBody Map<String, Object> map) throws JsonProcessingException{
		ResponseEntity<String> decide=null;
		if(map.isEmpty())
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		else{
				String type=  map.get("type").toString();
				switch(type){
				case "Student" :
					Map<String, Object> passw1= studentService.fetchPasswordByPhone(Long.parseLong(map.get("phone").toString()));
					BCryptPasswordEncoder passen=new BCryptPasswordEncoder();
					if(passw1!=null && passen.matches(map.get("password").toString(),passw1.get("password").toString())){
						Long usdet= studentService.fetchIdByPhone(Long.parseLong(map.get("phone").toString()),1);
						decide= new ResponseEntity<String>(
								new ObjectMapper().writeValueAsString(usdet),
								HttpStatus.OK);
					}
					else
						decide= new ResponseEntity<String>(HttpStatus.NOT_FOUND);
					break;
				    
				case "Management" :
					Map<String, Object> passw2= staffService.fetchPasswordByPhone(Long.parseLong(map.get("phone").toString()));
					BCryptPasswordEncoder passl=new BCryptPasswordEncoder();
					if(passw2!=null && passl.matches(map.get("password").toString(),passw2.get("password").toString())){
						Long usdet= studentService.fetchIdByPhone(Long.parseLong(map.get("phone").toString()),1);
						decide= new ResponseEntity<String>(
								new ObjectMapper().writeValueAsString(usdet)
								, HttpStatus.OK);
					}
					else
						decide= new ResponseEntity<String>(HttpStatus.NOT_FOUND);
					break;
					}
			}
		return decide;
		}
	
	
	}
	
	

