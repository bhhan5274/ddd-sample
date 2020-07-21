package com.bhhan.food.delivery.domain;

import com.bhhan.food.order.domain.OrderDeliveredEvent;
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
public class CompleteDeliveryWithOrderDeliveredEventHandler {
    private final DeliveryRepository deliveryRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = OrderDeliveredEvent.class)
    public void handle(OrderDeliveredEvent event){
        log.info("===================== CompleteDeliveryWithOrderDeliveredEventHandler =====================");
        final Delivery delivery = deliveryRepository.findById(event.getOrderId())
                .orElseThrow(IllegalArgumentException::new);
        delivery.complete();
    }
}
