package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.LeaseContractChild;
import com.ruoyi.project.system.domain.LeaseContractChildSales;
import com.ruoyi.project.system.service.ILeaseContractChildSalesService;
import com.ruoyi.project.system.service.ILeaseContractChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售合同子表信息Controller
 * 
 * @author ruoyi
 * @date 2020-07-30
 */
@RestController
@RequestMapping("/system/saleschild")
public class LeaseContractChildSalesController extends BaseController
{
    @Autowired
    private ILeaseContractChildSalesService leaseContractChildService;

    /**
     * 查询销售合同子表信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:saleschild:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaseContractChildSales leaseContractChild)
    {
        startPage();
        List<LeaseContractChildSales> list = leaseContractChildService.selectLeaseContractChildList(leaseContractChild);
        return getDataTable(list);
    }

    /**
     * 导出销售合同子表信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:saleschild:export')")
    @Log(title = "销售合同子表信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaseContractChildSales leaseContractChild)
    {
        List<LeaseContractChildSales> list = leaseContractChildService.selectLeaseContractChildList(leaseContractChild);
        ExcelUtil<LeaseContractChildSales> util = new ExcelUtil<LeaseContractChildSales>(LeaseContractChildSales.class);
        return util.exportExcel(list, "child");
    }

    /**
     * 获取销售合同子表信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:saleschild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(leaseContractChildService.selectLeaseContractChildById(id));
    }

    /**
     * 新增销售合同子表信息
     */
    @PreAuthorize("@ss.hasPermi('system:saleschild:add')")
    @Log(title = "销售合同子表信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaseContractChildSales leaseContractChild)
    {
        return toAjax(leaseContractChildService.insertLeaseContractChild(leaseContractChild));
    }

    /**
     * 修改销售合同子表信息
     */
    @PreAuthorize("@ss.hasPermi('system:saleschild:edit')")
    @Log(title = "销售合同子表信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaseContractChildSales leaseContractChild)
    {
        return toAjax(leaseContractChildService.updateLeaseContractChild(leaseContractChild));
    }

    /**
     * 删除销售合同子表信息
     */
    //@PreAuthorize("@ss.hasPermi('system:leasechild:remove')")
    //@Log(title = "销售合同子表信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            //修改摊位状态
            leaseContractChildService.updateStallInfoById(ids[i]);
        }
        return toAjax(leaseContractChildService.deleteLeaseContractChildByIds(ids));
    }
}
