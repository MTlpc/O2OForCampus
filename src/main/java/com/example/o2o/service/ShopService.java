package com.example.o2o.service;

import com.example.o2o.dto.ImageHolder;
import com.example.o2o.dto.ShopExecution;
import com.example.o2o.entity.Shop;
import com.example.o2o.exceptions.ShopOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 根据shopCondition分页返回店铺列表
     */
    public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);


    ShopExecution addShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;

    /**
     * 通过店铺Id获取店铺信息
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括图片处理
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

}
