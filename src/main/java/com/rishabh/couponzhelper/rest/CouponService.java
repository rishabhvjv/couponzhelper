package com.rishabh.couponzhelper.rest;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.couponzhelper.db.CouponEntity;
import com.rishabh.couponzhelper.db.CouponRepository;
import com.rishabh.couponzhelper.model.CouponData;
import com.rishabh.couponzhelper.model.GetCouponResponse;
import static com.rishabh.couponzhelper.utils.Constants.*;

@Service
public class CouponService {
	private final Logger LOGGER = LoggerFactory.getLogger(CouponService.class);
	
	@Autowired
	CouponRepository couponRepo;

	public GetCouponResponse fetchAllCoupons() {
		GetCouponResponse resp = new GetCouponResponse();
		List<CouponEntity> cpList = couponRepo.findAll(); 
		if(cpList == null || cpList.size() == 0) {
			throw new RuntimeException("No Coupons found in the system");
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
		resp.setResponseCode(SUCCESS_01);
		resp.setResponseMessage("Coupon Data Fetched Successfully");
		resp.setVersion(VERSION_V1);
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
		
		resp.setResponseCode(SUCCESS_01);
		resp.setResponseMessage("Coupon Added Successfully");
		resp.setVersion(VERSION_V1);
		resp.setResponse(finalData);
		return resp;
	}

	@Transactional
	public GetCouponResponse redeemCoupon(CouponData request) {
		if(request == null || StringUtils.isBlank(request.getCouponName())) {
			throw new RuntimeException("Mandatory request parameters are missing: couponName");
		}
		if(request.getAmount() < 0) {
			throw new RuntimeException("Negative value of coupon amount is Not allowed");
		}
		
		GetCouponResponse resp = new GetCouponResponse();
		
		List<CouponEntity> couponAvailList = couponRepo.findByNamenAmt(request.getCouponName(),
				request.getAmount());
		if(couponAvailList == null || couponAvailList.size() == 0) {
			throw new RuntimeException("No active coupons found with supplied parameters: name="
		+ request.getCouponName() + " amount:"+ request.getAmount());
		}

		List<CouponData> finalData = new ArrayList<CouponData>();
		
		for (CouponEntity en : couponAvailList) {
			CouponData cpn = new CouponData();
			cpn.setAmount(en.getAmount());
			cpn.setCouponCode(en.getCouponCode());
			cpn.setCouponName(en.getName());
			cpn.setExpiryDate(en.getExpiryDate());
			cpn.setImageLink(en.getImageLink());
			finalData.add(cpn);
			
			
			int deactivateCouponCount = couponRepo.updateCpnStatusById(en.getCid());
			LOGGER.info("#### Deactivated coupons count: "+ deactivateCouponCount);
			break;
		}

		resp.setResponseCode(SUCCESS_01);
		resp.setResponseMessage("Coupon Redeemed Successfully");
		resp.setVersion(VERSION_V1);
		resp.setResponse(finalData);
		return resp;
	}

}
