package com.bhhan.food.shop.domain;

import com.bhhan.food.generic.money.domain.Money;
import com.bhhan.food.generic.money.domain.Ratio;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "SHOPS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOP_ID")
    private Long id;

    private String name;
    private boolean open;
    private Money minOrderAmount;
    private Ratio commissionRate;

    @Builder
    public Shop(Long id, String name, boolean open, Money minOrderAmount, Ratio commissionRate){
        this.id = id;
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.commissionRate = commissionRate;
    }

    public boolean isValidOrderAmount(Money amount){
        return amount.isGreaterThanOrEqual(minOrderAmount);
    }

    public void open(){
        this.open = true;
    }

    public void close(){
        this.open = false;
    }

    public void modifyCommissionRate(Ratio commissionRate){
        this.commissionRate = commissionRate;
    }

    public Money calculateCommissionFee(Money price){
        return commissionRate.of(price);
    }
}
