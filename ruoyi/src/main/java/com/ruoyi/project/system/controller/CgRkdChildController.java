package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.project.system.domain.CgRkdSingle;
import com.ruoyi.project.system.service.ICgRkdSingleChildService;
import com.ruoyi.project.system.service.ICgRkdSingleService;
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
import com.ruoyi.project.system.domain.CgRkdChild;
import com.ruoyi.project.system.service.ICgRkdChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 进货单子表Controller
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@RestController
@RequestMapping("/system/cgrkdchild")
public class CgRkdChildController extends BaseController
{
    @Autowired
    private ICgRkdChildService cgRkdChildService;
    @Autowired
    private ICgRkdSingleService cgRkdSingleService;
    @Autowired
    private ICgRkdSingleChildService cgRkdSingleChildService;

    /**
     * 查询进货单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdchild:list')")
    @GetMapping("/list")
    public TableDataInfo list(CgRkdChild cgRkdChild)
    {
        startPage();
        List<CgRkdChild> list = cgRkdChildService.selectCgRkdChildList(cgRkdChild);
        return getDataTable(list);
    }
    /**
     * APP业户接收订单列表明细信息
     */
    @GetMapping("/mxList/{id}/{type}")
    public TableDataInfo appRkdChildListByStatus(@PathVariable String id,@PathVariable Integer type)
    {
        //单供应商
        if(type==0){
            CgRkdSingle CgRkd=cgRkdSingleService.selectCgRkdSingleById(id);
            return getDataTable(cgRkdSingleChildService.selectCgRkdSingleChildByNumber(CgRkd.getDjNumber()));
        }else{
            CgRkdChild cgRkdChild= cgRkdChildService.selectCgRkdChildById(id);
            List<CgRkdChild> CgRkdChild=new ArrayList<>();
            CgRkdChild.add(cgRkdChild);
            return getDataTable(CgRkdChild);
        }
    }
    @GetMapping("/getCgrkdChild")
    public TableDataInfo getCgrkdChild(CgRkdChild cgRkdChild)
    {
        List<CgRkdChild> list = cgRkdChildService.selectCgRkdChildList(cgRkdChild);
        return getDataTable(list);
    }

    /**
     * 导出进货单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdchild:export')")
    @Log(title = "进货单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CgRkdChild cgRkdChild)
    {
        List<CgRkdChild> list = cgRkdChildService.selectCgRkdChildList(cgRkdChild);
        ExcelUtil<CgRkdChild> util = new ExcelUtil<CgRkdChild>(CgRkdChild.class);
        return util.exportExcel(list, "cgrkdchild");
    }

    /**
     * 获取进货单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdchild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cgRkdChildService.selectCgRkdChildById(id));
    }

    /**
     * 新增进货单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdchild:add')")
    @Log(title = "进货单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CgRkdChild cgRkdChild)
    {
        return toAjax(cgRkdChildService.insertCgRkdChild(cgRkdChild));
    }

    /**
     * 修改进货单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdchild:edit')")
    @Log(title = "进货单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CgRkdChild cgRkdChild)
    {
        return toAjax(cgRkdChildService.updateCgRkdChild(cgRkdChild));
    }

    /**
     * 删除进货单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdchild:remove')")
    @Log(title = "进货单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cgRkdChildService.deleteCgRkdChildByIds(ids));
    }
}
