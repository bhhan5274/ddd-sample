package com.bhhan.food.order.domain;

import com.bhhan.food.generic.money.domain.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order extends AbstractAggregateRoot<Order> {
    public enum OrderStatus { ORDERED, PAYED, DELIVERED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    private Long userId;
    private Long shopId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    private LocalDateTime orderedTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus orderStatus;

    @Builder
    public Order(Long id, Long userId, Long shopId, List<OrderLineItem> orderLineItems,
                 LocalDateTime orderedTime, OrderStatus status){
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderStatus = status;
        this.orderLineItems.addAll(orderLineItems);
    }

    public void place(OrderValidator orderValidator){
        orderValidator.validate(this);
        ordered();
    }

    public List<Long> getMenuIds(){
        return orderLineItems.stream()
                .map(OrderLineItem::getMenuId)
                .collect(Collectors.toList());
    }

    public Money calculateTotalPrice(){
        return Money.sum(orderLineItems, OrderLineItem::calculatePrice);
    }

    public void payed(){
        this.orderStatus = OrderStatus.PAYED;
        registerEvent(new OrderPayedEvent(this));
    }

    public void delivered(){
        this.orderStatus = OrderStatus.DELIVERED;
        registerEvent(new OrderDeliveredEvent(this));
    }

    private void ordered(){
        this.orderStatus = OrderStatus.ORDERED;
    }
}
