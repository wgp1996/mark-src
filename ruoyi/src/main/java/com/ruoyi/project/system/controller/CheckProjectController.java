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
import com.ruoyi.project.system.domain.CheckProject;
import com.ruoyi.project.system.service.ICheckProjectService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 监测项目建档Controller
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/system/checkProject")
public class CheckProjectController extends BaseController
{
    @Autowired
    private ICheckProjectService checkProjectService;

    /**
     * 查询监测项目建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkProject:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(CheckProject checkProject)
    {
        startPage();
        List<CheckProject> list = checkProjectService.selectCheckProjectList(checkProject);
        return getDataTable(list);
    }

    /**
     * 导出监测项目建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkProject:export')")
    @Log(title = "监测项目建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CheckProject checkProject)
    {
        List<CheckProject> list = checkProjectService.selectCheckProjectList(checkProject);
        ExcelUtil<CheckProject> util = new ExcelUtil<CheckProject>(CheckProject.class);
        return util.exportExcel(list, "checkProject");
    }

    /**
     * 获取监测项目建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:checkProject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(checkProjectService.selectCheckProjectById(id));
    }

    /**
     * 新增监测项目建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkProject:add')")
    @Log(title = "监测项目建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckProject checkProject)
    {
        checkProject.setId(-1);
        if(checkProjectService.checkProjectIsContain(checkProject)>0){
            return  AjaxResult.error("重复添加!");
        }
        checkProject.setCreateBy(SecurityUtils.getUsername());
        if(checkProject.getIsDefault()==1){
            checkProjectService.updateCheckProjectDefault(checkProject);
        }
        checkProject.setProjectNum(StringUtils.getRandomCode("CR"));
        return toAjax(checkProjectService.insertCheckProject(checkProject));
    }

    /**
     * 修改监测项目建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkProject:edit')")
    @Log(title = "监测项目建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckProject checkProject)
    {
        if(checkProjectService.checkProjectIsContain(checkProject)>0){
            return  AjaxResult.error("重复添加!");
        }
        if(checkProject.getIsDefault()==1){
            checkProjectService.updateCheckProjectDefault(checkProject);
        }
        checkProject.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(checkProjectService.updateCheckProject(checkProject));
    }

    /**
     * 删除监测项目建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkProject:remove')")
    @Log(title = "监测项目建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(checkProjectService.deleteCheckProjectByIds(ids));
    }
}
