package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.project.system.domain.MarkType;
import com.ruoyi.project.system.service.IMarkTypeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 市场分类Controller
 * 
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/system/markType")
public class MarkTypeController extends BaseController
{
    @Autowired
    private IMarkTypeService markTypeService;

    /**
     * 查询市场分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:markType:list')")
    @GetMapping("/list")
    public AjaxResult list(MarkType markType)
    {
        List<MarkType> list = markTypeService.selectMarkTypeList(markType);
        return AjaxResult.success(list);
    }

    /**
     * 导出市场分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:markType:export')")
    @Log(title = "市场分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MarkType markType)
    {
        List<MarkType> list = markTypeService.selectMarkTypeList(markType);
        ExcelUtil<MarkType> util = new ExcelUtil<MarkType>(MarkType.class);
        return util.exportExcel(list, "markType");
    }

    /**
     * 获取市场分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:markType:query')")
    @GetMapping(value = "/{markId}")
    public AjaxResult getInfo(@PathVariable("markId") Integer markId)
    {
        return AjaxResult.success(markTypeService.selectMarkTypeById(markId));
    }

    /**
     * 新增市场分类
     */
    @PreAuthorize("@ss.hasPermi('system:markType:add')")
    @Log(title = "市场分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarkType markType)
    {
        if(markType.getMarkId().longValue()==markType.getParentId()){
            return toAjaxByError("上级分类不能是自己");
        }
        MarkType info=new MarkType();
        info.setMarkId(-1);
        info.setId(markType.getMarkId());
        List<MarkType> list=markTypeService.selectMarkTypeListWhere(info);
        if(list!=null&&list.size()>0){
            return AjaxResult.error("编码重复!");
        }
        MarkType name=new MarkType();
        name.setMarkTypeName(markType.getMarkTypeName());
        name.setMarkId(-1);
        List<MarkType> nameList=markTypeService.selectMarkTypeListWhere(name);
        if(nameList!=null&&nameList.size()>0){
            return AjaxResult.error("分类名称重复!");
        }
        markType.setCreateBy(SecurityUtils.getUsername());
        return toAjax(markTypeService.insertMarkType(markType));
    }

    /**
     * 修改市场分类
     */
    @PreAuthorize("@ss.hasPermi('system:markType:edit')")
    @Log(title = "市场分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarkType markType)
    {
        if(markType.getMarkId().longValue()==markType.getParentId()){
            return toAjaxByError("上级分类不能是自己");
        }
        MarkType info=new MarkType();
        info.setMarkId(markType.getMarkId());
        info.setId(markType.getMarkId());
        List<MarkType> list=markTypeService.selectMarkTypeListWhere(info);
        if(list!=null&&list.size()>0){
            return AjaxResult.error("编码重复!");
        }
        MarkType name=new MarkType();
        name.setMarkTypeName(markType.getMarkTypeName());
        name.setMarkId(markType.getMarkId());
        List<MarkType> nameList=markTypeService.selectMarkTypeListWhere(name);
        if(nameList!=null&&nameList.size()>0){
            return AjaxResult.error("分类名称重复!");
        }
        markType.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(markTypeService.updateMarkType(markType));
    }

    /**
     * 删除市场分类
     */
    @PreAuthorize("@ss.hasPermi('system:markType:remove')")
    @Log(title = "市场分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{markIds}")
    public AjaxResult remove(@PathVariable Integer[] markIds)
    {
        if (markTypeService.hasChildMarkTypeById(markIds[0])>0)
        {
            return AjaxResult.error("存在下级分类,不允许删除");
        }
        return toAjax(markTypeService.deleteMarkTypeByIds(markIds));
    }
}
