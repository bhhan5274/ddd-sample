package com.bhhan.food.shop.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */

@Data
public class OptionGroup {
    private String name;
    private List<Option> options;

    @Builder
    public OptionGroup(String name, List<Option> options){
        this.name = name;
        this.options = options;
    }
}
