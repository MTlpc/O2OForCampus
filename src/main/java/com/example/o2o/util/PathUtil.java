package com.example.o2o.util;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    /**
     * 获取项目图片的基础路径
     * @return basePath
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/data/";
        }else {
            basePath = "/usr/local/data/image/";
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }

    /**
     * 获取店铺图片的根路径
     * @param shopId 商铺ID
     * @return imagePath
     */
    public static String getShopImagePath(Long shopId){
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/",seperator);
    }
}
