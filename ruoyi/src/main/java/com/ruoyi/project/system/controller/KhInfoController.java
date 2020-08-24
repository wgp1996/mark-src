package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.PersonInfo;
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
import com.ruoyi.project.system.domain.KhInfo;
import com.ruoyi.project.system.service.IKhInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 客户建档Controller
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/system/kh")
public class KhInfoController extends BaseController
{
    @Autowired
    private IKhInfoService khInfoService;

    /**
     * 查询客户建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:kh:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(KhInfo khInfo)
    {
        startPage();
        List<KhInfo> list = khInfoService.selectKhInfoList(khInfo);
        return getDataTable(list);
    }

    /**
     * 导出客户建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:kh:export')")
    @Log(title = "客户建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(KhInfo khInfo)
    {
        List<KhInfo> list = khInfoService.selectKhInfoList(khInfo);
        ExcelUtil<KhInfo> util = new ExcelUtil<KhInfo>(KhInfo.class);
        return util.exportExcel(list, "kh");
    }

    /**
     * 获取客户建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:kh:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(khInfoService.selectKhInfoById(id));
    }

    /**
     * 新增客户建档
     */
    @PreAuthorize("@ss.hasPermi('system:kh:add')")
    @Log(title = "客户建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KhInfo khInfo)
    {
        khInfo.setCreateBy(SecurityUtils.getUsername());
        KhInfo info=khInfoService.selectKhInfoByName(khInfo.getKhName(),khInfo.getCreateBy(),-1);
        if(info!=null) {
            return  toAjaxByError("该客户在系统中已存在");
        }else {
            return toAjax(khInfoService.insertKhInfo(khInfo));
        }
    }

    /**
     * 修改客户建档
     */
    @PreAuthorize("@ss.hasPermi('system:kh:edit')")
    @Log(title = "客户建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KhInfo khInfo)
    {
        khInfo.setCreateBy(SecurityUtils.getUsername());
        KhInfo info=khInfoService.selectKhInfoByName(khInfo.getKhName(),khInfo.getCreateBy(),khInfo.getId());
        if(info!=null) {
            return  toAjaxByError("该客户在系统中已存在");
        }else {
            return toAjax(khInfoService.updateKhInfo(khInfo));
        }
    }

    /**
     * 删除客户建档
     */
    @PreAuthorize("@ss.hasPermi('system:kh:remove')")
    @Log(title = "客户建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(khInfoService.deleteKhInfoByIds(ids));
    }
}
