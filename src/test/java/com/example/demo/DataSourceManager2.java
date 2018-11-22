package com.example.demo;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceManager2 extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	protected Object determineCurrentLookupKey() {
	 ConversionService conversionService = new DefaultConversionService();

		return null;
	}
	public static void main(String[] args) {
		DataSourceManager2 dataSourceManager=new DataSourceManager2();
		//dataSourceManager.setDefaultTargetDataSource(defaultTargetDataSource);
	}
	@MyAnnotation(dataSourceType="datasource2")
    public static void test() {
	   
    }*/
}
