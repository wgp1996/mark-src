package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.project.system.domain.CgRkdChild;
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
import com.ruoyi.project.system.domain.AdmissPoundRoomChild;
import com.ruoyi.project.system.service.IAdmissPoundRoomChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 磅房入场单明细Controller
 * 
 * @author ruoyi
 * @date 2020-08-20
 */
@RestController
@RequestMapping("/system/admissChild")
public class AdmissPoundRoomChildController extends BaseController
{
    @Autowired
    private IAdmissPoundRoomChildService admissPoundRoomChildService;

    /**
     * 查询磅房入场单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:admissChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmissPoundRoomChild admissPoundRoomChild)
    {
        startPage();
        List<AdmissPoundRoomChild> list = admissPoundRoomChildService.selectAdmissPoundRoomChildList(admissPoundRoomChild);
        return getDataTable(list);
    }
    @GetMapping("/getAdmissChild")
    public TableDataInfo getAdmissChild(AdmissPoundRoomChild admissPoundRoomChild)
    {
        List<AdmissPoundRoomChild> list = admissPoundRoomChildService.selectAdmissPoundRoomChildList(admissPoundRoomChild);
        return getDataTable(list);
    }
    /**
     * 导出磅房入场单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:admissChild:export')")
    @Log(title = "磅房入场单明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AdmissPoundRoomChild admissPoundRoomChild)
    {
        List<AdmissPoundRoomChild> list = admissPoundRoomChildService.selectAdmissPoundRoomChildList(admissPoundRoomChild);
        ExcelUtil<AdmissPoundRoomChild> util = new ExcelUtil<AdmissPoundRoomChild>(AdmissPoundRoomChild.class);
        return util.exportExcel(list, "admissChild");
    }

    /**
     * 获取磅房入场单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:admissChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(admissPoundRoomChildService.selectAdmissPoundRoomChildById(id));
    }

    /**
     * 新增磅房入场单明细
     */
    @PreAuthorize("@ss.hasPermi('system:admissChild:add')")
    @Log(title = "磅房入场单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmissPoundRoomChild admissPoundRoomChild)
    {
        return toAjax(admissPoundRoomChildService.insertAdmissPoundRoomChild(admissPoundRoomChild));
    }

    /**
     * 修改磅房入场单明细
     */
    @PreAuthorize("@ss.hasPermi('system:admissChild:edit')")
    @Log(title = "磅房入场单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmissPoundRoomChild admissPoundRoomChild)
    {
        return toAjax(admissPoundRoomChildService.updateAdmissPoundRoomChild(admissPoundRoomChild));
    }

    /**
     * 删除磅房入场单明细
     */
    @PreAuthorize("@ss.hasPermi('system:admissChild:remove')")
    @Log(title = "磅房入场单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(admissPoundRoomChildService.deleteAdmissPoundRoomChildByIds(ids));
    }
}
