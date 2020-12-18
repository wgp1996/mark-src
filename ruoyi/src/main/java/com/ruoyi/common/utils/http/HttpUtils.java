package com.ruoyi.common.utils.http;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class HttpUtils {

    public static String sendPost(String url,String param,String token) throws IOException{
        OutputStreamWriter out =null;
        BufferedReader reader = null;
        String response = "";
        //创建连接
        URL httpUrl = null; //HTTP URL类 用这个类来创建连接
        //创建URL
        httpUrl = new URL(url);
        //建立连接
        HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        if(token!=""){
            conn.setRequestProperty("Authorization","Bearer "+token);
        }
        conn.setRequestProperty("connection", "keep-alive");
        conn.setUseCaches(false);//设置不要缓存
        conn.setInstanceFollowRedirects(true);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
        //POST请求
        out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
        out.write(param);
        out.flush();
        //读取响应
        reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        String lines;
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8");
            response+=lines;
        }
        reader.close();
        // 断开连接
        conn.disconnect();
        if(out!=null){
            out.close();
        }
        if(reader!=null){
            reader.close();
        }
        System.out.println(response);
        return response;
    }

    public static String sendPostByToken(String url,String param,String token) throws IOException{
        OutputStreamWriter out =null;
        BufferedReader reader = null;
        String response = "";
        //创建连接
        URL httpUrl = null; //HTTP URL类 用这个类来创建连接
        //创建URL
        httpUrl = new URL(url);
        //建立连接
        HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        if(token!=""){
            conn.setRequestProperty("Authorization",token);
        }
        conn.setRequestProperty("connection", "keep-alive");
        conn.setUseCaches(false);//设置不要缓存
        conn.setInstanceFollowRedirects(true);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
        //POST请求
        out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
        out.write(param);
        out.flush();
        //读取响应
        reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        String lines;
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8");
            response+=lines;
        }
        reader.close();
        // 断开连接
        conn.disconnect();
        if(out!=null){
            out.close();
        }
        if(reader!=null){
            reader.close();
        }
        System.out.println(response);
        return response;
    }
    public static String sendGet(String url,String param,String token){
        OutputStreamWriter out =null;
        BufferedReader reader = null;
        String response = "";
        //创建连接
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            if(token!=""){
                conn.setRequestProperty("Authorization", token);
            }
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //GET请求
            out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write(param);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response+=lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                    reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return response;
    }

    public static String sendPut(String url,String param,String token){
        OutputStreamWriter out =null;
        BufferedReader reader = null;
        String response = "";
        //创建连接
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            if(token!=""){
                conn.setRequestProperty("Authorization", token);
            }
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //GET请求
            out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write(param);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response+=lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                    reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return response;
    }
    public static String sendDelete(String url,String param,String token){
        OutputStreamWriter out =null;
        BufferedReader reader = null;
        String response = "";
        //创建连接
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            if(token!=""){
                conn.setRequestProperty("Authorization", token);
            }
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //GET请求
            out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write(param);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response+=lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                    reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return response;
    }
    public static String httpPost(String url,HashMap hmap,HashMap map){
        // 返回body
        String body = null;
        // 获取连接客户端工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        // 2、创建一个HttpPost请求
        HttpPost post = new HttpPost(url);
        // 5、设置header信息
        /**header中通用属性*/
        post.setHeader("Accept","*/*");
        post.setHeader("Accept-Encoding","gzip, deflate");
        post.setHeader("Cache-Control","no-cache");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        /**业务参数*/
        post.setHeader("BIZ_TYPE",hmap.get("BIZ_TYPE").toString());
        post.setHeader("REQ_TIME",hmap.get("REQ_TIME").toString());
        post.setHeader("REQ_ID",hmap.get("REQ_ID").toString());
        post.setHeader("AUTH_ID",hmap.get("AUTH_ID").toString());
        post.setHeader("PARAM",hmap.get("PARAM").toString());
        // 设置参数
        if (map != null) {
            //System.out.println(JSON.toJSONString(map));
            try {
                StringEntity entity1=new StringEntity(JSON.toJSONString(map),"UTF-8");
                entity1.setContentEncoding("UTF-8");
                entity1.setContentType("application/json");
                post.setEntity(entity1);
                // 7、执行post请求操作，并拿到结果
                httpResponse = httpClient.execute(post);
                // 获取结果实体
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    // 按指定编码转换结果实体为String类型
                    body = EntityUtils.toString(entity, "UTF-8");
                }
                try {
                    httpResponse.close();
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return body;
    }

    public static String getToken(){
        HashMap data=new HashMap();
        data.put("username","qdkji123");
        data.put("password", "123456");
        JSONObject jsonObject=null;
        String token="";
        try {
            jsonObject = new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V1/Login", JSONValue.toJSONString(data),""));
            JSONObject bodyObject=new JSONObject(jsonObject.get("body").toString());
            token=bodyObject.getString("token");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return token;
    }
}
