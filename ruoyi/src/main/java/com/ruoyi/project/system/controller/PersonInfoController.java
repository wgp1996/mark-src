package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.GoodsInfo;
import com.ruoyi.project.system.domain.StallInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.project.system.domain.PersonInfo;
import com.ruoyi.project.system.service.IPersonInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 供应商建档Controller
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
@Api("供应商档案管理")
@RestController
@RequestMapping("/system/person")
public class PersonInfoController extends BaseController
{
    @Autowired
    private IPersonInfoService personInfoService;

    /**
     * 查询供应商建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:person:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(PersonInfo personInfo)
    {
        startPage();
        List<PersonInfo> list = personInfoService.selectPersonInfoList(personInfo);
        return getDataTable(list);
    }

    /**
     * APP查询供应商建档列表
     */
    @ApiOperation("APP供应商档案")
    @GetMapping("/appPersonList")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo appPersonList(PersonInfo personInfo)
    {
        List<PersonInfo> list = personInfoService.selectPersonInfoList(personInfo);
        return getDataTable(list);
    }

    /**
     * 供应商下拉列表
     */
    @GetMapping("/getPersonAll")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo getStallAll(PersonInfo personInfo) {
        List<PersonInfo> list = personInfoService.selectPersonInfoList(personInfo);
        return getDataTable(list);
    }
    /**
     * 导出供应商建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:person:export')")
    @Log(title = "供应商建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PersonInfo personInfo)
    {
        List<PersonInfo> list = personInfoService.selectPersonInfoList(personInfo);
        ExcelUtil<PersonInfo> util = new ExcelUtil<PersonInfo>(PersonInfo.class);
        return util.exportExcel(list, "person");
    }

    /**
     * 获取供应商建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:person:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(personInfoService.selectPersonInfoById(id));
    }

    /**
     * 新增供应商建档
     */
    @PreAuthorize("@ss.hasPermi('system:person:add')")
    @Log(title = "供应商建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonInfo personInfo)
    {
        PersonInfo info=personInfoService.selectPersonInfoByName(personInfo.getPersonName(),-1);
        if(info!=null) {
            return  toAjaxByError("该供应商在系统中已存在");
        }else {
            personInfo.setCreateBy(SecurityUtils.getUsername());
            personInfo.setPersonCode(StringUtils.getRandomCode("PCM"));
            return toAjax(personInfoService.insertPersonInfo(personInfo));
        }
    }

    /**
     * 修改供应商建档
     */
    @PreAuthorize("@ss.hasPermi('system:person:edit')")
    @Log(title = "供应商建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonInfo personInfo)
    {
        PersonInfo info=personInfoService.selectPersonInfoByName(personInfo.getPersonName(),personInfo.getId());
        if(info!=null) {
            return  toAjaxByError("该供应商在系统中已存在");
        }else {
            personInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(personInfoService.updatePersonInfo(personInfo));
        }
    }

    /**
     * 删除供应商建档
     */
    @PreAuthorize("@ss.hasPermi('system:person:remove')")
    @Log(title = "供应商建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(personInfoService.deletePersonInfoByIds(ids));
    }
}
