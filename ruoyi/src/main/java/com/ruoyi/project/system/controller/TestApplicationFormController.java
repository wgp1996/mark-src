package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.RandomInspectionInfo;
import com.ruoyi.project.system.domain.RandomInspectionInfoChild;
import com.ruoyi.project.system.domain.TestApplicationFormChild;
import com.ruoyi.project.system.service.ITestApplicationFormChildService;
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
import com.ruoyi.project.system.domain.TestApplicationForm;
import com.ruoyi.project.system.service.ITestApplicationFormService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 检测申请单Controller
 * 
 * @author ruoyi
 * @date 2020-09-30
 */
@RestController
@RequestMapping("/system/TestApplicationForm")
public class TestApplicationFormController extends BaseController
{
    @Autowired
    private ITestApplicationFormService testApplicationFormService;
    @Autowired
    private ITestApplicationFormChildService testApplicationFormChildService;

    /**
     * 查询检测申请单列表
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationForm:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(TestApplicationForm testApplicationForm)
    {
        startPage();
        List<TestApplicationForm> list = testApplicationFormService.selectTestApplicationFormList(testApplicationForm);
        for(TestApplicationForm info:list) {
            TestApplicationFormChild TestApplicationFormChild = new TestApplicationFormChild();
            TestApplicationFormChild.setDjNumber(info.getDjNumber());
            info.setChildrenList(testApplicationFormChildService.selectTestApplicationFormChildList(TestApplicationFormChild));
        }
        return getDataTable(list);
    }

    /**
     * 导出检测申请单列表
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationForm:export')")
    @Log(title = "检测申请单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TestApplicationForm testApplicationForm)
    {
        List<TestApplicationForm> list = testApplicationFormService.selectTestApplicationFormList(testApplicationForm);
        ExcelUtil<TestApplicationForm> util = new ExcelUtil<TestApplicationForm>(TestApplicationForm.class);
        return util.exportExcel(list, "TestApplicationForm");
    }

    /**
     * 获取检测申请单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationForm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(testApplicationFormService.selectTestApplicationFormById(id));
    }

    /**
     * 新增检测申请单
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationForm:add')")
    @Log(title = "检测申请单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestApplicationForm testApplicationForm)
    {
        return toAjax(testApplicationFormService.insertTestApplicationForm(testApplicationForm));
    }

    /**
     * 安排检测
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationForm:edit')")
    @Log(title = "检测申请单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestApplicationForm testApplicationForm)
    {
        if(testApplicationForm.getStatus()==1||testApplicationForm.getStatus()==-1){
            return  toAjaxByError("重复操作!");
        }
        if(testApplicationForm.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        List<TestApplicationFormChild> childList= JSONArray.parseArray(testApplicationForm.getRows(),TestApplicationFormChild.class);
        //添加检测结果
        for(TestApplicationFormChild child:childList){
            testApplicationFormChildService.updateTestApplicationFormChild(child);
        }
        //修改状态
        testApplicationForm.setStatus(1);
        return toAjax(testApplicationFormService.updateTestApplicationForm(testApplicationForm));
    }

    /**
     * 拒绝检测
     */
    @PreAuthorize("@ss.hasPermi('system:TestApplicationForm:remove')")
    @Log(title = "检测申请单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {

        //修改单据状态
        for(int i=0;i<ids.length;i++){
            TestApplicationForm info=testApplicationFormService.selectTestApplicationFormById(ids[i]);
            if(info.getStatus()==1||info.getStatus()==-1){
                return  toAjaxByError(info.getDjNumber()+"重复操作!");
            }
            info.setStatus(-1);
            testApplicationFormService.updateTestApplicationForm(info);
        }
        AjaxResult AjaxResult=new AjaxResult();
        AjaxResult.put("code",200);
        AjaxResult.put("msc","操作成功!");
        return  AjaxResult;
    }
}
