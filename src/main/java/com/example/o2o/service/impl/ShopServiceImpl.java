package com.example.o2o.service.impl;

import com.example.o2o.dao.ShopDao;
import com.example.o2o.dto.ShopExecution;
import com.example.o2o.entity.Shop;
import com.example.o2o.enums.ShopStateEnum;
import com.example.o2o.exceptions.ShopOperationException;
import com.example.o2o.service.ShopService;
import com.example.o2o.util.ImageUtil;
import com.example.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
//        如果店铺信息为空，则直接返回店铺为空的信息
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
//        进行添加店铺的操作
        try{
//          添加一些必要信息
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
//          店铺添加操作
            int effectedNum = shopDao.insertShop(shop);
//            添加失败，则返回RuntimeException，直接回滚,否则将对图片进行存储
            if(effectedNum <= 0){
                throw new ShopOperationException("店铺创建失败");
            }else {
                if (shopImg != null){
                    //存储图片
                    try{
                        addShopImg(shop,shopImg);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error" + e.getMessage());
                    }

                    //更新图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if(effectedNum <= 0){
                        throw new ShopOperationException("店铺图片地址失败");
                    }

                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShop error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, File shopImg) throws Exception{

//        MultipartFile multipartFile;
//
//        FileInputStream input = new FileInputStream(shopImg);
//        multipartFile = new MockMultipartFile("file",
//                shopImg.getName(), "text/plain", input);

        //获取shop图片将要存储的文件夹路径 作为目标路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
//        将图片存储后 将保存的位置存储在shop对象中
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}
