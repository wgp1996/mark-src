package com.ruoyi.project.info.controller;

import java.util.List;

import com.ruoyi.project.info.domain.RkInfoChild;
import com.ruoyi.project.info.service.IRkInfoChildService;
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

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 入库单子表Controller
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
@RestController
@RequestMapping("/system/rkInfoChild")
public class RkInfoChildController extends BaseController
{
    @Autowired
    private IRkInfoChildService rkInfoChildService;

    /**
     * 查询入库单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfoChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(RkInfoChild rkInfoChild)
    {
        startPage();
        List<RkInfoChild> list = rkInfoChildService.selectRkInfoChildList(rkInfoChild);
        return getDataTable(list);
    }

    /**
     * 导出入库单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfoChild:export')")
    @Log(title = "入库单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RkInfoChild rkInfoChild)
    {
        List<RkInfoChild> list = rkInfoChildService.selectRkInfoChildList(rkInfoChild);
        ExcelUtil<RkInfoChild> util = new ExcelUtil<RkInfoChild>(RkInfoChild.class);
        return util.exportExcel(list, "rkInfoChild");
    }

    /**
     * 获取入库单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfoChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(rkInfoChildService.selectRkInfoChildById(id));
    }

    /**
     * 新增入库单子表
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfoChild:add')")
    @Log(title = "入库单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RkInfoChild rkInfoChild)
    {
        return toAjax(rkInfoChildService.insertRkInfoChild(rkInfoChild));
    }

    /**
     * 修改入库单子表
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfoChild:edit')")
    @Log(title = "入库单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RkInfoChild rkInfoChild)
    {
        return toAjax(rkInfoChildService.updateRkInfoChild(rkInfoChild));
    }

    /**
     * 删除入库单子表
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfoChild:remove')")
    @Log(title = "入库单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(rkInfoChildService.deleteRkInfoChildByIds(ids));
    }
}
