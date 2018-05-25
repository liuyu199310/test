/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.NettestTest;
import com.thinkgem.jeesite.modules.oa.service.NettestTestService;
import com.thinkgem.jeesite.common.utils.StringUtils;


/**
 * 使用activiti流程审批添加设备Controller
 * @author liuyu
 * @version 2018-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/test/nettestTest")
public class NettestTestController extends BaseController {

	@Autowired
	private NettestTestService nettestTestService;
	
	@ModelAttribute
	public NettestTest get(@RequestParam(required=false) String id) {
		NettestTest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nettestTestService.get(id);
		}
		if (entity == null){
			entity = new NettestTest();
		}
		return entity;
	}
	
	@RequiresPermissions("test:nettestTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(NettestTest nettestTest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NettestTest> page = nettestTestService.findPage(new Page<NettestTest>(request, response), nettestTest); 
		model.addAttribute("page", page);
		return "modules/test/nettestTestList";
	}

	@RequiresPermissions("test:nettestTest:view")
	@RequestMapping(value = "form")
	public String form(NettestTest nettestTest, Model model) {
		model.addAttribute("nettestTest", nettestTest);
		return "modules/test/nettestTestForm";
	}

	@RequiresPermissions("test:nettestTest:edit")
	@RequestMapping(value = "save")
	public String save(NettestTest nettestTest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nettestTest)){
			return form(nettestTest, model);
		}
		nettestTestService.save(nettestTest);
		addMessage(redirectAttributes, "保存测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/nettestTest/?repage";
	}
	
	@RequiresPermissions("test:nettestTest:edit")
	@RequestMapping(value = "delete")
	public String delete(NettestTest nettestTest, RedirectAttributes redirectAttributes) {
		nettestTestService.delete(nettestTest);
		addMessage(redirectAttributes, "删除测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/nettestTest/?repage";
	}

}