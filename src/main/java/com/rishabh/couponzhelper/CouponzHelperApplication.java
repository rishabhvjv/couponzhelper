package com.rishabh.couponzhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages="com.rishabh")
@RestController
public class CouponzHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponzHelperApplication.class, args);
	}

}
