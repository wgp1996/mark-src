package com.ruoyi.project.info.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.info.domain.KcInfo;
import com.ruoyi.project.info.domain.RkInfoChild;
import com.ruoyi.project.info.service.IKcInfoService;
import com.ruoyi.project.info.service.IRkInfoChildService;
import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.domain.CgRkdChild;
import com.ruoyi.project.system.domain.RandomInspectionInfo;
import com.ruoyi.project.system.domain.RandomInspectionInfoChild;
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
import com.ruoyi.project.info.domain.RkInfo;
import com.ruoyi.project.info.service.IRkInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 入库单Controller
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
@RestController
@RequestMapping("/system/rkInfo")
public class RkInfoController extends BaseController
{
    @Autowired
    private IRkInfoService rkInfoService;
    @Autowired
    private IRkInfoChildService rkInfoChildService;
    @Autowired
    private IKcInfoService kcInfoService;

    /**
     * 查询入库单列表
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfo:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(RkInfo rkInfo)
    {
        startPage();
        List<RkInfo> list = rkInfoService.selectRkInfoList(rkInfo);
        for(RkInfo info:list) {
            RkInfoChild rkInfoChild = new RkInfoChild();
            rkInfoChild.setDjNumber(info.getDjNumber());
            info.setChildrenList(rkInfoChildService.selectRkInfoChildList(rkInfoChild));
            rkInfoChild=null;
        }
        return getDataTable(list);
    }

    /**
     * 查询入库单列表
     */
    @GetMapping("/selectRkdList")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo selectRkdList(RkInfoChild rkInfoChild)
    {
        startPage();
        List<RkInfoChild> list = rkInfoChildService.selectRkInfoByCkd(rkInfoChild);
        return getDataTable(list);
    }

    /**
     * 导出入库单列表
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfo:export')")
    @Log(title = "入库单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RkInfo rkInfo)
    {
        List<RkInfo> list = rkInfoService.selectRkInfoList(rkInfo);
        ExcelUtil<RkInfo> util = new ExcelUtil<RkInfo>(RkInfo.class);
        return util.exportExcel(list, "rkInfo");
    }

    /**
     * 获取入库单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(rkInfoService.selectRkInfoById(id));
    }

    /**
     * 新增入库单
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfo:add')")
    @Log(title = "入库单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RkInfo rkInfo)
    {
        if(rkInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        rkInfo.setDjNumber(StringUtils.getRandomCode("RK"));
        rkInfo.setRkPc(StringUtils.getRandomCode("PC"));
        rkInfo.setDjStatus(0);
        rkInfo.setCreateBy(SecurityUtils.getUsername());
        rkInfo.setId(StringUtils.getId());
        List<RkInfoChild> childList= JSONArray.parseArray(rkInfo.getRows(), RkInfoChild.class);
        for(RkInfoChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(rkInfo.getDjNumber());
            child.setRkPc(rkInfo.getRkPc());
            child.setCreateTime(DateUtils.getNowDate());
            rkInfoChildService.insertRkInfoChild(child);
        }
        return toAjax(rkInfoService.insertRkInfo(rkInfo));
    }

    /**
     * 修改入库单
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfo:edit')")
    @Log(title = "入库单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RkInfo rkInfo)
    {
        //检查是否为已生效
        if(rkInfo.getDjStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(rkInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        rkInfo.setUpdateBy(SecurityUtils.getUsername());
        List<RkInfoChild> childList= JSONArray.parseArray(rkInfo.getRows(),RkInfoChild.class);
        for(RkInfoChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setUpdateBy(SecurityUtils.getUsername());
                child.setDjNumber(rkInfo.getDjNumber());
                child.setRkPc(rkInfo.getRkPc());
                rkInfoChildService.updateRkInfoChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setRkPc(rkInfo.getRkPc());
                child.setDjNumber(rkInfo.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                rkInfoChildService.insertRkInfoChild(child);
            }
        }
        return toAjax(rkInfoService.updateRkInfo(rkInfo));
    }

    /**
     * 删除入库单
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfo:remove')")
    @Log(title = "入库单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            RkInfo info = rkInfoService.selectRkInfoById(ids[i]);
            if(info.getDjStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        if(ids.length>0){
            rkInfoChildService.deleteRkInfoChildByPid(ids);
        }
        //删除子表信息
        int result=rkInfoService.deleteRkInfoByIds(ids);
        if(result>0){
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }


    /**
     * 生效
     */
    @PreAuthorize("@ss.hasPermi('system:rkInfo:effect')")
    @Log(title = "入库单生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        List<RkInfo> RkInfoList=new ArrayList<>();
        //检查提交状态
        for(int i=0;i<ids.length;i++) {
            RkInfo info = rkInfoService.selectRkInfoById(ids[i]);
            RkInfoList.add(info);
            if (info.getDjStatus() != 0) {
                return toAjaxByError(info.getDjNumber() + "：该单据状态禁止操作!");
            }
        }
        //添加库存
        for(int i=0;i<RkInfoList.size();i++){
            RkInfo info = RkInfoList.get(i);
            //查询明细
            RkInfoChild rkInfoChild=new RkInfoChild();
            rkInfoChild.setDjNumber(info.getDjNumber());
            List<RkInfoChild> rkInfoChildList=rkInfoChildService.selectRkInfoChildList(rkInfoChild);
            for(RkInfoChild child:rkInfoChildList){
                //添加库存
                KcInfo kcInfo=new KcInfo();
                kcInfo.setId(StringUtils.getId());
                kcInfo.setStoreNum(info.getStoreNum());
                kcInfo.setStoreName(info.getStoreName());
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
                kcInfo.setDjType("0");//代表入库
                kcInfo.setCreateBy(SecurityUtils.getUsername());
                kcInfoService.insertKcInfo(kcInfo);
            }
            rkInfoChild=null;
        }
        return toAjax(rkInfoService.updateRkdStatus(ids));
    }
}
