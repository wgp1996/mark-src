package com.ruoyi.project.system.controller;

import java.util.List;
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
import com.ruoyi.project.system.domain.AppInfo;
import com.ruoyi.project.system.service.IAppInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * APP更新Controller
 * 
 * @author ruoyi
 * @date 2020-09-03
 */
@RestController
@RequestMapping("/system/update")
public class AppInfoController extends BaseController
{
    @Autowired
    private IAppInfoService appInfoService;

    /**
     * 查询APP更新列表
     */
    @PreAuthorize("@ss.hasPermi('system:update:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppInfo appInfo)
    {
        startPage();
        List<AppInfo> list = appInfoService.selectAppInfoList(appInfo);
        return getDataTable(list);
    }

    /**
     * 导出APP更新列表
     */
    @PreAuthorize("@ss.hasPermi('system:update:export')")
    @Log(title = "APP更新", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AppInfo appInfo)
    {
        List<AppInfo> list = appInfoService.selectAppInfoList(appInfo);
        ExcelUtil<AppInfo> util = new ExcelUtil<AppInfo>(AppInfo.class);
        return util.exportExcel(list, "update");
    }

    /**
     * 获取APP更新详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:update:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appInfoService.selectAppInfoById(id));
    }

    /**
     * 新增APP更新
     */
    @PreAuthorize("@ss.hasPermi('system:update:add')")
    @Log(title = "APP更新", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppInfo appInfo)
    {
        return toAjax(appInfoService.insertAppInfo(appInfo));
    }

    /**
     * 修改APP更新
     */
    @PreAuthorize("@ss.hasPermi('system:update:edit')")
    @Log(title = "APP更新", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppInfo appInfo)
    {
        return toAjax(appInfoService.updateAppInfo(appInfo));
    }

    /**
     * 删除APP更新
     */
    @PreAuthorize("@ss.hasPermi('system:update:remove')")
    @Log(title = "APP更新", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(appInfoService.deleteAppInfoByIds(ids));
    }
}
