package com.thinkgem.jeesite.modules.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.BootStrapDataGrid;
import com.thinkgem.jeesite.modules.test.dao.ExternalVirtualMachineDao;
import com.thinkgem.jeesite.modules.test.entity.ExternalVirtualMachine;


@Service
@Transactional(readOnly = true)
public class PageMybatisService extends CrudService<ExternalVirtualMachineDao, ExternalVirtualMachine>{

	public BootStrapDataGrid getNettestWebTest(Page<ExternalVirtualMachine> page) {
		// TODO Auto-generated method stub
		BootStrapDataGrid bootStrapDataGrid=new BootStrapDataGrid();
		bootStrapDataGrid.setTotal(page.getCount());
		bootStrapDataGrid.setRows(page.getList());
		return bootStrapDataGrid;
	}

}
