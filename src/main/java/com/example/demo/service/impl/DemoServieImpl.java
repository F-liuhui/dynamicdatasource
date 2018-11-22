package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.annotation.MyAnnotation;
import com.example.demo.domain.DemoBean;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.service.DemoService;


@Service
public class DemoServieImpl implements DemoService {
	
	@Autowired
	DemoMapper mapper;
	@Override
	//动态数据源
	@MyAnnotation(dataSource="CSDB")
	public List<DemoBean> getOrgNames() {
		return mapper.getOrgName();
	}
	
	@Override
	//动态数据源
	@MyAnnotation(dataSource="MCQDB")
	//启用缓存
	@Cacheable(cacheNames="cacheStrategy1")
	public List<DemoBean> getOrgNames2() {
		System.out.println("调用了数据库---------3232323232---------------------------------------------------------------------------------------------------------");
		return mapper.getOrgName();
	}
	
	@Override
	public List<DemoBean> getOrgNames3() {
		return mapper.getOrgName();
	}

	@MyAnnotation(dataSource="MCQDB")
    @Transactional(rollbackFor= {Exception.class})
	public void saveData() {
		String bean="中国石油勘探院";
		mapper.saveDataA(bean);
		mapper.saveDataB(bean);
	}

}
