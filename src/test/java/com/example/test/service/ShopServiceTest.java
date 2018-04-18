package com.example.test.service;

import com.example.o2o.dto.ShopExecution;
import com.example.o2o.entity.Area;
import com.example.o2o.entity.PersonInfo;
import com.example.o2o.entity.Shop;
import com.example.o2o.entity.ShopCategory;
import com.example.o2o.enums.ShopStateEnum;
import com.example.o2o.service.ShopService;
import com.example.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
	public void testModifyShop() throws FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopName("修改后的店名");
		shop.setShopId(1L);
		shop.setShopDesc("修改后的详情描述");
		File shopImg = new File("C:\\Users\\92806\\Desktop\\面试\\头像.jpg");
		InputStream is = new FileInputStream(shopImg);

		ShopExecution shopExecution = shopService.modifyShop(shop, is, shopImg.getName());
		System.out.println("新的图片地址为："+shopExecution.getShop().getShopImg());
	}

    @Test
	public void testAddShop() throws Exception {
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

        shop.setShopName("测试的店铺3");
		shop.setShopDesc("test4");
		shop.setShopAddr("test4");
		shop.setPhone("test4");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("D:\\data\\xiaohuangren.jpg");
		InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop,is, shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
	}
}
