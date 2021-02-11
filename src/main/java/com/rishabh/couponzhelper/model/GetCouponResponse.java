package com.rishabh.couponzhelper.model;

import java.util.List;

public class GetCouponResponse {
	private String responseCode;
	private String responseMessage;
	private String version;
	private List<CouponData> response;
	public GetCouponResponse() {
		super();
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<CouponData> getResponse() {
		return response;
	}
	public void setResponse(List<CouponData> response) {
		this.response = response;
	}
	
}
