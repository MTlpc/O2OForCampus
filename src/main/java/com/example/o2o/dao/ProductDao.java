package com.example.o2o.dao;

import com.example.o2o.entity.Product;

public interface ProductDao {

    /**
     * 插入商品
     */
    int insertProduct(Product product);

}
