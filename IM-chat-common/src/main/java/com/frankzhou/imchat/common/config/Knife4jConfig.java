package com.frankzhou.imchat.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author This.FrankZhou
 * @version 1.0
 * @description Swagger+Knife4j接口文档配置
 * @date 2023-04-08
 */
@Configuration
@Profile({"dev"})
public class Knife4jConfig {

    /**
     * 接口文档配置
     * 文档地址:http://ip:port/doc.html
     */
    @Bean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("im-chat")
                        .description("即時通訊聊天室")
                        .version("1.0")
                        .build())
                .select()
                // 指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.frankzhou.imchat.*.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}