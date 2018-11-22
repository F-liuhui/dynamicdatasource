package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * 页面错误处理（拦截器方式,解决springboot错误页面处理打成war包后失效的问题）
 * 如果使用自定义拦截器处理错误页面的话，禁用掉springboot的错误页面自动配置，不然后台会一直有错误提示ErrorMvcAutoConfiguration找不到错误页面
 * @SpringBootApplication(exclude= {ErrorMvcAutoConfiguration.class})
 * 
 * @author admin
 *
 */
//如果使用自定义拦截器处理错误页面的话，禁用掉springboot的错误页面自动配置，不然后台会一直有错误提示
//@SpringBootApplication(/*exclude= {ErrorMvcAutoConfiguration.class}*/)
@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 
	 * springboot 默认在/static,/public,/resources ,/META-INF/resource这四个文件夹下去找静态文件（js,css,图片等），
	 * 所以，一般静态文件全部放在这四个个文件夹下，推荐放在static文件夹下，而templates文件夹下放的是thymeleaf模板的动态html文件，
	 * thymeleaf模板默认的前缀是/templates/,默认的后缀是.html
	 * 
	 */
	//每次响应前都会执行该拦截器的preHandle方法
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		     int status=response.getStatus();
		     switch (status) {
				case 400:
					response.sendRedirect("/errors/400.html");
					break;
				case 404:
					response.sendRedirect("/errors/404.html");
					break;
				case 408:
					response.sendRedirect("/errors/408.html");
					break;
				case 500:
					response.sendRedirect("/errors/500.html");
					break;
				case 503:
					response.sendRedirect("/errors/503.html");
					break;
				default:
					break;
			}
		//显示调用父类的preHandle方法
		return super.preHandle(request, response, handler);
	}
}
