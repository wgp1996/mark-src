package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.LeaseContractChildPool;
import com.ruoyi.project.system.domain.LeaseContractChildSales;
import com.ruoyi.project.system.service.ILeaseContractChildPoolService;
import com.ruoyi.project.system.service.ILeaseContractChildSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 联营合同子表信息Controller
 * 
 * @author ruoyi
 * @date 2020-07-30
 */
@RestController
@RequestMapping("/system/poolchild")
public class LeaseContractChildPoolController extends BaseController
{
    @Autowired
    private ILeaseContractChildPoolService leaseContractChildService;

    /**
     * 查询联营合同子表信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:poolchild:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaseContractChildPool leaseContractChild)
    {
        startPage();
        List<LeaseContractChildPool> list = leaseContractChildService.selectLeaseContractChildList(leaseContractChild);
        return getDataTable(list);
    }

    /**
     * 导出联营合同子表信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:poolchild:export')")
    @Log(title = "联营合同子表信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaseContractChildPool leaseContractChild)
    {
        List<LeaseContractChildPool> list = leaseContractChildService.selectLeaseContractChildList(leaseContractChild);
        ExcelUtil<LeaseContractChildPool> util = new ExcelUtil<LeaseContractChildPool>(LeaseContractChildPool.class);
        return util.exportExcel(list, "child");
    }

    /**
     * 获取联营合同子表信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:poolchild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(leaseContractChildService.selectLeaseContractChildById(id));
    }

    /**
     * 新增联营合同子表信息
     */
    @PreAuthorize("@ss.hasPermi('system:poolchild:add')")
    @Log(title = "联营合同子表信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaseContractChildPool leaseContractChild)
    {
        return toAjax(leaseContractChildService.insertLeaseContractChild(leaseContractChild));
    }

    /**
     * 修改联营合同子表信息
     */
    @PreAuthorize("@ss.hasPermi('system:poolchild:edit')")
    @Log(title = "联营合同子表信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaseContractChildPool leaseContractChild)
    {
        return toAjax(leaseContractChildService.updateLeaseContractChild(leaseContractChild));
    }

    /**
     * 删除联营合同子表信息
     */
    //@PreAuthorize("@ss.hasPermi('system:leasechild:remove')")
    //@Log(title = "联营合同子表信息", businessType = BusinessType.DELETE)
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
