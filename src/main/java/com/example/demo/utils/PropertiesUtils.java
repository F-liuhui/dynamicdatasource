package com.example.demo.utils;
import java.io.IOException;
import java.util.Properties;

/**
 * 类描述：用于读取和处理Properties文件的工具类
 * 
 * 开发时间：2017年11月29日09:53:29
 * 
 * @author 刘辉
 *
 */
public class PropertiesUtils {
 
	/**
	 * 方法描述：根据info.Properties文件的键获取值
	 * 
	 * @param key
	 * 
	 * @return
	 */
	public static String getValue(String key){
		String returnValue="";
		if(key!=null && !key.equals("")){
			Properties p=System.getProperties();
			try {
				p.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("info.properties"));
				returnValue=p.getProperty(key);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnValue.trim();
	}
   public static void main(String [] args){
	   System.out.println("");
   }
}
