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
import com.yugma.school.model.Complaints;
import com.yugma.school.service.ComplaintService;
import com.yugma.school.service.StaffService;
import com.yugma.school.service.StudentsService;

import io.swagger.annotations.Api;

@Api(tags={"complaints"})
@RestController
public class ComplaintController {
	
	@Autowired
	ComplaintService complaintService;
	
	@Autowired
	StudentsService studentsService;
	
	@Autowired
	StaffService staffService;
	
	@CrossOrigin
	@RequestMapping(value="/saveComplaint", 
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveStudents(@RequestBody Map<String, Object> complaint) throws JsonProcessingException{
		if(complaint==null)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				Complaints complaints=new ObjectMapper().convertValue(complaint, Complaints.class);
		        return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(complaintService.saveComplaint(complaints)),HttpStatus.OK);
			}
	}
	
	@CrossOrigin
	@RequestMapping(value="/studentName/{id}", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchStudentName(@PathVariable long id) throws JsonProcessingException{
		if(id==0)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				
		        return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(studentsService.fetchStudentName(id)),HttpStatus.OK);
			}
	}
	
	@CrossOrigin
	@RequestMapping(value="/managName", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchStaffName() throws JsonProcessingException{
				 return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(staffService.fetchStaffName()),HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/student/{id}/{type}/complaint", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchStuComplaint(@PathVariable long id, @PathVariable String type) throws JsonProcessingException{
		if(id==0||type==null)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				
		        return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(complaintService.fetchStuComplaint(id, type)),HttpStatus.OK);
			}
	}
	
	@CrossOrigin
	@RequestMapping(value="/manag/{id}/{type}/complaint", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchManagComplaint(@PathVariable long id, @PathVariable String type) throws JsonProcessingException{
		if(id==0||type==null)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				
		        return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(complaintService.fetchManagComplaint(id, type)),HttpStatus.OK);
			}
	}
	
	
	
	@CrossOrigin
	@RequestMapping(value="/complaint/{id}", 
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchComplaintDetail(@PathVariable long id) throws JsonProcessingException{
		if(id==0)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			else{
				
		        return new ResponseEntity<String>(
		        		new ObjectMapper().writeValueAsString(complaintService.fetchComplaintDetail(id)),HttpStatus.OK);
			}
	}
	
	

}
