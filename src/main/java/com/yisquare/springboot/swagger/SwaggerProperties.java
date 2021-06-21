package com.yisquare.springboot.swagger;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 是否开启swagger，生产环境配置关闭
     */
    private Boolean enable;

}

