package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.LabelInfo;
import com.ruoyi.project.system.domain.ShopInfoOwner;
import com.ruoyi.project.system.service.ILabelInfoService;
import com.ruoyi.project.system.service.IShopInfoOwnerService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店管理Controller
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@RestController
@RequestMapping("/system/shopInfoOwner")
public class ShopInfoOwnerController extends BaseController
{
    @Autowired
    private IShopInfoOwnerService shopInfoOwnerService;
    @Autowired
    private ILabelInfoService labelInfoService;
    /**
     * 查询门店管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfoOwner:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(ShopInfoOwner shopInfoOwner)
    {
        startPage();
        List<ShopInfoOwner> list = shopInfoOwnerService.selectShopInfoOwnerList(shopInfoOwner);
        return getDataTable(list);
    }

    /**
     * 查询门店管理列表
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/getShopList")
    public TableDataInfo getShopList(ShopInfoOwner shopInfoOwner)
    {
        List<ShopInfoOwner> list = shopInfoOwnerService.selectShopInfoOwnerList(shopInfoOwner);
        return getDataTable(list);
    }

    /**
     * 导出门店管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfoOwner:export')")
    @Log(title = "门店管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ShopInfoOwner shopInfoOwner)
    {
        List<ShopInfoOwner> list = shopInfoOwnerService.selectShopInfoOwnerList(shopInfoOwner);
        ExcelUtil<ShopInfoOwner> util = new ExcelUtil<ShopInfoOwner>(ShopInfoOwner.class);
        return util.exportExcel(list, "shopInfoOwner");
    }

    /**
     * 获取门店管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfoOwner:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(shopInfoOwnerService.selectShopInfoOwnerById(id));
    }

    /**
     * 新增门店管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfoOwner:add')")
    @Log(title = "门店管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopInfoOwner shopInfoOwner)
    {
        //查询门店是否重复
        if(shopInfoOwnerService.checkShopInfoOwner(shopInfoOwner.getStoreid(),"")>0){
            return AjaxResult.error("该门店已存在");
        }
        shopInfoOwner.setCreateBy(SecurityUtils.getUsername());
        shopInfoOwner.setId(StringUtils.getId());
        return toAjax(shopInfoOwnerService.insertShopInfoOwner(shopInfoOwner));
    }

    /**
     * 修改门店管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfoOwner:edit')")
    @Log(title = "门店管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopInfoOwner shopInfoOwner)
    {
        //查询门店是否重复
        if(shopInfoOwnerService.checkShopInfoOwner(shopInfoOwner.getStoreid(),shopInfoOwner.getId())>0){
            return AjaxResult.error("该门店已存在");
        }
        shopInfoOwner.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(shopInfoOwnerService.updateShopInfoOwner(shopInfoOwner));
    }

    /**
     * 删除门店管理
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfoOwner:remove')")
    @Log(title = "门店管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        //检测门店信息是否可以删除
        /*for(int i=0;i<ids.length;i++){
            ShopInfoOwner info=shopInfoOwnerService.selectShopInfoOwnerById(ids[i]);
            int result=shopInfoOwnerService.checkHasGoods(info.getStoreid());
            if(result>0){
                return AjaxResult.error("该门店下存在商品信息");
            }
            int resultLabel=shopInfoOwnerService.checkHasLabel(info.getStoreid());
            if(resultLabel>0){
                return AjaxResult.error("该门店下存在价签信息");
            }
        }*/
        return toAjax(shopInfoOwnerService.deleteShopInfoOwnerByIds(ids));
    }
}
