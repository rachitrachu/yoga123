package com.yugma.school.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yugma.school.model.Students;
import com.yugma.school.service.StudentsService;

import io.swagger.annotations.Api;

@Api(tags={"students"})
@RestController
public class StudentsController {
	
	@Autowired
	StudentsService studentsService;
	
	@CrossOrigin
	@RequestMapping(value="/saveStudent", 
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveStudents(@RequestBody Students student){
		if(student==null)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				studentsService.saveStudent(student);
		        return new ResponseEntity<String>(HttpStatus.OK);
			}
	}
	
	@CrossOrigin
	@RequestMapping(value="/updateStudent", 
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateStudents(@RequestBody Map<String,Object> map) throws JsonProcessingException{
		if(map.isEmpty())
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				
		        return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(studentsService.updateStudents(map)),HttpStatus.OK);
			}
	}
	
	@CrossOrigin
	@RequestMapping(value="/deleteStudent", 
			method=RequestMethod.DELETE, 
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteStudents(@RequestBody Map<String,Object> map) throws JsonProcessingException{
		if(map.isEmpty())
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		else{
			
			return new ResponseEntity<String>(
					new ObjectMapper().writeValueAsString(studentsService.deleteStudents(Long.parseLong(map.get("student_id").toString()))),HttpStatus.OK);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/student/{id}", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchStudentById(@PathVariable long id) throws JsonProcessingException{
		if(id==0)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				
		        return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(studentsService.fetchStudentById(id)),HttpStatus.OK);
			}
	}
	
	@CrossOrigin
	@RequestMapping(value="/student/phone/{phone}", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchStudentByPhone(@PathVariable long phone) throws JsonProcessingException{
		if(phone==0)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				
		        return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(studentsService.fetchStudentByPhone(phone)),HttpStatus.OK);
			}
	}
		
		@CrossOrigin
		@RequestMapping(value="/fetch3Student/{phone}", 
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> fetchPasswordByPhone(@PathVariable long phone) throws JsonProcessingException{
			if(phone==0)
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				else{
			        return new ResponseEntity<String>(
			        		new ObjectMapper().writeValueAsString(studentsService.fetchPasswordByPhone(phone)) ,HttpStatus.OK);
				}
		}
	}


