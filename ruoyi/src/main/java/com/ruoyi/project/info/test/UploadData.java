package com.ruoyi.project.info.test;

import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.sign.SHA256;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UploadData {
    //用户名
    private static String username="testtest";
    //密码
    private static String password="testtest";
    //URL
    private static String URL="http://rizhao.zhuisu.lipengwencai.com/interfaces-rezen";
    //获取token
    public static String getToken(){
        HashMap dataMap=new HashMap();
        String token="";
        dataMap.put("username",username);
        dataMap.put("password",password);
        JSONObject jsonObject=null;
        try{
            String jsonStr= JSONValue.toJSONString(dataMap);
            jsonObject = new JSONObject(HttpUtils.sendPost("http://rizhao.zhuisu.lipengwencai.com/auth/jwt/token",jsonStr,""));
            token=jsonObject.getString("token");
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
    //上传检测数据
    public static String sendCheckData(){
        String token=getToken();
        System.out.println(token);
        HashMap dataMap=new HashMap();
        dataMap.put("cityCode","276800");
        dataMap.put("batchCode","PC001100011");
        dataMap.put("marketCode","测试");
        dataMap.put("marketName","测试");
        dataMap.put("wholesalerCode","测试");
        dataMap.put("wholesalerName","测试");
        dataMap.put("goodsCode","010");
        dataMap.put("goodsName","测试");
        dataMap.put("detectionDate","2020-11-23");
        dataMap.put("detectionResult",1);
        dataMap.put("resultExpl","测试");
        dataMap.put("num",1);
        dataMap.put("sampleNum",1);
        dataMap.put("sampleCode","010");
        dataMap.put("detector","张三");
        dataMap.put("verifyImg","测试");
        dataMap.put("verifyImgs","测试");
        dataMap.put("verifyMultiImg","测试");
        dataMap.put("verifyCompany","测试单位");
        dataMap.put("inhibit","10%");
        JSONObject jsonObject=null;
        try{
            String jsonStr= JSONValue.toJSONString(dataMap);
            jsonObject = new JSONObject(HttpUtils.sendPostByToken(URL+"/trace/addTraceDetectionJsonData",jsonStr,token));

        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
    public static void main(String[] args) {
        sendCheckData();
    }
}
