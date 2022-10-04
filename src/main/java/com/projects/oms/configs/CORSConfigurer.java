package com.projects.oms.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CORSConfigurer {

    public static WebMvcConfigurer addCORSConfig(){
        return  new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/newcustomer").allowedMethods(String.valueOf(RequestMethod.POST)).allowedOrigins("http://localhost:3000");
                registry.addMapping("/allcustomers").allowedMethods(String.valueOf(RequestMethod.GET)).allowedOrigins("http://localhost:3000/");
                registry.addMapping("/newitem").allowedMethods(String.valueOf(RequestMethod.POST)).allowedOrigins("http://localhost:3000/");
                registry.addMapping("/allItems").allowedMethods(String.valueOf(RequestMethod.GET)).allowedOrigins("http://localhost:3000/");
                registry.addMapping("/orderitem").allowedMethods(String.valueOf(RequestMethod.POST)).allowedOrigins("http://localhost:3000/");
                registry.addMapping("/allOrderItems").allowedMethods(String.valueOf(RequestMethod.GET)).allowedOrigins("http://localhost:3000/");
                registry.addMapping("/createOrder").allowedMethods(String.valueOf(RequestMethod.POST)).allowedOrigins("http://localhost:3000/");
                registry.addMapping("/getOrders").allowedMethods(String.valueOf(RequestMethod.GET)).allowedOrigins("http://localhost:3000/");
                registry.addMapping("/customer/").allowedMethods(String.valueOf(RequestMethod.DELETE)).allowedOrigins("http://localhost:3000/");
                registry.addMapping("/itemquantity/").allowedMethods(String.valueOf(RequestMethod.PUT)).allowedOrigins("http://localhost:3000/");
            }
        };
    }
}
