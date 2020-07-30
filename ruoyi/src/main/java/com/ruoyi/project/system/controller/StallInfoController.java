package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.ruoyi.project.system.domain.StallInfo;
import com.ruoyi.project.system.service.IStallInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 市场摊位信息Controller
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/system/stall")
public class StallInfoController extends BaseController {
    @Autowired
    private IStallInfoService stallInfoService;

    /**
     * 查询市场摊位信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:stall:list')")
    @GetMapping("/list")
    public TableDataInfo list(StallInfo stallInfo) {
        startPage();
        List<StallInfo> list = stallInfoService.selectStallInfoList(stallInfo);
        return getDataTable(list);
    }

    /**
     * 导出市场摊位信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:stall:export')")
    @Log(title = "市场摊位信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StallInfo stallInfo) {
        List<StallInfo> list = stallInfoService.selectStallInfoList(stallInfo);
        ExcelUtil<StallInfo> util = new ExcelUtil<StallInfo>(StallInfo.class);
        return util.exportExcel(list, "stall");
    }

    /**
     * 获取市场摊位信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stall:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(stallInfoService.selectStallInfoById(id));
    }

    /**
     * 新增市场摊位信息
     */
    @PreAuthorize("@ss.hasPermi('system:stall:add')")
    @Log(title = "市场摊位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StallInfo stallInfo) {
        StallInfo info = stallInfoService.selectStallInfoByCode(stallInfo.getStallCode(), "");
        if (info != null) {
            return toAjaxByError("该摊位在系统中已存在");
        } else {
            stallInfo.setCreateBy(SecurityUtils.getUsername());
            stallInfo.setId(StringUtils.getId());
            //stallInfo.setMarkCode(StringUtils.getRandomCode("MK"));
            stallInfo.setCreateTime(DateUtils.getNowDate());
            return toAjax(stallInfoService.insertStallInfo(stallInfo));
        }
    }

    /**
     * 修改市场摊位信息
     */
    @PreAuthorize("@ss.hasPermi('system:stall:edit')")
    @Log(title = "市场摊位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StallInfo stallInfo) {
        StallInfo info = stallInfoService.selectStallInfoByCode(stallInfo.getStallCode(), stallInfo.getId());
        if (info != null) {
            return toAjaxByError("该摊位在系统中已存在");
        } else {
            stallInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(stallInfoService.updateStallInfo(stallInfo));
        }

    }

    /**
     * 获取二级市场信息
     */
    @GetMapping(value = "/cmarkData")
    public AjaxResult cmarkData() {
        return AjaxResult.success(stallInfoService.selectCMarkData());
    }

    /**
     * 删除市场摊位信息
     */
    @PreAuthorize("@ss.hasPermi('system:stall:remove')")
    @Log(title = "市场摊位信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(stallInfoService.deleteStallInfoByIds(ids));
    }
}
