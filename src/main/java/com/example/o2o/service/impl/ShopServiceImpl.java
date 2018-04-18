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
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
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
                if (shopImgInputStream != null){
                    //存储图片
                    try{
                        addShopImg(shop,shopImgInputStream, fileName);
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

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) throws Exception{

//        MultipartFile multipartFile;
//
//        FileInputStream input = new FileInputStream(shopImg);
//        multipartFile = new MockMultipartFile("file",
//                shopImg.getName(), "text/plain", input);

        //获取shop图片将要存储的文件夹路径 作为目标路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
//        将图片存储后 将保存的位置存储在shop对象中
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName, dest);
        shop.setShopImg(shopImgAddr);
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
            throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            // 1.判断是否需要处理图片
            try {
                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }
                // 2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }
}
