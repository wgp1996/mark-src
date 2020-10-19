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
import com.ruoyi.project.system.domain.WholeRetail;
import com.ruoyi.project.system.domain.WholeRetailChild;
import com.ruoyi.project.system.service.IWholeRetailChildService;
import com.ruoyi.project.system.service.IWholeRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 零售销货单Controller
 *
 * @author ruoyi
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/system/wholeRetail")
public class WholeRetailController extends BaseController
{
    @Autowired
    private IWholeRetailService wholeRetailService;
    @Autowired
    private IWholeRetailChildService wholeRetailChildService;

    /**
     * 查询零售销货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetail:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(WholeRetail wholeRetail)
    {
        startPage();
      //  List<WholeRetailChild> list = wholeRetailChildService.selectWholeRetailAllList(wholeRetail);
        List<WholeRetail> list = wholeRetailService.selectWholeRetailList(wholeRetail);
        for(WholeRetail info:list){
            WholeRetailChild child=new WholeRetailChild();
            child.setDjNumber(info.getDjNumber());
            info.setChildrenList(wholeRetailChildService.selectWholeRetailChildList(child));
            child=null;//销毁
        }
        return getDataTable(list);
    }

    /**
     * 导出零售销货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetail:export')")
    @Log(title = "零售销货单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WholeRetail wholeRetail)
    {
        List<WholeRetail> list = wholeRetailService.selectWholeRetailList(wholeRetail);
        ExcelUtil<WholeRetail> util = new ExcelUtil<WholeRetail>(WholeRetail.class);
        return util.exportExcel(list, "wholeRetail");
    }

    /**
     * 获取零售销货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(wholeRetailService.selectWholeRetailById(id));
    }

    /**
     * 新增零售销货单
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetail:add')")
    @Log(title = "零售销货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WholeRetail wholeRetail)
    {
        if(wholeRetail.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        wholeRetail.setDjNumber(StringUtils.getRandomCode("LSH"));
        wholeRetail.setStatus(0);
        wholeRetail.setFrom(0);//web
        wholeRetail.setCreateBy(SecurityUtils.getUsername());
        wholeRetail.setId(StringUtils.getId());
        List<WholeRetailChild> childList= JSONArray.parseArray(wholeRetail.getRows(),WholeRetailChild.class);
        for(WholeRetailChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(wholeRetail.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            wholeRetailChildService.insertWholeRetailChild(child);
        }
        return toAjax(wholeRetailService.insertWholeRetail(wholeRetail));
    }

    /**
     * 修改零售销货单
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetail:edit')")
    @Log(title = "零售销货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WholeRetail wholeRetail)
    {
        //检查是否为已生效的单据
        if(wholeRetail.getStatus()==1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(wholeRetail.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        wholeRetail.setUpdateBy(SecurityUtils.getUsername());
        List<WholeRetailChild> childList= JSONArray.parseArray(wholeRetail.getRows(),WholeRetailChild.class);
        for(WholeRetailChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(wholeRetail.getDjNumber());
                wholeRetailChildService.updateWholeRetailChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(wholeRetail.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                wholeRetailChildService.insertWholeRetailChild(child);
            }
        }
        return toAjax(wholeRetailService.updateWholeRetail(wholeRetail));
    }

    /**
     * 删除零售销货单
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetail:remove')")
    @Log(title = "零售销货单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            WholeRetail info = wholeRetailService.selectWholeRetailById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        if(ids.length>0){
            wholeRetailChildService.deleteWholeRetailChildByPid(ids);
        }
        int result=wholeRetailService.deleteWholeRetailByIds(ids);
        if(result>0){

            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
    /**
     * 单据生效
     */
    @PreAuthorize("@ss.hasPermi('system:wholeRetail:effect')")
    @Log(title = "零售销货单", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        return toAjax(wholeRetailService.updateWholeRetailStatus(ids));
    }
}
