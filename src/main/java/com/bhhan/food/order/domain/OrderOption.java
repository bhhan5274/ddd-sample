package com.bhhan.food.order.domain;

import com.bhhan.food.generic.money.domain.Money;
import com.bhhan.food.shop.domain.Option;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderOption {
    private String name;
    private Money price;

    @Builder
    public OrderOption(String name, Money price){
        this.name = name;
        this.price = price;
    }

    public Option convertToOption(){
        return new Option(name, price);
    }
}
