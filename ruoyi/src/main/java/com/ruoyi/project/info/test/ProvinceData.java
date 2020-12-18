package com.ruoyi.project.info.test;
import com.google.gson.JsonArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.sign.SHA256;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.domain.OwnerInfo;
import com.ruoyi.project.system.domain.RandomInspectionInfoChild;
import com.ruoyi.project.system.domain.WholeSalesChild;
import com.ruoyi.project.system.service.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Component("uploadTask")
public class ProvinceData {
    protected static String AUTH_ID="20201111110432620977";//认证编码
    protected static String KEY="QxYTWzNxoLTT9IEDsmrUujBPDXjt6VPD";//key

    @Autowired
    private IOwnerInfoService ownerInfoService;
    @Autowired
    private IRandomInspectionInfoChildService randomInspectionInfoChildService;
    @Autowired
    private IWholeSalesChildService wholeSalesChildService;
    @Autowired
    private ICgRkdService cgRkdService;

    //企业备案信息接口
    public void companyList()
    {
        OwnerInfo ownerInfo=new OwnerInfo();
        ownerInfo.setOwnerOrg("法人单位");//企业
        ownerInfo.setIsUpload(0);
        List<OwnerInfo> ownerList=ownerInfoService.selectOwnerInfoList(ownerInfo);
        HashMap dataMap=new HashMap();
        HashMap paramMap=new HashMap();
        List<HashMap> listMap=new ArrayList<HashMap>();
        JSONObject jsonObject=null;
        int i=0;
        for(OwnerInfo info:ownerList){
            if(info.getOwnerPersonId()!=null&&info.getOwnerPersonId()!=""&&info.getOwnerName()!=null&&info.getOwnerLxrPhone()!=null&&info.getOwnerLxrPhone()!=""
                    &&info.getOwnerName()!=""&&info.getOwnerLxr()!=null&&info.getOwnerLxr()!="") {
                HashMap map = new HashMap();
                map.put("SEQ_ID", "");//按照数据增量自动扩展
                map.put("CITY_CODE", "371500");//聊城市城市编码 业户统一
                map.put("SOCIAL_CREDIT_CODE", info.getCreditCode());//统一社会信用代码，若无统一社会信用代码填写组织机构代码
                map.put("COMP_NAME", info.getOwnerName());//负责人责任主体名称
                map.put("LEGAL_TYPE", "2");//1 企业法人、2 个体工商户
                map.put("LEGAL_REPRESENT", info.getOwnerLxr());//法人代码名称
                map.put("LEGAL_ID", info.getOwnerPersonId());//法人身份证号码
                map.put("RECORD_DATE", "2020-09-01");//备案日期
                map.put("ISSUING_TIME", "2020-09-01");//注册日期
                map.put("TERM_OF_VALIDITY", "2025-09-01");//证书有效日期
                map.put("AREA_CODE", "371502");//所属地区编码
                map.put("RECORD_NODE_ID", "371500");//所属企业编码
                map.put("RECORD_NODE_NAME", "山东省聊城市");//所属企业名称
                map.put("ADDRESS", "山东省聊城市东昌府区周公河农贸城");//经营地址
                map.put("LONGITUDE", "36.512661");//周公河地址纬度
                map.put("LATITUDE", "116.005918");//周公河地址经度
                map.put("CONTACTS", ownerInfo.getOwnerLxr());//责任主体联系人名称
                map.put("TEL", ownerInfo.getOwnerLxrPhone());//责任主体联系人电话
                map.put("FAX", "");//传真
                map.put("REG_TYPE", "01");//备案类型 00 节点
                map.put("ENTERPRISE_TYPE", "20");//经营类型
                map.put("UPDATE_TIME", DateUtils.getTime());//传真
                listMap.add(map);
                dataMap.put("BIZ_TYPE", "extendCityTraceRequest");
                dataMap.put("REQ_TIME", DateUtils.getTime());
                dataMap.put("REQ_ID", DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
                dataMap.put("AUTH_ID", AUTH_ID);
                paramMap.put("DATA", listMap);
                paramMap.put("TABLE_NAME", "TRACE_BASE_NODE");
                dataMap.put("PARAM", paramMap);
                try {
                    String jsonStr = JSONValue.toJSONString(dataMap);
                    jsonStr = new String(jsonStr.getBytes("UTF-8"), "UTF-8");
                    String sign = SHA256.getSHA256(jsonStr + KEY);
                    jsonStr += sign;
                    jsonObject = new JSONObject(HttpUtils.sendPost("http://zs.sdcom.gov.cn/ctweb/inf/collect", new String(jsonStr.getBytes("UTF-8"), "UTF-8"), ""));
                    JSONObject jsonObj = jsonObject.getJSONObject("RESULT");
                    System.out.println(jsonObject);
                    String code = jsonObj.getString("RESULT_CODE");
                    System.out.println(code);
                    if ("0000".equals(code)) {
                        JSONArray array = jsonObj.getJSONArray("RESULT_LIST");
                        if (array.length() > 0) {
                            String resultCode = array.getJSONObject(i).getString("RETURN_CODE");
                            System.out.println(resultCode);
                            if ("0000".equals(resultCode)) {
                                //成功修改状态
                                ownerInfoService.updateOwnerIsUpload(info.getId());
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                i++;
            }else{
                continue;
            }
        }

    }

    //自然人责任主体信息接口
    public void ownerList()
    {
        System.out.println("自然人责任主体信息接口开始上传----");
        OwnerInfo ownerInfo=new OwnerInfo();
        ownerInfo.setOwnerOrg("个人代表");
        ownerInfo.setIsUpload(0);
        List<OwnerInfo> ownerList=ownerInfoService.selectOwnerInfoList(ownerInfo);
        System.out.println(ownerList.size());
        HashMap dataMap=new HashMap();
        HashMap paramMap=new HashMap();
        List<HashMap> listMap=new ArrayList<HashMap>();
        JSONObject jsonObject=null;
        int i=0;
        for(OwnerInfo info:ownerList){
            if(info.getOwnerPersonId()!=null&&info.getOwnerPersonId()!=""&&info.getOwnerName()!=null&&info.getOwnerLxrPhone()!=null&&info.getOwnerLxrPhone()!=""
                    &&info.getOwnerName()!=""&&info.getOwnerLxr()!=null&&info.getOwnerLxr()!=""){
                HashMap map=new HashMap();
                map.put("SEQ_ID","");//按照数据增量自动扩展
                map.put("CITY_CODE","371500");//聊城市城市编码 业户统一
                map.put("HUMAN_ID",info.getOwnerPersonId());//身份证
                map.put("HUMAN_NAME",info.getOwnerName());//自然人责任主体名称
                map.put("RECORD_DATE","2020-09-01");//备案日期
                map.put("AREA_CODE","371502");//所属地区编码
                map.put("HUMAN_NODE_ID","371500");//所属企业编码 业户统一
                map.put("HUMAN_NODE_NAME","周公河农贸城");//所属企业名称
                map.put("ADDRESS","山东省聊城市东昌府区周公河农贸城");//通讯地址
                map.put("CONTACTS",info.getOwnerLxr());//责任主体联系人名称
                map.put("TEL",info.getOwnerLxrPhone());//责任主体联系人电话
                map.put("FAX","");//传真
                map.put("ENTERPRISE_TYPE","20");//经营类型
                map.put("UPDATE_TIME", DateUtils.getTime());//上传日期
                listMap.add(map);
                dataMap.put("BIZ_TYPE","extendCityTraceRequest");
                dataMap.put("REQ_TIME",DateUtils.getTime());
                dataMap.put("REQ_ID",DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
                dataMap.put("AUTH_ID",AUTH_ID);
                paramMap.put("DATA",listMap);
                paramMap.put("TABLE_NAME","TRACE_HUMAN_NODE");
                dataMap.put("PARAM",paramMap);
                try {
                    String jsonStr=JSONValue.toJSONString(dataMap);
                    jsonStr= new String(jsonStr.getBytes("UTF-8"),"UTF-8");
                    String sign= SHA256.getSHA256(jsonStr+KEY);
                    jsonStr+=sign;
                    jsonObject = new JSONObject(HttpUtils.sendPost("http://zs.sdcom.gov.cn/ctweb/inf/collect",new String(jsonStr.getBytes("UTF-8"),"UTF-8"),""));
                    JSONObject jsonObj=jsonObject.getJSONObject("RESULT");
                    String code=jsonObj.getString("RESULT_CODE");
                    System.out.println(code);
                    if("0000".equals(code)){
                        //成功修改状态
                        JSONArray array=jsonObj.getJSONArray("RESULT_LIST");
                        if(array.length()>0){
                            String resultCode=array.getJSONObject(i).getString("RETURN_CODE");
                            System.out.println(resultCode);
                            if("0000".equals(resultCode)){
                                //成功修改状态
                                ownerInfoService.updateOwnerIsUpload(info.getId());
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                i++;
            }else {
                continue;
            }
        }
        System.out.println("自然人责任主体信息接口结束----");
    }


    //主体类型信息接口
    public void ownerType()
    {
        OwnerInfo ownerInfo=new OwnerInfo();
        List<OwnerInfo> ownerList=ownerInfoService.selectOwnerInfoList(ownerInfo);
        System.out.println(ownerList.size());
        HashMap dataMap=new HashMap();
        HashMap paramMap=new HashMap();
        List<HashMap> listMap=new ArrayList<HashMap>();
        JSONObject jsonObject=null;
        int i=0;
        for(OwnerInfo info:ownerList){
            if(info.getOwnerPersonId()!=null&&info.getOwnerPersonId()!=""&&info.getOwnerOrg()!=null&&info.getOwnerOrg()!=""){
                HashMap map=new HashMap();
                map.put("SEQ_ID","");//按照数据增量自动扩展
                map.put("CITY_CODE","371500");//聊城市城市编码 业户统一
                if("个人代表".equals(info.getOwnerOrg())){
                    map.put("SOCIAL_CREDIT_CODE",info.getOwnerPersonId());//身份证
                    map.put("INDUSTRY_CODE","F5123");//企业类型或自然人从事行业代码代码  F5123 果品蔬菜批发
                    map.put("MAIN_TYPE","2");//主体类别  1 法人责任主体、2 自然人责任主体
                }else{
                    map.put("SOCIAL_CREDIT_CODE",info.getCreditCode());//信用代码
                    map.put("INDUSTRY_CODE","F5123");//自企业类型或自然人从事行业代码代码  F5123 果品蔬菜批发
                    map.put("MAIN_TYPE","1");//主体类别 1 法人责任主体、2 自然人责任主体
                }
                map.put("UPDATE_TIME", DateUtils.getTime());//上传日期
                listMap.add(map);
                dataMap.put("BIZ_TYPE","extendCityTraceRequest");
                dataMap.put("REQ_TIME",DateUtils.getTime());
                dataMap.put("REQ_ID",DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
                dataMap.put("AUTH_ID",AUTH_ID);
                paramMap.put("DATA",listMap);
                paramMap.put("TABLE_NAME","TRACE_BASE_TYPE");
                dataMap.put("PARAM",paramMap);
                try {
                    String jsonStr=JSONValue.toJSONString(dataMap);
                    jsonStr= new String(jsonStr.getBytes("UTF-8"),"UTF-8");
                    String sign= SHA256.getSHA256(jsonStr+KEY);
                    jsonStr+=sign;
                    jsonObject = new JSONObject(HttpUtils.sendPost("http://zs.sdcom.gov.cn/ctweb/inf/collect",new String(jsonStr.getBytes("UTF-8"),"UTF-8"),""));
                    JSONObject jsonObj=jsonObject.getJSONObject("RESULT");
                    String code=jsonObj.getString("RESULT_CODE");
                    System.out.println(code);
                    if("0000".equals(code)){
                        //成功修改状态
                        JSONArray array=jsonObj.getJSONArray("RESULT_LIST");
                        if(array.length()>0){
                            String resultCode=array.getJSONObject(i).getString("RETURN_CODE");
                            System.out.println(resultCode);
                            if("0000".equals(resultCode)){
                                //成功修改状态
                                //ownerInfoService.updateOwnerIsUpload(info.getId());
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                i++;
            }else {
                continue;
            }
        }
    }

    //追溯品种类型信息接口
    public void ownerGoodsType()
    {
        OwnerInfo ownerInfo=new OwnerInfo();
        List<OwnerInfo> ownerList=ownerInfoService.selectOwnerInfoList(ownerInfo);
        System.out.println(ownerList.size());
        HashMap dataMap=new HashMap();
        HashMap paramMap=new HashMap();
        List<HashMap> listMap=new ArrayList<HashMap>();
        JSONObject jsonObject=null;
        int i=0;
        for(OwnerInfo info:ownerList){
            if(info.getOwnerPersonId()!=null&&info.getOwnerPersonId()!=""&&info.getOwnerOrg()!=null&&info.getOwnerOrg()!=""){
                HashMap map=new HashMap();
                map.put("SEQ_ID","");//按照数据增量自动扩展
                map.put("CITY_CODE","371500");//聊城市城市编码 业户统一
                if("个人代表".equals(info.getOwnerOrg())){
                    map.put("SOCIAL_CREDIT_CODE",info.getOwnerPersonId());//身份证
                    map.put("TRACE_VARIETY_CODE","");//追溯品种类型：使用GB/T7635.1的产品代码前6位。
                    map.put("MAIN_TYPE","2");//主体类别
                }else{
                    map.put("SOCIAL_CREDIT_CODE",info.getCreditCode());//信用代码
                    map.put("TRACE_VARIETY_CODE","");//追溯品种类型：使用GB/T7635.1的产品代码前6位。
                    map.put("MAIN_TYPE","1");//主体类别
                }
                map.put("UPDATE_TIME", DateUtils.getTime());//上传日期
                listMap.add(map);
                dataMap.put("BIZ_TYPE","extendCityTraceRequest");
                dataMap.put("REQ_TIME",DateUtils.getTime());
                dataMap.put("REQ_ID",DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
                dataMap.put("AUTH_ID",AUTH_ID);
                paramMap.put("DATA",listMap);
                paramMap.put("TABLE_NAME","TRACE_VARIETY_TYPE");
                dataMap.put("PARAM",paramMap);
                try {
                    String jsonStr=JSONValue.toJSONString(dataMap);
                    jsonStr= new String(jsonStr.getBytes("UTF-8"),"UTF-8");
                    String sign= SHA256.getSHA256(jsonStr+KEY);
                    jsonStr+=sign;
                    jsonObject = new JSONObject(HttpUtils.sendPost("http://zs.sdcom.gov.cn/ctweb/inf/collect",new String(jsonStr.getBytes("UTF-8"),"UTF-8"),""));
                    JSONObject jsonObj=jsonObject.getJSONObject("RESULT");
                    String code=jsonObj.getString("RESULT_CODE");
                    System.out.println(code);
                    if("0000".equals(code)){
                        //成功修改状态
                        JSONArray array=jsonObj.getJSONArray("RESULT_LIST");
                        if(array.length()>0){
                            String resultCode=array.getJSONObject(i).getString("RETURN_CODE");
                            System.out.println(resultCode);
                            if("0000".equals(resultCode)){
                                //成功修改状态
                                //ownerInfoService.updateOwnerIsUpload(info.getId());
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                i++;
            }else {
                continue;
            }
        }
    }


    //检测信息接口
    public void randomList()
    {
        RandomInspectionInfoChild randomInspectionInfoChild=new RandomInspectionInfoChild();
        List<RandomInspectionInfoChild> randomList=randomInspectionInfoChildService.selectRandomInspectionInfoAllList(randomInspectionInfoChild);
        HashMap dataMap=new HashMap();
        HashMap paramMap=new HashMap();
        List<HashMap> listMap=new ArrayList<HashMap>();
        JSONObject jsonObject=null;
        int i=0;
        for(RandomInspectionInfoChild info:randomList){
            if(info.getGoodsCode()!=null&&info.getGoodsCode()!=""&&info.getGoodsName()!=null
                    &&info.getGoodsName()!=""&&info.getCheckResult()!=null){
                HashMap map=new HashMap();
                //暂无业户信息的跳出检测
//                if(info.getOwnerName().equals("admin")){
//                    continue;
//                }
                map.put("SEQ_ID","");//按照数据增量自动扩展
                map.put("CITY_CODE","371500");//聊城市城市编码 业户统一
                if(info.getOwnerCode().equals("admin")){
                    OwnerInfo owner=ownerInfoService.selectOwnerInfoByCode(info.getOwnerCode(),"");
                    map.put("MARKET_ID","91371500MA3F247D9L");//若为法人责任主体则填写供应商统一社会信用代码或组织机构代码，若为自然人责任主体则填写自然人责任主体身份证号码
                    map.put("MARKET_NAME","周公河农贸批发市场");//批发市场名称
                    map.put("WHOLESALER_ID","372526197711245310");
                    map.put("WHOLESALER_NAME","聊城经济技术开发区王连凯果蔬批发部");//批发商名称 默认王连凯
                }else{
                    OwnerInfo owner=ownerInfoService.selectOwnerInfoByCode(info.getOwnerCode(),"");
                    map.put("MARKET_ID","91371500MA3F247D9L");//若为法人责任主体则填写供应商统一社会信用代码或组织机构代码，若为自然人责任主体则填写自然人责任主体身份证号码
                    map.put("MARKET_NAME","周公河农贸批发市场");//批发市场名称
                    if("个人代表".equals(owner.getOwnerOrg())){
                        map.put("WHOLESALER_ID",owner.getOwnerPersonId());//若为法人责任主体则填写供应商统一社会信用代码或组织机构代码，若为自然人责任主体则填写自然人责任主体身份证号码
                    }else{
                        map.put("WHOLESALER_ID",owner.getCreditCode());//若为法人责任主体则填写供应商统一社会信用代码或组织机构代码，若为自然人责任主体则填写自然人责任主体身份证号码
                    }
                    map.put("WHOLESALER_NAME",info.getOwnerName());//批发商名称
                }
                map.put("BATCH_ID",info.getDjNumber());//批次号
                map.put("VOUCHER_TYPE",3);//凭证类型
                map.put("VARIETY_CODE",info.getGoodsCode());//商品编码+三位随机数
                map.put("VARIETY_NAME",info.getGoodsName());//商品名称
                map.put("SAMPLE_ID",info.getGoodsCode());//样品编码
                map.put("DETECTOR","刘亚景");//检测员  刘亚景
                map.put("DETECTION_DATE",info.getCreateTime());//抽检日期
                map.put("DETECTION_RESULT",info.getCheckResult());//抽检结果
                map.put("COMPANY","周公河农贸批发市场");//检测单位
                map.put("INHIBIT",info.getInhibitionNum());//抑制率
                map.put("VERIFY_IMG","");//检测报告图片
                map.put("RESULT_EXPL",info.getRemark());//检测结果说明
                map.put("UPDATE_TIME", DateUtils.getTime());//上传日期
                listMap.add(map);
                dataMap.put("BIZ_TYPE","extendCityTraceRequest");
                dataMap.put("REQ_TIME",DateUtils.getTime());
                dataMap.put("REQ_ID",DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
                dataMap.put("AUTH_ID",AUTH_ID);
                paramMap.put("DATA",listMap);
                paramMap.put("TABLE_NAME","TRACE_MARKET_DETECTION");
                dataMap.put("PARAM",paramMap);
                try {
                    String jsonStr=JSONValue.toJSONString(dataMap);
                    jsonStr= new String(jsonStr.getBytes("UTF-8"),"UTF-8");
                    String sign= SHA256.getSHA256(jsonStr+KEY);
                    jsonStr+=sign;
                    System.out.println(jsonStr);
                    jsonObject = new JSONObject(HttpUtils.sendPost("http://zs.sdcom.gov.cn/ctweb/inf/collect",new String(jsonStr.getBytes("UTF-8"),"UTF-8"),""));
                    JSONObject jsonObj=jsonObject.getJSONObject("RESULT");
                    String code=jsonObj.getString("RESULT_CODE");
                    System.out.println(code);
                    if("0000".equals(code)){
                        //成功修改状态
                        JSONArray array=jsonObj.getJSONArray("RESULT_LIST");
                        if(array.length()>0){
                            String resultCode=array.getJSONObject(i).getString("RETURN_CODE");
                            System.out.println(resultCode);
                            if("0000".equals(resultCode)){
                                //成功修改状态
                                //ownerInfoService.updateOwnerIsUpload(info.getId());
                            }
                        }
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                i++;
            }else {
                continue;
            }
        }
    }

    //进场信息接口
    public void rkdList()
    {
        CgRkd cgRkd=new CgRkd();
        List<CgRkd> rkdList=cgRkdService.selectCgRkdAllList(cgRkd);
        HashMap dataMap=new HashMap();
        List<HashMap> listMap=new ArrayList<HashMap>();
        JSONObject jsonObject=null;
        for(CgRkd info:rkdList){
            if(info.getGoodsName()!=null
                    &&info.getGoodsName()!=""){
                HashMap map=new HashMap();
                map.put("SEQ_ID","");//按照数据增量自动扩展
                map.put("CITY_CODE","371500");//聊城市城市编码 业户统一
                map.put("MARKET_ID","91371500MA3F247D9L");//若为法人责任主体则填写供应商统一社会信用代码或组织机构代码，若为自然人责任主体则填写自然人责任主体身份证号码
                map.put("MARKET_NAME","周公河农贸城");//批发市场名称
                map.put("IN_DATE",info.getDjTime());//进场日期(yyyy-mm-dd
                map.put("WHOLESALER_ID","");//批发商身份证编码
                map.put("WHOLESALER_NAME","");//批发商名称
                map.put("BATCH_ID","");//批次号
                map.put("VOUCHER_TYPE",3);//凭证类型
                map.put("VARIETY_CODE","");//商品编码
                map.put("VARIETY_NAME",info.getGoodsName());//商品名称
                map.put("WEIGHT","");//重量
                map.put("AREA_ORIGIN_ID","");//产地编码
                map.put("AREA_ORIGIN_NAME",info.getGoodsAddress());//产地名称
                map.put("ENTERPRISE_CODE","");//种植者编码/养殖者编码
                map.put("INHIBIT","周公河农贸批发市场");//种植者名称/养殖者名称
                map.put("VENDER_CODE","");//供货市场编码
                map.put("RESULT_EXPL",info.getRemark());//检测结果说明
                map.put("UPDATE_TIME", DateUtils.getTime());//上传日期
                listMap.add(map);
            }else {
                continue;
            }
        }
        dataMap.put("BIZ_TYPE","");
        dataMap.put("REQ_TIME",DateUtils.getTime());
        dataMap.put("REQ_ID",DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
        dataMap.put("AUTH_ID",AUTH_ID);
        dataMap.put("PARAM",listMap);
        try {
            jsonObject = new JSONObject(HttpUtils.sendPost("http://zs.sdcom.gov.cn/ctweb/inf/collect/TRACE_MARKET_DETECTION", JSONValue.toJSONString(listMap),""));
            JSONObject bodyObject=new JSONObject(jsonObject.get("RESULT_CODE").toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //交易信息接口
    public void wholeList()
    {
        WholeSalesChild wholeSalesChild=new WholeSalesChild();
        List<WholeSalesChild> wholeList=wholeSalesChildService.selectWholeAllList(wholeSalesChild);
        HashMap dataMap=new HashMap();
        List<HashMap> listMap=new ArrayList<HashMap>();
        JSONObject jsonObject=null;
        for(WholeSalesChild info:wholeList){
            if(info.getGoodsCode()!=null&&info.getGoodsCode()!=""&&info.getGoodsName()!=null
                    &&info.getGoodsName()!=""&&info.getKhCode()!=null){
                HashMap map=new HashMap();
                map.put("SEQ_ID","");//按照数据增量自动扩展
                map.put("CITY_CODE","371500");//聊城市城市编码 业户统一
                map.put("MARKET_ID","91371500MA3F247D9L");//若为法人责任主体则填写供应商统一社会信用代码或组织机构代码，若为自然人责任主体则填写自然人责任主体身份证号码
                map.put("MARKET_NAME","周公河农贸城");//批发市场名称
                map.put("TRANSANTION_DATE",info.getRemark());//交易日期
                map.put("WHOLESALER_ID","");//批发商身份证编码
                map.put("WHOLESALER_NAME","");//批发商名称
                map.put("RETAILER_ID","");//零售商身份证编码
                map.put("RETAILER_NAME","");//零售商名称
                map.put("VARIETY_CODE","");//品种编码
                map.put("VARIETY_NAME","");//品种名称
                map.put("BATCH_ID","");//批次号
                map.put("TRAN_ID","");//凭证号
                map.put("WEIGHT","");//重量
                map.put("PRICE","");//单价
                map.put("DEST","");//到达地地区编码
                map.put("UPDATE_TIME", DateUtils.getTime());//上传日期
                listMap.add(map);
            }else {
                continue;
            }
        }
        dataMap.put("BIZ_TYPE","");
        dataMap.put("REQ_TIME",DateUtils.getTime());
        dataMap.put("REQ_ID",DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
        dataMap.put("AUTH_ID",AUTH_ID);
        dataMap.put("PARAM",listMap);
        try {
            jsonObject = new JSONObject(HttpUtils.sendPost("http://zs.sdcom.gov.cn/ctweb/inf/collect/TRACE_MARKET_DETECTION", JSONValue.toJSONString(listMap),""));
            JSONObject bodyObject=new JSONObject(jsonObject.get("RESULT_CODE").toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HashMap paramMap=new HashMap();
        HashMap dataMap=new HashMap();
        List<HashMap> listMap=new ArrayList<HashMap>();
        JSONObject jsonObject=null;
        HashMap map=new HashMap();
        map.put("SEQ_ID", "");//按照数据增量自动扩展
        map.put("CITY_CODE", "371500");//聊城市城市编码 业户统一
        map.put("SOCIAL_CREDIT_CODE","913715006619506367");//统一社会信用代码，若无统一社会信用代码填写组织机构代码 百大 913715006619506367 亿丰 91371500730650857Y
        map.put("COMP_NAME","山东省聊城百大连锁有限公司");//负责人责任主体名称 百大 山东省聊城百大连锁有限公司 亿丰 山东聊城亿沣连锁超市有限公司
        map.put("LEGAL_TYPE", "1");//1 企业法人、2 个体工商户
        map.put("LEGAL_REPRESENT", "王媛媛");//法人代码名称  王媛媛  王广胜
        map.put("LEGAL_ID", "372522198210214224");//法人身份证号码  百大 372522198210214224  亿丰  372501197303282035
        map.put("RECORD_DATE", "2020-09-01");//备案日期
        map.put("ISSUING_TIME", "2020-09-01");//注册日期
        map.put("TERM_OF_VALIDITY", "2025-09-01");//证书有效日期
        map.put("AREA_CODE", "371502");//所属地区编码
        map.put("RECORD_NODE_ID", "371500");//所属企业编码
        map.put("RECORD_NODE_NAME", "山东省聊城百大连锁有限公司");//所属企业名称
        map.put("ADDRESS", "山东省聊城市东昌西路2号");//经营地址 百大 山东省聊城市东昌西路2号  聊城市东昌府区陈口路金柱大学城B区商业楼
        map.put("LONGITUDE", "115.959392");//周公河地址纬度 百大地址115.959392,36.462228 亿丰 116.02146,36.445971
        map.put("LATITUDE", "36.462228");//周公河地址经度
        map.put("CONTACTS", "王媛媛");//责任主体联系人名称 王媛媛
        map.put("TEL", "18663000502");//责任主体联系人电话 百大 18663000502 亿丰  19963579053
        map.put("FAX", "");//传真
        map.put("REG_TYPE", "00");//备案类型 00 节点
        map.put("ENTERPRISE_TYPE", "20");//经营类型
        map.put("UPDATE_TIME", DateUtils.getTime());//传真
        listMap.add(map);
        dataMap.put("BIZ_TYPE", "extendCityTraceRequest");
        dataMap.put("REQ_TIME", DateUtils.getTime());
        dataMap.put("REQ_ID", DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
        dataMap.put("AUTH_ID", AUTH_ID);
        paramMap.put("DATA", listMap);
        paramMap.put("TABLE_NAME", "TRACE_BASE_NODE");
        dataMap.put("PARAM", paramMap);
        listMap.add(map);
        dataMap.put("BIZ_TYPE","extendCityTraceRequest");
        dataMap.put("REQ_TIME",DateUtils.getTime());
        dataMap.put("REQ_ID",DateUtils.getRandomTime());//yyyyMMddHHmmss+6位字母数字组合随机码
        dataMap.put("AUTH_ID",AUTH_ID);
        paramMap.put("DATA",listMap);
        paramMap.put("TABLE_NAME","TRACE_BASE_NODE");
        dataMap.put("PARAM",paramMap);
        try {
            String jsonStr=JSONValue.toJSONString(dataMap);
            jsonStr= new String(jsonStr.getBytes("UTF-8"),"UTF-8");
            String sign= SHA256.getSHA256(jsonStr+KEY);
            System.out.println(sign);
            jsonStr+=sign;
            System.out.println(jsonStr);
            jsonObject = new JSONObject(HttpUtils.sendPost("http://zs.sdcom.gov.cn/ctweb/inf/collect",new String(jsonStr.getBytes("UTF-8"),"UTF-8"),""));
            System.out.println(jsonObject);
            JSONObject bodyObject=new JSONObject(jsonObject.get("RESULT").toString());
            //System.out.println(bodyObject.getString("RESULT_CODE"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
