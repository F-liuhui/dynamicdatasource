package com.example.demo.routing;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.example.demo.handler.DataSourceContextHandler;
/**
 * spring 提供的动态数据源路由选择类，继承该类，
 * 实现determineCurrentLookupKey方法，得到数据源
 * @author admin
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {	
		return DataSourceContextHandler.getDB();
	}
}
