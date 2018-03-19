package com.zhangmin.restfulservice.domain;

public class UserLoginLogInfo {
	private String id;
	private String customerId;

	private String IPadress;
	

	private Integer type;
	
	
	private String createTime;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getIPadress() {
		return IPadress;
	}


	public void setIPadress(String iPadress) {
		IPadress = iPadress;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	} 
	
}
