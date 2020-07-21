package com.bhhan.food.order.service;

import com.bhhan.food.order.domain.Order;
import com.bhhan.food.order.domain.OrderRepository;
import com.bhhan.food.order.domain.OrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OrderMapper orderMapper;

    public Order placeOrder(Cart cart){
        final Order order = orderMapper.mapFrom(cart);
        order.place(orderValidator);
        return orderRepository.save(order);
    }

    public Order payOrder(Long orderId){
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);
        order.payed();
        return orderRepository.save(order);
    }

    public Order deleverOrder(Long orderId){
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);
        order.delivered();
        return orderRepository.save(order);
    }
}
