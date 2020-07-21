package com.bhhan.food.shop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hbh5274@gmail.com on 2020-07-20
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "OPTION_GROUP_SPECS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OptionGroupSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_GROUP_SPEC_ID")
    private Long id;

    private String name;
    private boolean exclusive;
    private boolean basic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OPTION_GROUP_SPEC_ID")
    private List<OptionSpecification> optionSpecs = new ArrayList<>();

    @Builder
    public OptionGroupSpecification(Long id, String name, boolean exclusive, boolean basic, List<OptionSpecification> optionSpecs){
        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs.addAll(optionSpecs);
    }

    public boolean isSatisfiedBy(OptionGroup optionGroup){
        if(!name.equals(optionGroup.getName())){
            return false;
        }

        if(!isSatisfiedBy(optionGroup.getOptions())){
            return false;
        }

        return true;
    }

    private boolean isSatisfiedBy(List<Option> options) {
        final List<Option> satisfiedOptions = satisfied(options);

        if(satisfiedOptions.isEmpty()){
            return false;
        }

        if(exclusive && satisfiedOptions.size() > 1){
            return false;
        }

        if(options.size() != satisfiedOptions.size()){
            return false;
        }

        return true;
    }

    private List<Option> satisfied(List<Option> options) {
        return optionSpecs.stream()
                .flatMap(spec -> options.stream().filter(spec::isSatisfiedBy))
                .collect(Collectors.toList());
    }
}
