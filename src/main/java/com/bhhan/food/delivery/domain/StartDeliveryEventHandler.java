package com.bhhan.food.delivery.domain;

import com.bhhan.food.order.domain.OrderPayedEvent;
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
public class StartDeliveryEventHandler {
    private final DeliveryRepository deliveryRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = OrderPayedEvent.class)
    public void handle(OrderPayedEvent event){
        log.info("===================== StartDeliveryEventHandler =====================");
        final Delivery delivery = Delivery.builder()
                .deliveryStatus(Delivery.DeliveryStatus.DELIVERING)
                .orderId(event.getOrderId())
                .build();
        deliveryRepository.save(delivery);
    }
}
