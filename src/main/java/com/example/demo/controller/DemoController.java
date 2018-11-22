package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.DemoBean;
import com.example.demo.service.DemoService;
import com.example.demo.utils.PropertiesUtils;

@Controller
public class DemoController {
	@Autowired
	DemoService demoService;
	@RequestMapping(value="/test1")
	public String  index2() {
		List<DemoBean> orgNames=demoService.getOrgNames2();
		for(DemoBean orgName:orgNames) {
			System.out.println(orgName.getOrgName());
		}
		System.out.println(PropertiesUtils.getValue("name")+"----------------------------------------------");
		return "html/sayhello";
	}
	@RequestMapping(value="/test2")
	public String  index3() {
		List<DemoBean> orgNames=demoService.getOrgNames3();
		for(DemoBean orgName:orgNames) {
			System.out.println(orgName.getOrgName());
		}
		//int a=10/0;
		return "html/sayhello";
	}
	@RequestMapping("/save")
	public String saveDate() {
		try {
			demoService.saveData();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "html/sayhello";
	}
}
