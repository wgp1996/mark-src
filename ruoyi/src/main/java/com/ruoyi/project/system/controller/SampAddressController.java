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
import com.ruoyi.project.system.domain.SampAddress;
import com.ruoyi.project.system.service.ISampAddressService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 取样地建档Controller
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@RestController
@RequestMapping("/system/sampAddress")
public class SampAddressController extends BaseController
{
    @Autowired
    private ISampAddressService sampAddressService;

    /**
     * 查询取样地建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:sampAddress:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(SampAddress sampAddress)
    {
        startPage();
        List<SampAddress> list = sampAddressService.selectSampAddressList(sampAddress);
        return getDataTable(list);
    }

    /**
     * 导出取样地建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:sampAddress:export')")
    @Log(title = "取样地建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SampAddress sampAddress)
    {
        List<SampAddress> list = sampAddressService.selectSampAddressList(sampAddress);
        ExcelUtil<SampAddress> util = new ExcelUtil<SampAddress>(SampAddress.class);
        return util.exportExcel(list, "sampAddress");
    }

    /**
     * 获取取样地建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sampAddress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sampAddressService.selectSampAddressById(id));
    }

    /**
     * 新增取样地建档
     */
    @PreAuthorize("@ss.hasPermi('system:sampAddress:add')")
    @Log(title = "取样地建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SampAddress sampAddress)
    {
        sampAddress.setSampAddressId(StringUtils.getRandomCode("SA"));
        sampAddress.setId(StringUtils.getId());
        sampAddress.setCreateBy(SecurityUtils.getUsername());
        return toAjax(sampAddressService.insertSampAddress(sampAddress));
    }

    /**
     * 修改取样地建档
     */
    @PreAuthorize("@ss.hasPermi('system:sampAddress:edit')")
    @Log(title = "取样地建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SampAddress sampAddress)
    {
        sampAddress.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sampAddressService.updateSampAddress(sampAddress));
    }

    /**
     * 删除取样地建档
     */
    @PreAuthorize("@ss.hasPermi('system:sampAddress:remove')")
    @Log(title = "取样地建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sampAddressService.deleteSampAddressByIds(ids));
    }
}
