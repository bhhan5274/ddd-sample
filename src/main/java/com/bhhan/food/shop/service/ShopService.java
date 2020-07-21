package com.bhhan.food.shop.service;

import com.bhhan.food.shop.domain.Menu;
import com.bhhan.food.shop.domain.MenuRepository;
import com.bhhan.food.shop.domain.Shop;
import com.bhhan.food.shop.domain.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-21
 * Github : http://github.com/bhhan5274
 */

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ShopService {
    private final ShopRepository shopRepository;
    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public MenuBoard getMenuBoard(Long shopId){
        final Shop shop = shopRepository.findById(shopId)
                .orElseThrow(IllegalArgumentException::new);
        final List<Menu> menus = menuRepository.findByShopId(shopId);

        return new MenuBoard(shop, menus);
    }
}
