package com.ruoyi.project.info.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.info.domain.*;
import com.ruoyi.project.info.service.IKcInfoService;
import com.ruoyi.project.info.service.ILkckInfoChildService;
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
import com.ruoyi.project.info.service.ILkckInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
@RestController
@RequestMapping("/system/lkckInfo")
public class LkckInfoController extends BaseController
{
    @Autowired
    private ILkckInfoService lkckInfoService;
    @Autowired
    private ILkckInfoChildService lkckInfoChildService;
    @Autowired
    private IKcInfoService kcInfoService;


    /**
     * 查询出库单列表
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfo:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(LkckInfo lkckInfo)
    {
        startPage();
        List<LkckInfo> list = lkckInfoService.selectLkckInfoList(lkckInfo);
        for(LkckInfo info:list) {
            LkckInfoChild ckInfoChild = new LkckInfoChild();
            ckInfoChild.setDjNumber(info.getDjNumber());
            info.setChildrenList(lkckInfoChildService.selectLkckInfoChildList(ckInfoChild));
            ckInfoChild=null;
        }
        return getDataTable(list);
    }

    /**
     * 导出出库单列表
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfo:export')")
    @Log(title = "出库单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LkckInfo lkckInfo)
    {
        List<LkckInfo> list = lkckInfoService.selectLkckInfoList(lkckInfo);
        ExcelUtil<LkckInfo> util = new ExcelUtil<LkckInfo>(LkckInfo.class);
        return util.exportExcel(list, "lkckInfo");
    }

    /**
     * 获取出库单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(lkckInfoService.selectLkckInfoById(id));
    }

    /**
     * 新增出库单
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfo:add')")
    @Log(title = "出库单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LkckInfo lkckInfo)
    {
        if(lkckInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        lkckInfo.setDjNumber(StringUtils.getRandomCode("CK"));
        lkckInfo.setDjStatus(0);
        lkckInfo.setCreateBy(SecurityUtils.getUsername());
        lkckInfo.setId(StringUtils.getId());
        List<LkckInfoChild> childList= JSONArray.parseArray(lkckInfo.getRows(), LkckInfoChild.class);
        for(LkckInfoChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(lkckInfo.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            lkckInfoChildService.insertLkckInfoChild(child);
        }
        return toAjax(lkckInfoService.insertLkckInfo(lkckInfo));
    }

    /**
     * 修改出库单
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfo:edit')")
    @Log(title = "出库单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LkckInfo lkckInfo)
    {
        //检查是否为已生效
        if(lkckInfo.getDjStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(lkckInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        lkckInfo.setUpdateBy(SecurityUtils.getUsername());
        List<LkckInfoChild> childList= JSONArray.parseArray(lkckInfo.getRows(),LkckInfoChild.class);
        for(LkckInfoChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setUpdateBy(SecurityUtils.getUsername());
                child.setDjNumber(lkckInfo.getDjNumber());
                lkckInfoChildService.updateLkckInfoChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(lkckInfo.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                lkckInfoChildService.insertLkckInfoChild(child);
            }
        }
        return toAjax(lkckInfoService.updateLkckInfo(lkckInfo));
    }

    /**
     * 删除出库单
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfo:remove')")
    @Log(title = "出库单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            LkckInfo info = lkckInfoService.selectLkckInfoById(ids[i]);
            if(info.getDjStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        if(ids.length>0){
            lkckInfoChildService.deleteLkckInfoChildByPid(ids);
        }
        //删除子表信息
        int result=lkckInfoService.deleteLkckInfoByIds(ids);
        if(result>0){
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }

    /**
     * 生效
     */
    @PreAuthorize("@ss.hasPermi('system:lkckInfo:effect')")
    @Log(title = "出库单生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        List<LkckInfo> ckInfoList=new ArrayList<>();
        //检查提交状态
        for(int i=0;i<ids.length;i++) {
            LkckInfo info = lkckInfoService.selectLkckInfoById(ids[i]);
            ckInfoList.add(info);
            if (info.getDjStatus() != 0) {
                return toAjaxByError(info.getDjNumber() + "：该单据状态禁止操作!");
            }
        }
        //添加库存
        for(int i=0;i<ckInfoList.size();i++){
            LkckInfo info = ckInfoList.get(i);
            //查询明细
            LkckInfoChild rkInfoChild=new LkckInfoChild();
            rkInfoChild.setDjNumber(info.getDjNumber());
            List<LkckInfoChild> rkInfoChildList=lkckInfoChildService.selectLkckInfoChildList(rkInfoChild);
            for(LkckInfoChild child:rkInfoChildList){
                //添加库存
                KcInfo kcInfo=new KcInfo();
                kcInfo.setId(StringUtils.getId());
                kcInfo.setStoreNum(child.getStoreNum());
                kcInfo.setStoreName(child.getStoreName());
                kcInfo.setDjNumber(info.getDjNumber());
                kcInfo.setLsNumber(child.getRkPc());
                kcInfo.setGoodsCode(child.getGoodsCode());
                kcInfo.setGoodsName(child.getGoodsName());
                kcInfo.setGoodsDw(child.getGoodsDw());
                kcInfo.setGoodsGg(child.getGoodsGg());
                kcInfo.setGoodsNum(child.getGoodsNum());
                kcInfo.setGoodsPrice(child.getGoodsPrice());
                kcInfo.setGoodsMoney(child.getGoodsMoney());
                kcInfo.setDjTime(info.getDjTime());
                kcInfo.setDjType("1");//代表出库
                kcInfo.setCreateBy(SecurityUtils.getUsername());
                kcInfoService.insertKcInfo(kcInfo);
            }
            rkInfoChild=null;
        }
        return toAjax(lkckInfoService.updateCkdStatus(ids));
    }
}
