package org.youth.imovie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class Test {

	@RequestMapping("test")
	public String test(){
		System.out.println("qqqqqqqqqqqqqqq");
		return "index";
	}
	
}
