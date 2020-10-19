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
import com.ruoyi.project.info.domain.LkckInfoChild;
import com.ruoyi.project.info.service.ILkckInfoChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 出库单明细Controller
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
@RestController
@RequestMapping("/system/lkckInfoChild")
public class LkckInfoChildController extends BaseController
{
    @Autowired
    private ILkckInfoChildService lkckInfoChildService;

    /**
     * 查询出库单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfoChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(LkckInfoChild lkckInfoChild)
    {
        startPage();
        List<LkckInfoChild> list = lkckInfoChildService.selectLkckInfoChildList(lkckInfoChild);
        return getDataTable(list);
    }

    /**
     * 导出出库单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfoChild:export')")
    @Log(title = "出库单明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LkckInfoChild lkckInfoChild)
    {
        List<LkckInfoChild> list = lkckInfoChildService.selectLkckInfoChildList(lkckInfoChild);
        ExcelUtil<LkckInfoChild> util = new ExcelUtil<LkckInfoChild>(LkckInfoChild.class);
        return util.exportExcel(list, "lkckInfoChild");
    }

    /**
     * 获取出库单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfoChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(lkckInfoChildService.selectLkckInfoChildById(id));
    }

    /**
     * 新增出库单明细
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfoChild:add')")
    @Log(title = "出库单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LkckInfoChild lkckInfoChild)
    {
        return toAjax(lkckInfoChildService.insertLkckInfoChild(lkckInfoChild));
    }

    /**
     * 修改出库单明细
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfoChild:edit')")
    @Log(title = "出库单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LkckInfoChild lkckInfoChild)
    {
        return toAjax(lkckInfoChildService.updateLkckInfoChild(lkckInfoChild));
    }

    /**
     * 删除出库单明细
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfoChild:remove')")
    @Log(title = "出库单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(lkckInfoChildService.deleteLkckInfoChildByIds(ids));
    }
}
