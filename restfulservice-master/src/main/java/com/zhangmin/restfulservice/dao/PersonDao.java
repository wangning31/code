package com.zhangmin.restfulservice.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zhangmin.restfulservice.domain.PersonInfo;

@Repository("PersonDao")
public interface PersonDao {

	void  insert (@Param("param") PersonInfo param);
}
