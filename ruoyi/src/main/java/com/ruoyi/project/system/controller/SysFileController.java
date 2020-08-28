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
import com.ruoyi.project.system.domain.SysFile;
import com.ruoyi.project.system.service.ISysFileService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 附件建档Controller
 * 
 * @author ruoyi
 * @date 2020-08-27
 */
@RestController
@RequestMapping("/system/file")
public class SysFileController extends BaseController
{
    @Autowired
    private ISysFileService sysFileService;

    /**
     * 查询附件建档列表
     * @PreAuthorize("@ss.hasPermi('system:file:list')")
     */
    @GetMapping("/list")
    public TableDataInfo list(SysFile sysFile)
    {
        //startPage();
        List<SysFile> list = sysFileService.selectSysFileList(sysFile);
        return getDataTable(list);
    }

    /**
     * 导出附件建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:file:export')")
    @Log(title = "附件建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysFile sysFile)
    {
        List<SysFile> list = sysFileService.selectSysFileList(sysFile);
        ExcelUtil<SysFile> util = new ExcelUtil<SysFile>(SysFile.class);
        return util.exportExcel(list, "file");
    }

    /**
     * 获取附件建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:file:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysFileService.selectSysFileById(id));
    }

    /**
     * 新增附件建档
     */
    @PreAuthorize("@ss.hasPermi('system:file:add')")
    @Log(title = "附件建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFile sysFile)
    {
        return toAjax(sysFileService.insertSysFile(sysFile));
    }

    /**
     * 修改附件建档
     */
    @PreAuthorize("@ss.hasPermi('system:file:edit')")
    @Log(title = "附件建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFile sysFile)
    {
        return toAjax(sysFileService.updateSysFile(sysFile));
    }

    /**
     * 删除附件建档
     */
    @PreAuthorize("@ss.hasPermi('system:file:remove')")
    @Log(title = "附件建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysFileService.deleteSysFileByIds(ids));
    }
}
