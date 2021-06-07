package com.yisquare.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@EnableCaching
public class SystemManagerApplication {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SystemManagerApplication.class,args);

    }

}
