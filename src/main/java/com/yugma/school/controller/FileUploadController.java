package com.yugma.school.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yugma.school.CloudStorageFileUpload;

@RestController
public class FileUploadController {
	
	@CrossOrigin
	@RequestMapping(value = "/upload-file",
						method = RequestMethod.POST,
							consumes = "multipart/form-data")
	public ResponseEntity<String> uploadProfilePic(
			@RequestParam(value = "file") MultipartFile file){
	
		try{
			CloudStorageFileUpload fileUpload = new CloudStorageFileUpload();
			final String prefix="";
			return new ResponseEntity<String>(
					new ObjectMapper().writeValueAsString(
							fileUpload.uploadFile(file,prefix)),HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
