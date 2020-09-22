package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.LabelInfo;
import com.ruoyi.project.system.service.ILabelInfoService;
import org.json.JSONArray;
import org.json.JSONObject;
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
import com.ruoyi.project.system.domain.ShopInfo;
import com.ruoyi.project.system.service.IShopInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 门店管理Controller
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@RestController
@RequestMapping("/system/shopInfo")
public class ShopInfoController extends BaseController
{
    @Autowired
    private IShopInfoService shopInfoService;
    @Autowired
    private ILabelInfoService labelInfoService;
    /**
     * 查询门店管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(ShopInfo shopInfo)
    {
        startPage();
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        return getDataTable(list);
    }

    /**
     * 查询门店管理列表
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/getShopList")
    public TableDataInfo getShopList(ShopInfo shopInfo)
    {
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        return getDataTable(list);
    }

    /**
     * 导出门店管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:export')")
    @Log(title = "门店管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ShopInfo shopInfo)
    {
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        ExcelUtil<ShopInfo> util = new ExcelUtil<ShopInfo>(ShopInfo.class);
        return util.exportExcel(list, "shopInfo");
    }

    /**
     * 获取门店管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(shopInfoService.selectShopInfoById(id));
    }

    /**
     * 新增门店管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:add')")
    @Log(title = "门店管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopInfo shopInfo)
    {
        //查询门店是否重复
        if(shopInfoService.checkShopInfo(shopInfo.getStoreid(),"")>0){
            return AjaxResult.error("该门店已存在");
        }
        shopInfo.setId(StringUtils.getId());
        return toAjax(shopInfoService.insertShopInfo(shopInfo));
    }

    /**
     * 修改门店管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:edit')")
    @Log(title = "门店管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopInfo shopInfo)
    {
        //查询门店是否重复
        if(shopInfoService.checkShopInfo(shopInfo.getStoreid(),shopInfo.getId())>0){
            return AjaxResult.error("该门店已存在");
        }
        return toAjax(shopInfoService.updateShopInfo(shopInfo));
    }

    /**
     * 删除门店管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:remove')")
    @Log(title = "门店管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        //检测门店信息是否可以删除
        for(int i=0;i<ids.length;i++){
            ShopInfo info=shopInfoService.selectShopInfoById(ids[i]);
            int result=shopInfoService.checkHasGoods(info.getStoreid());
            if(result>0){
                return AjaxResult.error("该门店下存在商品信息");
            }
            int resultLabel=shopInfoService.checkHasLabel(info.getStoreid());
            if(resultLabel>0){
                return AjaxResult.error("该门店下存在价签信息");
            }
        }
        return toAjax(shopInfoService.deleteShopInfoByIds(ids));
    }

    /**
     * 刷新电子价签
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:refresh')")
    @Log(title = "刷新电子价签", businessType = BusinessType.DELETE)
    @DeleteMapping("/refresh/{storeId}")
    public AjaxResult refresh(@PathVariable String storeId)
    {
        String token= HttpUtils.getToken();
        try {
            int sumPage=1;
            //查询标签总数
            LabelInfo info=new LabelInfo();
            info.setStoreid(storeId);
            int num=labelInfoService.selectLabelInfoList(info).size();
            if(num%20==0){
                sumPage=num/20;
            }else{
                sumPage=(num/20)+1;
            }
            for(int j=1;j<=sumPage;j++){
                JSONObject jsonObject=new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/pub/label/query?page="+j+"&size=20&storeUuid="+storeId,"",token));
                JSONObject bodyObject=new JSONObject(jsonObject.get("body").toString());
                JSONArray array=new JSONArray(bodyObject.get("list").toString());
                if (array.length()>0) {
                    for (int i = 0; i < array.length(); i++) {
                        try{
                            JSONObject jsonObj = array.getJSONObject(i);
                            String mac = jsonObj.getString("mac");
                            String type = jsonObj.getString("type");
                            int battery = jsonObj.getInt("battery");
                            Long temperature=jsonObj.getLong("temperature");
                            LabelInfo labelInfo=new LabelInfo();
                            labelInfo.setMac(mac);
                            labelInfo.setStatus(Integer.parseInt(type));
                            labelInfo.setBattery(battery);
                            labelInfo.setTemperature(temperature.toString());
                            return toAjax(labelInfoService.updateLabelInfoByMac(labelInfo));
                        }catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                            continue;
                        }
                    }
                } else {
                    return AjaxResult.error("刷新失败！");
                }
            }
            return AjaxResult.success("刷新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("刷新失败！");
        }
    }
}
