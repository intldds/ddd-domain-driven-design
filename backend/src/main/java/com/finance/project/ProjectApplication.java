package com.finance.project;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonService;
import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@RestController
public class ProjectApplication implements ApplicationRunner {

    @Autowired
    CreatePersonService createPersonService;
    @Autowired
    CreateGroupService createGroupService;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        System.out.println("Initializing Database");
        Bootstrapping.loadData(createPersonService, createGroupService);
        System.out.println("Database Created");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }


}

