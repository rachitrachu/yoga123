package com.yugma.school.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@Autowired
	private SimpMessagingTemplate template;

    @RequestMapping(path="/websocket/{greeting}", method=RequestMethod.GET)
    public ResponseEntity<String> greet(@PathVariable String greeting) throws InterruptedException, MessagingException, JsonGenerationException, JsonMappingException, IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("name",greeting);
       // Thread.sleep(10000);
        this.template.convertAndSend("/management/greeting", new ObjectMapper().writeValueAsString(map));
        return new ResponseEntity<String>("Done",HttpStatus.OK);
    }

}
