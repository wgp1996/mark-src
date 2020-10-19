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
import com.ruoyi.project.system.domain.TestApplicationFormChild;
import com.ruoyi.project.system.service.ITestApplicationFormChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 检测申请单子表Controller
 * 
 * @author ruoyi
 * @date 2020-09-30
 */
@RestController
@RequestMapping("/system/TestApplicationFormChild")
public class TestApplicationFormChildController extends BaseController
{
    @Autowired
    private ITestApplicationFormChildService testApplicationFormChildService;

    /**
     * 查询检测申请单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationFormChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestApplicationFormChild testApplicationFormChild)
    {
        //startPage();
        List<TestApplicationFormChild> list = testApplicationFormChildService.selectTestApplicationFormChildList(testApplicationFormChild);
        return getDataTable(list);
    }

    /**
     * 导出检测申请单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationFormChild:export')")
    @Log(title = "检测申请单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TestApplicationFormChild testApplicationFormChild)
    {
        List<TestApplicationFormChild> list = testApplicationFormChildService.selectTestApplicationFormChildList(testApplicationFormChild);
        ExcelUtil<TestApplicationFormChild> util = new ExcelUtil<TestApplicationFormChild>(TestApplicationFormChild.class);
        return util.exportExcel(list, "TestApplicationFormChild");
    }

    /**
     * 获取检测申请单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationFormChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(testApplicationFormChildService.selectTestApplicationFormChildById(id));
    }

    /**
     * 新增检测申请单子表
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationFormChild:add')")
    @Log(title = "检测申请单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestApplicationFormChild testApplicationFormChild)
    {
        return toAjax(testApplicationFormChildService.insertTestApplicationFormChild(testApplicationFormChild));
    }

    /**
     * 修改检测申请单子表
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationFormChild:edit')")
    @Log(title = "检测申请单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestApplicationFormChild testApplicationFormChild)
    {
        return toAjax(testApplicationFormChildService.updateTestApplicationFormChild(testApplicationFormChild));
    }

    /**
     * 删除检测申请单子表
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationFormChild:remove')")
    @Log(title = "检测申请单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(testApplicationFormChildService.deleteTestApplicationFormChildByIds(ids));
    }
}
