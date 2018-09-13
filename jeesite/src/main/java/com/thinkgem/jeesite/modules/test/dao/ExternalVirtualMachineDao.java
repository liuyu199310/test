package com.thinkgem.jeesite.modules.test.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.ExternalVirtualMachine;




@MyBatisDao
public interface ExternalVirtualMachineDao extends CrudDao<ExternalVirtualMachine>{

	public List<Map<String, Object>> externalVirtalListUnsynchronizedData(ExternalVirtualMachine externalVirtualMachine);

	public List<Map<String, Object>> externalVirtalListData(ExternalVirtualMachine externalVirtualMachine);

	public void cancelExternalVirtalListData(ExternalVirtualMachine externalVirtualMachine);

	public void cancelALLVirtalListData(ExternalVirtualMachine externalVirtualMachine);

	public Integer returnInterger(ExternalVirtualMachine externalVirtualMachine);

}
