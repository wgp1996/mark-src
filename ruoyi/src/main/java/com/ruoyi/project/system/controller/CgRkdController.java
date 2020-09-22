package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.push.JPush;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.*;
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
    @Autowired
    private ICgRkdSingleService cgRkdSingleService;
    @Autowired
    private ICgRkdSingleChildService cgRkdSingleChildService;
    @Autowired
    private IResultInfoService resultInfoService;
    /**
     * 查询进货单列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(CgRkd cgRkd)
    {
        startPage();
        //改为合并子表列表展示 不显示子表信息
        List<CgRkd> list = cgRkdService.selectCgRkdAllList(cgRkd);
        /*List<CgRkd> list = cgRkdService.selectCgRkdList(cgRkd);
        for(CgRkd info:list){
            info.setChildrenList(cgRkdChildService.selectCgRkdChildByNumber(info.getDjNumber()));
        }*/
        return getDataTable(list);
    }

    /**
     * 业户接收订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/orderList/{type}")
    public TableDataInfo orderList(@PathVariable Integer type)
    {
        startPage();
        List<CgRkd> list = cgRkdService.selectCgRkdAllListByStatus(SecurityUtils.getUsername(),type);
        for(CgRkd info:list){
            if(info.getType()==0){
                List<CgRkdChild> cgRkdChild= cgRkdChildService.selectCgRkdSingleChildByNumber(info.getDjNumber());
                info.setChildrenList(cgRkdChild);
            }else{
                CgRkdChild cgRkdChild= cgRkdChildService.selectCgRkdChildById(info.getId());
                List<CgRkdChild> CgRkdChild=new ArrayList<>();
                CgRkdChild.add(cgRkdChild);
                info.setChildrenList(CgRkdChild);
            }
        }
        return getDataTable(list);
    }
    /**
     * 查询进货单汇总列表
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/rkdSummaryList")
    public TableDataInfo rkdSummaryList(CgRkd cgRkd)
    {
        startPage();
        List<CgRkd> list = cgRkdService.rkdSummaryList(cgRkd);
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
        cgRkd.setDjNumber(StringUtils.getRandomCode("PO"));
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
        if(cgRkd.getStatus()>=1){
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
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            CgRkd info = cgRkdService.selectCgRkdById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止提交!");
            }
        }
        return toAjax(cgRkdService.updateCgRkdStatus(ids));
    }

    /**
     * 进货单提交
     */
    @PreAuthorize("@ss.hasPermi('system:cgrkd:submint')")
    @Log(title = "进货单提交", businessType = BusinessType.UPDATE)
    @DeleteMapping("/submint/{ids}")
    public AjaxResult submint(@PathVariable String[] ids)
    {
        boolean lag=true;
        List<String> ownerCodeList=new ArrayList<>();
        List<String> numberList=new ArrayList<>();
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            CgRkd info = cgRkdService.selectCgRkdById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止提交!");
            }
        }
        for(int i=0;i<numberList.size();i++){
            //多供应商查询明细信息
            List<CgRkdChild> children=cgRkdChildService.selectCgRkdChildByNumber(numberList.get(i));
            //多供应商添加业户代码
            for(CgRkdChild child:children){
                ownerCodeList.add(child.getPersonCode());
            }
        }
        int result=cgRkdService.updateCgRkdStatus(ids);
        if(result>0) {
            //进行消息推送
            if (lag) {
                for (String info : ownerCodeList) {
                    Map<String, String> parm = new HashMap<>();
                    parm.put("id", info);
                    parm.put("msg", "您有新的订单待接收!");
                    JPush.jpushAndroid(parm, 0);
                    JPush.jpushIOS(parm, 0);
                }
            }
        }
        return toAjaxBySuccess("提交成功!");
    }

    /**
     * 业户接收订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:receive')")
    @Log(title = "接收订单", businessType = BusinessType.UPDATE)
    @PostMapping("/rkdReceive")
    public AjaxResult appRkdReceive(@RequestBody CgRkd cgRkd)
    {
        try{
            String  createBy="";
            //单供应商
            if(cgRkd.getType()==0){
                //修改订单状态
                CgRkdSingle CgRkdSingle=new CgRkdSingle();
                CgRkdSingle.setDjNumber(cgRkd.getDjNumber());
                //查询百大用户信息进行推送
                CgRkdSingle single=cgRkdSingleService.selectCgRkdSingleList(CgRkdSingle).get(0);
                if(single.getStatus()!=1){
                    return AjaxResult.success("接收成功");
                }
                createBy=single.getCreateBy();
                CgRkdSingle.setStatus(2);
                cgRkdSingleService.updateCgRkdSingleStatusByNumber(CgRkdSingle);
                //修改明细产地跟单价
                List<CgRkdSingleChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdSingleChild.class);
                for(CgRkdSingleChild child:childList){
                    cgRkdSingleChildService.updateCgRkdSingleChild(child);
                }
                //多供应商
            }else{

                //查询百大用户信息进行推送
                CgRkd CgRkd=new CgRkd();
                CgRkd.setDjNumber(cgRkd.getDjNumber());
                CgRkd rkd=cgRkdService.selectCgRkdAllList(CgRkd).get(0);
                createBy=rkd.getCreateBy();
                //修改明细产地,单价以及状态
                List<CgRkdChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdChild.class);
                for(CgRkdChild child:childList){
                    child.setStatus(1);
                    cgRkdChildService.updateCgRkdChild(child);
                }
                //检查明细所有订单是否接收完毕
                int result=cgRkdService.checkRkdAllReceive(cgRkd.getDjNumber());
                //全部接收完毕修改订单状态
                if(result==0){
                    cgRkd.setStatus(2);
                    cgRkdService.updateCgRkdStatusByNumber(cgRkd);
                }
            }
            //推送给百大
            if(createBy!=""){
                Map<String, String> parm=new HashMap<>();
                parm.put("id",createBy);
                parm.put("msg","您有新的订单待审核!");
                JPush.jpushAndroid(parm,1);
                JPush.jpushIOS(parm,1);
            }
            return AjaxResult.success("接收成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("接收失败");
        }
    }

    /**
     * 拒绝接收订单
     */
    @Log(title = "拒绝接收订单", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('system:order:refuse')")
    @PostMapping("/rkdRefuse")
    public AjaxResult rkdRefuse(@RequestBody ResultInfo resultInfo)
    {
        try{
            boolean lag=false;
            //单供应商
            if(resultInfo.getType()==0){
                //修改订单状态
                CgRkdSingle CgRkdSingle=new CgRkdSingle();
                CgRkdSingle.setDjNumber(resultInfo.getDjNumber());
                CgRkdSingle.setStatus(-1);
                int result=cgRkdSingleService.updateCgRkdSingleStatusByNumber(CgRkdSingle);
                if(result>0){
                    lag=true;
                }
                //多供应商
            }else{
                CgRkd cgRkd=new CgRkd();
                cgRkd.setDjNumber(resultInfo.getDjNumber());
                cgRkd.setStatus(-1);
                cgRkdService.updateCgRkdStatusByNumber(cgRkd);
                //修改明细状态
                CgRkdChild CgRkdChild=new CgRkdChild();
                CgRkdChild.setId(resultInfo.getDjId());
                CgRkdChild.setStatus(-1);
                int result= cgRkdChildService.updateCgRkdChild(CgRkdChild);
                if(result>0){
                    lag=true;
                }
            }
            if(lag){
                //添加拒绝原因
                resultInfo.setId(StringUtils.getId());
                resultInfo.setType(0);//业户拒绝
                resultInfo.setReturnTime(DateUtils.getTime());
                resultInfoService.insertResultInfo(resultInfo);
                return AjaxResult.success("拒绝成功");
            }else{
                return AjaxResult.error("状态修改失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("接收失败");
        }
    }



    /**
     * 确认订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:confirm')")
    @Log(title = "确认订单", businessType = BusinessType.UPDATE)
    @PostMapping("/rkdConfirm")
    public AjaxResult rkdConfirm(@RequestBody CgRkd cgRkd)
    {
        try{
            //单供应商
            if(cgRkd.getType()==0){
                //修改订单状态
                CgRkdSingle CgRkdSingle=new CgRkdSingle();
                CgRkdSingle.setDjNumber(cgRkd.getDjNumber());
                CgRkdSingle.setStatus(3);
                cgRkdSingleService.updateCgRkdSingleStatusByNumber(CgRkdSingle);
                //多供应商
            }else{
                //修改明细状态
                CgRkdChild CgRkdChild=new CgRkdChild();
                CgRkdChild.setStatus(2);
                CgRkdChild.setId(cgRkd.getId());
                cgRkdChildService.updateCgRkdChild(CgRkdChild);
                //检查明细所有订单是否确认完毕
                int result=cgRkdService.checkRkdAllConfirm(cgRkd.getDjNumber());
                //全部确认完毕修改订单状态
                if(result==0){
                    cgRkd.setStatus(3);
                    cgRkdService.updateCgRkdStatusByNumber(cgRkd);
                }
            }
            return AjaxResult.success("确认成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("确认失败");
        }
    }


    /**
     * 拒绝确认订单
     */
    @Log(title = "拒绝确认订单", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('system:order:cancel')")
    @PostMapping("/rkdCancel")
    public AjaxResult rkdCancel(@RequestBody ResultInfo resultInfo)
    {
        try{
            boolean lag=false;
            //单供应商
            if(resultInfo.getType()==0){
                //修改订单状态
                CgRkdSingle CgRkdSingle=new CgRkdSingle();
                CgRkdSingle.setDjNumber(resultInfo.getDjNumber());
                CgRkdSingle.setStatus(-2);
                int result=cgRkdSingleService.updateCgRkdSingleStatusByNumber(CgRkdSingle);
                if(result>0){
                    lag=true;
                }
                //多供应商
            }else{
                CgRkd cgRkd=new CgRkd();
                cgRkd.setDjNumber(resultInfo.getDjNumber());
                cgRkd.setStatus(-2);
                cgRkdService.updateCgRkdStatusByNumber(cgRkd);
                //修改明细状态
                CgRkdChild CgRkdChild=new CgRkdChild();
                CgRkdChild.setId(resultInfo.getDjId());
                CgRkdChild.setStatus(-2);
                int result= cgRkdChildService.updateCgRkdChild(CgRkdChild);
                if(result>0){
                    lag=true;
                }
            }
            if(lag){
                //添加拒绝原因
                resultInfo.setId(StringUtils.getId());
                resultInfo.setType(1);//百大拒绝
                resultInfo.setReturnTime(DateUtils.getTime());
                resultInfoService.insertResultInfo(resultInfo);
                return AjaxResult.success("拒绝成功");
            }else{
                return AjaxResult.error("状态修改失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("接收失败");
        }
    }

}
