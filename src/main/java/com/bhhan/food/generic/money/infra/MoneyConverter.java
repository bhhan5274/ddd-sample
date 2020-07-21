package com.bhhan.food.generic.money.infra;

import com.bhhan.food.generic.money.domain.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
    @Override
    public Long convertToDatabaseColumn(Money money) {
        return money.getAmount().longValue();
    }

    @Override
    public Money convertToEntityAttribute(Long amount) {
        return Money.wons(amount);
    }
}
