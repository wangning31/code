package com.zhangmin.restfulservice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zhangmin.restfulservice.dao.entry.MonthRecordEntry;

@Repository("ResbillTempDao")
public interface ResbillTempDao
{
    
    List<MonthRecordEntry> query(@Param("param") Map param);
    
    int count(@Param("param") Map param);
}
