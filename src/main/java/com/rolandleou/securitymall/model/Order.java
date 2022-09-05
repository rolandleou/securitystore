package com.rolandleou.securitymall.model;

import java.util.Date;

public class Order {

	private Integer orderId;
	private Integer userId;
	private Integer totalAmount;
	private Date createdSDate;
	private Date lastModifiedDate;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getCreatedSDate() {
		return createdSDate;
	}
	public void setCreatedSDate(Date createdSDate) {
		this.createdSDate = createdSDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	
	
}
