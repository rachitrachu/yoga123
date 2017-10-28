package com.yugma.school.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = { "info"},description = "Info")
@RestController
public class RootController {

	@Autowired
    private JavaMailSender javaMailSender;

	private void send() {
        MimeMessage mail = javaMailSender.createMimeMessage();  
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo("ajaykumargarg01@gmail.com");
            helper.setBcc("ajaykumargarg01@gmail.com");
            helper.setSubject("Lorem ipsum");
            helper.setText("Lorem ipsum dolor sit amet [...]");
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        javaMailSender.send(mail);
        //return helper;
    }
	
	@CrossOrigin 
	@RequestMapping(value = "/_ah/health",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchHealth(){
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}

	@CrossOrigin 
    @RequestMapping(value = "/",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchHome(){
		send();
        return new ResponseEntity<String>("Hellooooo",HttpStatus.OK);
    }
	

	
}
