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
import com.ruoyi.project.system.domain.CheckObject;
import com.ruoyi.project.system.service.ICheckObjectService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 监测物体建档Controller
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/system/checkObject")
public class CheckObjectController extends BaseController
{
    @Autowired
    private ICheckObjectService checkObjectService;

    /**
     * 查询监测物体建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkObject:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(CheckObject checkObject)
    {
        startPage();
        List<CheckObject> list = checkObjectService.selectCheckObjectList(checkObject);
        return getDataTable(list);
    }

    /**
     * 导出监测物体建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkObject:export')")
    @Log(title = "监测物体建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CheckObject checkObject)
    {
        List<CheckObject> list = checkObjectService.selectCheckObjectList(checkObject);
        ExcelUtil<CheckObject> util = new ExcelUtil<CheckObject>(CheckObject.class);
        return util.exportExcel(list, "checkObject");
    }

    /**
     * 获取监测物体建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:checkObject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(checkObjectService.selectCheckObjectById(id));
    }

    /**
     * 新增监测物体建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkObject:add')")
    @Log(title = "监测物体建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckObject checkObject)
    {
        checkObject.setId(-1);
        if(checkObjectService.checkObjectIsContain(checkObject)>0){
            return AjaxResult.error("重复添加!");
        }
        checkObject.setCreateBy(SecurityUtils.getUsername());
        checkObject.setObjectNum(StringUtils.getRandomCode("CO"));
        return toAjax(checkObjectService.insertCheckObject(checkObject));
    }

    /**
     * 修改监测物体建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkObject:edit')")
    @Log(title = "监测物体建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckObject checkObject)
    {
        if(checkObjectService.checkObjectIsContain(checkObject)>0){
            return AjaxResult.error("重复添加!");
        }
        checkObject.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(checkObjectService.updateCheckObject(checkObject));
    }

    /**
     * 删除监测物体建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkObject:remove')")
    @Log(title = "监测物体建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(checkObjectService.deleteCheckObjectByIds(ids));
    }
}
