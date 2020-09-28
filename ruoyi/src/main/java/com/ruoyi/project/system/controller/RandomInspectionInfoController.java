package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.IRandomInspectionInfoChildService;
import com.ruoyi.project.system.service.ISysUserService;
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
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private ISysUserService userService;
    /**
     * 导入进货单
     */
    @PreAuthorize("@ss.hasPermi('system:randomInsp:import')")
    @Log(title = "检测单", businessType = BusinessType.EXPORT)
    @PostMapping("/import")
    public AjaxResult uploadFile(MultipartFile file,String djNumber) throws Exception
    {
        try
        {
            System.out.println(djNumber);
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            System.out.println(filePath);
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            //解析Excel
            List<List<String>> list=ExcelUtil.importCgRkd(filePath+fileName.substring(fileName.indexOf("upload")+6,fileName.length()));
            if(list.size()>0){
                String checkTime="";
                String checkNum="";
                for(int i=0;i<list.size();i++){
                    //取检测日期跟检测标准
                    List<String> info=list.get(i);
                   /* if(i==0){
                        checkTime=info.get(0);
                        checkNum=info.get(1);
                    }*/
                    if(!"".equals(info.get(4))&&info.get(4)!=null) {
                        //RandomInspectionInfo randomInspectionInfo=new RandomInspectionInfo();
                        //自动匹配业户
                        SysUser user=new SysUser();
                        user.setPhonenumber(info.get(3));
                        user.setUnitName(info.get(1));
                        user.setNickName(info.get(2));
                        List<SysUser> userList=userService.selectUserByUserLike(user);

                        /*randomInspectionInfo.setDjNumber(StringUtils.getRandomCode("CH"));
                        randomInspectionInfo.setDjStatus(Long.valueOf(1));
                        randomInspectionInfo.setDjTime(checkTime);//检测日期
                        randomInspectionInfo.setCheckNum(checkNum);//检验标准
                        randomInspectionInfo.setCreateBy(SecurityUtils.getUsername());
                        randomInspectionInfo.setId(StringUtils.getId());*/
                        RandomInspectionInfoChild child=new RandomInspectionInfoChild();
                        if(userList!=null&&userList.size()>0){
                            //默认取第一条
                            SysUser item=userList.get(0);
                            child.setOwnerCode(user.getUserName());
                            child.setOwnerName(user.getNickName());
                            //randomInspectionInfo.setCreateBy(item.getUserName());
                        }else{
                            child.setOwnerCode("");
                            child.setOwnerName("");
                            child.setRemark("业户待建档");
                            //未匹配到的数据默认放到admin帐号下 可修改
                            //randomInspectionInfo.setCreateBy("admin");
                        }
                       // child.setGoodsCode();
                        child.setGoodsName(info.get(4));
                        child.setCheckProject(info.get(5));
                        child.setSampTime(info.get(6));
                        child.setTestResult(info.get(7));
                        if("合格".equals(info.get(8))){
                            child.setCheckResult(1);
                        }else{
                            child.setCheckResult(0);
                        }
                        child.setCreateBy(SecurityUtils.getUsername());
                        child.setId(StringUtils.getId());
                        child.setDjNumber(djNumber);
                        child.setCreateTime(DateUtils.getNowDate());
                        randomInspectionInfoChildService.insertRandomInspectionInfoChild(child);

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
        randomInspectionInfo.setDjNumber(StringUtils.getRandomCode("CH"));
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
