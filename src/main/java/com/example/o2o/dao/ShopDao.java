package com.example.o2o.dao;

import com.example.o2o.entity.Shop;

public interface ShopDao {

    /**
     * 新增店铺
     * @param shop 传入Shop对象
     * @return 影响的行数 1是成功 -1是失败
     */
    int insertShop(Shop shop);

    int updateShop(Shop shop);

}
