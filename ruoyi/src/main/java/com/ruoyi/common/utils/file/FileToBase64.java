package com.ruoyi.common.utils.file;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class FileToBase64 {
    public List<StringBuilder> readFile(String fileDir) {
        fileDir="D:\\test";
        List<File> fileList = new ArrayList<File>();
        List<StringBuilder> codeList = new ArrayList<StringBuilder>();
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            return null;
        }

        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
                String code=FileToBase64.getFileStr(file.getPath());
                StringBuilder StringBuilder=new StringBuilder(code);
                codeList.add(StringBuilder);
            } else if (f.isDirectory()) {
                System.out.println(f.getAbsolutePath());
                readFile(f.getAbsolutePath());
            }
        }
        for (File f1 : fileList) {
            System.out.println(f1.getName());
        }
        return codeList;

    }



    private static String targetFilePath = "D://test123.xlsx";
    public static void main(String[] args) throws Exception {
        double str=0.11;
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        System.out.println(df.format(str));
//         String fileStr = getFileStr("D://gh1.png");
//         System.out.println("fileStr ===" + fileStr);
//        System.out.println(generateFile(fileStr, targetFilePath));
//        System.out.println("end");
//        String name="test.ppt";
//        String [] strs=name.split("\\.");
//        System.out.println(strs[0]);
    }


    /**
     * 文件转化成base64字符串
     * 将文件转化为字节数组字符串，并对其进行Base64编码处理
     */
    public static String getFileStr(String filePath) {
        InputStream in = null;
        byte[] data = null;
        // 读取文件字节数组
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回 Base64 编码过的字节数组字符串
        return encoder.encode(data);
    }


    /**
     * base64字符串转化成文件，可以是JPEG、PNG、TXT和AVI等等
     *
     * @param base64FileStr
     * @param filePath
     * @return
     * @throws Exception
     */
    public static boolean generateFile(String base64FileStr, String filePath) throws Exception {
        // 数据为空
        if (base64FileStr == null) {
            System.out.println(" 不行，oops！ ");
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();


        // Base64解码,对字节数组字符串进行Base64解码并生成文件
        byte[] byt = decoder.decodeBuffer(base64FileStr);
        for (int i = 0, len = byt.length; i < len; ++i) {
            // 调整异常数据
            if (byt[i] < 0) {
                byt[i] += 256;
            }
        }
        OutputStream out = null;
        InputStream input = new ByteArrayInputStream(byt);
        try {
            // 生成指定格式的文件
            out = new FileOutputStream(filePath);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = input.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
        return true;
    }
}
