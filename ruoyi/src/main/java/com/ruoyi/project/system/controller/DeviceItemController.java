package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
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
import com.ruoyi.project.system.domain.DeviceItem;
import com.ruoyi.project.system.service.IDeviceItemService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 设备建档Controller
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@RestController
@RequestMapping("/system/deviceItem")
public class DeviceItemController extends BaseController
{
    @Autowired
    private IDeviceItemService deviceItemService;

    /**
     * 查询设备建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:deviceItem:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(DeviceItem deviceItem)
    {
        startPage();
        List<DeviceItem> list = deviceItemService.selectDeviceItemList(deviceItem);
        return getDataTable(list);
    }

    /**
     * 查询设备建档列表
     */
    @GetMapping("/getAllDeviceItem")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo getAllDeviceItem(DeviceItem deviceItem)
    {
        startPage();
        List<DeviceItem> list = deviceItemService.selectDeviceItemList(deviceItem);
        return getDataTable(list);
    }

    /**
     * 导出设备建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:deviceItem:export')")
    @Log(title = "设备建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DeviceItem deviceItem)
    {
        List<DeviceItem> list = deviceItemService.selectDeviceItemList(deviceItem);
        ExcelUtil<DeviceItem> util = new ExcelUtil<DeviceItem>(DeviceItem.class);
        return util.exportExcel(list, "deviceItem");
    }

    /**
     * 获取设备建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:deviceItem:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(deviceItemService.selectDeviceItemById(id));
    }

    /**
     * 新增设备建档
     */
    @PreAuthorize("@ss.hasPermi('system:deviceItem:add')")
    @Log(title = "设备建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceItem deviceItem)
    {
        deviceItem.setDeviceId(StringUtils.getRandomCode("DEV"));
        deviceItem.setId(StringUtils.getId());
        deviceItem.setCreateBy(SecurityUtils.getUsername());
        return toAjax(deviceItemService.insertDeviceItem(deviceItem));
    }

    /**
     * 修改设备建档
     */
    @PreAuthorize("@ss.hasPermi('system:deviceItem:edit')")
    @Log(title = "设备建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceItem deviceItem)
    {
        deviceItem.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(deviceItemService.updateDeviceItem(deviceItem));
    }

    /**
     * 删除设备建档
     */
    @PreAuthorize("@ss.hasPermi('system:deviceItem:remove')")
    @Log(title = "设备建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(deviceItemService.deleteDeviceItemByIds(ids));
    }
}
