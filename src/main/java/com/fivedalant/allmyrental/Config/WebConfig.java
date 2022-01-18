package com.fivedalant.allmyrental.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
        //registry.addMapping("/**").allowedOrigins("http://localhost:3000");
        //registry.addMapping("/**").allowedOrigins("http://ec2-13-209-238-24.ap-northeast-2.compute.amazonaws.com:5000");
    }
}
