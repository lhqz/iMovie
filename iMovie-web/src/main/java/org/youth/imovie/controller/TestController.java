package org.youth.imovie.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.youth.imovie.pojo.entity.Test;
import org.youth.imovie.service.TestService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("test")
public class TestController {

	@Resource
	private TestService testService;
	
	@RequestMapping("test")
	public String test(Model model,Page<Test> page){
		PageInfo<Test> result = testService.findAll(page);
		model.addAttribute("pageinfo",result);
		return "index";
	}
	
}
