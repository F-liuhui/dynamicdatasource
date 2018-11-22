package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.example.demo.routing.DynamicDataSource;
/**
 * 
   * 数据源相关配置，采用动态数据源的方式解决多数据源的问题
 * 
 * 
 * @author admin
 *
 */
@Configuration
public class DataBaseConfig {
    
	//数据源1
	@Bean(name="MCQDB")
	//该注解可用在方法上，也可用在类上，用在方法上更为细化
	@ConfigurationProperties(prefix="spring.datasource1") 
	public DataSource getDataSourceMcq() {
		return DataSourceBuilder.create().build();
	}
	//数据源2
	@Bean(name="CSDB")
	@ConfigurationProperties(prefix="spring.datasource2")
	public DataSource getDataSourceCs() {
		return DataSourceBuilder.create().build();
	}
	
	//动态数据源
	@Bean
	public DataSource getDynamicDataSource(){
	 DynamicDataSource dynamicDataSource = new DynamicDataSource();
	  // 设置默认数据源
	  dynamicDataSource.setDefaultTargetDataSource(getDataSourceCs());
	  // 配置多数据源
	  Map<Object, Object> dsMap = new HashMap<Object, Object>();
	  dsMap.put("MCQDB", getDataSourceMcq());
	  dsMap.put("CSDB", getDataSourceCs());
	  dynamicDataSource.setTargetDataSources(dsMap);
	  return dynamicDataSource;
	}
	
	
	//SqlSessionFactory(多数据源方式)
	@Bean
	public SqlSessionFactory getSqlSessionFactory() {
		SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
		SqlSessionFactory sqlSessionFactory=null;
		factoryBean.setDataSource(getDynamicDataSource());
		try {
			sqlSessionFactory= factoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
	
	//事务管理者（多数据源方式）
	@Bean
	public DataSourceTransactionManager getDataSourceTransactionManager() {
		return new DataSourceTransactionManager(getDynamicDataSource());
	}
	
}
