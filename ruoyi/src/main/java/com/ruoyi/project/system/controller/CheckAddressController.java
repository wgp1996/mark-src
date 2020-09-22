package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
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
import com.ruoyi.project.system.domain.CheckAddress;
import com.ruoyi.project.system.service.ICheckAddressService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 检测地点建档Controller
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@RestController
@RequestMapping("/system/checkAddress")
public class CheckAddressController extends BaseController
{
    @Autowired
    private ICheckAddressService checkAddressService;

    /**
     * 查询检测地点建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkAddress:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(CheckAddress checkAddress)
    {
        startPage();
        List<CheckAddress> list = checkAddressService.selectCheckAddressList(checkAddress);
        return getDataTable(list);
    }
    @GetMapping("/getAllCheckAddress")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo getAllCheckAddress(CheckAddress checkAddress)
    {
        startPage();
        List<CheckAddress> list = checkAddressService.selectCheckAddressList(checkAddress);
        return getDataTable(list);
    }

    /**
     * 导出检测地点建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkAddress:export')")
    @Log(title = "检测地点建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CheckAddress checkAddress)
    {
        List<CheckAddress> list = checkAddressService.selectCheckAddressList(checkAddress);
        ExcelUtil<CheckAddress> util = new ExcelUtil<CheckAddress>(CheckAddress.class);
        return util.exportExcel(list, "checkAddress");
    }

    /**
     * 获取检测地点建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:checkAddress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(checkAddressService.selectCheckAddressById(id));
    }

    /**
     * 新增检测地点建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkAddress:add')")
    @Log(title = "检测地点建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckAddress checkAddress)
    {
        checkAddress.setId(StringUtils.getId());
        checkAddress.setCreateBy(SecurityUtils.getUsername());
        return toAjax(checkAddressService.insertCheckAddress(checkAddress));
    }

    /**
     * 修改检测地点建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkAddress:edit')")
    @Log(title = "检测地点建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckAddress checkAddress)
    {
        checkAddress.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(checkAddressService.updateCheckAddress(checkAddress));
    }

    /**
     * 删除检测地点建档
     */
    @PreAuthorize("@ss.hasPermi('system:checkAddress:remove')")
    @Log(title = "检测地点建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        //检测是否可删除
        for(String id:ids){
          int result=checkAddressService.checkAddress(id);
          if(result>0){
              return  AjaxResult.error("ID:"+id+"的地点已被使用!");
          }
        }
        return toAjax(checkAddressService.deleteCheckAddressByIds(ids));
    }
}
