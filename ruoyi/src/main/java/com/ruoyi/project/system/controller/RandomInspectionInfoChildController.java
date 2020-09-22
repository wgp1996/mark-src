package com.ruoyi.project.system.controller;

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
import com.ruoyi.project.system.domain.RandomInspectionInfoChild;
import com.ruoyi.project.system.service.IRandomInspectionInfoChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 检测单明细Controller
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@RestController
@RequestMapping("/system/randomInspChild")
public class RandomInspectionInfoChildController extends BaseController
{
    @Autowired
    private IRandomInspectionInfoChildService randomInspectionInfoChildService;

    /**
     * 查询检测单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:randomInspChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(RandomInspectionInfoChild randomInspectionInfoChild)
    {
        startPage();
        List<RandomInspectionInfoChild> list = randomInspectionInfoChildService.selectRandomInspectionInfoChildList(randomInspectionInfoChild);
        return getDataTable(list);
    }

    /**
     * 导出检测单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:randomInspChild:export')")
    @Log(title = "检测单明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RandomInspectionInfoChild randomInspectionInfoChild)
    {
        List<RandomInspectionInfoChild> list = randomInspectionInfoChildService.selectRandomInspectionInfoChildList(randomInspectionInfoChild);
        ExcelUtil<RandomInspectionInfoChild> util = new ExcelUtil<RandomInspectionInfoChild>(RandomInspectionInfoChild.class);
        return util.exportExcel(list, "randomInspChild");
    }

    /**
     * 获取检测单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:randomInspChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(randomInspectionInfoChildService.selectRandomInspectionInfoChildById(id));
    }

    /**
     * 新增检测单明细
     */
    @PreAuthorize("@ss.hasPermi('system:randomInspChild:add')")
    @Log(title = "检测单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RandomInspectionInfoChild randomInspectionInfoChild)
    {
        return toAjax(randomInspectionInfoChildService.insertRandomInspectionInfoChild(randomInspectionInfoChild));
    }

    /**
     * 修改检测单明细
     */
    @PreAuthorize("@ss.hasPermi('system:randomInspChild:edit')")
    @Log(title = "检测单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RandomInspectionInfoChild randomInspectionInfoChild)
    {
        return toAjax(randomInspectionInfoChildService.updateRandomInspectionInfoChild(randomInspectionInfoChild));
    }

    /**
     * 删除检测单明细
     */
    @PreAuthorize("@ss.hasPermi('system:randomInspChild:remove')")
    @Log(title = "检测单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(randomInspectionInfoChildService.deleteRandomInspectionInfoChildByIds(ids));
    }
}
