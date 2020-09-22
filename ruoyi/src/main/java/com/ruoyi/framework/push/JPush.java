package com.ruoyi.framework.push;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;

public class JPush {
    // 周公河APP
    private static String APP_KEY = "7a9db0a98ebf60052cf6786b";
    private static String MASTER_SECRET = "aef9f77590cb224a64daa91e";

    // 百大APP
    private static String APP_KEY_BD = "77d88930f36394ac8b557a9d";
    private static String MASTER_SECRET_BD = "d3f3857f9d024fc2dec009e4";

    //极光推送>>Android
    //Map<String, String> parm自定义参数
    //type 0 周公河 1百大
    public static void jpushAndroid(Map<String, String> parm,Integer type) {
        System.out.println(parm.get("id"));
        System.out.println(parm.get("msg"));
        //创建JPushClient(极光推送的实例)
        String key="";
        String secret="";
        String title="";
        if(type==0){
             key=APP_KEY;
             secret=MASTER_SECRET;
             title="周公河食安追溯";
        }
        if(type==1){
            key=APP_KEY_BD;
            secret=MASTER_SECRET_BD;
            title="百大生鲜采购";
        }
        JPushClient jpushClient = new JPushClient(secret, key);
        //推送的关键,构造一个payload

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                //.setAudience(Audience.all())//你项目中的所有用户
                .setAudience(Audience.alias(parm.get("id")))//registrationId指定用户
                .setNotification(Notification.android(parm.get("msg"), title, parm))
                //发送内容//true-推送生产环境 false-推送开发环境（测试使用参数）
                /*.setNotification( Notification.newBuilder()
                .setAlert("alert content")
                .addPlatformNotification(AndroidNotification.newBuilder()
                        .setTitle("百大生鲜采购")
                        .setAlert(parm.get("msg"))

                        .setIntent()
                        .build())
                .build())*/
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                //这里是指定开发环境,不用设置也没关系
                .setMessage(Message.content(parm.get("msg")))//自定义信息
                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
            //System.out.println(pu);
        } catch (APIConnectionException e) {
            System.out.println("1");
            e.printStackTrace();
        } catch (APIRequestException e) {
            System.out.println(e.getStatus()+e.getMessage());
            e.printStackTrace();
        }
    }

    //极光推送>>ios
    //Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
    public static  void jpushIOS(Map<String, String> parm,Integer type) {
        //创建JPushClient
        String key="";
        String secret="";
        if(type==0){
            key=APP_KEY;
            secret=MASTER_SECRET;
        }
        if(type==1){
            key=APP_KEY_BD;
            secret=MASTER_SECRET_BD;
        }
        JPushClient jpushClient = new JPushClient(secret, key);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.ios())//ios平台的用户
                //.setAudience(Audience.all())//所有用户
                .setAudience(Audience.alias(parm.get("id")))//registrationId指定用户
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(parm.get("msg"))
                                .setBadge(+1)
                                .setSound("happy")//这里是设置提示音(更多可以去官网看看)
                                .addExtras(parm)
                                .build())
                        .build())//true-推送生产环境 false-推送开发环境（测试使用参数）
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
                .build();

        try {
            PushResult pu = jpushClient.sendPush(payload);

        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<String, String> parm=new HashMap<>();
        parm.put("id","10001");
        parm.put("msg","测试");
        jpushAndroid(parm,0);
    }
}
