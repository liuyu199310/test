/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 使用activiti流程审批添加设备Entity
 * @author liuyu
 * @version 2018-04-20
 */
public class NettestTest extends DataEntity<NettestTest> {
	
	private static final long serialVersionUID = 1L;
	private String proposer;		// 申请人
	private String position;		// 部署位置
	private String devicename;		// devicename
	private String devicetype;		// devicetype
	
	public NettestTest() {
		super();
	}

	public NettestTest(String id){
		super(id);
	}

	@Length(min=0, max=90, message="申请人长度必须介于 0 和 90 之间")
	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	
	@Length(min=0, max=255, message="部署位置长度必须介于 0 和 255 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Length(min=0, max=255, message="devicename长度必须介于 0 和 255 之间")
	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	
	@Length(min=0, max=255, message="devicetype长度必须介于 0 和 255 之间")
	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	
}