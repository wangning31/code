package com.zhangmin.restfulservice.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zhangmin.restfulservice.domain.UserLoginLogInfo;

@Repository("UserLoginLogDao")
public interface UserLoginLogDao {

	
	void  insert (@Param("param") UserLoginLogInfo param);
}
