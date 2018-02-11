package com.example.o2o.service;

import com.example.o2o.dto.ShopExecution;
import com.example.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
