/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.NettestTest;


/**
 * 使用activiti流程审批添加设备DAO接口
 * @author liuyu
 * @version 2018-04-20
 */
@MyBatisDao
public interface NettestTestDao extends CrudDao<NettestTest> {
	
}