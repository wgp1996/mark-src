package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.LeaseContract;
import com.ruoyi.project.system.domain.StallInfo;
import com.ruoyi.project.system.service.ILeaseContractPoolService;
import com.ruoyi.project.system.service.ILeaseContractSalesService;
import com.ruoyi.project.system.service.ILeaseContractService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.ruoyi.project.system.domain.OwnerInfo;
import com.ruoyi.project.system.service.IOwnerInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 业户信息Controller
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/system/owner")
public class OwnerInfoController extends BaseController {
    @Autowired
    private IOwnerInfoService ownerInfoService;
    @Autowired
    private ILeaseContractService leaseContractService;
    @Autowired
    private ILeaseContractSalesService leaseContractSalesService;
    @Autowired
    private ILeaseContractPoolService leaseContractPoolService;
    /**
     * 获取业户信息
     */
    @GetMapping(value = "/ownerList")
    public AjaxResult ownerList(OwnerInfo ownerInfo)
    {
        return AjaxResult.success(ownerInfoService.selectOwnerInfoList(ownerInfo));
    }
    /**
     * 查询业户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:owner:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(OwnerInfo ownerInfo) {
        startPage();
        List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(ownerInfo);
        return getDataTable(list);
    }

    /**
     * 查询业户信息列表
     */
    @GetMapping("/ownerAllList")
    public TableDataInfo ownerAllList(OwnerInfo ownerInfo) {
        startPage();
        List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(ownerInfo);
        return getDataTable(list);
    }

    @GetMapping("/getOwnerList/{ownerName}/{ownerCode}")
    public TableDataInfo getOwnerList(@PathVariable("ownerName") String ownerName,@PathVariable("ownerCode") String ownerCode)
    {
        OwnerInfo info=new OwnerInfo();
        if(!"-1".equals(ownerName)){
            info.setOwnerName(ownerName);
        }
        if(!"-1".equals(ownerCode)){
            info.setOwnerCode(ownerCode);
        }
        List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(info);
        info=null;
        return getDataTable(list);
    }

    /**
     * 导出业户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:owner:export')")
    @Log(title = "业户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OwnerInfo ownerInfo) {
        List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(ownerInfo);
        ExcelUtil<OwnerInfo> util = new ExcelUtil<OwnerInfo>(OwnerInfo.class);
        return util.exportExcel(list, "owner");
    }

    /**
     * 获取业户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:owner:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ownerInfoService.selectOwnerInfoById(id));
    }

    /**
     * 新增业户信息
     */
    @PreAuthorize("@ss.hasPermi('system:owner:add')")
    @Log(title = "业户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OwnerInfo ownerInfo) {
        if(ownerInfo.getUserName()!=null&&ownerInfo.getUserName()!=""){
            //查询分配的帐号是否存在
            OwnerInfo info=new OwnerInfo();
            info.setUserName(ownerInfo.getUserName());
            List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(info);
            if(list!=null&&list.size()>0){
                return  toAjaxByError("该账户已被关联!");
            }
        }
        OwnerInfo info = ownerInfoService.selectOwnerInfoByCode(ownerInfo.getOwnerCode(), "");
        if (info != null) {
            return toAjaxByError("该业户在系统中已存在");
        } else {
            ownerInfo.setCreateBy(SecurityUtils.getUsername());
            ownerInfo.setId(StringUtils.getId());
            //ownerInfo.setOwnerCode(StringUtils.getRandomCode("OR"));
            ownerInfo.setCreateTime(DateUtils.getNowDate());
            return toAjax(ownerInfoService.insertOwnerInfo(ownerInfo));
        }
    }

    /**
     * 修改业户信息
     */
    @PreAuthorize("@ss.hasPermi('system:owner:edit')")
    @Log(title = "业户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OwnerInfo ownerInfo) {
        if(ownerInfo.getUserName()!=null&&ownerInfo.getUserName()!=""){
            //查询分配的帐号是否存在
            OwnerInfo info=new OwnerInfo();
            info.setUserName(ownerInfo.getUserName());
            info.setId(ownerInfo.getId());
            List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(info);
            if(list!=null&&list.size()>0){
                return  toAjaxByError("该账户已被关联!");
            }
        }
        OwnerInfo info = ownerInfoService.selectOwnerInfoByCode(ownerInfo.getOwnerCode(), ownerInfo.getId());
        if (info != null) {
            return toAjaxByError("该业户在系统中已存在");
        } else {
            ownerInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(ownerInfoService.updateOwnerInfo(ownerInfo));
        }
    }

    /**
     * 删除业户信息
     */
    @PreAuthorize("@ss.hasPermi('system:owner:remove')")
    @Log(title = "业户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        //查询是该业户下是否存在已生效合同
//        LeaseContract LeaseContract=new LeaseContract();
//        LeaseContract.setContractStatus("已生效");
//        List<LeaseContract> LeaseContractList=leaseContractService.selectLeaseContractList(LeaseContract);
//        if(LeaseContractList!=null&&LeaseContractList.size()>0){
//            return toAjaxByError("该业户在系统中存在销售合同");
//        }

        return toAjax(ownerInfoService.deleteOwnerInfoByIds(ids));
    }

    public static void main(String[] args) {

    }
}
