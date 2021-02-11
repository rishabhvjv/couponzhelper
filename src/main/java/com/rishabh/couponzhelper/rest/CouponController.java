package com.rishabh.couponzhelper.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rishabh.couponzhelper.model.GetCouponResponse;

@RestController
@RequestMapping("/rest")
public class CouponController {
	
	@Autowired
	CouponService couponService;

	@GetMapping(path = "/couponz/fetch-all-coupon/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetCouponResponse> getAllCouponData() {
		GetCouponResponse response = couponService.fetchAllCoupons();
		return ResponseEntity.ok(response);
	}
}
