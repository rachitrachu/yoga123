package com.yugma.school.config.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yugma.school.SwaggerConfig;

@Configuration
@EnableAsync
@EnableWebMvc
@ComponentScan({ "org.yugma.school" })
@Import(SwaggerConfig.class)
public class ResourceServerWebConfig extends WebMvcConfigurerAdapter {
    
	@Bean(name="multipartResolver") 
    public CommonsMultipartResolver getResolver() throws IOException{
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //Set the maximum allowed size (in bytes) for each individual file.
        resolver.setMaxUploadSizePerFile(5242880);//5MB
        //You may also set other available properties.
        return resolver;
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
	    registry.addResourceHandler("/swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
