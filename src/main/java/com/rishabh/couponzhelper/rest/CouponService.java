package com.rishabh.couponzhelper.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.couponzhelper.db.CouponEntity;
import com.rishabh.couponzhelper.db.CouponRepository;
import com.rishabh.couponzhelper.model.CouponData;
import com.rishabh.couponzhelper.model.GetCouponResponse;

@Service
public class CouponService {
	
	@Autowired
	CouponRepository couponRepo;

	public GetCouponResponse fetchAllCoupons() {
		GetCouponResponse resp = new GetCouponResponse();
		List<CouponEntity> cpList = couponRepo.findAll(); 
		if(cpList == null || cpList.size() == 0) {
			throw new RuntimeException("No Coupons present in the system");
		}
		List<CouponData> finalData = new ArrayList<CouponData>();
		
		for(CouponEntity en: cpList) {
			CouponData cpn = new CouponData();
			cpn.setAmount(en.getAmount());
			cpn.setCouponCode(en.getCouponCode());
			cpn.setCouponName(en.getName());
			cpn.setExpiryDate(en.getExpiryDate());
			cpn.setImageLink(en.getImageLink());
			finalData.add(cpn);
		}
		resp.setResponseCode("S01");
		resp.setResponseMessage("Coupon Data Fetched Successfully");
		resp.setVersion("v1");
		resp.setResponse(finalData);
		return resp;
	}

	public GetCouponResponse addNewCoupon(CouponData request) {
		if(request == null || StringUtils.isBlank(request.getCouponCode())
				|| StringUtils.isBlank(request.getCouponName())) {
			throw new RuntimeException("Mandatory request parameters are missing");
		}
		if(request.getAmount() < 0) {
			throw new RuntimeException("Negative value of coupon amount is Not allowed");
		}
		
		GetCouponResponse resp = new GetCouponResponse();
		
		CouponEntity en = new CouponEntity(request);
		couponRepo.save(en);
		
		List<CouponData> finalData = new ArrayList<CouponData>();
		finalData.add(request);
		
		resp.setResponseCode("S01");
		resp.setResponseMessage("Coupon Added Successfully");
		resp.setVersion("v1");
		resp.setResponse(finalData);
		return resp;
	}

}
