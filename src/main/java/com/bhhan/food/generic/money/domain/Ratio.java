package com.bhhan.food.generic.money.domain;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */
public class Ratio {
    private double rate;

    public static Ratio valueOf(double rate){
        return new Ratio(rate);
    }

    Ratio(double rate){
        this.rate = rate;
    }

    public Money of(Money price){
        return price.times(rate);
    }

    public double getRate() {
        return rate;
    }
}
