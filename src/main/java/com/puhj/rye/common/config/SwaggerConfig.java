package com.puhj.rye.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author puhanjie
 * @description Swagger配置类
 * @create 2022-11-30
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${swagger.swaggerEnabled}")
    private Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描路径包
                .apis(RequestHandlerSelectors.basePackage("com.puhj.rye.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Rye接口文档")
                .description("REST接口")
                // 作者信息
                .contact(new Contact("puhanjie", "", "hanjie.pu@outlook.com"))
                .version("1.0")
                .build();
    }

}
