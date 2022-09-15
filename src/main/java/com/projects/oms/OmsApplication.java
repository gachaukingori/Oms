package com.projects.oms;

import com.projects.oms.Controllers.CustomerController;
import com.projects.oms.Controllers.ItemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
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

	@RequestMapping("/testurl")
	public String secondGet(){

		return "Welcome to OMS";
	}


	@RequestMapping(path = "/testordersystem", method = RequestMethod.GET)
	public String testorder(){

		return "This is an order management system";
	}
}
