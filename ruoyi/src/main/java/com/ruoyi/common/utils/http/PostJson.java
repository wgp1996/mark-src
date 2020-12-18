package com.ruoyi.common.utils.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import com.ruoyi.common.utils.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PostJson {
    private static class TrustAnyTrustManager implements X509TrustManager {
        //该方法检查客户端的证书，若不信任该证书则抛出异常。由于我们不需要对客户端进行认证，因此我们只需要执行默认的信任管理器的这个方法。
        //JSSE中，默认的信任管理器类为TrustManager。
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        //该方法检查服务器的证书，若不信任该证书同样抛出异常。通过自己实现该方法，可以使之信任我们指定的任何证书。在实现该方法时，也可以简单的不做任何处理，即一个空的函数体，由于不会抛出异常，它就会信任任何证书。
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        //返回受信任的X509证书数组。
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

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

    /**
     * post方式请求服务器(https协议)
     *
     * @param url
     *            请求地址
     * @param content
     *            参数
     * @param charset
     *            编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static byte[] post(String url, String content, String charset)
            throws NoSuchAlgorithmException, KeyManagementException,
            IOException {
        /*类HttpsURLConnection似乎并没有提供方法设置信任管理器。其实，HttpsURLConnection通过SSLSocket来建立与HTTPS的安全连接，SSLSocket对象是由SSLSocketFactory生成的。
         * HttpsURLConnection提供了方法setSSLSocketFactory(SSLSocketFactory)设置它使用的SSLSocketFactory对象。
         * SSLSocketFactory通过SSLContext对象来获得，在初始化SSLContext对象时，可指定信任管理器对象。
         * */
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                new java.security.SecureRandom());

        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.setDoOutput(true);
        //设置请求头
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes(charset));
        // 刷新、关闭
        out.flush();
        out.close();
        InputStream is = conn.getInputStream();
        if (is != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            is.close();
            return outStream.toByteArray();
        }
        return null;
    }
    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException{
        String url = "http://zs.sdcom.gov.cn/ctweb/inf/collect/TRACE_HUMAN_NODE";
        PostJson pj = new PostJson();
        String content=pj.getbaowen();
        String charset="UTF-8";
        String a = pj.sendPost(url, content, charset);
        System.out.println(a);
    }
    //构造嵌套的JSON报文方式，即在new一个JSONObject,并返回报文字符串
    public  String getbaowen(){
        JSONObject dataMap=new JSONObject();
        List<JSONObject> listMap=new ArrayList<JSONObject>();
        JSONObject jsonObject=null;
        JSONObject map=new JSONObject();
        map.put("SEQ_ID","");//按照数据增量自动扩展
        map.put("CITY_CODE","371500");//聊城市城市编码 业户统一
        map.put("HUMAN_ID","372501197904038230");//身份证
        map.put("HUMAN_NAME","聊城经济技术开发区齐树金蘑菇专卖店");//自然人责任主体名称
        map.put("RECORD_DATE","2020-09-01");//备案日期
        map.put("AREA_CODE","371502");//所属地区编码
        map.put("HUMAN_NODE_ID","371500");//所属企业编码 业户统一
        map.put("HUMAN_NODE_NAME","周公河农贸城");//所属企业名称
        map.put("ADDRESS","山东省聊城市东昌府区周公河农贸城");//通讯地址
        map.put("CONTACTS","齐树金");//责任主体联系人名称
        map.put("TEL","18263573685");//责任主体联系人电话
        map.put("FAX","");//传真
        map.put("ENTERPRISE_TYPE","20");//经营类型
        map.put("UPDATE_TIME", DateUtils.getTime());//上传日期
        listMap.add(map);
        dataMap.put("BIZ_TYPE","cityTraceRequest");
        dataMap.put("REQ_TIME",DateUtils.getTime());
        dataMap.put("REQ_ID",DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
        dataMap.put("AUTH_ID","20201111110432620977");
        dataMap.put("PARAM",listMap);
        String resp= null;
        String query = dataMap.toString();
        return query;
    }
}
