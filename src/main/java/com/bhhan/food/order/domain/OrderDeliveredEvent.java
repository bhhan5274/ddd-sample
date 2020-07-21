package com.bhhan.food.order.domain;

import com.bhhan.food.generic.money.domain.Money;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */
public class OrderDeliveredEvent {
    private Order order;

    public OrderDeliveredEvent(Order order){
        this.order = order;
    }

    public Long getOrderId(){
        return order.getId();
    }

    public Long getShopId(){
        return order.getShopId();
    }

    public Money getTotalPrice(){
        return order.calculateTotalPrice();
    }
}
