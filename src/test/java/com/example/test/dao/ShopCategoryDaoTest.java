package com.example.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.example.o2o.dao.ShopCategoryDao;
import com.example.o2o.entity.ShopCategory;
import com.example.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory() {
//        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
//        assertEquals(1, shopCategoryList.size());

//        ShopCategory shopCategory =  new ShopCategory();
//        ShopCategory parentCategory =  new ShopCategory();
//        parentCategory.setShopCategoryId(1L);
//        shopCategory.setParent(parentCategory);
//        shopCategoryList = shopCategoryDao.queryShopCategory(shopCategory);
//        assertEquals(1, shopCategoryList.size());
//        System.out.println(shopCategoryList.get(0).getShopCategoryName());
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
        System.out.println(shopCategoryList.size());
    }
}
