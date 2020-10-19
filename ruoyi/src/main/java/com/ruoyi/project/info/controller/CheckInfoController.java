package com.ruoyi.project.info.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.info.domain.*;
import com.ruoyi.project.info.domain.CheckInfoChild;
import com.ruoyi.project.info.service.ICheckInfoChildService;
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
import com.ruoyi.project.info.domain.CheckInfo;
import com.ruoyi.project.info.service.ICheckInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 盘点单Controller
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
@RestController
@RequestMapping("/system/checkInfo")
public class CheckInfoController extends BaseController
{
    @Autowired
    private ICheckInfoService checkInfoService;
    @Autowired
    private ICheckInfoChildService checkInfoChildService;

    /**
     * 查询盘点单列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfo:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(CheckInfo checkInfo)
    {
        startPage();
        List<CheckInfo> list = checkInfoService.selectCheckInfoList(checkInfo);
        for(CheckInfo info:list) {
            CheckInfoChild checkInfoChild = new CheckInfoChild();
            checkInfoChild.setDjNumber(info.getDjNumber());
            info.setChildrenList(checkInfoChildService.selectCheckInfoChildList(checkInfoChild));
            checkInfoChild=null;
        }
        return getDataTable(list);
    }

    /**
     * 导出盘点单列表
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfo:export')")
    @Log(title = "盘点单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CheckInfo checkInfo)
    {
        List<CheckInfo> list = checkInfoService.selectCheckInfoList(checkInfo);
        ExcelUtil<CheckInfo> util = new ExcelUtil<CheckInfo>(CheckInfo.class);
        return util.exportExcel(list, "checkInfo");
    }

    /**
     * 获取盘点单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(checkInfoService.selectCheckInfoById(id));
    }

    /**
     * 新增盘点单
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfo:add')")
    @Log(title = "盘点单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckInfo checkInfo)
    {
        if(checkInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        checkInfo.setDjNumber(StringUtils.getRandomCode("PD"));
        checkInfo.setDjStatus(0);
        checkInfo.setCreateBy(SecurityUtils.getUsername());
        checkInfo.setId(StringUtils.getId());
        List<CheckInfoChild> childList= JSONArray.parseArray(checkInfo.getRows(), CheckInfoChild.class);
        for(CheckInfoChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(checkInfo.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            checkInfoChildService.insertCheckInfoChild(child);
        }
        return toAjax(checkInfoService.insertCheckInfo(checkInfo));
    }

    /**
     * 修改盘点单
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfo:edit')")
    @Log(title = "盘点单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckInfo checkInfo)
    {
        //检查是否为已生效
        if(checkInfo.getDjStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(checkInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        checkInfo.setUpdateBy(SecurityUtils.getUsername());
        List<CheckInfoChild> childList= JSONArray.parseArray(checkInfo.getRows(),CheckInfoChild.class);
        for(CheckInfoChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setUpdateBy(SecurityUtils.getUsername());
                child.setDjNumber(checkInfo.getDjNumber());
                checkInfoChildService.updateCheckInfoChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(checkInfo.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                checkInfoChildService.insertCheckInfoChild(child);
            }
        }
        return toAjax(checkInfoService.updateCheckInfo(checkInfo));
    }

    /**
     * 删除盘点单
     */
    @PreAuthorize("@ss.hasPermi('system:checkInfo:remove')")
    @Log(title = "盘点单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            CheckInfo info = checkInfoService.selectCheckInfoById(ids[i]);
            if(info.getDjStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=checkInfoChildService.deleteCheckInfoChildByPid(ids);
        if(result>0){
            checkInfoService.deleteCheckInfoByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
}
