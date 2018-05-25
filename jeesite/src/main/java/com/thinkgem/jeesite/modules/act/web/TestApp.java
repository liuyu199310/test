package com.thinkgem.jeesite.modules.act.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "${adminPath}/test")
public class TestApp {
	
	
    @RequestMapping(value = {"detail"})
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("modules/test/testApp");
		return mv;
	}
}
