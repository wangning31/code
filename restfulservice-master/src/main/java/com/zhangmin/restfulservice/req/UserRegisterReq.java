package com.zhangmin.restfulservice.req;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterReq {
	
	private String customerName;
	
	private String passWord;
	
	private String passWordAgain;
	
	private String email;



	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPassWordAgain() {
		return passWordAgain;
	}

	public void setPassWordAgain(String passWordAgain) {
		this.passWordAgain = passWordAgain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
