package com.zhangmin.restfulservice.resource;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.util.StringUtils;
import com.zhangmin.restfulservice.common.util.DateUtilService;
import com.zhangmin.restfulservice.dao.ResbillDao;
import com.zhangmin.restfulservice.dao.ResbillTempDao;
import com.zhangmin.restfulservice.dao.entry.MonthRecordEntry;
import com.zhangmin.restfulservice.domain.ConsumeDetail;
import com.zhangmin.restfulservice.domain.MonthRecord;
import com.zhangmin.restfulservice.req.ResbillReq;
import com.zhangmin.restfulservice.rsp.ResbillRsp;

/*
假设当天是4月1日,即当月的第一天。这时候查询资源账单有两种场景：

4月1日查询时3月的账单可能已经计算完成，也可能未计算完成，分两个场景，该两个场景都从临时表查询

1 查询3月份的资源账单


(1)若4月1日的资源账单计算任务已经计算完成，即3月份整个月份的数据已经计算完成，不需要再查询本地，直接查询临时表的数据 consumetime='2018-03',inserTime='2018-04-01'


(2)若4月1日的资源账单计算任务尚未完成，即3月1号至3月30号的资源账单计算完成，缺少3月31日的资源账单数据，查询要查两个表：临时表和mpp表
 
 查询临时表时consumetime='2018-03',inserTime='2018-03-31'
 
 查询mmp表consumeday='2018-03-31',calcount取最大值。此场景下mpp表只查询1天的数据
 
 然后将两个查询结果汇总一起。由于临时表是按月汇总的，mpp表是按天汇总，需要加mpp的数据累加到临时表而不是直接追加
   方案：将两个结果组装成两个map:map1,map2 key为资源信息的联合key
   新建一个list
         遍历map1,如果map2存在一样的key值,将两个结果汇总存入到list中，map2可以移除此key值，
                  如果map2不存在一样的key值，将map1的value存入到list中
         循环遍历完成后，如果map2人存在值，将map2的value转化存入到list中         
   
 

2 查询4月份的资源账单

1)此时无论4月1日的计算结果有没有计算完成，临时表都不会存在4月份的资源账单。当然也可以去查临时表，只是结果为空

2)查询mpp表consumeday='2018-04-01',calcount取最大值。此场景下mpp表只查询1天的数据

汇总结果方案可以参考上面





假设当天不是4月1日，即不是当月的第一天

1 查询3月份的数据(历史月份数据)，直接查询正式表


2 查询4月份的数据 假设查询当天日志为4月3号

方案1:4月3号查询，4月2日的计算任务已经完成，即4月2号版本的数据已经存在，此时计算的结果4月1号当天的数据
    
    mpp表需要查询最近2天的数据即4月2号,4月3号的数据。这两天的数据可能要分两次查询，暂时没有想到好的sql语句可以一次性查询2天的数据

     
方案2:4月3号查询，判断临时表是否已经存在4月3号的版本，即4月1号至4月2号的版本。如果存在mpp只查询4月3号的数据
   若不存在，则需要查询最近2天的数据，即   即4月2号,4月3号的数据。
   */
@Path("/resbill")
public class ResBillResource
{
    
    private static final Logger LOGGER =
        LoggerFactory.getLogger(ResBillResource.class);
    
    @Autowired
    ResbillDao resbillDao;
    
    @Autowired
    ResbillTempDao resbillTempDao;
    
    @Path("/query")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public ResbillRsp query(@Context HttpServletRequest request, ResbillReq req)
    {
        LOGGER.info("query resbill start,the req is:" + req.toString());
        ResbillRsp resbillRsp = new ResbillRsp();
        if (StringUtils.isEmpty(req.getBeId())
            || StringUtils.isEmpty(req.getCustomerId())
            || StringUtils.isEmpty(req.getConsumeTime())
            || StringUtils.isEmpty(req.getPayMethod()))
        {
            resbillRsp.setRetCode("-1");
            resbillRsp.setRetText("参数不能为空");
            
        }
        Map<String, String> param = new HashMap<String, String>();
        param.put("customerId", req.getCustomerId());
        param.put("beId", req.getBeId());
        param.put("consumeTime", req.getConsumeTime());
        param.put("resourceId", req.getResourceId());
        param.put("cloudServiceTypeCode", req.getCloudServiceTypeCode());
        String yesterDay = DateUtilService.getYesterDay();
        // yesterDay="2018-03-31";
        LOGGER.info("query resbill start,the yesterDay is:" + yesterDay);
        String localThreadTime = yesterDay.substring(0, 7);
        LOGGER.info(
            "query resbill start,the localThreadTime is:" + localThreadTime);
        
        String currentDay = DateUtilService.getDate();
        LOGGER.info("query resbill start,the currentDay is:" + currentDay);
        List<MonthRecordEntry> monthRecordList =
            new ArrayList<MonthRecordEntry>();
        if (req.getConsumeTime().compareTo(localThreadTime) >= 0)
        {
            param.put("insertTime", currentDay);
            LOGGER.info("query resbill start,query from t_query_resbill_temp");
            
            LOGGER.info("query resbill start,the param is:" + param.toString());
            int count = resbillTempDao.count(param);
            if (count == 0)
            {
                param.put("insertTime", yesterDay);
                count = resbillTempDao.count(param);
            }
            LOGGER.info("query resbill start,the count is:" + count);
            LOGGER.info("query resbill start,the param is:" + param.toString());

            // 当月临时表查询完毕，查询mpp表
            
            // 判断当前时间是否当月第一天
            
            String firstday = DateUtilService.getFirstDayOfMonth();
            if (firstday.equalsIgnoreCase(currentDay))
            {
                //判断查询的账单时间 如果
                
                if(req.getConsumeTime().compareTo(localThreadTime)==0)
                {
                    //当前日期假如说4月1日,临界时间说2018-03,如果consumeTime=2018-03  判断4月1日的计算任务是否已经完成, insertTime='2018-03-31'.如果已经计算完成 直接查询临时表的数据
                    if(param.get("insertTime").equalsIgnoreCase(currentDay))
                    {
                        monthRecordList = resbillTempDao.query(param);
                    }
                    //如果4月1日的计算任务没有完成,insertTime='2018-03-31',mpp表还需要查询2018-03-31当天的数据
                    else
                    {  // 查询临时表
                        monthRecordList = resbillTempDao.query(param);
                        
                       //查询mpp表
                        
                        List<String> consumeTimeList=new ArrayList<String>();
                        consumeTimeList.add(param.get("insertTime"));
                        //查询mpp表
                        
                        //把两个查询结果组合起来
                    }
                }
                else
                {
                  //当前日期假如说4月1日,consumeTime='2018-04' 4月的账单肯定没有,不需要查询临时表,直接查询mpp表
                    List<String> consumeTimeList=new ArrayList<String>();
                    consumeTimeList.add("2018-04-01");
                    //查询mpp表
                }
            }
            else
            {
                //如果不是当月的第一天,临时表已经存在当月的数据  判断当天的数据是否已经计算完成 如果已经计算完成  mpp表查询当天的数据
                
                 //如果没有计算完 mpp 查询最近2天的数据
                
            }
        }
        else
        {
            LOGGER.info("query resbill start,query from t_query_resbill");
            
            monthRecordList = resbillDao.query(param);
            LOGGER.info(monthRecordList.get(0).toString());
        }
        List<MonthRecord> monthRecords = new ArrayList<MonthRecord>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(monthRecordList))
        {
            
            for (MonthRecordEntry monthRecordEntry : monthRecordList)
            {
                MonthRecord monthRecord = new MonthRecord();
                buildRsp(monthRecord, monthRecordEntry);
                if (!StringUtils.isEmpty(param.get("resourceId")))
                {
                    LOGGER.info("获取折扣信息");
                }
                totalAmount = totalAmount.add(monthRecord.getCousumeAmount());
                monthRecords.add(monthRecord);
            }
        }
        
        resbillRsp.setRetCode("0");
        resbillRsp.setRetText("成功");
        resbillRsp.setTotalConsumeAmount(totalAmount);
        resbillRsp.setTotalCount(monthRecords.size());
        resbillRsp.setMonthRecords(monthRecords);
        return resbillRsp;
        
    }
    
    private void buildRsp(MonthRecord monthRecord,
        MonthRecordEntry monthRecordEntry)
    {
        monthRecord.setCustomerId(monthRecordEntry.getCustomerId());
        monthRecord.setBeId(monthRecordEntry.getBeId());
        monthRecord.setCloudServiceTypeCode(
            monthRecordEntry.getCloudServiceTypeCode());
        monthRecord.setConsumeTime(monthRecordEntry.getConsumeTime());
        monthRecord.setMeasureId(monthRecordEntry.getMeasuerId());
        monthRecord.setResourceId(monthRecordEntry.getResourceId());
        
        List<ConsumeDetail> consumeDetail = new ArrayList<ConsumeDetail>();
        buildConsumeDetail(consumeDetail, monthRecordEntry);
        monthRecord.setConsumeDetail(consumeDetail);
        monthRecord.setCousumeAmount(monthRecordEntry.getConsumeAmount());
    }
    
    private void buildConsumeDetail(List<ConsumeDetail> consumeDetail,
        MonthRecordEntry monthRecordEntry)
    {
        ConsumeDetail balanceTypeDebit = new ConsumeDetail();
        balanceTypeDebit.setBalanceTypeId("BALANCE_TYPE_DEBIT");
        balanceTypeDebit
            .setConsumeAmount(monthRecordEntry.getBalanceTypeDebit());
        balanceTypeDebit.setMeasureId(monthRecordEntry.getMeasuerId());
        consumeDetail.add(balanceTypeDebit);
        
        ConsumeDetail balanceTypeBonus = new ConsumeDetail();
        balanceTypeBonus.setBalanceTypeId("BALANCE_TYPE_BONUS");
        balanceTypeBonus
            .setConsumeAmount(monthRecordEntry.getBalanceTypeBonus());
        balanceTypeBonus.setMeasureId(monthRecordEntry.getMeasuerId());
        consumeDetail.add(balanceTypeBonus);
        
        ConsumeDetail balanceTypeCoupon = new ConsumeDetail();
        balanceTypeCoupon.setBalanceTypeId("BALANCE_TYPE_COUPON");
        balanceTypeCoupon
            .setConsumeAmount(monthRecordEntry.getBalanceTypeCoupon());
        balanceTypeCoupon.setMeasureId(monthRecordEntry.getMeasuerId());
        consumeDetail.add(balanceTypeCoupon);
    }
}
