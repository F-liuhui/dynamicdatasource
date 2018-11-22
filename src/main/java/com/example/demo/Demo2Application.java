package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
/**
 * 
 * SBA方式的spring-boot-admin客户端
 * @author admin
 *
 */

//如果使用自定义拦截器处理错误页面的话，禁用掉springboot的错误页面自动配置，不然后台会一直有错误提示
@SpringBootApplication(/*exclude= {ErrorMvcAutoConfiguration.class}*/)
@EnableCaching
public class Demo2Application {
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}
}
