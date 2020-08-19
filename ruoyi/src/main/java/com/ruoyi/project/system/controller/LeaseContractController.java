package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.LeaseContractChild;
import com.ruoyi.project.system.domain.StallInfo;
import com.ruoyi.project.system.service.ILeaseContractChildService;
import com.ruoyi.project.system.service.IStallInfoService;
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
import com.ruoyi.project.system.domain.LeaseContract;
import com.ruoyi.project.system.service.ILeaseContractService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 租赁合同Controller
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@RestController
@RequestMapping("/system/contract")
public class LeaseContractController extends BaseController
{
    @Autowired
    private ILeaseContractChildService leaseContractChildService;
    @Autowired
    private ILeaseContractService leaseContractService;

    @Autowired
    private IStallInfoService stallInfoService;

    /**
     * 查询租赁合同列表
     */
    @PreAuthorize("@ss.hasPermi('system:contract:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(LeaseContract leaseContract)
    {
        startPage();
        List<LeaseContract> list = leaseContractService.selectLeaseContractList(leaseContract);
        for(LeaseContract info:list){
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
     * 导出租赁合同列表
     */
    @PreAuthorize("@ss.hasPermi('system:contract:export')")
    @Log(title = "租赁合同", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaseContract leaseContract)
    {
        List<LeaseContract> list = leaseContractService.selectLeaseContractList(leaseContract);
        ExcelUtil<LeaseContract> util = new ExcelUtil<LeaseContract>(LeaseContract.class);
        return util.exportExcel(list, "contract");
    }

    /**
     * 获取租赁合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:contract:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(leaseContractService.selectLeaseContractById(id));
    }

    /**
     * 新增租赁合同
     */
    @PreAuthorize("@ss.hasPermi('system:contract:add')")
    @Log(title = "租赁合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaseContract leaseContract)
    {
        //查询合同编号是否重复
        LeaseContract info = leaseContractService.selectLeaseContractByCode(leaseContract.getContractCode(), "");
        if (info != null) {
            return toAjaxByError("合同编号重复!");
        }
        if(leaseContract.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        List<LeaseContractChild> childList= JSONArray.parseArray(leaseContract.getRows(),LeaseContractChild.class);
        for(LeaseContractChild child:childList){
            //修改摊位信息
            StallInfo stallInfo=new StallInfo();
            stallInfo.setStallCode(child.getStallCode());//摊位编码
            stallInfo.setStallStatus("1");//已出租
            stallInfo.setStallStartTime(child.getLeaseStartTime());//开始日期
            stallInfo.setStallEndTime(child.getLeaseEndTime());//结束日期
            stallInfo.setStallMoney(child.getRentMoney());//租金
            stallInfo.setStallLeaseholder(leaseContract.getOwnerName());//租赁方
            stallInfoService.updateStallInfoByCode(stallInfo);

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
     * 修改租赁合同
     * let nums=data.houseName;
     * for()
     */
    @PreAuthorize("@ss.hasPermi('system:contract:edit')")
    @Log(title = "租赁合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaseContract leaseContract)
    {
        //查询合同编号是否重复
        LeaseContract info = leaseContractService.selectLeaseContractByCode(leaseContract.getContractCode(), leaseContract.getId());
        if (info != null) {
            return toAjaxByError("合同编号重复!");
        }
        //检查是否为已生效的合同
        if("已生效".equals(leaseContract.getContractStatus())){
            return  toAjaxByError("该合同状态禁止修改!");
        }
        if(leaseContract.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        List<LeaseContractChild> childList= JSONArray.parseArray(leaseContract.getRows(),LeaseContractChild.class);
        for(LeaseContractChild child:childList){
            //修改摊位信息
            StallInfo stallInfo=new StallInfo();
            stallInfo.setStallCode(child.getStallCode());//摊位编码
            stallInfo.setStallStatus("1");//已出租
            stallInfo.setStallStartTime(child.getLeaseStartTime());//开始日期
            stallInfo.setStallEndTime(child.getLeaseEndTime());//结束日期
            stallInfo.setStallMoney(child.getRentMoney());//租金
            stallInfo.setStallLeaseholder(leaseContract.getOwnerName());//租赁方
            stallInfoService.updateStallInfoByCode(stallInfo);

            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setContractCode(leaseContract.getContractCode());
                child.setCreateTime(DateUtils.getNowDate());
                leaseContractChildService.updateLeaseContractChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setContractCode(leaseContract.getContractCode());
                child.setCreateTime(DateUtils.getNowDate());
                leaseContractChildService.insertLeaseContractChild(child);
            }
        }
        leaseContract.setContractStatus("正操作");
        leaseContract.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(leaseContractService.updateLeaseContract(leaseContract));
    }

    /**
     * 租赁合同生效
     */
    @PreAuthorize("@ss.hasPermi('system:contract:effect')")
    @Log(title = "租赁合同", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        return toAjax(leaseContractService.updateLeaseContractStatus(ids));
    }

    /**
     * 删除租赁合同
     */
    @PreAuthorize("@ss.hasPermi('system:contract:remove')")
    @Log(title = "租赁合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            LeaseContract info = leaseContractService.selectLeaseContractById(ids[i]);
            if(!"正操作".equals(info.getContractStatus())){
                return toAjaxByError(info.getContractName()+"：该合同状态禁止删除!");
            }
        }
        //批量修改摊位信息
        int result=leaseContractChildService.updateStallInfoByPids(ids);
        if(result>0){
            //删除子表信息
            leaseContractChildService.deleteLeaseContractChildPid(ids);
            //删除主表信息
            leaseContractService.deleteLeaseContractByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
}
