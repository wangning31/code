package com.zhangmin.restfulservice.rsp;

import java.math.BigDecimal;
import java.util.List;

import com.zhangmin.restfulservice.CommonRsp;
import com.zhangmin.restfulservice.domain.MonthRecord;

public class ResbillRsp extends  CommonRsp {
	
	private int totalCount;
	
	private BigDecimal totalConsumeAmount;

	private List<MonthRecord> MonthRecords;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getTotalConsumeAmount() {
		return totalConsumeAmount;
	}

	public void setTotalConsumeAmount(BigDecimal totalConsumeAmount) {
		this.totalConsumeAmount = totalConsumeAmount;
	}

	public List<MonthRecord> getMonthRecords() {
		return MonthRecords;
	}

	public void setMonthRecords(List<MonthRecord> monthRecords) {
		MonthRecords = monthRecords;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResbillRsp [totalCount=");
		builder.append(totalCount);
		builder.append(", totalConsumeAmount=");
		builder.append(totalConsumeAmount);
		builder.append(", MonthRecords=");
		builder.append(MonthRecords);
		builder.append("]");
		return builder.toString();
	}
	
	
}
