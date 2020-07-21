package com.bhhan.food.order.domain;

import com.bhhan.food.generic.money.domain.Money;
import com.bhhan.food.shop.domain.OptionGroup;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "ORDER_OPTION_GROUPS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_OPTION_GROUP_ID")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "ORDER_OPTIONS", joinColumns = @JoinColumn(name = "ORDER_OPTION_GROUP_ID"))
    private List<OrderOption> orderOptions;

    @Builder
    public OrderOptionGroup(Long id, String name, List<OrderOption> options){
        this.id = id;
        this.name = name;
        this.orderOptions = options;
    }

    public Money calculatePrice(){
        return Money.sum(orderOptions, OrderOption::getPrice);
    }

    public OptionGroup convertToOptionGroup(){
        return new OptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(Collectors.toList()));
    }
}
