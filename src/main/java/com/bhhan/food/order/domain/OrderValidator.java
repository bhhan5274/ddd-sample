package com.bhhan.food.order.domain;

import com.bhhan.food.shop.domain.Menu;
import com.bhhan.food.shop.domain.MenuRepository;
import com.bhhan.food.shop.domain.Shop;
import com.bhhan.food.shop.domain.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Component
@RequiredArgsConstructor
public class OrderValidator {
    private final ShopRepository shopRepository;
    private final MenuRepository menuRepository;

    public void validate(Order order){
        final Shop shop = getShop(order);
        final Map<Long, Menu> menus = getMenus(order);

        if(!shop.isOpen()){
            throw new IllegalArgumentException("가게가 영업중이 아닙니다.");
        }

        if(order.getOrderLineItems().isEmpty()){
            throw new IllegalArgumentException("주문 항목이 비어 있습니다.");
        }

        if(!shop.isValidOrderAmount(order.calculateTotalPrice())){
            throw new IllegalArgumentException(String.format("최소 주문 금액 %s 이상을 주문하셔야 합니다.", shop.getMinOrderAmount()));
        }

        for (OrderLineItem orderLineItem : order.getOrderLineItems()) {
            final Menu menu = menus.get(orderLineItem.getMenuId());

            if(Objects.isNull(menu)){
                throw new IllegalArgumentException("메뉴를 찾을 수 없습니다.");
            }

            menu.validateOrder(orderLineItem.getName(), orderLineItem.conventToOptionGroups());
        }
    }

    private Shop getShop(Order order) {
        return shopRepository.findById(order.getShopId())
                .orElseThrow(IllegalArgumentException::new);
    }

    private Map<Long, Menu> getMenus(Order order) {
        return menuRepository.findAllById(order.getMenuIds())
                .stream()
                .collect(toMap(Menu::getId, Function.identity()));
    }
}
