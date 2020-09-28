package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.project.system.domain.CheckNum;
import com.ruoyi.project.system.service.ICheckNumService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 检测标准设定Controller
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/system/check")
public class CheckNumController extends BaseController
{
    @Autowired
    private ICheckNumService checkNumService;

    /**
     * 查询检测标准设定列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(CheckNum checkNum)
    {
        startPage();
        List<CheckNum> list = checkNumService.selectCheckNumList(checkNum);
        return getDataTable(list);
    }

    /**
     * 导出检测标准设定列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:export')")
    @Log(title = "检测标准设定", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CheckNum checkNum)
    {
        List<CheckNum> list = checkNumService.selectCheckNumList(checkNum);
        ExcelUtil<CheckNum> util = new ExcelUtil<CheckNum>(CheckNum.class);
        return util.exportExcel(list, "check");
    }

    /**
     * 获取检测标准设定详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:check:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(checkNumService.selectCheckNumById(id));
    }

    /**
     * 新增检测标准设定
     */
    @PreAuthorize("@ss.hasPermi('system:check:add')")
    @Log(title = "检测标准设定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckNum checkNum)
    {
        if(checkNumService.checkNumIsContain(checkNum)>0){
            return  AjaxResult.error("只能添加一条默认数据!");
        }
        checkNum.setCreateBy(SecurityUtils.getUsername());
        return toAjax(checkNumService.insertCheckNum(checkNum));
    }

    /**
     * 修改检测标准设定
     */
    @PreAuthorize("@ss.hasPermi('system:check:edit')")
    @Log(title = "检测标准设定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckNum checkNum)
    {
        return toAjax(checkNumService.updateCheckNum(checkNum));
    }

    /**
     * 删除检测标准设定
     */
    @PreAuthorize("@ss.hasPermi('system:check:remove')")
    @Log(title = "检测标准设定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(checkNumService.deleteCheckNumByIds(ids));
    }
}
