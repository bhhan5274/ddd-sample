package com.bhhan.food.order.domain;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */
public class OrderPayedEvent {
    private Order order;

    public OrderPayedEvent(Order order){
        this.order = order;
    }

    public Long getOrderId(){
        return order.getId();
    }
}
