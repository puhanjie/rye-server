package com.puhj.rye.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author puhanjie
 * @description Web配置类
 * @create 2022/12/14 21:32
 * @modify 2022/12/14 21:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/res/**")   // 客户端访问静态资源前缀
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/res/"); // 映射的本地静态资源地址
    }

}
