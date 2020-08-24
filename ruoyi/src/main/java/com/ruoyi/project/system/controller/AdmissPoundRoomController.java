package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.AdmissPoundRoomChild;
import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.domain.CgRkdChild;
import com.ruoyi.project.system.service.IAdmissPoundRoomChildService;
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
import com.ruoyi.project.system.domain.AdmissPoundRoom;
import com.ruoyi.project.system.service.IAdmissPoundRoomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 磅房入场单Controller
 * 
 * @author ruoyi
 * @date 2020-08-20
 */
@RestController
@RequestMapping("/system/admiss")
public class AdmissPoundRoomController extends BaseController
{
    @Autowired
    private IAdmissPoundRoomService admissPoundRoomService;

    @Autowired
    private IAdmissPoundRoomChildService admissPoundRoomChildService;

    /**
     * 查询磅房入场单列表
     */
    @PreAuthorize("@ss.hasPermi('system:admiss:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(AdmissPoundRoom admissPoundRoom)
    {
        startPage();
        List<AdmissPoundRoom> list = admissPoundRoomService.selectAdmissPoundRoomList(admissPoundRoom);
        for(AdmissPoundRoom info:list){
            AdmissPoundRoomChild child=new AdmissPoundRoomChild();
            child.setDjNumber(info.getDjNumber());
            info.setChildrenList(admissPoundRoomChildService.selectAdmissPoundRoomChildList(child));
        }
        return getDataTable(list);
    }

    /**
     * 导出磅房入场单列表
     */
    @PreAuthorize("@ss.hasPermi('system:admiss:export')")
    @Log(title = "磅房入场单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AdmissPoundRoom admissPoundRoom)
    {
        List<AdmissPoundRoom> list = admissPoundRoomService.selectAdmissPoundRoomList(admissPoundRoom);
        ExcelUtil<AdmissPoundRoom> util = new ExcelUtil<AdmissPoundRoom>(AdmissPoundRoom.class);
        return util.exportExcel(list, "admiss");
    }

    /**
     * 获取磅房入场单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:admiss:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(admissPoundRoomService.selectAdmissPoundRoomById(id));
    }

    /**
     * 新增磅房入场单
     */
    @PreAuthorize("@ss.hasPermi('system:admiss:add')")
    @Log(title = "磅房入场单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmissPoundRoom admissPoundRoom)
    {
        if(admissPoundRoom.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        admissPoundRoom.setDjNumber(StringUtils.getRandomCode("RCD"));
        admissPoundRoom.setStatus(0);
        admissPoundRoom.setCreateBy(SecurityUtils.getUsername());
        admissPoundRoom.setId(StringUtils.getId());
        List<AdmissPoundRoomChild> childList= JSONArray.parseArray(admissPoundRoom.getRows(),AdmissPoundRoomChild.class);
        for(AdmissPoundRoomChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(admissPoundRoom.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            admissPoundRoomChildService.insertAdmissPoundRoomChild(child);
        }
        return toAjax(admissPoundRoomService.insertAdmissPoundRoom(admissPoundRoom));
    }

    /**
     * 修改磅房入场单
     */
    @PreAuthorize("@ss.hasPermi('system:admiss:edit')")
    @Log(title = "磅房入场单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmissPoundRoom admissPoundRoom)
    {
        //检查是否为已生效
        if(admissPoundRoom.getStatus()==1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(admissPoundRoom.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        admissPoundRoom.setUpdateBy(SecurityUtils.getUsername());
        List<AdmissPoundRoomChild> childList= JSONArray.parseArray(admissPoundRoom.getRows(),AdmissPoundRoomChild.class);
        for(AdmissPoundRoomChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(admissPoundRoom.getDjNumber());
                admissPoundRoomChildService.updateAdmissPoundRoomChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(admissPoundRoom.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                admissPoundRoomChildService.insertAdmissPoundRoomChild(child);
            }
        }
        return toAjax(admissPoundRoomService.updateAdmissPoundRoom(admissPoundRoom));
    }

    /**
     * 删除磅房入场单
     */
    @PreAuthorize("@ss.hasPermi('system:admiss:remove')")
    @Log(title = "磅房入场单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            AdmissPoundRoom info = admissPoundRoomService.selectAdmissPoundRoomById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=admissPoundRoomChildService.deleteAdmissPoundRoomChildByPid(ids);
        if(result>0){
            admissPoundRoomService.deleteAdmissPoundRoomByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }

    /**
     * 磅房入场单生效
     */
    @PreAuthorize("@ss.hasPermi('system:admiss:effect')")
    @Log(title = "磅房入场单生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        return toAjax(admissPoundRoomService.updateAdmissPoundRoomServiceStatus(ids));
    }

}
