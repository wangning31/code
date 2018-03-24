package com.zhangmin.restfulservice.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zhangmin.restfulservice.domain.UserInfo;
import com.zhangmin.restfulservice.domain.UserLoginLogInfo;

@Repository("UserLoginDao")
public interface UserLoginDao {

	
	UserInfo getUserInfo(@Param("param") Map param);
	
	UserInfo checkUser(@Param("customerId") String customerId);
	
	UserInfo checkUseByCustomerName(@Param("customerName") String customerName);
	
	UserInfo checkUserByEmail(@Param("email") String email);
	
	void  insert (@Param("param") UserInfo param);
}
