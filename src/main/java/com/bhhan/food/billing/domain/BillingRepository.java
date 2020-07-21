package com.bhhan.food.billing.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */
public interface BillingRepository extends JpaRepository<Billing, Long> {
    Optional<Billing> findByShopId(Long shopId);
}
