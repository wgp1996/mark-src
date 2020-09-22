package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.StallInfo;
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
import com.ruoyi.project.system.domain.StoreInfo;
import com.ruoyi.project.system.service.IStoreInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 冷库建档Controller
 * 
 * @author ruoyi
 * @date 2020-08-11
 */
@RestController
@RequestMapping("/system/store")
public class StoreInfoController extends BaseController
{
    @Autowired
    private IStoreInfoService storeInfoService;

    /**
     * 查询冷库建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:store:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(StoreInfo storeInfo)
    {
        startPage();
        List<StoreInfo> list = storeInfoService.selectStoreInfoList(storeInfo);
        return getDataTable(list);
    }

    /**
     * 仓库下拉列表
     */
    @GetMapping("/getStoreAll")
    public TableDataInfo getStoreAll() {
        StoreInfo storeInfo=new StoreInfo();
        storeInfo.setCreateBy(SecurityUtils.getUsername());
        List<StoreInfo> list = storeInfoService.selectStoreInfoList(storeInfo);
        storeInfo=null;
        return getDataTable(list);
    }

    /**
     * 导出冷库建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:store:export')")
    @Log(title = "冷库建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StoreInfo storeInfo)
    {
        List<StoreInfo> list = storeInfoService.selectStoreInfoList(storeInfo);
        ExcelUtil<StoreInfo> util = new ExcelUtil<StoreInfo>(StoreInfo.class);
        return util.exportExcel(list, "store");
    }

    /**
     * 获取冷库建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:store:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(storeInfoService.selectStoreInfoById(id));
    }

    /**
     * 新增冷库建档
     */
    @PreAuthorize("@ss.hasPermi('system:store:add')")
    @Log(title = "冷库建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreInfo storeInfo)
    {
        StoreInfo info = storeInfoService.selectStoreInfoByName(storeInfo.getStoreName(), "");
        if (info != null) {
            return toAjaxByError("该冷库在系统中已存在");
        } else {
            storeInfo.setCreateBy(SecurityUtils.getUsername());
            storeInfo.setId(StringUtils.getId());
            storeInfo.setStoreCode(StringUtils.getRandomCode("LK"));
            return toAjax(storeInfoService.insertStoreInfo(storeInfo));
        }
    }

    /**
     * 修改冷库建档
     */
    @PreAuthorize("@ss.hasPermi('system:store:edit')")
    @Log(title = "冷库建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreInfo storeInfo)
    {
        StoreInfo info = storeInfoService.selectStoreInfoByName(storeInfo.getStoreName(), storeInfo.getId());
        if (info != null) {
            return toAjaxByError("该冷库在系统中已存在");
        } else {
            storeInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(storeInfoService.updateStoreInfo(storeInfo));
        }
    }

    /**
     * 删除冷库建档
     */
    @PreAuthorize("@ss.hasPermi('system:store:remove')")
    @Log(title = "冷库建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(storeInfoService.deleteStoreInfoByIds(ids));
    }
}
