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
import com.ruoyi.project.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 联营合同Controller
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@RestController
@RequestMapping("/system/pool")
public class LeaseContractPoolController extends BaseController {
    @Autowired
    private ILeaseContractChildPoolService leaseContractChildService;
    @Autowired
    private ILeaseContractPoolService leaseContractService;
    @Autowired
    private IStallInfoService stallInfoService;
    /**
     * 查询联营合同列表
     */
    @PreAuthorize("@ss.hasPermi('system:pool:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(LeaseContractPool leaseContract) {
        startPage();
        List<LeaseContractPool> list = leaseContractService.selectLeaseContractList(leaseContract);
        for (LeaseContractPool info : list) {
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
     * 导出联营合同列表
     */
    @PreAuthorize("@ss.hasPermi('system:pool:export')")
    @Log(title = "联营合同", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaseContractPool leaseContract) {
        List<LeaseContractPool> list = leaseContractService.selectLeaseContractList(leaseContract);
        ExcelUtil<LeaseContractPool> util = new ExcelUtil<LeaseContractPool>(LeaseContractPool.class);
        return util.exportExcel(list, "contract");
    }

    /**
     * 获取联营合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:pool:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(leaseContractService.selectLeaseContractById(id));
    }

    /**
     * 新增联营合同
     */
    @PreAuthorize("@ss.hasPermi('system:pool:add')")
    @Log(title = "联营合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaseContractPool leaseContract) {
        //查询合同编号是否重复
        LeaseContractPool info = leaseContractService.selectLeaseContractByCode(leaseContract.getContractCode(), "");
        if (info != null) {
            return toAjaxByError("合同编号重复!");
        }

        if (leaseContract.getRows() == "") {
            return toAjaxByError("明细信息不能为空!");
        }
        List<LeaseContractChildPool> childList = JSONArray.parseArray(leaseContract.getRows(), LeaseContractChildPool.class);
        for (LeaseContractChildPool child : childList) {
            //修改摊位信息
            /*StallInfo stallInfo=new StallInfo();
            stallInfo.setStallCode(child.getStallCode());//摊位编码
            stallInfo.setStallStatus("3");//已联营
            stallInfo.setStallStartTime(child.getLeaseTime());//联营日期
            stallInfo.setStallLeaseholder(leaseContract.getOwnerName());//联营方
            stallInfoService.updateStallInfoByCode(stallInfo);*/


            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setContractCode(leaseContract.getContractCode());
            child.setCreateTime(DateUtils.getNowDate());
            leaseContractChildService.insertLeaseContractChild(child);
        }
        leaseContract.setContractStatus("正操作");
        leaseContract.setCreateBy(SecurityUtils.getUsername());
        leaseContract.setId(StringUtils.getId());
        //leaseContract.setContractCode(StringUtils.getRandomCode("CTA"));
        leaseContract.setCreateTime(DateUtils.getNowDate());
        return toAjax(leaseContractService.insertLeaseContract(leaseContract));
    }

    /**
     * 修改联营合同
     * let nums=data.houseName;
     * for()
     */
    @PreAuthorize("@ss.hasPermi('system:pool:edit')")
    @Log(title = "联营合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaseContractPool leaseContract) {
        //查询合同编号是否重复
        LeaseContractPool info = leaseContractService.selectLeaseContractByCode(leaseContract.getContractCode(), leaseContract.getId());
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
        List<LeaseContractChildPool> childList = JSONArray.parseArray(leaseContract.getRows(), LeaseContractChildPool.class);
        for (LeaseContractChildPool child : childList) {
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
            //修改摊位信息
           /* StallInfo stallInfo=new StallInfo();
            stallInfo.setStallCode(child.getStallCode());//摊位编码
            stallInfo.setStallStatus("3");//已联营
            stallInfo.setStallStartTime(child.getLeaseTime());//联营日期
            stallInfo.setStallLeaseholder(leaseContract.getOwnerName());//联营方
            stallInfoService.updateStallInfoByCode(stallInfo);*/
        }
        leaseContract.setContractStatus("正操作");
        leaseContract.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(leaseContractService.updateLeaseContract(leaseContract));
    }

    /**
     * 删除联营合同
     */
    @PreAuthorize("@ss.hasPermi('system:pool:remove')")
    @Log(title = "联营合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        for(int i=0;i<ids.length;i++){
            LeaseContractPool info = leaseContractService.selectLeaseContractById(ids[i]);
            if(!"正操作".equals(info.getContractStatus())){
                return toAjaxByError(info.getContractName()+"：该合同状态禁止删除!");
            }
        }
        //删除子表信息
        leaseContractChildService.deleteLeaseContractChildPid(ids);
        //删除主表信息
        leaseContractService.deleteLeaseContractByIds(ids);
        return toAjaxBySuccess("删除成功!");
        //批量修改摊位信息
        /*int result = leaseContractChildService.updateStallInfoByPids(ids);
        if (result > 0) {
            //删除子表信息
            leaseContractChildService.deleteLeaseContractChildPid(ids);
            //删除主表信息
            leaseContractService.deleteLeaseContractByIds(ids);
            return toAjaxBySuccess("删除成功!");
        } else {
            return toAjaxByError("删除失败!");
        }*/
    }
    /**
     * 联营合同生效
     */
    @PreAuthorize("@ss.hasPermi('system:pool:effect')")
    @Log(title = "联营合同", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            LeaseContractPool leaseContract=leaseContractService.selectLeaseContractById(ids[i]);
            List<LeaseContractChildPool> childList=leaseContractChildService.selectLeaseContractChildByCode(leaseContract.getContractCode());
            for(LeaseContractChildPool child:childList){
                //修改摊位信息
                StallInfo stallInfo=new StallInfo();
                stallInfo.setStallCode(child.getStallCode());//摊位编码
                stallInfo.setStallStatus("3");//已联营
                stallInfo.setStallStartTime(child.getLeaseTime());//联营日期
                stallInfo.setStallLeaseholder(leaseContract.getOwnerName());//联营方
                stallInfoService.updateStallInfoByCode(stallInfo);
            }
        }
        return toAjax(leaseContractService.updateLeaseContractStatus(ids));
    }
}
