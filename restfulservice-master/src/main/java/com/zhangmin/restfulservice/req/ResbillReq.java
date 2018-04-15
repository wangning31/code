package com.zhangmin.restfulservice.req;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResbillReq {

	private String customerId;
	
	private String beId;
	
	private String consumeTime;
	
	private String resourceId;
	
	private String cloudServiceTypeCode;
	
	private String payMethod;

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

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResbillReq [customerId=");
		builder.append(customerId);
		builder.append(", beId=");
		builder.append(beId);
		builder.append(", consumeTime=");
		builder.append(consumeTime);
		builder.append(", resourceId=");
		builder.append(resourceId);
		builder.append(", cloudServiceTypeCode=");
		builder.append(cloudServiceTypeCode);
		builder.append(", payMethod=");
		builder.append(payMethod);
		builder.append("]");
		return builder.toString();
	}
	

}
