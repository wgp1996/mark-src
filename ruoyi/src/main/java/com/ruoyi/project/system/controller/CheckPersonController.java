package com.ruoyi.project.system.controller;

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
import com.ruoyi.project.system.domain.CheckPerson;
import com.ruoyi.project.system.service.ICheckPersonService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 检测人员建档Controller
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@RestController
@RequestMapping("/system/checkPerson")
public class CheckPersonController extends BaseController
{
    @Autowired
    private ICheckPersonService checkPersonService;

    /**
     * 查询检测人员建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkPerson:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(CheckPerson checkPerson)
    {
        startPage();
        List<CheckPerson> list = checkPersonService.selectCheckPersonList(checkPerson);
        return getDataTable(list);
    }

    /**
     * 导出检测人员建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkPerson:export')")
    @Log(title = "检测人员建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CheckPerson checkPerson)
    {
        List<CheckPerson> list = checkPersonService.selectCheckPersonList(checkPerson);
        ExcelUtil<CheckPerson> util = new ExcelUtil<CheckPerson>(CheckPerson.class);
        return util.exportExcel(list, "checkPerson");
    }

    /**
     * 获取检测人员建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:checkPerson:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(checkPersonService.selectCheckPersonById(id));
    }

    /**
     * 新增检测人员建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkPerson:add')")
    @Log(title = "检测人员建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckPerson checkPerson)
    {
        checkPerson.setCheckPersonCode(StringUtils.getRandomCode("PC"));
        checkPerson.setId(StringUtils.getId());
        checkPerson.setCreateBy(SecurityUtils.getUsername());
        return toAjax(checkPersonService.insertCheckPerson(checkPerson));
    }

    /**
     * 修改检测人员建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkPerson:edit')")
    @Log(title = "检测人员建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckPerson checkPerson)
    {
        checkPerson.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(checkPersonService.updateCheckPerson(checkPerson));
    }

    /**
     * 删除检测人员建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkPerson:remove')")
    @Log(title = "检测人员建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(checkPersonService.deleteCheckPersonByIds(ids));
    }
}
