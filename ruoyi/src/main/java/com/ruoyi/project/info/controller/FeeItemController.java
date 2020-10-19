package com.ruoyi.project.info.controller;

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
import com.ruoyi.project.info.domain.FeeItem;
import com.ruoyi.project.info.service.IFeeItemService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 费用项目Controller
 * 
 * @author ruoyi
 * @date 2020-10-16
 */
@RestController
@RequestMapping("/info/feeItem")
public class FeeItemController extends BaseController
{
    @Autowired
    private IFeeItemService feeItemService;

    /**
     * 查询费用项目列表
     */
    @PreAuthorize("@ss.hasPermi('info:feeItem:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(FeeItem feeItem)
    {
        startPage();
        List<FeeItem> list = feeItemService.selectFeeItemList(feeItem);
        return getDataTable(list);
    }

    /**
     * 导出费用项目列表
     */
    @PreAuthorize("@ss.hasPermi('info:feeItem:export')")
    @Log(title = "费用项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FeeItem feeItem)
    {
        List<FeeItem> list = feeItemService.selectFeeItemList(feeItem);
        ExcelUtil<FeeItem> util = new ExcelUtil<FeeItem>(FeeItem.class);
        return util.exportExcel(list, "feeItem");
    }

    /**
     * 获取费用项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:feeItem:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(feeItemService.selectFeeItemById(id));
    }

    /**
     * 新增费用项目
     */
    @PreAuthorize("@ss.hasPermi('info:feeItem:add')")
    @Log(title = "费用项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeeItem feeItem)
    {
        feeItem.setCreateBy(SecurityUtils.getUsername());
        int result=feeItemService.checkFeeItem(null,feeItem.getCreateBy(),feeItem.getFeeName());
        if(result>0){
            return AjaxResult.error("费用项目重复!");
        }
        feeItem.setFeeCode(StringUtils.getRandomCode("F"));
        return toAjax(feeItemService.insertFeeItem(feeItem));
    }

    /**
     * 修改费用项目
     */
    @PreAuthorize("@ss.hasPermi('info:feeItem:edit')")
    @Log(title = "费用项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeeItem feeItem)
    {
        int result=feeItemService.checkFeeItem(feeItem.getId(),feeItem.getCreateBy(),feeItem.getFeeName());
        if(result>0){
            return AjaxResult.error("费用项目重复!");
        }
        feeItem.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(feeItemService.updateFeeItem(feeItem));
    }

    /**
     * 删除费用项目
     */
    @PreAuthorize("@ss.hasPermi('info:feeItem:remove')")
    @Log(title = "费用项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(feeItemService.deleteFeeItemByIds(ids));
    }
}
