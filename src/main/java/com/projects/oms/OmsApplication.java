package com.projects.oms;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.Controllers.ItemController;
import com.projects.oms.configs.CORSConfigurer;
import com.projects.oms.models.JSONResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class OmsApplication extends SpringBootServletInitializer {
	public static final Logger logger =
			LoggerFactory.getLogger(OmsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OmsApplication.class, args);


	}

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OmsApplication.class);
	}
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return CORSConfigurer.addCORSConfig();
    }

//	@RequestMapping("/testurl")
//	public String secondGet(){
//
//		return "Welcome to OMS";
//	}


		static JSONResponse jsonResponse = new JSONResponse();
	@RequestMapping(path = "/testordersystem", method = RequestMethod.GET)
	public static ResponseEntity<JSONResponse> testorder(){
		jsonResponse.setStatus("200");
		jsonResponse.setMessage("Welcome to Oms");
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}
}
