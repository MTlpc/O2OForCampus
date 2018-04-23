package com.example.o2o.dao;

import com.example.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

    /**
     * 批量添加商品详情图片
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除指定商品下的所有详情图
     */
    int deleteProductImgByProductId(long productId);

    /**
     * 列出某个商品的详情图列表
     */
    List<ProductImg> queryProductImgList(long productId);

}
