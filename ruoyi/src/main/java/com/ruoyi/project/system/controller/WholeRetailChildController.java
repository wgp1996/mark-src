package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.WholeRetailChild;
import com.ruoyi.project.system.service.IWholeRetailChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 零售销货子表Controller
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/system/wholeRetailChild")
public class WholeRetailChildController extends BaseController
{
    @Autowired
    private IWholeRetailChildService wholeRetailChildService;

    /**
     * 查询零售销货子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetailChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(WholeRetailChild wholeRetailChild)
    {
        startPage();
        List<WholeRetailChild> list = wholeRetailChildService.selectWholeRetailChildList(wholeRetailChild);
        return getDataTable(list);
    }

    @GetMapping("/getWholeRetailChild")
    public TableDataInfo getWholeRetailChild(WholeRetailChild wholeRetailChild)
    {
        List<WholeRetailChild> list = wholeRetailChildService.selectWholeRetailChildList(wholeRetailChild);
        return getDataTable(list);
    }
    /**
     * 导出零售销货子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetailChild:export')")
    @Log(title = "零售销货子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WholeRetailChild wholeRetailChild)
    {
        List<WholeRetailChild> list = wholeRetailChildService.selectWholeRetailChildList(wholeRetailChild);
        ExcelUtil<WholeRetailChild> util = new ExcelUtil<WholeRetailChild>(WholeRetailChild.class);
        return util.exportExcel(list, "wholeRetailChild");
    }

    /**
     * 获取零售销货子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetailChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(wholeRetailChildService.selectWholeRetailChildById(id));
    }

    /**
     * 新增零售销货子表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetailChild:add')")
    @Log(title = "零售销货子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WholeRetailChild wholeRetailChild)
    {
        return toAjax(wholeRetailChildService.insertWholeRetailChild(wholeRetailChild));
    }

    /**
     * 修改零售销货子表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetailChild:edit')")
    @Log(title = "零售销货子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WholeRetailChild wholeRetailChild)
    {
        return toAjax(wholeRetailChildService.updateWholeRetailChild(wholeRetailChild));
    }

    /**
     * 删除零售销货子表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetailChild:remove')")
    @Log(title = "零售销货子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(wholeRetailChildService.deleteWholeRetailChildByIds(ids));
    }
}
