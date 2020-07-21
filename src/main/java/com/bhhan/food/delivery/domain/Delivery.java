package com.bhhan.food.delivery.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "DELIVERIES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Delivery {
    public enum DeliveryStatus { DELIVERING, DELIVERED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DeliveryStatus deliveryStatus;

    @Builder
    public Delivery(Long id, Long orderId, DeliveryStatus deliveryStatus){
        this.id = id;
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
    }

    public void complete(){
        this.deliveryStatus = DeliveryStatus.DELIVERED;
    }
}
