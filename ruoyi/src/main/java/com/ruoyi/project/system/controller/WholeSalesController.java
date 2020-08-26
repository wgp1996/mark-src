package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.domain.CgRkdChild;
import com.ruoyi.project.system.domain.WholeSalesChild;
import com.ruoyi.project.system.service.ICgRkdChildService;
import com.ruoyi.project.system.service.IWholeSalesChildService;
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
import com.ruoyi.project.system.domain.WholeSales;
import com.ruoyi.project.system.service.IWholeSalesService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 批发销货单Controller
 *
 * @author ruoyi
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/system/wholeSales")
public class WholeSalesController extends BaseController
{
    @Autowired
    private IWholeSalesService wholeSalesService;
    @Autowired
    private IWholeSalesChildService wholeSalesChildService;

    /**
     * 查询批发销货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSales:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(WholeSales wholeSales)
    {
        startPage();
        List<WholeSales> list = wholeSalesService.selectWholeSalesList(wholeSales);
        for(WholeSales info:list){
            WholeSalesChild child=new WholeSalesChild();
            child.setDjNumber(info.getDjNumber());
            info.setChildrenList(wholeSalesChildService.selectWholeSalesChildList(child));
            child=null;//销毁
        }
        return getDataTable(list);
    }

    /**
     * 导出批发销货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSales:export')")
    @Log(title = "批发销货单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WholeSales wholeSales)
    {
        List<WholeSales> list = wholeSalesService.selectWholeSalesList(wholeSales);
        ExcelUtil<WholeSales> util = new ExcelUtil<WholeSales>(WholeSales.class);
        return util.exportExcel(list, "wholeSales");
    }

    /**
     * 获取批发销货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSales:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(wholeSalesService.selectWholeSalesById(id));
    }

    /**
     * 新增批发销货单
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSales:add')")
    @Log(title = "批发销货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WholeSales wholeSales)
    {
        if(wholeSales.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        wholeSales.setDjNumber(StringUtils.getRandomCode("LXH"));
        wholeSales.setStatus(0);
        wholeSales.setFrom(0);//web
        wholeSales.setCreateBy(SecurityUtils.getUsername());
        wholeSales.setId(StringUtils.getId());
        List<WholeSalesChild> childList= JSONArray.parseArray(wholeSales.getRows(),WholeSalesChild.class);
        for(WholeSalesChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(wholeSales.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            wholeSalesChildService.insertWholeSalesChild(child);
        }
        return toAjax(wholeSalesService.insertWholeSales(wholeSales));
    }

    /**
     * 修改批发销货单
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSales:edit')")
    @Log(title = "批发销货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WholeSales wholeSales)
    {
        //检查是否为已生效的单据
        if(wholeSales.getStatus()==1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(wholeSales.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        wholeSales.setUpdateBy(SecurityUtils.getUsername());
        List<WholeSalesChild> childList= JSONArray.parseArray(wholeSales.getRows(),WholeSalesChild.class);
        for(WholeSalesChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(wholeSales.getDjNumber());
                wholeSalesChildService.updateWholeSalesChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(wholeSales.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                wholeSalesChildService.insertWholeSalesChild(child);
            }
        }
        return toAjax(wholeSalesService.updateWholeSales(wholeSales));
    }

    /**
     * 删除批发销货单
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSales:remove')")
    @Log(title = "批发销货单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            WholeSales info = wholeSalesService.selectWholeSalesById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=wholeSalesChildService.deleteWholeSalesChildByPid(ids);
        if(result>0){
            wholeSalesService.deleteWholeSalesByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
    /**
     * 单据生效
     */
    @PreAuthorize("@ss.hasPermi('system:wholeSales:effect')")
    @Log(title = "批发销货单", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        return toAjax(wholeSalesService.updateWholeSalesStatus(ids));
    }
}
