package com.projects.oms;


import com.projects.oms.configs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

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

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return CORSConfigurer.addCORSConfig();
    }




}
