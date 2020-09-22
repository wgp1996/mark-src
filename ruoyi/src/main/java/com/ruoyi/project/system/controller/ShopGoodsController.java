package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.GoodsInfoOwner;
import com.ruoyi.project.system.domain.LabelInfo;
import com.ruoyi.project.system.service.IGoodsInfoOwnerService;
import com.ruoyi.project.system.service.ILabelInfoService;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.ShopGoods;
import com.ruoyi.project.system.service.IShopGoodsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 门店商品管理Controller
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@RestController
@RequestMapping("/system/shopGoods")
public class ShopGoodsController extends BaseController
{
    @Autowired
    private IShopGoodsService shopGoodsService;
    @Autowired
    private IGoodsInfoOwnerService goodsInfoOwnerService;
    @Autowired
    private ILabelInfoService labelInfoService;
    /**
     * 查询门店商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoods:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(ShopGoods shopGoods)
    {
        startPage();
        List<ShopGoods> list = shopGoodsService.selectShopGoodsList(shopGoods);
        return getDataTable(list);
    }

    /**
     * 导出门店商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoods:export')")
    @Log(title = "门店商品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ShopGoods shopGoods)
    {
        List<ShopGoods> list = shopGoodsService.selectShopGoodsList(shopGoods);
        ExcelUtil<ShopGoods> util = new ExcelUtil<ShopGoods>(ShopGoods.class);
        return util.exportExcel(list, "shopGoods");
    }

    /**
     * 获取门店商品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(shopGoodsService.selectShopGoodsById(id));
    }

    /**
     * 新增门店商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoods:add')")
    @Log(title = "门店商品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoods shopGoods)
    {
        //查询该商品是否存在,存在修改，不存在信息
        int result=shopGoodsService.checkShopGoods(shopGoods.getStoreid(),shopGoods.getGoodsCode());
        GoodsInfoOwner goods=goodsInfoOwnerService.selectGoodsInfoOwnerByCode(-1,shopGoods.getGoodsCode(),SecurityUtils.getUsername());
        if(goods!=null){
                String token=HttpUtils.getToken();
                HashMap map=new HashMap();
                List<HashMap> listGoods=new ArrayList<HashMap>();
                HashMap info=new HashMap();
                info.put("barcode", goods.getGoodsCode());//条码
                info.put("qrcode","");//二维码 链接地址
                info.put("label1",  goods.getGoodsCode());//id
                info.put("label2","");//二维码文本
                info.put("label3", goods.getGoodsCode());//编号
                info.put("label4", goods.getGoodsName());//名称
                info.put("label5", goods.getGoodsName());//简称
                info.put("label6", shopGoods.getGoodsPrice());//价格
                info.put("label7",goods.getGoodsGg());//品牌
                info.put("label8", goods.getGoodsGg());//规格
                info.put("label9", "");//等级
                info.put("label10", goods.getGoodsDw());//单位
                info.put("label11", goods.getGoodsAddress());//产地
                info.put("label12", "");//供货商
                info.put("label13", shopGoods.getGoodsHyPrice());//会员单价
                listGoods.add(info);
                map.put("storeUuid", shopGoods.getStoreid());
                map.put("goods",listGoods);
                try{
                    JSONObject jsonObject=new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/good",JSONValue.toJSONString(map).toString(),token));
                    System.out.println(jsonObject);
                    int status=jsonObject.getInt("status");
                    if(status==200){
                        //查询该商品是否绑定了价签 如果绑定则刷新模板
                        int label=shopGoodsService.checkShopGoodsLabel(shopGoods.getGoodsCode());
                        if(label>0){
                           //查询绑定的价签信息
                           LabelInfo label_info=labelInfoService.selectLabelInfoByGoodsCode(shopGoods.getGoodsCode(),shopGoods.getStoreid());
                            //更改模板信息
                            HashMap labelMap=new HashMap();
                            labelMap.put("storeId",shopGoods.getStoreid());//门店编号
                            labelMap.put("barcode",shopGoods.getGoodsCode());//商品编码
                            labelMap.put("mac", label_info.getMac());//mac
                            labelMap.put("demoId", label_info.getDemoid());//模板id
                            JSONObject labeljsonObject=new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/pub/bind/update", JSONValue.toJSONString(labelMap),token));
                            int labelstatus=labeljsonObject.getInt("status");
                            if(labelstatus!=200){
                                return AjaxResult.error("模板更新失败!");
                            }
                        }
                        if(result>0){
                            //修改商品信息
                            return toAjax(shopGoodsService.updateShopGoods(shopGoods));
                        }else{
                            //添加商品信息
                            shopGoods.setId(StringUtils.getId());
                            shopGoods.setCreateBy(SecurityUtils.getUsername());
                            return toAjax(shopGoodsService.insertShopGoods(shopGoods));
                        }
                    } else{
                        return AjaxResult.error("云商品更新失败");
                    }
                }catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    return AjaxResult.error("云商品更新失败!");
                }

        }else{
            return  AjaxResult.error("商品不存在");
        }
    }
    /**
     * 修改门店商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoods:edit')")
    @Log(title = "门店商品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoods shopGoods)
    {
        //查询该商品是否存在,存在修改，不存在信息
        int result=shopGoodsService.checkShopGoods(shopGoods.getStoreid(),shopGoods.getGoodsCode());
        GoodsInfoOwner goods=goodsInfoOwnerService.selectGoodsInfoOwnerByCode(-1,shopGoods.getGoodsCode(),SecurityUtils.getUsername());
        if(goods!=null){
            String token=HttpUtils.getToken();
            HashMap map=new HashMap();
            List<HashMap> listGoods=new ArrayList<HashMap>();
            HashMap info=new HashMap();
            info.put("barcode", goods.getGoodsCode());//条码
            info.put("qrcode","");//二维码 链接地址
            info.put("label1",  goods.getGoodsCode());//id
            info.put("label2","");//二维码文本
            info.put("label3", goods.getGoodsCode());//编号
            info.put("label4", goods.getGoodsName());//名称
            info.put("label5", goods.getGoodsName());//简称
            info.put("label6", shopGoods.getGoodsPrice());//价格
            info.put("label7",goods.getGoodsGg());//品牌
            info.put("label8", goods.getGoodsGg());//规格
            info.put("label9", "");//等级
            info.put("label10", goods.getGoodsDw());//单位
            info.put("label11", goods.getGoodsAddress());//产地
            info.put("label12", "");//供货商
            info.put("label13", shopGoods.getGoodsHyPrice());//会员单价
            listGoods.add(info);
            map.put("storeUuid", shopGoods.getStoreid());
            map.put("goods",listGoods);
            try{
                JSONObject jsonObject=new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/good",JSONValue.toJSONString(map).toString(),token));
                System.out.println(jsonObject);
                int status=jsonObject.getInt("status");
                if(status==200){
                    //查询该商品是否绑定了价签 如果绑定则刷新模板
                    int label=shopGoodsService.checkShopGoodsLabel(shopGoods.getGoodsCode());
                    if(label>0){
                        //查询绑定的价签信息
                        LabelInfo label_info=labelInfoService.selectLabelInfoByGoodsCode(shopGoods.getGoodsCode(),shopGoods.getStoreid());
                        //更改模板信息
                        HashMap labelMap=new HashMap();
                        labelMap.put("storeId",shopGoods.getStoreid());//门店编号
                        labelMap.put("barcode",shopGoods.getGoodsCode());//商品编码
                        labelMap.put("mac", label_info.getMac());//mac
                        labelMap.put("demoId", label_info.getDemoid());//模板id
                        JSONObject labeljsonObject=new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/pub/bind/update", JSONValue.toJSONString(labelMap),token));
                        int labelstatus=labeljsonObject.getInt("status");
                        if(labelstatus!=200){
                            return AjaxResult.error("模板更新失败!");
                        }
                    }
                    if(result>0){
                        //修改商品信息
                        return toAjax(shopGoodsService.updateShopGoods(shopGoods));
                    }else{
                        //添加商品信息
                        shopGoods.setId(StringUtils.getId());
                        shopGoods.setCreateBy(SecurityUtils.getUsername());
                        return toAjax(shopGoodsService.insertShopGoods(shopGoods));
                    }
                } else{
                    return AjaxResult.error("云商品更新失败");
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return AjaxResult.error("云商品更新失败!");
            }

        }else{
            return  AjaxResult.error("商品不存在");
        }
    }

    /**
     * 删除门店商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoods:remove')")
    @Log(title = "门店商品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        HashMap map=new HashMap();
        List<HashMap> listGoods=new ArrayList<HashMap>();
        String storeId="";
        String [] goodsList=new String[ids.length];
        String token=HttpUtils.getToken();
        for(int i=0;i<ids.length;i++){
            ShopGoods goods=shopGoodsService.selectShopGoodsById(ids[i]);
            storeId=goods.getStoreid();
            HashMap info=new HashMap();
            info.put("barcode",goods.getGoodsCode());
            goodsList[i]=goods.getGoodsCode();
            listGoods.add(info);
        }
        map.put("goods", listGoods);
        map.put("storeUuid",storeId);
        System.out.println(JSONValue.toJSONString(map).toString());
        JSONObject jsonObject=new JSONObject(HttpUtils.sendDelete("http://esl.ylwlesl.com:9191/V2/good",JSONValue.toJSONString(map).toString(),token));
        int status=jsonObject.getInt("status");
        if(status>0){
            //修改价签绑定的商品为null
            labelInfoService.updateLabelInfoStatus(goodsList,storeId);
            return toAjax(shopGoodsService.deleteShopGoodsByIds(ids));
        }else{
            return AjaxResult.error("价签更新失败");
        }
    }
}
