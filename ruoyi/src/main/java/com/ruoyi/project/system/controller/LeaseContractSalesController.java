package com.ruoyi.project.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.ILeaseContractChildSalesService;
import com.ruoyi.project.system.service.ILeaseContractSalesService;
import com.ruoyi.project.system.service.IStallInfoService;
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
    private ILeaseContractChildSalesService leaseContractChildSalesService;
    @Autowired
    private ILeaseContractSalesService leaseContractSalesService;
    @Autowired
    private IStallInfoService stallInfoService;
    /**
     * 查询销售合同列表
     */
    @PreAuthorize("@ss.hasPermi('system:sales:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(LeaseContractSales leaseContractSales) {
        startPage();
        List<LeaseContractSales> list = leaseContractSalesService.selectLeaseContractSalesList(leaseContractSales);
        for (LeaseContractSales info : list) {
            info.setChildrenList(leaseContractChildSalesService.selectLeaseContractChildByCode(info.getContractCode()));
        }
        return getDataTable(list);
    }

    /**
     * 获取子表信息
     */
    @GetMapping(value = "/leaseChildData/{code}")
    public AjaxResult leaseChildData(@PathVariable String code) {
        return AjaxResult.success(leaseContractChildSalesService.selectLeaseContractChildByCode(code));
    }

    /**
     * 导出销售合同列表
     */
    @PreAuthorize("@ss.hasPermi('system:sales:export')")
    @Log(title = "销售合同", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaseContractSales leaseContract) {
        List<LeaseContractSales> list = leaseContractSalesService.selectLeaseContractSalesList(leaseContract);
        ExcelUtil<LeaseContractSales> util = new ExcelUtil<LeaseContractSales>(LeaseContractSales.class);
        return util.exportExcel(list, "contract");
    }

    /**
     * 获取销售合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sales:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(leaseContractSalesService.selectLeaseContractById(id));
    }

    /**
     * 新增销售合同
     */
    @PreAuthorize("@ss.hasPermi('system:sales:add')")
    @Log(title = "销售合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaseContractSales leaseContract) {
        //查询合同编号是否重复
        LeaseContractSales info = leaseContractSalesService.selectLeaseContractByCode(leaseContract.getContractCode(), "");
        if (info != null) {
            return toAjaxByError("合同编号重复!");
        }
        if (leaseContract.getRows() == "") {
            return toAjaxByError("明细信息不能为空!");
        }
        List<LeaseContractChildSales> childList = JSONArray.parseArray(leaseContract.getRows(), LeaseContractChildSales.class);
        for (LeaseContractChildSales child : childList) {
            //修改摊位信息
           /* StallInfo stallInfo=new StallInfo();
            stallInfo.setStallCode(child.getStallCode());//摊位编码
            stallInfo.setStallStatus("2");//已销售
            stallInfo.setStallStartTime(child.getLeaseTime());//销售日期
            stallInfo.setStallMoney(child.getRentMoney());//销售金额
            stallInfo.setStallLeaseholder(leaseContract.getOwnerName());//租赁方
            stallInfoService.updateStallInfoByCode(stallInfo);*/

            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setContractCode(leaseContract.getContractCode());
            child.setCreateTime(DateUtils.getNowDate());
            leaseContractChildSalesService.insertLeaseContractChild(child);
        }
        leaseContract.setContractStatus("正操作");
        leaseContract.setCreateBy(SecurityUtils.getUsername());
        leaseContract.setId(StringUtils.getId());
        //leaseContract.setContractCode(StringUtils.getRandomCode("CTA"));
        leaseContract.setCreateTime(DateUtils.getNowDate());
        return toAjax(leaseContractSalesService.insertLeaseContract(leaseContract));
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
        LeaseContractSales info = leaseContractSalesService.selectLeaseContractByCode(leaseContract.getContractCode(), leaseContract.getId());
        if (info != null) {
            return toAjaxByError("合同编号重复!");
        }
        //检查是否为已生效的合同
        if("已生效".equals(leaseContract.getContractStatus())){
            return  toAjaxByError("该合同状态禁止修改!");
        }
        if (leaseContract.getRows() == "") {
            return toAjaxByError("明细信息不能为空!");
        }
        List<LeaseContractChildSales> childList = JSONArray.parseArray(leaseContract.getRows(), LeaseContractChildSales.class);
        for (LeaseContractChildSales child : childList) {
            //修改摊位信息
          /*  StallInfo stallInfo=new StallInfo();
            stallInfo.setStallCode(child.getStallCode());//摊位编码
            stallInfo.setStallStatus("2");//已销售
            stallInfo.setStallStartTime(child.getLeaseTime());//销售日期
            stallInfo.setStallMoney(child.getRentMoney());//销售金额
            stallInfo.setStallLeaseholder(leaseContract.getOwnerName());//租赁方
            stallInfoService.updateStallInfoByCode(stallInfo);*/

            if (child.getId() != "" && child.getId() != null && !"".equals(child.getId())) {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setContractCode(leaseContract.getContractCode());
                child.setCreateTime(DateUtils.getNowDate());
                leaseContractChildSalesService.updateLeaseContractChild(child);
            } else {
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setContractCode(leaseContract.getContractCode());
                child.setCreateTime(DateUtils.getNowDate());
                leaseContractChildSalesService.insertLeaseContractChild(child);
            }
        }
        leaseContract.setContractStatus("正操作");
        leaseContract.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(leaseContractSalesService.updateLeaseContract(leaseContract));
    }

    /**
     * 删除销售合同
     */
    @PreAuthorize("@ss.hasPermi('system:sales:remove')")
    @Log(title = "销售合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        for(int i=0;i<ids.length;i++){
            LeaseContractSales info = leaseContractSalesService.selectLeaseContractById(ids[i]);
            if(!"正操作".equals(info.getContractStatus())){
                return toAjaxByError(info.getContractName()+"：该合同状态禁止删除!");
            }
        }
        //删除子表信息
        leaseContractChildSalesService.deleteLeaseContractChildPid(ids);
        //删除主表信息
        leaseContractSalesService.deleteLeaseContractByIds(ids);
        return toAjaxBySuccess("删除成功!");
        //批量修改摊位信息
       /* int result = leaseContractChildSalesService.updateStallInfoByPids(ids);
        if (result > 0) {
            //删除子表信息
            leaseContractChildSalesService.deleteLeaseContractChildPid(ids);
            //删除主表信息
            leaseContractSalesService.deleteLeaseContractByIds(ids);
            return toAjaxBySuccess("删除成功!");
        } else {
            return toAjaxByError("删除失败!");
        }*/
    }

    /**
     * 销售合同生效
     */
    @PreAuthorize("@ss.hasPermi('system:sales:effect')")
    @Log(title = "销售合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            LeaseContractSales leaseContract=leaseContractSalesService.selectLeaseContractById(ids[i]);
            List<LeaseContractChildSales> childList=leaseContractChildSalesService.selectLeaseContractChildByCode(leaseContract.getContractCode());
            for(LeaseContractChildSales child:childList){
                //修改摊位信息
                StallInfo stallInfo=new StallInfo();
                stallInfo.setStallCode(child.getStallCode());//摊位编码
                stallInfo.setStallStatus("2");//已销售
                stallInfo.setStallStartTime(child.getLeaseTime());//销售日期
                stallInfo.setStallMoney(child.getRentMoney());//销售金额
                stallInfo.setStallLeaseholder(leaseContract.getOwnerName());//租赁方
                stallInfoService.updateStallInfoByCode(stallInfo);
            }
        }
        return toAjax(leaseContractSalesService.updateLeaseContractStatus(ids));
    }
}
