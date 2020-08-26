package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.project.system.domain.CgRkdChild;
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
import com.ruoyi.project.system.domain.WholeSalesChild;
import com.ruoyi.project.system.service.IWholeSalesChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 批发销货子表Controller
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/system/wholeSalesChild")
public class WholeSalesChildController extends BaseController
{
    @Autowired
    private IWholeSalesChildService wholeSalesChildService;

    /**
     * 查询批发销货子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSalesChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(WholeSalesChild wholeSalesChild)
    {
        startPage();
        List<WholeSalesChild> list = wholeSalesChildService.selectWholeSalesChildList(wholeSalesChild);
        return getDataTable(list);
    }

    @GetMapping("/getWholeSalesChild")
    public TableDataInfo getWholeSalesChild(WholeSalesChild wholeSalesChild)
    {
        List<WholeSalesChild> list = wholeSalesChildService.selectWholeSalesChildList(wholeSalesChild);
        return getDataTable(list);
    }
    /**
     * 导出批发销货子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSalesChild:export')")
    @Log(title = "批发销货子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WholeSalesChild wholeSalesChild)
    {
        List<WholeSalesChild> list = wholeSalesChildService.selectWholeSalesChildList(wholeSalesChild);
        ExcelUtil<WholeSalesChild> util = new ExcelUtil<WholeSalesChild>(WholeSalesChild.class);
        return util.exportExcel(list, "wholeSalesChild");
    }

    /**
     * 获取批发销货子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSalesChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(wholeSalesChildService.selectWholeSalesChildById(id));
    }

    /**
     * 新增批发销货子表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSalesChild:add')")
    @Log(title = "批发销货子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WholeSalesChild wholeSalesChild)
    {
        return toAjax(wholeSalesChildService.insertWholeSalesChild(wholeSalesChild));
    }

    /**
     * 修改批发销货子表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSalesChild:edit')")
    @Log(title = "批发销货子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WholeSalesChild wholeSalesChild)
    {
        return toAjax(wholeSalesChildService.updateWholeSalesChild(wholeSalesChild));
    }

    /**
     * 删除批发销货子表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSalesChild:remove')")
    @Log(title = "批发销货子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(wholeSalesChildService.deleteWholeSalesChildByIds(ids));
    }
}
