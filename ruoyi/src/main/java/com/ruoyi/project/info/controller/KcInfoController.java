package com.ruoyi.project.info.controller;

import java.util.List;

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
import com.ruoyi.project.info.domain.KcInfo;
import com.ruoyi.project.info.service.IKcInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 收发存查询Controller
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
@RestController
@RequestMapping("/system/kcInfo")
public class KcInfoController extends BaseController
{
    @Autowired
    private IKcInfoService kcInfoService;

    /**
     * 查询收发存查询列表
     */
    @PreAuthorize("@ss.hasPermi('system:kcInfo:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(KcInfo kcInfo)
    {
        startPage();
        List<KcInfo> list = kcInfoService.selectKcInfoList(kcInfo);
        return getDataTable(list);
    }

    /**
     * 导出收发存查询列表
     */
    @PreAuthorize("@ss.hasPermi('system:kcInfo:export')")
    @Log(title = "收发存查询", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(KcInfo kcInfo)
    {
        List<KcInfo> list = kcInfoService.selectKcInfoList(kcInfo);
        ExcelUtil<KcInfo> util = new ExcelUtil<KcInfo>(KcInfo.class);
        return util.exportExcel(list, "kcInfo");
    }

    /**
     * 获取收发存查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:kcInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(kcInfoService.selectKcInfoById(id));
    }

    /**
     * 新增收发存查询
     */
    @PreAuthorize("@ss.hasPermi('system:kcInfo:add')")
    @Log(title = "收发存查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KcInfo kcInfo)
    {
        return toAjax(kcInfoService.insertKcInfo(kcInfo));
    }

    /**
     * 修改收发存查询
     */
    @PreAuthorize("@ss.hasPermi('system:kcInfo:edit')")
    @Log(title = "收发存查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KcInfo kcInfo)
    {
        return toAjax(kcInfoService.updateKcInfo(kcInfo));
    }

    /**
     * 删除收发存查询
     */
    @PreAuthorize("@ss.hasPermi('system:kcInfo:remove')")
    @Log(title = "收发存查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(kcInfoService.deleteKcInfoByIds(ids));
    }
}
