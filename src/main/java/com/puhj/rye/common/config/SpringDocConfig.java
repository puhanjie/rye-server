package com.puhj.rye.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author puhanjie
 * @description SpringDoc配置类
 * @create 2023-07-19
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI createOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rye接口文档")
                        .version("V1.0.0")
                        .description("Rye Restful接口描述文档")
                        .license(new License()
                                .name("rye license")
                                .url("https://github.com/puhanjie/rye-server"))
                        .contact(new Contact()
                                .name("puhanjie")
                                .email("hanjie.pu@outlook.com")
                                .url("https://github.com/puhanjie")))
                .externalDocs(new ExternalDocumentation()
                        .description("Rye服务端项目rye-server详情")
                        .url("https://github.com/puhanjie/rye-server"))
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .name("认证")
                                        .type(SecurityScheme.Type.HTTP)
                                        .description("JWT认证")
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")));
    }

}
