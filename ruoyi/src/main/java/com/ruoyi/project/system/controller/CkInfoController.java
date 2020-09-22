package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.CkInfo;
import com.ruoyi.project.system.service.ICkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库建档Controller
 * 
 * @author ruoyi
 * @date 2020-08-11
 */
@RestController
@RequestMapping("/system/ck")
public class CkInfoController extends BaseController
{
    @Autowired
    private ICkInfoService ckInfoService;

    /**
     * 查询仓库建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:ck:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(CkInfo ckInfo)
    {
        startPage();
        List<CkInfo> list = ckInfoService.selectCkInfoList(ckInfo);
        return getDataTable(list);
    }

    /**
     * 仓库下拉列表
     */
    @GetMapping("/getCkAll")
    public TableDataInfo getCkAll() {
        CkInfo ckInfo=new CkInfo();
        //ckInfo.setCreateBy(SecurityUtils.getUsername());
        List<CkInfo> list = ckInfoService.selectCkInfoList(ckInfo);
        ckInfo=null;
        return getDataTable(list);
    }

    /**
     * 导出仓库建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:ck:export')")
    @Log(title = "仓库建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CkInfo ckInfo)
    {
        List<CkInfo> list = ckInfoService.selectCkInfoList(ckInfo);
        ExcelUtil<CkInfo> util = new ExcelUtil<CkInfo>(CkInfo.class);
        return util.exportExcel(list, "ck");
    }

    /**
     * 获取仓库建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ck:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ckInfoService.selectCkInfoById(id));
    }

    /**
     * 新增仓库建档
     */
    @PreAuthorize("@ss.hasPermi('system:ck:add')")
    @Log(title = "仓库建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CkInfo ckInfo)
    {
        CkInfo info = ckInfoService.selectCkInfoByName(ckInfo.getCkName(), "");
        if (info != null) {
            return toAjaxByError("该仓库在系统中已存在");
        } else {
            ckInfo.setCreateBy(SecurityUtils.getUsername());
            ckInfo.setId(StringUtils.getId());
            ckInfo.setCkCode(StringUtils.getRandomCode("CK"));
            return toAjax(ckInfoService.insertCkInfo(ckInfo));
        }
    }

    /**
     * 修改仓库建档
     */
    @PreAuthorize("@ss.hasPermi('system:ck:edit')")
    @Log(title = "仓库建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CkInfo ckInfo)
    {
        CkInfo info = ckInfoService.selectCkInfoByName(ckInfo.getCkName(), ckInfo.getId());
        if (info != null) {
            return toAjaxByError("该仓库在系统中已存在");
        } else {
            ckInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(ckInfoService.updateCkInfo(ckInfo));
        }
    }

    /**
     * 删除仓库建档
     */
    @PreAuthorize("@ss.hasPermi('system:ck:remove')")
    @Log(title = "仓库建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ckInfoService.deleteCkInfoByIds(ids));
    }
}
