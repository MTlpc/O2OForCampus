package com.example.o2o.dao;

import com.example.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     *
     * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域ID,owner
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
                             @Param("rowIndex")int rowIndex,
                             @Param("pageSize")int pageSize);

    /**
     * 返回queryShopList总数
     */
    int queryShopCount(@Param("shopCondition")Shop shopCondition);


    /**
     * 通过 shop id 查询店铺
     */
    Shop queryByShopId(long shopId);

    /**
     * 新增店铺
     * @param shop 传入Shop对象
     * @return 影响的行数 1是成功 -1是失败
     */
    int insertShop(Shop shop);

    int updateShop(Shop shop);

}
