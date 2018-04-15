package com.zhangmin.restfulservice.domain;

import java.math.BigDecimal;

public class DiscountDetail {
	
	private String promotionType;
	
	private BigDecimal disCountAmount;
	
	
	private int measureId;


	public String getPromotionType() {
		return promotionType;
	}


	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}


	public BigDecimal getDisCountAmount() {
		return disCountAmount;
	}


	public void setDisCountAmount(BigDecimal disCountAmount) {
		this.disCountAmount = disCountAmount;
	}


	public int getMeasureId() {
		return measureId;
	}


	public void setMeasureId(int measureId) {
		this.measureId = measureId;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiscountDetail [promotionType=");
		builder.append(promotionType);
		builder.append(", disCountAmount=");
		builder.append(disCountAmount);
		builder.append(", measureId=");
		builder.append(measureId);
		builder.append("]");
		return builder.toString();
	}

	
}
