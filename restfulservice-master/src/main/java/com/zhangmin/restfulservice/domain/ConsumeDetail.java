package com.zhangmin.restfulservice.domain;

import java.math.BigDecimal;

public class ConsumeDetail {
	

	private String balanceTypeId;
	
	private BigDecimal consumeAmount;
	
	
	private int measureId;


	public String getBalanceTypeId() {
		return balanceTypeId;
	}


	public void setBalanceTypeId(String balanceTypeId) {
		this.balanceTypeId = balanceTypeId;
	}


	public BigDecimal getConsumeAmount() {
		return consumeAmount;
	}


	public void setConsumeAmount(BigDecimal consumeAmount) {
		this.consumeAmount = consumeAmount;
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
		builder.append("ConsumeDetail [balanceTypeId=");
		builder.append(balanceTypeId);
		builder.append(", consumeAmount=");
		builder.append(consumeAmount);
		builder.append(", measureId=");
		builder.append(measureId);
		builder.append("]");
		return builder.toString();
	}
	
}
