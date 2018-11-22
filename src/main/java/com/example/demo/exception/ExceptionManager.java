package com.example.demo.exception;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
   * 全局统一异常处理，使用@ControllerAdvice注解标记为全局异常处理类，只有在使用了@RequestMapping注解的方法上使用该异常处理类
   * 使用@ExceptionHandler的方法为在异常发生时具体执行的方法
 * 
 * @author admin
 *
 */
//@ControllerAdvice
public class ExceptionManager extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@ExceptionHandler(value=Exception.class)
	public String doExceptHandler(Exception e,HttpServletRequest seq,HttpServletResponse resp) {
		/*ModelAndView modelAndView = new ModelAndView();
		  modelAndView.setViewName("/error/500.html");
		  return modelAndView;*/
		//System.out.println(resp.getStatus()+"-------------------------------------------");
		return "/error/500.html";
	}
}
