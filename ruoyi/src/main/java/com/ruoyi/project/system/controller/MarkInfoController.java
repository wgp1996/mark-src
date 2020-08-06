package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
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
import com.ruoyi.project.system.domain.MarkInfo;
import com.ruoyi.project.system.service.IMarkInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 【市场信息】Controller
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
@RestController
@RequestMapping("/system/mark")
public class MarkInfoController extends BaseController
{
    @Autowired
    private IMarkInfoService markInfoService;

    /**
     * 查询【市场信息】列表
     */
    @PreAuthorize("@ss.hasPermi('system:mark:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(MarkInfo markInfo)
    {
        startPage();
        List<MarkInfo> list = markInfoService.selectMarkInfoList(markInfo);
        return getDataTable(list);
    }

    /**
     * 导出【市场信息】列表
     */
    @PreAuthorize("@ss.hasPermi('system:mark:export')")
    @Log(title = "【市场信息】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MarkInfo markInfo)
    {
        List<MarkInfo> list = markInfoService.selectMarkInfoList(markInfo);
        ExcelUtil<MarkInfo> util = new ExcelUtil<MarkInfo>(MarkInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取【市场信息】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:mark:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(markInfoService.selectMarkInfoById(id));
    }

    /**
     * 新增【市场信息】
     */
    @PreAuthorize("@ss.hasPermi('system:mark:add')")
    @Log(title = "【市场信息新增】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarkInfo markInfo)
    {
        MarkInfo info=markInfoService.selectMarkInfoByName(markInfo.getMarkName(),"");
        if(info!=null) {
            return  toAjaxByError("该市场在系统中已存在");
        }else{
            markInfo.setCreateBy(SecurityUtils.getUsername());
            markInfo.setId(StringUtils.getId());
            markInfo.setMarkCode(StringUtils.getRandomCode("MK"));
            markInfo.setCreateTime(DateUtils.getNowDate());
            return toAjax(markInfoService.insertMarkInfo(markInfo));
        }
    }

    /**
     * 修改【市场信息】
     */
    @PreAuthorize("@ss.hasPermi('system:mark:edit')")
    @Log(title = "【市场信息修改】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarkInfo markInfo)
    {
        MarkInfo info=markInfoService.selectMarkInfoByName(markInfo.getMarkName(),markInfo.getId());
        if(info!=null) {
            return  toAjaxByError("该市场在系统中已存在");
        }else{
            markInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(markInfoService.updateMarkInfo(markInfo));
        }
    }

    /**
     * 删除【市场信息】
     */
    @PreAuthorize("@ss.hasPermi('system:mark:remove')")
    @Log(title = "【市场信息删除】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(markInfoService.deleteMarkInfoByIds(ids));
    }
}
