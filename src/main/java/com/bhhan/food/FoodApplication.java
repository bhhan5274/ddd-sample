package com.bhhan.food;

import com.bhhan.food.generic.money.domain.Money;
import com.bhhan.food.order.service.Cart;
import com.bhhan.food.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAsync(proxyTargetClass = true)
public class FoodApplication implements CommandLineRunner {

    private final OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(FoodApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Cart cart = new Cart(1L, 1L,
                Arrays.asList(new Cart.CartLineItem(1L, "삼겹살 1인세트", 2,
                        Arrays.asList(new Cart.CartOptionGroup("기본",
                                Arrays.asList(new Cart.CartOption("소(250g)", Money.wons(12000))))))));

        orderService.placeOrder(cart);
        orderService.payOrder(1L);
        Thread.sleep(Duration.ofSeconds(5L).toMillis());
        orderService.deleverOrder(1L);
    }
}
