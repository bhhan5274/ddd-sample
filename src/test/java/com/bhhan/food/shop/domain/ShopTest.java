package com.bhhan.food.shop.domain;

import com.bhhan.food.generic.money.domain.Money;
import com.bhhan.food.generic.money.domain.Ratio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bhhan.food.Fixtures.aShop;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */
public class ShopTest {
    @Test
    @DisplayName("최수주문금액_체크")
    void test1(){
        final Shop shop = aShop().minOrderAmount(Money.wons(15000)).build();

        assertEquals(false, shop.isValidOrderAmount(Money.wons(14000)));
        assertEquals(true, shop.isValidOrderAmount(Money.wons(15000)));
        assertEquals(true, shop.isValidOrderAmount(Money.wons(16000)));
    }

    @Test
    @DisplayName("수수료 계산")
    void test2(){
        final Shop shop = aShop().commissionRate(Ratio.valueOf(0.1))
                .build();

        assertEquals(Money.wons(100), shop.calculateCommissionFee(Money.wons(1000)));
    }
}
