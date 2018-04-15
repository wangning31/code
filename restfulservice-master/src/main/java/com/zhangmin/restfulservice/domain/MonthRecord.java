package com.zhangmin.restfulservice.domain;

import java.math.BigDecimal;
import java.util.List;

public class MonthRecord {
	

	private String customerId;
	
	private String beId;
	
	private String consumeTime;
	
	private String resourceId;
	
	private String cloudServiceTypeCode;
	
	private BigDecimal disCountAmount;
	
	private BigDecimal cousumeAmount;
	
	private int measureId;
	
	private List<ConsumeDetail> consumeDetail;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public String getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getCloudServiceTypeCode() {
		return cloudServiceTypeCode;
	}

	public void setCloudServiceTypeCode(String cloudServiceTypeCode) {
		this.cloudServiceTypeCode = cloudServiceTypeCode;
	}

	public BigDecimal getDisCountAmount() {
		return disCountAmount;
	}

	public void setDisCountAmount(BigDecimal disCountAmount) {
		this.disCountAmount = disCountAmount;
	}

	public BigDecimal getCousumeAmount() {
		return cousumeAmount;
	}

	public void setCousumeAmount(BigDecimal cousumeAmount) {
		this.cousumeAmount = cousumeAmount;
	}

	public int getMeasureId() {
		return measureId;
	}

	public void setMeasureId(int measureId) {
		this.measureId = measureId;
	}

	public List<ConsumeDetail> getConsumeDetail() {
		return consumeDetail;
	}

	public void setConsumeDetail(List<ConsumeDetail> consumeDetail) {
		this.consumeDetail = consumeDetail;
	}

	public List<DiscountDetail> getDiscountDetail() {
		return discountDetail;
	}

	public void setDiscountDetail(List<DiscountDetail> discountDetail) {
		this.discountDetail = discountDetail;
	}

	private List<DiscountDetail> discountDetail;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonthRecord [customerId=");
		builder.append(customerId);
		builder.append(", beId=");
		builder.append(beId);
		builder.append(", consumeTime=");
		builder.append(consumeTime);
		builder.append(", resourceId=");
		builder.append(resourceId);
		builder.append(", cloudServiceTypeCode=");
		builder.append(cloudServiceTypeCode);
		builder.append(", disCountAmount=");
		builder.append(disCountAmount);
		builder.append(", cousumeAmount=");
		builder.append(cousumeAmount);
		builder.append(", measureId=");
		builder.append(measureId);
		builder.append(", consumeDetail=");
		builder.append(consumeDetail);
		builder.append(", discountDetail=");
		builder.append(discountDetail);
		builder.append("]");
		return builder.toString();
	}
	
	
}
