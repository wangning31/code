package com.zhangmin.restfulservice.rsp;

import com.zhangmin.restfulservice.CommonRsp;
import com.zhangmin.restfulservice.domain.UserInfo;

public class UserLoginRsp extends  CommonRsp {

	private UserInfo userinfo;

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	
	
}
