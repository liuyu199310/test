/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.dao.NettestTestDao;
import com.thinkgem.jeesite.modules.oa.entity.NettestTest;


/**
 * 使用activiti流程审批添加设备Service
 * @author liuyu
 * @version 2018-04-20
 */
@Service
@Transactional(readOnly = true)
public class NettestTestService extends CrudService<NettestTestDao, NettestTest> {

	public NettestTest get(String id) {
		return super.get(id);
	}
	
	public List<NettestTest> findList(NettestTest nettestTest) {
		return super.findList(nettestTest);
	}
	
	public Page<NettestTest> findPage(Page<NettestTest> page, NettestTest nettestTest) {
		return super.findPage(page, nettestTest);
	}
	
	@Transactional(readOnly = false)
	public void save(NettestTest nettestTest) {
		super.save(nettestTest);
	}
	
	@Transactional(readOnly = false)
	public void delete(NettestTest nettestTest) {
		super.delete(nettestTest);
	}
	
}