package com.zhangmin.restfulservice.req;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;	

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginReq {

	private String userName;
	
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
