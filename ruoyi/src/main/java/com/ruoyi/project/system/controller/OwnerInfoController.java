package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.project.system.domain.OwnerInfo;
import com.ruoyi.project.system.service.IOwnerInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 业户信息Controller
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/system/owner")
public class OwnerInfoController extends BaseController {
    @Autowired
    private IOwnerInfoService ownerInfoService;

    /**
     * 查询业户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:owner:list')")
    @GetMapping("/list")
    public TableDataInfo list(OwnerInfo ownerInfo) {
        startPage();
        List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(ownerInfo);
        return getDataTable(list);
    }

    /**
     * 导出业户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:owner:export')")
    @Log(title = "业户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OwnerInfo ownerInfo) {
        List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(ownerInfo);
        ExcelUtil<OwnerInfo> util = new ExcelUtil<OwnerInfo>(OwnerInfo.class);
        return util.exportExcel(list, "owner");
    }

    /**
     * 获取业户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:owner:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ownerInfoService.selectOwnerInfoById(id));
    }

    /**
     * 新增业户信息
     */
    @PreAuthorize("@ss.hasPermi('system:owner:add')")
    @Log(title = "业户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OwnerInfo ownerInfo) {
        OwnerInfo info = ownerInfoService.selectOwnerInfoByCode(ownerInfo.getOwnerCode(), "");
        if (info != null) {
            return toAjaxByError("该业户在系统中已存在");
        } else {
            ownerInfo.setCreateBy(SecurityUtils.getUsername());
            ownerInfo.setId(StringUtils.getId());
            //ownerInfo.setOwnerCode(StringUtils.getRandomCode("OR"));
            ownerInfo.setCreateTime(DateUtils.getNowDate());
            return toAjax(ownerInfoService.insertOwnerInfo(ownerInfo));
        }
    }

    /**
     * 修改业户信息
     */
    @PreAuthorize("@ss.hasPermi('system:owner:edit')")
    @Log(title = "业户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OwnerInfo ownerInfo) {
        OwnerInfo info = ownerInfoService.selectOwnerInfoByCode(ownerInfo.getOwnerCode(), ownerInfo.getId());
        if (info != null) {
            return toAjaxByError("该业户在系统中已存在");
        } else {
            ownerInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(ownerInfoService.updateOwnerInfo(ownerInfo));
        }
    }

    /**
     * 删除业户信息
     */
    @PreAuthorize("@ss.hasPermi('system:owner:remove')")
    @Log(title = "业户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ownerInfoService.deleteOwnerInfoByIds(ids));
    }
}
