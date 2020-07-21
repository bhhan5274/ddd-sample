package com.bhhan.food.billing.domain;

import com.bhhan.food.generic.money.domain.Money;
import com.bhhan.food.order.domain.Order;
import com.bhhan.food.order.domain.OrderDeliveredEvent;
import com.bhhan.food.order.domain.OrderRepository;
import com.bhhan.food.shop.domain.Shop;
import com.bhhan.food.shop.domain.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BillShopWithOrderDeliveredEventHandler {
    private final ShopRepository shopRepository;
    private final BillingRepository billingRepository;
    private final OrderRepository orderRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = OrderDeliveredEvent.class)
    public void handle(OrderDeliveredEvent event){
        log.info("===================== BillShopWithOrderDeliveredEventHandler =====================");
        final Shop shop = shopRepository.findById(event.getShopId())
                .orElseThrow(IllegalArgumentException::new);
        final Billing billing = billingRepository.findByShopId(event.getShopId())
                .orElseGet(() -> billingRepository.save(Billing.builder().shopId(event.getShopId()).commission(Money.ZERO).build()));

        final Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(IllegalArgumentException::new);

        billing.billCommissionFee(shop.calculateCommissionFee(order.calculateTotalPrice()));
    }
}
