package com.zhangmin.restfulservice.dao.entry;

import java.math.BigDecimal;

public class MonthRecordEntry
{
    
    private String customerId;
    
    private String beId;
    
    private String consumeTime;
    
    private String resourceId;
    
    private BigDecimal consumeAmount;
    
    private int measuerId;
    
    private String cloudServiceTypeCode;
    
    private BigDecimal balanceTypeDebit;
    
    private BigDecimal balanceTypeBonus;
    
    private BigDecimal balanceTypeCoupon;
    
    

    public String getCustomerId()
    {
        return customerId;
    }



    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }



    public String getBeId()
    {
        return beId;
    }



    public void setBeId(String beId)
    {
        this.beId = beId;
    }



    public String getConsumeTime()
    {
        return consumeTime;
    }



    public void setConsumeTime(String consumeTime)
    {
        this.consumeTime = consumeTime;
    }



    public String getResourceId()
    {
        return resourceId;
    }



    public void setResourceId(String resourceId)
    {
        this.resourceId = resourceId;
    }



    public BigDecimal getConsumeAmount()
    {
        return consumeAmount;
    }



    public void setConsumeAmount(BigDecimal consumeAmount)
    {
        this.consumeAmount = consumeAmount;
    }



    public int getMeasuerId()
    {
        return measuerId;
    }



    public void setMeasuerId(int measuerId)
    {
        this.measuerId = measuerId;
    }



    public String getCloudServiceTypeCode()
    {
        return cloudServiceTypeCode;
    }



    public void setCloudServiceTypeCode(String cloudServiceTypeCode)
    {
        this.cloudServiceTypeCode = cloudServiceTypeCode;
    }



    public BigDecimal getBalanceTypeDebit()
    {
        return balanceTypeDebit;
    }



    public void setBalanceTypeDebit(BigDecimal balanceTypeDebit)
    {
        this.balanceTypeDebit = balanceTypeDebit;
    }



    public BigDecimal getBalanceTypeBonus()
    {
        return balanceTypeBonus;
    }



    public void setBalanceTypeBonus(BigDecimal balanceTypeBonus)
    {
        this.balanceTypeBonus = balanceTypeBonus;
    }



    public BigDecimal getBalanceTypeCoupon()
    {
        return balanceTypeCoupon;
    }



    public void setBalanceTypeCoupon(BigDecimal balanceTypeCoupon)
    {
        this.balanceTypeCoupon = balanceTypeCoupon;
    }



    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("MonthRecordEntry [customerId=");
        builder.append(customerId);
        builder.append(", beId=");
        builder.append(beId);
        builder.append(", consumeTime=");
        builder.append(consumeTime);
        builder.append(", resourceId=");
        builder.append(resourceId);
        builder.append(", consumeAmount=");
        builder.append(consumeAmount);
        builder.append(", measuerId=");
        builder.append(measuerId);
        builder.append(", cloudServiceTypeCode=");
        builder.append(cloudServiceTypeCode);
        builder.append(", balanceTypeDebit=");
        builder.append(balanceTypeDebit);
        builder.append(", balanceTypeBonus=");
        builder.append(balanceTypeBonus);
        builder.append(", balanceTypeCoupon=");
        builder.append(balanceTypeCoupon);
        builder.append("]");
        return builder.toString();
    }


    
}
