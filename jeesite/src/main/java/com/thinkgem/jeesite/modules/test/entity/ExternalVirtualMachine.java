package com.thinkgem.jeesite.modules.test.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;



public class ExternalVirtualMachine extends DataEntity<ExternalVirtualMachine>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String ip;
    
    private String virtualName;
    
    private String constructionNumber;
    
    private String system;
    
    private String deviceId;
    
    private Integer flag;

	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期
	
	private String funcName;
	
	private String pageNo;
	
	
	
    
  

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getVirtualName() {
		return virtualName;
	}

	public void setVirtualName(String virtualName) {
		this.virtualName = virtualName;
	}

	public String getConstructionNumber() {
		return constructionNumber;
	}

	public void setConstructionNumber(String constructionNumber) {
		this.constructionNumber = constructionNumber;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
    
    


	

}
