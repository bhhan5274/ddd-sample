package com.bhhan.food.billing.domain;

import com.bhhan.food.generic.money.domain.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "BILLINGS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BILLING_ID")
    private Long id;

    private Long shopId;
    private Money commission = Money.ZERO;

    @Builder
    public Billing(Long id, Long shopId, Money commission){
        this.id = id;
        this.shopId = shopId;
        this.commission = commission;
    }

    public void billCommissionFee(Money commission){
        this.commission = this.commission.plus(commission);
    }
}
