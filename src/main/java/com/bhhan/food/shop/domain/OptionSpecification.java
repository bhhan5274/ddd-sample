package com.bhhan.food.shop.domain;

import com.bhhan.food.generic.money.domain.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "OPTION_SPECS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OptionSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_SPEC_ID")
    private Long id;

    private String name;
    private Money price;

    @Builder
    public OptionSpecification(Long id, String name, Money price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public boolean isSatisfiedBy(Option option){
        return Objects.equals(name, option.getName()) &&
                Objects.equals(price, option.getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionSpecification that = (OptionSpecification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
