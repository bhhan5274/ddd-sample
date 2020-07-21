package com.bhhan.food;

import com.bhhan.food.billing.domain.Billing;
import com.bhhan.food.delivery.domain.Delivery;
import com.bhhan.food.generic.money.domain.Money;
import com.bhhan.food.generic.money.domain.Ratio;
import com.bhhan.food.order.domain.Order;
import com.bhhan.food.order.domain.OrderLineItem;
import com.bhhan.food.order.domain.OrderOption;
import com.bhhan.food.order.domain.OrderOptionGroup;
import com.bhhan.food.shop.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */
public class Fixtures {
    public static Shop.ShopBuilder aShop(){
        return Shop.builder()
                .id(1L)
                .name("오겹돼지")
                .commissionRate(Ratio.valueOf(0.01))
                .open(true)
                .minOrderAmount(Money.wons(13000));
    }

    public static Menu.MenuBuilder aMenu(){
        return Menu.builder()
                .id(1L)
                .shopId(aShop().build().getId())
                .name("삼겹살 1인세트")
                .description("삼겹살 + 야채세트 + 김치찌개")
                .optionGroupSpecs(
                        Arrays.asList(
                            anOptionGroupSpec()
                                .name("기본")
                                .optionSpecs(Arrays.asList(anOptionSpec().name("소(250g)").price(Money.wons(12000)).build()))
                                .build(),
                            anOptionGroupSpec()
                                .name("맛선택")
                                .basic(false)
                                .optionSpecs(Arrays.asList(anOptionSpec().name("매콤 맛").price(Money.wons(1000)).build()))
                                .build()
                        )
                );
    }

    public static OptionGroupSpecification.OptionGroupSpecificationBuilder anOptionGroupSpec(){
        return OptionGroupSpecification.builder()
                .basic(true)
                .exclusive(true)
                .name("기본")
                .optionSpecs(Arrays.asList(anOptionSpec().build()));
    }

    public static OptionSpecification.OptionSpecificationBuilder anOptionSpec(){
        return OptionSpecification.builder()
                .name("소(250g)")
                .price(Money.wons(12000));
    }

    public static OptionGroup.OptionGroupBuilder anOptionGroup(){
        return OptionGroup.builder()
                .name("기본")
                .options(Arrays.asList(anOption().build()));
    }

    public static Option.OptionBuilder anOption(){
        return Option.builder()
                .name("소(250g)")
                .price(Money.wons(12000));
    }

    public static Order.OrderBuilder anOrder(){
        return Order.builder()
                .id(1L)
                .userId(1L)
                .shopId(aShop().build().getId())
                .status(Order.OrderStatus.ORDERED)
                .orderedTime(LocalDateTime.now())
                .orderLineItems(Arrays.asList(anOrderLineItem().build()));
    }

    public static OrderLineItem.OrderLineItemBuilder anOrderLineItem(){
        return OrderLineItem.builder()
                .menuId(aMenu().build().getId())
                .name("삼겹살 1인세트")
                .count(1)
                .groups(Arrays.asList(
                        anOrderOptionGroup()
                                .name("기본")
                                .options(Arrays.asList(anOrderOption().name("소(250g)").price(Money.wons(12000)).build())).build(),
                        anOrderOptionGroup()
                                .name("기본")
                                .options(Arrays.asList(anOrderOption().name("소(250g)").price(Money.wons(12000)).build())).build()
                ));
    }

    public static OrderOptionGroup.OrderOptionGroupBuilder anOrderOptionGroup(){
        return OrderOptionGroup.builder()
                .name("기본")
                .options(Arrays.asList(anOrderOption().build()));
    }

    public static OrderOption.OrderOptionBuilder anOrderOption(){
        return OrderOption.builder()
                .name("소(250g)")
                .price(Money.wons(12000));
    }

    public static Delivery.DeliveryBuilder aDelivery(){
        return Delivery.builder()
                .id(1L)
                .deliveryStatus(Delivery.DeliveryStatus.DELIVERING)
                .orderId(anOrder().build().getId());
    }

    public static Billing.BillingBuilder aBilling(){
        return Billing.builder()
                .id(1L)
                .shopId(aShop().build().getId())
                .commission(Money.ZERO);
    }
}
