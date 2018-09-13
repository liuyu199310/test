package com.thinkgem.jeesite.modules.test.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.BootStrapDataGrid;

import com.thinkgem.jeesite.modules.test.entity.ExternalVirtualMachine;
import com.thinkgem.jeesite.modules.test.service.PageMybatisService;

@Controller
@RequestMapping(value = "${adminPath}/test/pageMybatis")
public class PageMybatisController extends BaseController{
	
	@Autowired
	private PageMybatisService pageMybatisService;
	

	
	@ModelAttribute
	public ExternalVirtualMachine get(@RequestParam(required=false) String id){
		ExternalVirtualMachine externalVirtualMachine = null;
		if (StringUtils.isNotBlank(id)){
			externalVirtualMachine = pageMybatisService.get(id);

		}
		if (externalVirtualMachine == null){
			externalVirtualMachine = new ExternalVirtualMachine();
			// 设置默认时间范围，默认当前月
			if (externalVirtualMachine.getBeginDate() == null){
				externalVirtualMachine.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
			}
			if (externalVirtualMachine.getEndDate() == null){
				externalVirtualMachine.setEndDate(DateUtils.addMonths(externalVirtualMachine.getBeginDate(), 1));
			}
		}
		externalVirtualMachine.setFuncName("myPage");
		externalVirtualMachine.setPageNo("20");
	
		
		
		return externalVirtualMachine;
	}
	
	
	

	@RequestMapping(value = {"list", ""})
	public String list( HttpServletRequest request, ExternalVirtualMachine externalVirtualMachine,HttpServletResponse response, Model model) {

		Page<ExternalVirtualMachine> midPage=new Page<ExternalVirtualMachine>(request, response);
		midPage.setFuncName(externalVirtualMachine.getFuncName());
		midPage.setPageSize(4);
        Page<ExternalVirtualMachine> page = pageMybatisService.findPage(midPage, externalVirtualMachine); 
        model.addAttribute("page", page);
		return "modules/test/pageMybatis";
	}
	
	
	@RequestMapping(value = { "detail" })
	public ModelAndView detail(){
		
		ModelAndView modelAndView =new ModelAndView("modules/test/pageList2");
		return modelAndView;
		
	}
	
	@RequestMapping(value = { "getNettestWebTest" })
	@ResponseBody
	public BootStrapDataGrid getNettestWebTest(ExternalVirtualMachine externalVirtualMachine,HttpServletRequest request,HttpServletResponse response) {

		BootStrapDataGrid dataGrid = new BootStrapDataGrid();
		Page<ExternalVirtualMachine> page = pageMybatisService.findPage(new Page<ExternalVirtualMachine>(request, response), externalVirtualMachine); 
		dataGrid=pageMybatisService.getNettestWebTest(page);

		return dataGrid;
	}
	
	
}
