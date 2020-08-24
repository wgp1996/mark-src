package com.ruoyi.common.utils.code;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 配置图像写入器
 *
 * @author 18316
 *
 */
public class MatrixToImageWriter {
    @Autowired
    private ServerConfig serverConfig;

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriter() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    public static String createCodeImg(String content){
        //生成二维码
        String url="";
        try {
            String path = RuoYiConfig.getUploadPath()+"/codeImg";// 二维码保存的路径
            String codeName = UUID.randomUUID().toString();// 二维码的图片名
            String imageType = "jpg";// 图片类型
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
            File file = new File(path);
            if(!file.exists()){//如果文件夹不存在
                file.mkdir();//创建文件夹
            }
            File file1 = new File(path, codeName + "." + imageType);
            MatrixToImageWriter.writeToFile(bitMatrix, imageType, file1);
            url= Constants.RESOURCE_PREFIX + "/upload/codeImg/"+codeName + "." + imageType;
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  url;
    }

}