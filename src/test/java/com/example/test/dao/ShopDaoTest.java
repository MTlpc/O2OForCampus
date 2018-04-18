package com.example.test.dao;

import com.example.o2o.dao.ShopDao;
import com.example.o2o.entity.Area;
import com.example.o2o.entity.PersonInfo;
import com.example.o2o.entity.Shop;
import com.example.o2o.entity.ShopCategory;
import com.example.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest{

    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopList() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 3);
        System.out.println("店铺列表的大小：" + shopList.size());
        System.out.println("店铺列表的总数：" + shopDao.queryShopCount(shopCondition));

        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(18L);
        shopCondition.setShopCategory(sc);

        shopList = shopDao.queryShopList(shopCondition, 0, 2);
        System.out.println("xin店铺列表的大小：" + shopList.size());
        System.out.println("xin店铺列表的总数：" + shopDao.queryShopCount(shopCondition));

    }

    @Test
    public void testQueryByShopId() {
        long shopId = 21;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId:" + shop.getArea().getAreaId());
        System.out.println("areaName:" + shop.getArea().getAreaName());
    }

    @Test
    public void testInsert(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);

        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");

        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");

        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);

        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());

        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }


}
