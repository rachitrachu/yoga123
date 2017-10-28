package com.yugma.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class MainApplication extends SpringBootServletInitializer {
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        	return application.sources(MainApplication.class);
    	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
 
}
