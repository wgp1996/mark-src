package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.IRandomInspectionInfoChildService;
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
import com.ruoyi.project.system.service.IRandomInspectionInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 随机检测单Controller
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@RestController
@RequestMapping("/system/randomInsp")
public class RandomInspectionInfoController extends BaseController
{
    @Autowired
    private IRandomInspectionInfoService randomInspectionInfoService;
    @Autowired
    private IRandomInspectionInfoChildService randomInspectionInfoChildService;

    /**
     * 查询随机检测单列表
     */
    @PreAuthorize("@ss.hasPermi('system:randomInsp:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(RandomInspectionInfo randomInspectionInfo)
    {
        startPage();
        List<RandomInspectionInfo> list = randomInspectionInfoService.selectRandomInspectionInfoList(randomInspectionInfo);
        for(RandomInspectionInfo info:list) {
            RandomInspectionInfoChild RandomInspectionInfoChild = new RandomInspectionInfoChild();
            RandomInspectionInfoChild.setDjNumber(info.getDjNumber());
            info.setChildrenList(randomInspectionInfoChildService.selectRandomInspectionInfoChildList(RandomInspectionInfoChild));
        }
        return getDataTable(list);
    }

    /**
     * 导出随机检测单列表
     */
    @PreAuthorize("@ss.hasPermi('system:randomInsp:export')")
    @Log(title = "随机检测单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RandomInspectionInfo randomInspectionInfo)
    {
        List<RandomInspectionInfo> list = randomInspectionInfoService.selectRandomInspectionInfoList(randomInspectionInfo);
        ExcelUtil<RandomInspectionInfo> util = new ExcelUtil<RandomInspectionInfo>(RandomInspectionInfo.class);
        return util.exportExcel(list, "randomInsp");
    }

    /**
     * 获取随机检测单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:randomInsp:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(randomInspectionInfoService.selectRandomInspectionInfoById(id));
    }

    /**
     * 新增随机检测单
     */
    @PreAuthorize("@ss.hasPermi('system:randomInsp:add')")
    @Log(title = "随机检测单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RandomInspectionInfo randomInspectionInfo)
    {
        if(randomInspectionInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        randomInspectionInfo.setDjNumber(StringUtils.getRandomCode("PO"));
        randomInspectionInfo.setDjStatus(Long.valueOf(0));
        randomInspectionInfo.setCreateBy(SecurityUtils.getUsername());
        randomInspectionInfo.setId(StringUtils.getId());
        List<RandomInspectionInfoChild> childList= JSONArray.parseArray(randomInspectionInfo.getRows(),RandomInspectionInfoChild.class);
        for(RandomInspectionInfoChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(randomInspectionInfo.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            randomInspectionInfoChildService.insertRandomInspectionInfoChild(child);
        }
        return toAjax(randomInspectionInfoService.insertRandomInspectionInfo(randomInspectionInfo));
    }

    /**
     * 修改随机检测单
     */
    @PreAuthorize("@ss.hasPermi('system:randomInsp:edit')")
    @Log(title = "随机检测单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RandomInspectionInfo randomInspectionInfo)
    {
        //检查是否为已生效的单子
        if(randomInspectionInfo.getDjStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(randomInspectionInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        randomInspectionInfo.setUpdateBy(SecurityUtils.getUsername());
        List<RandomInspectionInfoChild> childList= JSONArray.parseArray(randomInspectionInfo.getRows(),RandomInspectionInfoChild.class);
        for(RandomInspectionInfoChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(randomInspectionInfo.getDjNumber());
                randomInspectionInfoChildService.updateRandomInspectionInfoChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(randomInspectionInfo.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                randomInspectionInfoChildService.insertRandomInspectionInfoChild(child);
            }
        }
        return toAjax(randomInspectionInfoService.updateRandomInspectionInfo(randomInspectionInfo));
    }

    /**
     * 删除随机检测单
     */
    @PreAuthorize("@ss.hasPermi('system:randomInsp:remove')")
    @Log(title = "随机检测单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            RandomInspectionInfo info = randomInspectionInfoService.selectRandomInspectionInfoById(ids[i]);
            if(info.getDjStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        return toAjax(randomInspectionInfoService.deleteRandomInspectionInfoByIds(ids));
    }
}
