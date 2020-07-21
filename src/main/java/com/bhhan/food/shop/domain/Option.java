package com.bhhan.food.shop.domain;

import com.bhhan.food.generic.money.domain.Money;
import lombok.Builder;
import lombok.Data;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */

@Data
public class Option {
    private String name;
    private Money price;

    @Builder
    public Option(String name, Money price){
        this.name = name;
        this.price = price;
    }
}
