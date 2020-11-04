package com.ruoyi.project.info.controller;

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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.info.domain.WholeRetails;
import com.ruoyi.project.info.domain.WholeRetailsChild;
import com.ruoyi.project.info.service.IWholeRetailsChildService;
import com.ruoyi.project.info.service.IWholeRetailsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * 零售销货单Controller
 *
 * @author ruoyi
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/info/wholeRetail")
public class WholeRetailsController extends BaseController
{
    @Autowired
    private IWholeRetailsService wholeRetailService;
    @Autowired
    private IWholeRetailsChildService wholeRetailChildService;

    /**
     * 导入零售销货单
     */
    @PreAuthorize("@ss.hasPermi('info:wholeRetail:import')")
    @Log(title = "导入零售销货单", businessType = BusinessType.EXPORT)
    @PostMapping("/import")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            //解析Excel
            List<List<String>> list=ExcelUtil.importWholeRetail(filePath+fileName.substring(fileName.indexOf("upload")+6,fileName.length()));
            if(list.size()>0){
                String djNumber="";
                boolean lag=false;
                for(int i=0;i<list.size();i++){
                    List<String> info=list.get(i);
                    if(!"".equals(info.get(3))&&info.get(3)!=null&&!"".equals(info.get(2))&&info.get(2)!=null) {
                        //添加表头
                        if(i==0) {
                            WholeRetails wholeRetail=new WholeRetails();
                            wholeRetail.setDjTime(info.get(1));
                            wholeRetail.setDjNumber(StringUtils.getRandomCode("LSH"));
                            wholeRetail.setStatus(0);
                            wholeRetail.setFrom(0);//web
                            wholeRetail.setCreateBy(SecurityUtils.getUsername());
                            wholeRetail.setId(StringUtils.getId());
                            djNumber=wholeRetail.getDjNumber();
                            int result=wholeRetailService.insertWholeRetail(wholeRetail);
                            if(result>0){
                                lag=true;
                            }
                        }
                        if(lag){
                            //添加表体
                            WholeRetailsChild child=new WholeRetailsChild();
                            child.setCreateBy(SecurityUtils.getUsername());
                            child.setId(StringUtils.getId());
                            child.setDjNumber(djNumber);
                            child.setGoodsName(info.get(3).toString());//商品名称
                            child.setKhName(info.get(2).toString());//客户
                            child.setWholeNum(info.get(4).toString());//数量
                            child.setWholePrice(info.get(6).toString());//单价
                            child.setWholeMoney(info.get(7).toString());//金额
                            wholeRetailChildService.insertWholeRetailChild(child);
                        }
                    }else{
                        break;
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
     * 查询零售销货单列表
     */
    @PreAuthorize("@ss.hasPermi('info:wholeRetail:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(WholeRetails wholeRetail)
    {
        startPage();
      //  List<WholeRetailsChild> list = wholeRetailChildService.selectWholeRetailAllList(wholeRetail);
        List<WholeRetails> list = wholeRetailService.selectWholeRetailList(wholeRetail);
        for(WholeRetails info:list){
            WholeRetailsChild child=new WholeRetailsChild();
            child.setDjNumber(info.getDjNumber());
            info.setChildrenList(wholeRetailChildService.selectWholeRetailChildList(child));
            child=null;//销毁
        }
        return getDataTable(list);
    }

    /**
     * 导出零售销货单列表
     */
    @PreAuthorize("@ss.hasPermi('info:wholeRetail:export')")
    @Log(title = "零售销货单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WholeRetails wholeRetail)
    {
        List<WholeRetails> list = wholeRetailService.selectWholeRetailList(wholeRetail);
        ExcelUtil<WholeRetails> util = new ExcelUtil<WholeRetails>(WholeRetails.class);
        return util.exportExcel(list, "wholeRetail");
    }

    /**
     * 获取零售销货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:wholeRetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(wholeRetailService.selectWholeRetailById(id));
    }

    /**
     * 获取零售销货单详细信息
     */
    @GetMapping(value = "/selectNumFlList")
    public AjaxResult selectNumFlList(WholeRetails wholeRetail)
    {
        return AjaxResult.success(wholeRetailService.selectNumFlList(wholeRetail));
    }

    /**
     * 查询首页数量信息
     *
     */
    @GetMapping(value = "/selectNumlList")
    public AjaxResult selectNumlList()
    {
        AjaxResult AjaxResult=new AjaxResult();
        WholeRetails wholeRetails= wholeRetailService.selectNumlList();
        HashMap map=new HashMap();
        map.put("ownerCount",wholeRetails.getIsRateName());
        map.put("goodsCount",wholeRetails.getPayTypeName());
        map.put("rkdCount",wholeRetails.getDjStatusName());
        map.put("addressCount",wholeRetails.getCreateName());
        map.put("wholeCount",wholeRetails.getKhName());
        map.put("randomCount",wholeRetails.getKhCode());
        AjaxResult.put("code",200);
        AjaxResult.put("msg","操作成功");
        AjaxResult.put("data",map);
        return  AjaxResult;
    }

    /**
     * 新增零售销货单
     */
    @PreAuthorize("@ss.hasPermi('info:wholeRetail:add')")
    @Log(title = "零售销货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WholeRetails wholeRetail)
    {
        if(wholeRetail.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        wholeRetail.setDjNumber(StringUtils.getRandomCode("LSH"));
        wholeRetail.setStatus(0);
        wholeRetail.setFrom(0);//web
        wholeRetail.setCreateBy(SecurityUtils.getUsername());
        wholeRetail.setId(StringUtils.getId());
        List<WholeRetailsChild> childList= JSONArray.parseArray(wholeRetail.getRows(),WholeRetailsChild.class);
        for(WholeRetailsChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setId(StringUtils.getId());
            child.setDjNumber(wholeRetail.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            wholeRetailChildService.insertWholeRetailChild(child);
        }
        return toAjax(wholeRetailService.insertWholeRetail(wholeRetail));
    }

    /**
     * 修改零售销货单
     */
    @PreAuthorize("@ss.hasPermi('info:wholeRetail:edit')")
    @Log(title = "零售销货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WholeRetails wholeRetail)
    {
        //检查是否为已生效的单据
        if(wholeRetail.getStatus()==1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(wholeRetail.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        wholeRetail.setUpdateBy(SecurityUtils.getUsername());
        List<WholeRetailsChild> childList= JSONArray.parseArray(wholeRetail.getRows(),WholeRetailsChild.class);
        for(WholeRetailsChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(wholeRetail.getDjNumber());
                wholeRetailChildService.updateWholeRetailChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setId(StringUtils.getId());
                child.setDjNumber(wholeRetail.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                wholeRetailChildService.insertWholeRetailChild(child);
            }
        }
        return toAjax(wholeRetailService.updateWholeRetail(wholeRetail));
    }

    /**
     * 删除零售销货单
     */
    @PreAuthorize("@ss.hasPermi('info:wholeRetail:remove')")
    @Log(title = "零售销货单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            WholeRetails info = wholeRetailService.selectWholeRetailById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        if(ids.length>0){
            wholeRetailChildService.deleteWholeRetailChildByPid(ids);
        }
        int result=wholeRetailService.deleteWholeRetailByIds(ids);
        if(result>0){

            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
    /**
     * 单据生效
     */
    @PreAuthorize("@ss.hasPermi('info:wholeRetail:effect')")
    @Log(title = "零售销货单", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable String[] ids)
    {
        return toAjax(wholeRetailService.updateWholeRetailStatus(ids));
    }
}
