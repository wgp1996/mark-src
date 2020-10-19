package com.ruoyi.project.info.controller;

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
import com.ruoyi.project.info.domain.CheckInfoChild;
import com.ruoyi.project.info.service.ICheckInfoChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 盘点单子表Controller
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
@RestController
@RequestMapping("/system/checkInfoChild")
public class CheckInfoChildController extends BaseController
{
    @Autowired
    private ICheckInfoChildService checkInfoChildService;

    /**
     * 查询盘点单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfoChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckInfoChild checkInfoChild)
    {
        startPage();
        List<CheckInfoChild> list = checkInfoChildService.selectCheckInfoChildList(checkInfoChild);
        return getDataTable(list);
    }

    /**
     * 导出盘点单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfoChild:export')")
    @Log(title = "盘点单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CheckInfoChild checkInfoChild)
    {
        List<CheckInfoChild> list = checkInfoChildService.selectCheckInfoChildList(checkInfoChild);
        ExcelUtil<CheckInfoChild> util = new ExcelUtil<CheckInfoChild>(CheckInfoChild.class);
        return util.exportExcel(list, "checkInfoChild");
    }

    /**
     * 获取盘点单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfoChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(checkInfoChildService.selectCheckInfoChildById(id));
    }

    /**
     * 新增盘点单子表
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfoChild:add')")
    @Log(title = "盘点单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckInfoChild checkInfoChild)
    {
        return toAjax(checkInfoChildService.insertCheckInfoChild(checkInfoChild));
    }

    /**
     * 修改盘点单子表
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfoChild:edit')")
    @Log(title = "盘点单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckInfoChild checkInfoChild)
    {
        return toAjax(checkInfoChildService.updateCheckInfoChild(checkInfoChild));
    }

    /**
     * 删除盘点单子表
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfoChild:remove')")
    @Log(title = "盘点单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(checkInfoChildService.deleteCheckInfoChildByIds(ids));
    }
}
