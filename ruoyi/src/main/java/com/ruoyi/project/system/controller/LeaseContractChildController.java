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
import com.ruoyi.project.system.domain.LeaseContractChild;
import com.ruoyi.project.system.service.ILeaseContractChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 租赁合同子表信息Controller
 * 
 * @author ruoyi
 * @date 2020-07-30
 */
@RestController
@RequestMapping("/system/leasechild")
public class LeaseContractChildController extends BaseController
{
    @Autowired
    private ILeaseContractChildService leaseContractChildService;

    /**
     * 查询租赁合同子表信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:leasechild:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaseContractChild leaseContractChild)
    {
        startPage();
        List<LeaseContractChild> list = leaseContractChildService.selectLeaseContractChildList(leaseContractChild);
        return getDataTable(list);
    }

    /**
     * 租赁收款查询租赁合同子表信息列表
     */
    @GetMapping("/getLeaseContractlist")
    public TableDataInfo getContractlist(LeaseContractChild leaseContractChild) {
        startPage();
        List<LeaseContractChild> list = leaseContractChildService.selectLeaseByCollection(leaseContractChild);
        return getDataTable(list);
    }

    /**
     * 导出租赁合同子表信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:leasechild:export')")
    @Log(title = "租赁合同子表信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaseContractChild leaseContractChild)
    {
        List<LeaseContractChild> list = leaseContractChildService.selectLeaseContractChildList(leaseContractChild);
        ExcelUtil<LeaseContractChild> util = new ExcelUtil<LeaseContractChild>(LeaseContractChild.class);
        return util.exportExcel(list, "child");
    }

    /**
     * 获取租赁合同子表信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:leasechild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(leaseContractChildService.selectLeaseContractChildById(id));
    }

    /**
     * 新增租赁合同子表信息
     */
    @PreAuthorize("@ss.hasPermi('system:leasechild:add')")
    @Log(title = "租赁合同子表信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaseContractChild leaseContractChild)
    {
        return toAjax(leaseContractChildService.insertLeaseContractChild(leaseContractChild));
    }

    /**
     * 修改租赁合同子表信息
     */
    @PreAuthorize("@ss.hasPermi('system:leasechild:edit')")
    @Log(title = "租赁合同子表信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaseContractChild leaseContractChild)
    {
        return toAjax(leaseContractChildService.updateLeaseContractChild(leaseContractChild));
    }

    /**
     * 删除租赁合同子表信息
     */
    //@PreAuthorize("@ss.hasPermi('system:leasechild:remove')")
    //@Log(title = "租赁合同子表信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(leaseContractChildService.deleteLeaseContractChildByIds(ids));
    }
}
