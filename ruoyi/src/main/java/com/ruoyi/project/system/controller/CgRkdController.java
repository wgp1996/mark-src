package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.ICgRkdChildService;
import io.swagger.annotations.Api;
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
import com.ruoyi.project.system.service.ICgRkdService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 进货单Controller
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@RestController
@RequestMapping("/system/cgrkd")
public class CgRkdController extends BaseController
{
    @Autowired
    private ICgRkdService cgRkdService;
    @Autowired
    private ICgRkdChildService cgRkdChildService;
    /**
     * 查询进货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(CgRkd cgRkd)
    {
        startPage();
        List<CgRkd> list = cgRkdService.selectCgRkdList(cgRkd);
        for(CgRkd info:list){
            info.setChildrenList(cgRkdChildService.selectCgRkdChildByNumber(info.getDjNumber()));
        }
        return getDataTable(list);
    }



    /**
     * 导出进货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:export')")
    @Log(title = "进货单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CgRkd cgRkd)
    {
        List<CgRkd> list = cgRkdService.selectCgRkdList(cgRkd);
        ExcelUtil<CgRkd> util = new ExcelUtil<CgRkd>(CgRkd.class);
        return util.exportExcel(list, "cgrkd");
    }

    /**
     * 获取进货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cgRkdService.selectCgRkdById(id));
    }

    /**
     * 新增进货单
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:add')")
    @Log(title = "进货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CgRkd cgRkd)
    {
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setDjNumber(StringUtils.getRandomCode("RKD"));
        cgRkd.setStatus(0);
        cgRkd.setCreateBy(SecurityUtils.getUsername());
        cgRkd.setId(StringUtils.getId());
        List<CgRkdChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdChild.class);
        for(CgRkdChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(cgRkd.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            cgRkdChildService.insertCgRkdChild(child);
        }
        return toAjax(cgRkdService.insertCgRkd(cgRkd));
    }

    /**
     * 修改进货单
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:edit')")
    @Log(title = "进货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CgRkd cgRkd)
    {
        //检查是否为已生效的合同
        if(cgRkd.getStatus()==1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setUpdateBy(SecurityUtils.getUsername());
        List<CgRkdChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdChild.class);
        for(CgRkdChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(cgRkd.getDjNumber());
                cgRkdChildService.updateCgRkdChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(cgRkd.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                cgRkdChildService.insertCgRkdChild(child);
            }
        }
        return toAjax(cgRkdService.updateCgRkd(cgRkd));
    }

    /**
     * 删除进货单
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:remove')")
    @Log(title = "进货单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            CgRkd info = cgRkdService.selectCgRkdById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=cgRkdChildService.deleteCgRkdChildByPid(ids);
        if(result>0){
            cgRkdService.deleteCgRkdByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
    /**
     * 租赁合同生效
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:effect')")
    @Log(title = "进货单生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        return toAjax(cgRkdService.updateCgRkdStatus(ids));
    }





}
