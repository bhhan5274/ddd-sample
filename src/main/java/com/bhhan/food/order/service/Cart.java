package com.bhhan.food.order.service;

import com.bhhan.food.generic.money.domain.Money;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Data
@NoArgsConstructor
public class Cart {
    private Long shopId;
    private Long userId;
    private List<CartLineItem> cartLineItems = new ArrayList<>();

    public Cart(Long shopId, Long userId, List<CartLineItem> cartLineItems){
        this.shopId = shopId;
        this.userId = userId;
        this.cartLineItems.addAll(cartLineItems);
    }

    @Data
    @NoArgsConstructor
    public static class CartLineItem {
        private Long menuId;
        private String name;
        private int count;
        private List<CartOptionGroup> groups = new ArrayList<>();

        public CartLineItem(Long menuId, String name, int count, List<CartOptionGroup> groups){
            this.menuId = menuId;
            this.name = name;
            this.count = count;
            this.groups.addAll(groups);
        }
    }

    @Data
    @NoArgsConstructor
    public static class CartOptionGroup {
        private String name;
        private List<CartOption> options = new ArrayList<>();

        public CartOptionGroup(String name, List<CartOption> options){
            this.name = name;
            this.options.addAll(options);
        }
    }

    @Data
    @NoArgsConstructor
    public static class CartOption {
        private String name;
        private Money price;

        public CartOption(String name, Money price){
            this.name = name;
            this.price = price;
        }
    }
}
