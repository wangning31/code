package com.zhangmin.restfulservice.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zhangmin.restfulservice.domain.UserInfo;

@Repository("UserLoginDao")
public interface UserLoginDao {

	
	UserInfo getUserInfo(@Param("param") Map param);
	
	UserInfo checkUser(@Param("customerId") String customerId);
}
