package com.example.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    /**
     *  将店铺照片和商品照片进行水印和存储处理
     * @param thumbnail Spring自带的文件处理对象，接收用户的传送流
     * @param targetAddr 存储的目标路径
     * @return
     */
    public static String generateThumbnail(File thumbnail,String targetAddr){

//          获取随机生成的文件名
        String realFileName = getRandomFileName();
//          获取扩展名
        String extension = getFileExtension(thumbnail);

//        创建目标文件的路径
        makeDirPath(targetAddr);
//        获取完整路径
        String relativeAddr = targetAddr + realFileName + extension;
//        创建文件对象， 基础路径 + 完整路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);

        try {
//            将图片添加水印之后存储到完整路径下
            Thumbnails.of(thumbnail).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "watermark.png")),0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        将最后的完整绝对路径返回
        return relativeAddr;
    }

    /**
     * 创建目录路径及涉及到的目录
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * 获取文件流的扩展名
     * @param thumbnail 文件流
     * @return
     */
    private static String getFileExtension(File thumbnail) {
         String originalFileName = thumbnail.getName();
         return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * @return
     */
    public static String getRandomFileName() {
//获取随机的五位数
        int rannum = r.nextInt(89999)+10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr+rannum;
    }

    public static void main(String[] args) throws IOException{
//        改变图片的大小并加上水印，压缩，输出到同级目录下
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("D:\\data\\xiaohuangren.jpg"))
                .size(200,200).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath + "/watermark.png")),0.25f)
                .outputQuality(0.8f).toFile("D:\\data\\xiaohuangrennew.jpg");
    }
}
