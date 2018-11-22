package com.example.demo.config;

import javax.annotation.Resource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.ErrorPageInterceptor;


/**
 *  自定义web拦截器配置类，继承实现WebMvcConfigurer接口 用于添加自定义拦截器到spring拦截器管理类中
 *   
 * @author admin
 *
 */
//@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
	    
	    //注入自定义拦截器
	     @Resource
	     ErrorPageInterceptor errorPageInterceptor;
	    /**
	              * 将自定义拦截器添加到springboot拦截器管理类中
	     * InterceptorRegistry 为spring拦截器管理类
	     */
	    public void addInterceptors(InterceptorRegistry registry) {
	    	// InterceptorRegistration  registration=registry.addInterceptor(errorPageInterceptor);
	    	// registration.addPathPatterns(需要拦截的访问路径) ，如果不设置的话默认在响应前和响应后使用拦截器;
	    	registry.addInterceptor(errorPageInterceptor);
	    	//可以添加需要拦截的访问路径 例：registry.addInterceptor(errorPageInterceptor).addPathPatterns("/**");
	        //显示的调用接口中的默认方法,jdk1.8之后的新特性，接口中可以定义默认方法和今天方法
	        //WebMvcConfigurer.super.addInterceptors(registry);
	    }
}
