package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
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
import com.ruoyi.project.system.domain.LabelInfo;
import com.ruoyi.project.system.service.ILabelInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
/**
 * 电子价签管理Controller
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@RestController
@RequestMapping("/system/shopLabel")
public class LabelInfoController extends BaseController
{
    @Autowired
    private ILabelInfoService labelInfoService;

    /**
     * 查询电子价签管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopLabel:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(LabelInfo labelInfo)
    {
        startPage();
        List<LabelInfo> list = labelInfoService.selectLabelInfoList(labelInfo);
        return getDataTable(list);
    }

    /**
     * 导出电子价签管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopLabel:export')")
    @Log(title = "电子价签管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LabelInfo labelInfo)
    {
        List<LabelInfo> list = labelInfoService.selectLabelInfoList(labelInfo);
        ExcelUtil<LabelInfo> util = new ExcelUtil<LabelInfo>(LabelInfo.class);
        return util.exportExcel(list, "shopLabel");
    }

    /**
     * 获取电子价签管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopLabel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(labelInfoService.selectLabelInfoById(id));
    }

    /**
     * 新增电子价签管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopLabel:add')")
    @Log(title = "电子价签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LabelInfo item)
    {
        if(labelInfoService.checkLabel(item.getMac(),"")>0){
            return AjaxResult.error("该价签已存在!");
        }
        HashMap map=new HashMap();
        List<HashMap> listGoods=new ArrayList<HashMap>();
        HashMap info=new HashMap();
        info.put("mac",item.getMac());
        info.put("activekey", "3141592653589793");//秘钥 通用
        listGoods.add(info);
        map.put("storeUuid", item.getStoreid());
        map.put("labels",listGoods);
        String token=HttpUtils.getToken();
        try{
            JSONObject jsonObject=new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/pub/label/add", JSONValue.toJSONString(map).toString(),token));
            System.out.println(jsonObject);
            int status=jsonObject.getInt("status");
            if(status==200){
                //添加标签信息
                item.setCreateBy(SecurityUtils.getUsername());
                item.setId(StringUtils.getId());
                //绑定价签信息
                if(item.getDemoid()!=""&&item.getGoodsCode()!="") {
                    HashMap labelMap = new HashMap();
                    labelMap.put("storeId", item.getStoreid());//门店编号
                    labelMap.put("barcode", item.getGoodsCode());//商品编码
                    labelMap.put("mac", item.getMac());//mac
                    labelMap.put("demoId", item.getDemoid());//模板id
                    JSONObject labeljsonObject = new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/pub/bind/update", JSONValue.toJSONString(labelMap), token));
                }
                return toAjax(labelInfoService.insertLabelInfo(item));
            } else{
                return AjaxResult.error("云标签更新失败");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return AjaxResult.error("云标签更新失败");
        }
    }

    /**
     * 修改电子价签管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopLabel:edit')")
    @Log(title = "电子价签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LabelInfo item)
    {
        String token=HttpUtils.getToken();
        HashMap map=new HashMap();
        List<HashMap> listGoods=new ArrayList<HashMap>();
        try{
            //更改模板信息
            HashMap labelMap=new HashMap();
            labelMap.put("storeId",item.getStoreid());//门店编号
            labelMap.put("barcode",item.getGoodsCode());//商品编码
            labelMap.put("mac", item.getMac());//mac
            labelMap.put("demoId", item.getDemoid());//模板id
            JSONObject labeljsonObject=new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/pub/bind/update",JSONValue.toJSONString(labelMap),token));
            int status=labeljsonObject.getInt("status");
            if(status==200){
                //添加标签信息
                item.setUpdateBy(SecurityUtils.getUsername());
                return toAjax(labelInfoService.updateLabelInfo(item));
            } else{
                return AjaxResult.error("云标签更新失败");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return AjaxResult.error("云标签更新失败");
        }

    }

    /**
     * 删除电子价签管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopLabel:remove')")
    @Log(title = "电子价签管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(labelInfoService.deleteLabelInfoByIds(ids));
    }

    /**
     * 绑定网关
     */
    @PreAuthorize("@ss.hasPermi('system:shopLabel:bind')")
    @Log(title = "电子价签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/bind/{macs}/{storeId}")
    public AjaxResult remove(@PathVariable String[] macs,@PathVariable String storeId)
    {
        String token=HttpUtils.getToken();
        try {
            HashMap map=new HashMap();
            List<HashMap> listLabels=new ArrayList<HashMap>();
            for(int i=0;i<macs.length;i++){
                HashMap info=new HashMap();
                info.put("mac",macs[i]);
                listLabels.add(info);
            }
            map.put("labels", listLabels);
            map.put("storeUuid",storeId);
            System.out.println(JSONValue.toJSONString(map).toString());
            JSONObject jsonObject=new JSONObject(HttpUtils.sendPost("http://esl.ylwlesl.com:9191/V2/pub/label/bingding",JSONValue.toJSONString(map).toString(),token));
            int status=jsonObject.getInt("status");
            if (status==200) {
                return toAjax(labelInfoService.bindGateWayMacs(macs));
            } else {
                return AjaxResult.error( "绑定失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error( "绑定失败！");
        }

    }


}
