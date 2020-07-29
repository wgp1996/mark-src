package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.MarkInfo;
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
import com.ruoyi.project.system.domain.MarkChildInfo;
import com.ruoyi.project.system.service.IMarkChildInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 二级市场信息Controller
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
@RestController
@RequestMapping("/system/cmark")
public class MarkChildInfoController extends BaseController
{
    @Autowired
    private IMarkChildInfoService markChildInfoService;

    /**
     * 获取主市场信息
     */
    @GetMapping(value = "/markData")
    public AjaxResult areaData()
    {
        return AjaxResult.success(markChildInfoService.selectMarkData());
    }
    /**
     * 查询二级市场信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:cmark:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarkChildInfo markChildInfo)
    {
        startPage();
        List<MarkChildInfo> list = markChildInfoService.selectMarkChildInfoList(markChildInfo);
        return getDataTable(list);
    }

    /**
     * 导出二级市场信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:cmark:export')")
    @Log(title = "二级市场信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MarkChildInfo markChildInfo)
    {
        List<MarkChildInfo> list = markChildInfoService.selectMarkChildInfoList(markChildInfo);
        ExcelUtil<MarkChildInfo> util = new ExcelUtil<MarkChildInfo>(MarkChildInfo.class);
        return util.exportExcel(list, "cmark");
    }

    /**
     * 获取二级市场信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cmark:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(markChildInfoService.selectMarkChildInfoById(id));
    }


    /**
     * 新增二级市场信息
     */
    @PreAuthorize("@ss.hasPermi('system:cmark:add')")
    @Log(title = "二级市场信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarkChildInfo markChildInfo)
    {
        MarkChildInfo info=markChildInfoService.selectMarkChildInfoByName(markChildInfo.getMarkChildName(),"");
        if(info!=null) {
            return  toAjaxByError("该二级市场在系统中已存在");
        }else{
            markChildInfo.setCreateBy(SecurityUtils.getUsername());
            markChildInfo.setId(StringUtils.getId());
            markChildInfo.setMarkChildCode(StringUtils.getRandomCode("CMK"));
            markChildInfo.setCreateTime(DateUtils.getNowDate());
            return toAjax(markChildInfoService.insertMarkChildInfo(markChildInfo));
        }
    }

    /**
     * 修改二级市场信息
     */
    @PreAuthorize("@ss.hasPermi('system:cmark:edit')")
    @Log(title = "二级市场信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarkChildInfo markChildInfo)
    {
        MarkChildInfo info=markChildInfoService.selectMarkChildInfoByName(markChildInfo.getMarkChildName(),markChildInfo.getId());
        if(info!=null) {
            return  toAjaxByError("该二级市场在系统中已存在");
        }else{
            markChildInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(markChildInfoService.insertMarkChildInfo(markChildInfo));
        }
    }

    /**
     * 删除二级市场信息
     */
    @PreAuthorize("@ss.hasPermi('system:cmark:remove')")
    @Log(title = "二级市场信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(markChildInfoService.deleteMarkChildInfoByIds(ids));
    }
}
