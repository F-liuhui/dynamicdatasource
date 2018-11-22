package com.example.demo.handler;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
/**
 * 
   *全局统一页面错误处理(springboot自带的ErrorPageRegistrar方式处理，该方式有可能在打成war包后失效，待测试)
 * 
 * 
 * @author lh
 *
 */
 
@Component
public class ErrorPageHandler implements ErrorPageRegistrar{
	/**
	 * 
	 * springboot 默认在/static,/public,/resources ,/META-INF/resource这四个文件夹下去找静态文件（js,css,图片等），
	 * 所以，一般静态文件全部放在这四个个文件夹下，推荐放在static文件夹下，而templates文件夹下放的是thymeleaf模板的动态html文件，
	 * thymeleaf模板默认的前缀是/templates/,默认的后缀是.html
	 * 
	 * 
	 */
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage[] errorPages ={
				//HttpStatus为springboot内部的枚举类，定义了响应状态码
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errors/500.html"),
                new ErrorPage(HttpStatus.NOT_FOUND, "/errors/404.html"),
                new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/errors/503.html"),
                new ErrorPage(HttpStatus.REQUEST_TIMEOUT, "/errors/408.html"),
                new ErrorPage(HttpStatus.BAD_REQUEST, "/errors/400.html")
        };
		//将自定义拦截器添加到拦截器管理器中
		registry.addErrorPages(errorPages);
	}
}
