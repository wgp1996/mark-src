package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
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
import com.ruoyi.project.system.domain.LeaseCollection;
import com.ruoyi.project.system.service.ILeaseCollectionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 租赁收款Controller
 *
 * @author ruoyi
 * @date 2020-08-05
 */
@RestController
@RequestMapping("/system/leaseCollection")
public class LeaseCollectionController extends BaseController {
    @Autowired
    private ILeaseCollectionService leaseCollectionService;

    /**
     * 查询租赁收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:leaseCollection:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(LeaseCollection leaseCollection) {
        startPage();
        List<LeaseCollection> list = leaseCollectionService.selectLeaseCollectionList(leaseCollection);
        return getDataTable(list);
    }

    /**
     * 导出租赁收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:leaseCollection:export')")
    @Log(title = "租赁收款导出", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaseCollection leaseCollection) {
        List<LeaseCollection> list = leaseCollectionService.selectLeaseCollectionList(leaseCollection);
        ExcelUtil<LeaseCollection> util = new ExcelUtil<LeaseCollection>(LeaseCollection.class);
        return util.exportExcel(list, "leaseCollection");
    }

    /**
     * 获取租赁收款详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:leaseCollection:query')")
    @GetMapping(value = "/{collectionCode}")
    public AjaxResult getInfo(@PathVariable("collectionCode") String collectionCode) {
        return AjaxResult.success(leaseCollectionService.selectLeaseCollectionById(collectionCode));
    }

    /**
     * 新增租赁收款
     */
    @PreAuthorize("@ss.hasPermi('system:leaseCollection:add')")
    @Log(title = "租赁收款新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaseCollection leaseCollection) {
        leaseCollection.setCreateBy(SecurityUtils.getUsername());
        leaseCollection.setId(StringUtils.getId());
        leaseCollection.setCollectionCode(StringUtils.getRandomCode("SKD-Zl-"));
        leaseCollection.setCreateTime(DateUtils.getNowDate());
        return toAjax(leaseCollectionService.insertLeaseCollection(leaseCollection));
    }

    /**
     * 修改租赁收款
     */
    @PreAuthorize("@ss.hasPermi('system:leaseCollection:edit')")
    @Log(title = "租赁收款修改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaseCollection leaseCollection) {
        leaseCollection.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(leaseCollectionService.updateLeaseCollection(leaseCollection));
    }

    /**
     * 删除租赁收款
     */
    @PreAuthorize("@ss.hasPermi('system:leaseCollection:remove')")
    @Log(title = "租赁收款删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collectionCodes}")
    public AjaxResult remove(@PathVariable String[] collectionCodes) {
        return toAjax(leaseCollectionService.deleteLeaseCollectionByIds(collectionCodes));
    }
}
