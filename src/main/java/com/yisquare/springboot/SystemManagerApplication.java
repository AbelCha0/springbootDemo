package com.yisquare.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@EnableCaching
public class SystemManagerApplication {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SystemManagerApplication.class,args);

    }

    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer() {

        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
            @Override
            public void customize(ConfigurableServletWebServerFactory factory) {

                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
                factory.addErrorPages(error404Page);
            }
        };
    }

}
