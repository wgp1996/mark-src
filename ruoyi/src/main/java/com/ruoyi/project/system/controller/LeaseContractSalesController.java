package com.ruoyi.project.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.LeaseContract;
import com.ruoyi.project.system.domain.LeaseContractChild;
import com.ruoyi.project.system.domain.LeaseContractChildSales;
import com.ruoyi.project.system.domain.LeaseContractSales;
import com.ruoyi.project.system.service.ILeaseContractChildSalesService;
import com.ruoyi.project.system.service.ILeaseContractChildService;
import com.ruoyi.project.system.service.ILeaseContractSalesService;
import com.ruoyi.project.system.service.ILeaseContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售合同Controller
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@RestController
@RequestMapping("/system/sales")
public class LeaseContractSalesController extends BaseController {
    @Autowired
    private ILeaseContractChildSalesService leaseContractChildService;
    @Autowired
    private ILeaseContractSalesService leaseContractService;

    /**
     * 查询销售合同列表
     */
    @PreAuthorize("@ss.hasPermi('system:sales:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaseContractSales leaseContract) {
        startPage();
        List<LeaseContractSales> list = leaseContractService.selectLeaseContractList(leaseContract);
        for (LeaseContractSales info : list) {
            info.setChildrenList(leaseContractChildService.selectLeaseContractChildByCode(info.getContractCode()));
        }
        return getDataTable(list);
    }

    /**
     * 获取子表信息
     */
    @GetMapping(value = "/leaseChildData/{code}")
    public AjaxResult leaseChildData(@PathVariable String code) {
        return AjaxResult.success(leaseContractChildService.selectLeaseContractChildByCode(code));
    }

    /**
     * 导出销售合同列表
     */
    @PreAuthorize("@ss.hasPermi('system:sales:export')")
    @Log(title = "销售合同", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaseContractSales leaseContract) {
        List<LeaseContractSales> list = leaseContractService.selectLeaseContractList(leaseContract);
        ExcelUtil<LeaseContractSales> util = new ExcelUtil<LeaseContractSales>(LeaseContractSales.class);
        return util.exportExcel(list, "contract");
    }

    /**
     * 获取销售合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:contract:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(leaseContractService.selectLeaseContractById(id));
    }

    /**
     * 新增销售合同
     */
    @PreAuthorize("@ss.hasPermi('system:sales:add')")
    @Log(title = "销售合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaseContractSales leaseContract) {
        //查询合同编号是否重复
        LeaseContractSales info = leaseContractService.selectLeaseContractByCode(leaseContract.getContractCode(), "");
        if (info != null) {
            return toAjaxByError("合同编号重复!");
        }
        if (leaseContract.getRows() == "") {
            return toAjaxByError("明细信息不能为空!");
        }
        List<LeaseContractChildSales> childList = JSONArray.parseArray(leaseContract.getRows(), LeaseContractChildSales.class);
        for (LeaseContractChildSales child : childList) {
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setContractCode(leaseContract.getContractCode());
            child.setCreateTime(DateUtils.getNowDate());
            leaseContractChildService.insertLeaseContractChild(child);
        }
        leaseContract.setCreateBy(SecurityUtils.getUsername());
        leaseContract.setId(StringUtils.getId());
        //leaseContract.setContractCode(StringUtils.getRandomCode("CTA"));
        leaseContract.setCreateTime(DateUtils.getNowDate());
        return toAjax(leaseContractService.insertLeaseContract(leaseContract));
    }

    /**
     * 修改销售合同
     * let nums=data.houseName;
     * for()
     */
    @PreAuthorize("@ss.hasPermi('system:sales:edit')")
    @Log(title = "销售合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaseContractSales leaseContract) {
        //查询合同编号是否重复
        LeaseContractSales info = leaseContractService.selectLeaseContractByCode(leaseContract.getContractCode(), leaseContract.getId());
        if (info != null) {
            return toAjaxByError("合同编号重复!");
        }
        if (leaseContract.getRows() == "") {
            return toAjaxByError("明细信息不能为空!");
        }
        List<LeaseContractChildSales> childList = JSONArray.parseArray(leaseContract.getRows(), LeaseContractChildSales.class);
        for (LeaseContractChildSales child : childList) {
            if (child.getId() != "" && child.getId() != null && !"".equals(child.getId())) {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setContractCode(leaseContract.getContractCode());
                child.setCreateTime(DateUtils.getNowDate());
                leaseContractChildService.updateLeaseContractChild(child);
            } else {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setContractCode(leaseContract.getContractCode());
                child.setCreateTime(DateUtils.getNowDate());
                leaseContractChildService.insertLeaseContractChild(child);
            }
        }
        leaseContract.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(leaseContractService.updateLeaseContract(leaseContract));
    }

    /**
     * 删除销售合同
     */
    @PreAuthorize("@ss.hasPermi('system:sales:remove')")
    @Log(title = "销售合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        //先删除子表信息
        int result = leaseContractChildService.deleteLeaseContractChildPid(ids);
        if (result > 0) {
            return toAjax(leaseContractService.deleteLeaseContractByIds(ids));
        } else {
            return toAjaxByError("删除失败!");
        }
    }
}
