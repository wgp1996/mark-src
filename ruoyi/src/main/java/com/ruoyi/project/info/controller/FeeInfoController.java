package com.ruoyi.project.info.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.project.info.domain.FeeInfo;
import com.ruoyi.project.info.service.IFeeInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 帐夹Controller
 * 
 * @author ruoyi
 * @date 2020-10-16
 */
@RestController
@RequestMapping("/info/feeInfo")
public class FeeInfoController extends BaseController
{
    @Autowired
    private IFeeInfoService feeInfoService;

    /**
     * 查询帐夹列表
     */
    @PreAuthorize("@ss.hasPermi('info:feeInfo:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(FeeInfo feeInfo)
    {
        startPage();
        List<FeeInfo> list = feeInfoService.selectFeeInfoList(feeInfo);
        return getDataTable(list);
    }

    /**
     * 导出帐夹列表
     */
    @PreAuthorize("@ss.hasPermi('info:feeInfo:export')")
    @Log(title = "帐夹", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FeeInfo feeInfo)
    {
        List<FeeInfo> list = feeInfoService.selectFeeInfoList(feeInfo);
        ExcelUtil<FeeInfo> util = new ExcelUtil<FeeInfo>(FeeInfo.class);
        return util.exportExcel(list, "feeInfo");
    }

    /**
     * 获取帐夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:feeInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(feeInfoService.selectFeeInfoById(id));
    }

    /**
     * 新增帐夹
     */
    @PreAuthorize("@ss.hasPermi('info:feeInfo:add')")
    @Log(title = "帐夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeeInfo feeInfo)
    {
        feeInfo.setCreateBy(SecurityUtils.getUsername());
        feeInfo.setSource(0);
        return toAjax(feeInfoService.insertFeeInfo(feeInfo));
    }

    /**
     * 修改帐夹
     */
    @PreAuthorize("@ss.hasPermi('info:feeInfo:edit')")
    @Log(title = "帐夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeeInfo feeInfo)
    {
        feeInfo.setSource(0);
        feeInfo.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(feeInfoService.updateFeeInfo(feeInfo));
    }

    /**
     * 删除帐夹
     */
    @PreAuthorize("@ss.hasPermi('info:feeInfo:remove')")
    @Log(title = "帐夹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(feeInfoService.deleteFeeInfoByIds(ids));
    }
}
