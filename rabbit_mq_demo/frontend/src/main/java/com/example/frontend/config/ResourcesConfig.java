package com.example.frontend.config;

import constant.RabbitConstants;
import mapper.DogMapper;
import mapper.DogMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class ResourcesConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/static/images/**")
                .addResourceLocations("/static/images/");
    }
    @Bean
    public RabbitConstants rabbitConstants(){
        return new RabbitConstants();
    }
    @Bean
    DogMapper dogMapper(){
        return new DogMapperImpl();
    }
}
