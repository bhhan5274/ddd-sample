package com.bhhan.food.order.service;

import com.bhhan.food.order.domain.Order;
import com.bhhan.food.order.domain.OrderLineItem;
import com.bhhan.food.order.domain.OrderOption;
import com.bhhan.food.order.domain.OrderOptionGroup;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Component
public class OrderMapper {
    public Order mapFrom(Cart cart){
        return Order.builder()
                .shopId(cart.getShopId())
                .userId(cart.getUserId())
                .orderLineItems(cart.getCartLineItems()
                        .stream()
                        .map(this::toOrderLineItem)
                        .collect(toList()))
                .build();
    }

    private OrderLineItem toOrderLineItem(Cart.CartLineItem cartLineItem) {
        return OrderLineItem.builder()
                .menuId(cartLineItem.getMenuId())
                .name(cartLineItem.getName())
                .count(cartLineItem.getCount())
                .groups(cartLineItem.getGroups()
                    .stream()
                    .map(this::toOrderOptionGroup)
                    .collect(toList()))
                .build();
    }

    private OrderOptionGroup toOrderOptionGroup(Cart.CartOptionGroup cartOptionGroup) {
        return OrderOptionGroup.builder()
                .name(cartOptionGroup.getName())
                .options(cartOptionGroup.getOptions()
                    .stream()
                    .map(this::toOrderOption)
                    .collect(toList()))
                .build();
    }

    private OrderOption toOrderOption(Cart.CartOption cartOption) {
        return OrderOption.builder()
                .name(cartOption.getName())
                .price(cartOption.getPrice())
                .build();
    }
}
