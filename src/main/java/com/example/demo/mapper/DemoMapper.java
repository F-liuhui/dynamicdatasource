package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.DemoBean;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface DemoMapper {
	public List<DemoBean> getOrgName();
	
	public int saveDataA(@Param("demoBean") String demoBean);
	public int saveDataB(@Param("demoBean") String demoBean);
}


