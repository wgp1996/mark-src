package com.ruoyi.project.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.push.JPush;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.domain.CgRkdSingle;
import com.ruoyi.project.system.domain.CgRkdSingleChild;
import com.ruoyi.project.system.service.ICgRkdSingleChildService;
import com.ruoyi.project.system.service.ICgRkdSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进货单Controller
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@RestController
@RequestMapping("/system/cgrkdSingle")
public class CgRkdSingleController extends BaseController
{
    @Autowired
    private ICgRkdSingleService cgRkdService;
    @Autowired
    private ICgRkdSingleChildService cgRkdChildService;
    /**
     * 查询进货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(CgRkdSingle cgRkd)
    {
        startPage();
        //改为合并子表列表展示 不显示子表信息
        List<CgRkdSingle> list = cgRkdService.selectCgRkdSingleAllList(cgRkd);
        /*List<CgRkdSingle> list = cgRkdService.selectCgRkdSingleList(cgRkd);
        for(CgRkdSingle info:list){
            info.setChildrenList(cgRkdChildService.selectCgRkdSingleChildByNumber(info.getDjNumber()));
        }*/
        return getDataTable(list);
    }
    /**
     * 导入进货单
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:import')")
    @Log(title = "进货单", businessType = BusinessType.EXPORT)
    @PostMapping("/import")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            System.out.println(filePath);
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            //解析Excel
            List<List<String>> list=ExcelUtil.importCgRkd(filePath+fileName.substring(fileName.indexOf("upload")+6,fileName.length()));
            if(list.size()>0){
                for(List<String> info:list){
                    if(!"".equals(info.get(3))&&info.get(3)!=null) {
                        CgRkdSingle cgRkd = new CgRkdSingle();
                        cgRkd.setDjNumber(StringUtils.getRandomCode("POC"));//代表导入的采购单;
                        cgRkd.setStatus(3);//导入代表已生效
                        cgRkd.setCreateBy(SecurityUtils.getUsername());
                        cgRkd.setId(StringUtils.getId());
                        cgRkd.setDjTime(info.get(0));//单据日期
                        cgRkd.setCreateBy(SecurityUtils.getUsername());//制单人
                        cgRkdService.insertCgRkdSingle(cgRkd);
                        //插入子表
                        CgRkdSingleChild child = new CgRkdSingleChild();
                        child.setCreateBy(SecurityUtils.getUsername());
                        child.setId(StringUtils.getId());
                        child.setDjNumber(cgRkd.getDjNumber());
                        child.setCreateTime(DateUtils.getNowDate());
                        child.setShopName(info.get(2));//门店
                        child.setGoodsCode(info.get(3));//商品编码
                        child.setGoodsName(info.get(4));//商品名称
                        child.setGoodsAddress(info.get(5));//商品产地
                        child.setGoodsDw(info.get(6));//商品单位
                        child.setGoodsNum(info.get(7));//数量
                        child.setGoodsNum(info.get(8));//单价
                        child.setGoodsNum(info.get(9));//金额
                        child.setRemark(info.get(10));//备注
                        cgRkdChildService.insertCgRkdSingleChild(child);
                    }
                }
                return  AjaxResult.success("导入成功!");
            }else{
                return AjaxResult.error("导入失败!");
            }
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 导出进货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:export')")
    @Log(title = "进货单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CgRkdSingle cgRkd)
    {
        List<CgRkdSingle> list = cgRkdService.selectCgRkdSingleList(cgRkd);
        ExcelUtil<CgRkdSingle> util = new ExcelUtil<CgRkdSingle>(CgRkdSingle.class);
        return util.exportExcel(list, "cgrkdSingle");
    }

    /**
     * 获取进货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cgRkdService.selectCgRkdSingleById(id));
    }

    /**
     * 新增进货单
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:add')")
    @Log(title = "进货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CgRkdSingle cgRkd)
    {
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setDjNumber(StringUtils.getRandomCode("PO"));
        cgRkd.setStatus(0);
        cgRkd.setCreateBy(SecurityUtils.getUsername());
        cgRkd.setId(StringUtils.getId());
        List<CgRkdSingleChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdSingleChild.class);
        for(CgRkdSingleChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(cgRkd.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            cgRkdChildService.insertCgRkdSingleChild(child);
        }
        return toAjax(cgRkdService.insertCgRkdSingle(cgRkd));
    }

    /**
     * 修改进货单
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:edit')")
    @Log(title = "进货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CgRkdSingle cgRkd)
    {
        //检查是否为已生效的合同
        if(cgRkd.getStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setUpdateBy(SecurityUtils.getUsername());
        List<CgRkdSingleChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdSingleChild.class);
        for(CgRkdSingleChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(cgRkd.getDjNumber());
                cgRkdChildService.updateCgRkdSingleChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(cgRkd.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                cgRkdChildService.insertCgRkdSingleChild(child);
            }
        }
        return toAjax(cgRkdService.updateCgRkdSingle(cgRkd));
    }

    /**
     * 删除进货单
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:remove')")
    @Log(title = "进货单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            CgRkdSingle info = cgRkdService.selectCgRkdSingleById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=cgRkdChildService.deleteCgRkdSingleChildByPid(ids);
        if(result>0){
            cgRkdService.deleteCgRkdSingleByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
    /**
     * 租赁合同生效
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:effect')")
    @Log(title = "进货单生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            CgRkdSingle info = cgRkdService.selectCgRkdSingleById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止提交!");
            }
        }
        return toAjax(cgRkdService.updateCgRkdSingleStatus(ids));
    }

    /**
     * 单供应采购提交
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkdSingle:submit')")
    @Log(title = "进货单提交", businessType = BusinessType.UPDATE)
    @DeleteMapping("/submit/{ids}")
    public AjaxResult appRkdSingleSub(@PathVariable String[] ids)
    {
        boolean lag=true;
        List<String> ownerCodeList=new ArrayList<>();
        for(int i=0;i<ids.length;i++){
            CgRkdSingle info = cgRkdService.selectCgRkdSingleById(ids[i]);
            ownerCodeList.add(info.getPersonCode());
            if(info.getStatus()!=0){
                lag=false;
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止提交!");
            }
        }
        int result=cgRkdService.updateCgRkdSingleStatus(ids);
        if(result>0){
            //进行消息推送
            if(lag){
                for(String info:ownerCodeList){
                    Map<String, String> parm=new HashMap<>();
                    parm.put("id",info);
                    parm.put("msg","您有一条新的订单待接收!");
                    JPush.jpushAndroid(parm,0);
                    JPush.jpushIOS(parm,0);
                }
            }
            return toAjaxBySuccess("提交成功!");
        }else{
            return  toAjaxByError("提交失败!");
        }
    }





}
