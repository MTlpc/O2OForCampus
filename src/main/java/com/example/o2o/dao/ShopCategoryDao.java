package com.example.o2o.dao;

import java.util.List;

import com.example.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;


public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);
}
