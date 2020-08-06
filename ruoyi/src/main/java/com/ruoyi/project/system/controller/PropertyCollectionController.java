package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
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
import com.ruoyi.project.system.domain.PropertyCollection;
import com.ruoyi.project.system.service.IPropertyCollectionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 物业收款Controller
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
@RestController
@RequestMapping("/system/propertyCollection")
public class PropertyCollectionController extends BaseController
{
    @Autowired
    private IPropertyCollectionService propertyCollectionService;

    /**
     * 查询物业收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:propertyCollection:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(PropertyCollection propertyCollection)
    {
        startPage();
        List<PropertyCollection> list = propertyCollectionService.selectPropertyCollectionList(propertyCollection);
        return getDataTable(list);
    }

    /**
     * 导出物业收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:propertyCollection:export')")
    @Log(title = "物业收款", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PropertyCollection propertyCollection)
    {
        List<PropertyCollection> list = propertyCollectionService.selectPropertyCollectionList(propertyCollection);
        ExcelUtil<PropertyCollection> util = new ExcelUtil<PropertyCollection>(PropertyCollection.class);
        return util.exportExcel(list, "propertyCollection");
    }

    /**
     * 获取物业收款详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:propertyCollection:query')")
    @GetMapping(value = "/{collectionCode}")
    public AjaxResult getInfo(@PathVariable("collectionCode") String collectionCode)
    {
        return AjaxResult.success(propertyCollectionService.selectPropertyCollectionById(collectionCode));
    }

    /**
     * 新增物业收款
     */
    @PreAuthorize("@ss.hasPermi('system:propertyCollection:add')")
    @Log(title = "物业收款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PropertyCollection propertyCollection)
    {
        propertyCollection.setCreateBy(SecurityUtils.getUsername());
        propertyCollection.setId(StringUtils.getId());
        propertyCollection.setCollectionCode(StringUtils.getRandomCode("SKD-WY-"));
        propertyCollection.setCreateTime(DateUtils.getNowDate());
        return toAjax(propertyCollectionService.insertPropertyCollection(propertyCollection));
    }

    /**
     * 修改物业收款
     */
    @PreAuthorize("@ss.hasPermi('system:propertyCollection:edit')")
    @Log(title = "物业收款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PropertyCollection propertyCollection)
    {
        propertyCollection.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(propertyCollectionService.updatePropertyCollection(propertyCollection));
    }

    /**
     * 删除物业收款
     */
    @PreAuthorize("@ss.hasPermi('system:propertyCollection:remove')")
    @Log(title = "物业收款", businessType = BusinessType.DELETE)
	@DeleteMapping("/{collectionCodes}")
    public AjaxResult remove(@PathVariable String[] collectionCodes)
    {
        return toAjax(propertyCollectionService.deletePropertyCollectionByIds(collectionCodes));
    }
}
