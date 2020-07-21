package com.bhhan.food.order.domain;

import com.bhhan.food.generic.money.domain.Money;
import com.bhhan.food.shop.domain.OptionGroup;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "ORDER_LINE_ITEMS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_LINE_ITEM_ID")
    private Long id;

    private Long menuId;
    private String name;
    private int count;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_LINE_ITEM_ID")
    private List<OrderOptionGroup> groups = new ArrayList<>();

    @Builder
    public OrderLineItem(Long id, Long menuId, String name, int count,
                         List<OrderOptionGroup> groups){
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.count = count;
        this.groups.addAll(groups);
    }

    public Money calculatePrice(){
        return Money.sum(groups, OrderOptionGroup::calculatePrice).times(count);
    }

    public List<OptionGroup> conventToOptionGroups(){
        return groups.stream()
                .map(OrderOptionGroup::convertToOptionGroup)
                .collect(Collectors.toList());
    }
}
