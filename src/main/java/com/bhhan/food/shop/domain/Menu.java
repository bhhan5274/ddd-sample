package com.bhhan.food.shop.domain;

import com.bhhan.food.generic.money.domain.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "MENUS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    private String name;
    private String description;
    private Long shopId;
    private Money price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID")
    private List<OptionGroupSpecification> optionGroupSpecs = new ArrayList<>();

    @Builder
    public Menu(Long id, String name, Money price, String description, Long shopId, List<OptionGroupSpecification> optionGroupSpecs){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.shopId = shopId;
        this.optionGroupSpecs.addAll(optionGroupSpecs);
    }

    public Money getBasePrice(){
        return getBasicOptionGroupSpecification()
                .getOptionSpecs()
                .get(0)
                .getPrice();
    }

    public void validateOrder(String menuName, List<OptionGroup> optionGroups){
        if(!this.name.equals(menuName)){
            throw new IllegalArgumentException("기본 상품이 변경됐습니다.");
        }

        if(!isSatisfiedBy(optionGroups)){
            throw new IllegalArgumentException("메뉴가 변경됐습니다.");
        }
    }

    private boolean isSatisfiedBy(List<OptionGroup> optionGroups) {
        return optionGroups
                .stream()
                .allMatch(this::isSatisfiedBy);
    }

    private boolean isSatisfiedBy(OptionGroup optionGroup) {
        return optionGroupSpecs.stream()
                        .anyMatch(optionGroupSpecification ->
                                optionGroupSpecification.isSatisfiedBy(optionGroup));
    }

    private OptionGroupSpecification getBasicOptionGroupSpecification() {
        return optionGroupSpecs
                .stream()
                .filter(OptionGroupSpecification::isBasic)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
