package com.rishabh.couponzhelper.db;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rishabh.couponzhelper.model.CouponData;
import static com.rishabh.couponzhelper.utils.Constants.*;
@Entity
@Table(name = "coupondetails")
public class CouponEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid")
	private long cid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "coupon_code")
	private String couponCode;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "image_link")
	private String imageLink;
	
	@Column(name = "expiry_date")
	private String expiryDate;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "status")
	private String status;

	public CouponEntity() {
		super();
	}

	public CouponEntity(CouponData request) {
		this.name = request.getCouponName();
		this.couponCode = request.getCouponCode();
		this.amount = request.getAmount();
		this.expiryDate = request.getExpiryDate();
		this.imageLink = request.getImageLink();
		this.createdAt = String.valueOf(Instant.now().getEpochSecond());
		this.createdBy = CREATED_BY;
		this.status = STATUS_ACTIVE;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	

}
