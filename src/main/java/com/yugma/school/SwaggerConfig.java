package com.yugma.school;


import static com.google.common.collect.Lists.newArrayList;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.schema.AlternateTypeRules.*;
@Configuration
@EnableSwagger2
//@Order(value=-1)
public class SwaggerConfig {
	
	 	@Bean
	    public Docket adminApi(){
	        return new Docket(DocumentationType.SWAGGER_2)
		            .groupName("admin")
		            .select()
		            .apis(RequestHandlerSelectors.any()
		            		/*RequestHandlerSelectors.basePackage("com.springboot.controller.management")*/)
		            .paths(PathSelectors.regex("/admin/.*"))
		            .build()
		            .directModelSubstitute(LocalDate.class,
		                    String.class)
		                .genericModelSubstitutes(ResponseEntity.class)
		                .alternateTypeRules(
		                    newRule(typeResolver.resolve(DeferredResult.class,
		                            typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
		                        typeResolver.resolve(WildcardType.class)))
		            .apiInfo(apiInfo())
		            .securitySchemes(newArrayList(apiKey()))
		            .securityContexts(newArrayList(securityContext()))
		            .enableUrlTemplating(true);
	    }
	
	 	@Bean
	    public Docket directorApi(){
	        return new Docket(DocumentationType.SWAGGER_2)
		            .groupName("director")
		            .select()
		            .apis(RequestHandlerSelectors.any()
		            		/*RequestHandlerSelectors.basePackage("com.springboot.controller.management")*/)
		            .paths(PathSelectors.regex("/director/.*"))
		            .build()
		            .directModelSubstitute(LocalDate.class,
		                    String.class)
		                .genericModelSubstitutes(ResponseEntity.class)
		                .alternateTypeRules(
		                    newRule(typeResolver.resolve(DeferredResult.class,
		                            typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
		                        typeResolver.resolve(WildcardType.class)))
		            .apiInfo(apiInfo())
		            .securitySchemes(newArrayList(apiKey()))
		            .securityContexts(newArrayList(securityContext()))
		            .enableUrlTemplating(true);
	    }
	 	
	 	@Bean
	    public Docket teacherApi(){
	        return new Docket(DocumentationType.SWAGGER_2)
		            .groupName("teacher")
		            .select()
		            .apis(RequestHandlerSelectors.any()
		            		/*RequestHandlerSelectors.basePackage("com.springboot.controller.management")*/)
		            .paths(PathSelectors.regex("/teacher/.*"))
		            .build()
		            .directModelSubstitute(LocalDate.class,
		                    String.class)
		                .genericModelSubstitutes(ResponseEntity.class)
		                .alternateTypeRules(
		                    newRule(typeResolver.resolve(DeferredResult.class,
		                            typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
		                        typeResolver.resolve(WildcardType.class)))
		            .apiInfo(apiInfo())
		            .securitySchemes(newArrayList(apiKey()))
		            .securityContexts(newArrayList(securityContext()))
		            .enableUrlTemplating(true);
	    }
	 	
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("Yugma App")
	            .description("Yugma App")
	            .termsOfServiceUrl("http://www.nxtlifetechnologies.com")
	            .build();
	    }
	    
	    
	    @Autowired
	    private TypeResolver typeResolver;

	    private ApiKey apiKey() {
	      return new ApiKey("Authorization", "api_key", "header");
	    }

	    private SecurityContext securityContext() {
	      return SecurityContext.builder()
	          .securityReferences(defaultAuth())
	          .forPaths(PathSelectors.regex("/anyPath.*"))
	          .build();
	    }

	    List<SecurityReference> defaultAuth() {
	      AuthorizationScope authorizationScope
	          = new AuthorizationScope("global", "accessEverything");
	      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	      authorizationScopes[0] = authorizationScope;
	      return newArrayList(
	          new SecurityReference("Autorization", authorizationScopes));
	    }

	    @Bean
	    SecurityConfiguration security() {
	      return new SecurityConfiguration(
	          "fooClientIdPassword",
	          "secret",
	          "test-app-realm",
	          "test-app",
	          "Authorization",
	          ApiKeyVehicle.HEADER, 
	          "api_key", 
	          "," /*scope separator*/);
	    }
	   
}