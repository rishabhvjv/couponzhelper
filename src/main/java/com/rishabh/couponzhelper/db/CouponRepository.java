package com.rishabh.couponzhelper.db;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

	@Query(value="SELECT en FROM CouponEntity en where en.name = ?1 and en.amount = ?2 and en.status = 'a' ")
	List<CouponEntity> findByNamenAmt(String couponName, double amount);

	@Transactional
	@Modifying
	@Query(value="UPDATE CouponEntity SET status = 'na' where cid = ?1")
	int updateCpnStatusById(long cid);

}
