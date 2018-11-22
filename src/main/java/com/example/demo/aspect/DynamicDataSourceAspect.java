package com.example.demo.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.MyAnnotation;
import com.example.demo.handler.DataSourceContextHandler;

@Aspect//标注为切面
/**
 * ||========================================||
 * || 确保切面在事务之前执行                 ||                                                   |
 * ||========================================||
 */
@Order(-1)
@Component //交给spring容器管理
public class DynamicDataSourceAspect {	
	 @Before("@annotation(myAnnotation)")
	 // a 此处必须将注解类型作为参数传递进去，且切入点括号内的值必须与参数名相同，否则启动将报错
	 public void beforeSwitchDS(JoinPoint point,MyAnnotation myAnnotation){
	  // a 切换数据源
	  if(myAnnotation!=null){
		  String dataSource=myAnnotation.dataSource();
		  DataSourceContextHandler.setDB(dataSource);
		  System.out.println(dataSource+"------------------------------------------------------------------");
	  }
	   //a 由于直接将注解传递了进来，所以以下操作就没必要了
	   //a 获得目标类的Class类的对象
	   //Class<?> clazz = point.getTarget().getClass();
	   //a 获得目标方法的名字
	   //String methodName = point.getSignature().getName();
	   //a 获得目标方法的参数
	   //Class<?>[] clazzs=((MethodSignature)point.getSignature()).getParameterTypes();
	   //try {
	   //a 得到目标方法的Method对象
	   //Method method=clazz.getDeclaredMethod(methodName, clazzs);
	   //a 判断是否使用了指定的注解
	   //	if(method.isAnnotationPresent(MyAnnotation.class)) {
	   //		MyAnnotation annotation=method.getAnnotation(MyAnnotation.class);
	   //aa得到注解的值
	   //		dataSource=annotation.dataSource();
	   //	}
	   //} catch (NoSuchMethodException e) {
	   //	e.printStackTrace();
	   //} catch (SecurityException e) {
	   //	e.printStackTrace();
	   //}*/
  }
   @After("@annotation(myAnnotation)")
   public void afterSwitchDS(JoinPoint point,MyAnnotation myAnnotation){
	//a清空当前线程绑的数据源
    DataSourceContextHandler.clearDB();
  }
}
