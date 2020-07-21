package com.bhhan.food.generic.money.infra;

import com.bhhan.food.generic.money.domain.Ratio;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */

@Converter(autoApply = true)
public class RatioConverter implements AttributeConverter<Ratio, Double> {

    @Override
    public Double convertToDatabaseColumn(Ratio ratio) {
        return ratio.getRate();
    }

    @Override
    public Ratio convertToEntityAttribute(Double rate) {
        return Ratio.valueOf(rate);
    }
}
