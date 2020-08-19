package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SalesCollection;
import com.ruoyi.project.system.domain.SalesCollection;
import com.ruoyi.project.system.service.ISalesCollectionService;
import com.ruoyi.project.system.service.ISalesCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售收款Controller
 *
 * @author ruoyi
 * @date 2020-08-05
 */
@RestController
@RequestMapping("/system/salesCollection")
public class SalesCollectionController extends BaseController
{
    @Autowired
    private ISalesCollectionService salesCollectionService;

    /**
     * 查询销售收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:salesCollection:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(SalesCollection SalesCollection)
    {
        startPage();
        List<SalesCollection> list = salesCollectionService.selectSalesCollectionList(SalesCollection);
        return getDataTable(list);
    }

    /**
     * 导出销售收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:salesCollection:export')")
    @Log(title = "销售收款", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SalesCollection SalesCollection)
    {
        List<SalesCollection> list = salesCollectionService.selectSalesCollectionList(SalesCollection);
        ExcelUtil<SalesCollection> util = new ExcelUtil<SalesCollection>(SalesCollection.class);
        return util.exportExcel(list, "SalesCollection");
    }

    /**
     * 获取销售收款详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:salesCollection:query')")
    @GetMapping(value = "/{collectionCode}")
    public AjaxResult getInfo(@PathVariable("collectionCode") String collectionCode)
    {
        return AjaxResult.success(salesCollectionService.selectSalesCollectionById(collectionCode));
    }

    /**
     * 新增销售收款
     */
    @PreAuthorize("@ss.hasPermi('system:salesCollection:add')")
    @Log(title = "销售收款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SalesCollection SalesCollection)
    {
        SalesCollection.setCreateBy(SecurityUtils.getUsername());
        SalesCollection.setId(StringUtils.getId());
        SalesCollection.setCollectionCode(StringUtils.getRandomCode("SKD-XS-"));
        SalesCollection.setCreateTime(DateUtils.getNowDate());
        return toAjax(salesCollectionService.insertSalesCollection(SalesCollection));
    }

    /**
     * 修改销售收款
     */
    @PreAuthorize("@ss.hasPermi('system:salesCollection:edit')")
    @Log(title = "销售收款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SalesCollection SalesCollection)
    {
        SalesCollection.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(salesCollectionService.updateSalesCollection(SalesCollection));
    }

    /**
     * 删除销售收款
     */
    @PreAuthorize("@ss.hasPermi('system:salesCollection:remove')")
    @Log(title = "销售收款", businessType = BusinessType.DELETE)
	@DeleteMapping("/{collectionCodes}")
    public AjaxResult remove(@PathVariable String[] collectionCodes)
    {
        return toAjax(salesCollectionService.deleteSalesCollectionByIds(collectionCodes));
    }
}
