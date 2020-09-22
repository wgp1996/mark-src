package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.CgRkdSingleChild;
import com.ruoyi.project.system.service.ICgRkdSingleChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 进货单子表Controller
 *
 * @author ruoyi
 * @date 2020-08-17
 */
@RestController
@RequestMapping("/system/cgrkdSingleChild")
public class CgRkdSingleChildController extends BaseController
{
    @Autowired
    private ICgRkdSingleChildService cgRkdChildService;

    /**
     * 查询进货单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingleChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(CgRkdSingleChild cgRkdChild)
    {
        startPage();
        List<CgRkdSingleChild> list = cgRkdChildService.selectCgRkdSingleChildList(cgRkdChild);
        return getDataTable(list);
    }

    @GetMapping("/getCgrkdChild")
    public TableDataInfo getCgrkdChild(CgRkdSingleChild cgRkdChild)
    {
        List<CgRkdSingleChild> list = cgRkdChildService.selectCgRkdSingleChildList(cgRkdChild);
        return getDataTable(list);
    }

    /**
     * 导出进货单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingleChild:export')")
    @Log(title = "进货单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CgRkdSingleChild cgRkdChild)
    {
        List<CgRkdSingleChild> list = cgRkdChildService.selectCgRkdSingleChildList(cgRkdChild);
        ExcelUtil<CgRkdSingleChild> util = new ExcelUtil<CgRkdSingleChild>(CgRkdSingleChild.class);
        return util.exportExcel(list, "cgrkdSingleChild");
    }

    /**
     * 获取进货单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingleChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cgRkdChildService.selectCgRkdSingleChildById(id));
    }

    /**
     * 新增进货单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingleChild:add')")
    @Log(title = "进货单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CgRkdSingleChild cgRkdChild)
    {
        return toAjax(cgRkdChildService.insertCgRkdSingleChild(cgRkdChild));
    }

    /**
     * 修改进货单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingleChild:edit')")
    @Log(title = "进货单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CgRkdSingleChild cgRkdChild)
    {
        return toAjax(cgRkdChildService.updateCgRkdSingleChild(cgRkdChild));
    }

    /**
     * 删除进货单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingleChild:remove')")
    @Log(title = "进货单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cgRkdChildService.deleteCgRkdSingleChildByIds(ids));
    }
}
