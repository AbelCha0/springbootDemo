package com.yisquare.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SystemManagerApplication {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SystemManagerApplication.class,args);

    }

}
