package net.lab1024.smartadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月12日 10:10
 * @Description: 解决CORS跨域访问错误
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// 允许跨域访问的路径
                .allowedOrigins("http://192.168.13.240:9528")// 允许跨域访问的源
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")// 允许请求方法
                .allowedHeaders("*")// 允许头部设置
                .allowCredentials(true)// 是否发送cookie
                .maxAge(168000); // 预检间隔时间
    }
}
