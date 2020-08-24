package com.ruoyi.project.system.api;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 进货单Controller
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@Api("APP接口管理")
@RestController
@RequestMapping("/mark/api")
public class ApiController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private IStallInfoService stallInfoService;
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Autowired
    private IGoodsTypeService goodsTypeService;
    @Autowired
    private IGoodsInfoOwnerService goodsInfoOwnerService;
    @Autowired
    private IPersonInfoService personInfoService;
    @Autowired
    private ICgRkdChildService cgRkdChildService;
    @Autowired
    private ICgRkdService cgRkdService;

    /**
     * 摊位下拉列表
     */
    @ApiOperation("选择摊位信息")
    @GetMapping("/appStallList/{createBy}")
    public TableDataInfo appStallList(@PathVariable("createBy") String createBy)
    {
        List<StallInfo> list = stallInfoService.selectStallInfoListByOwner(createBy);
        return getDataTable(list);
    }

    /**
     * 查询商品建档列表
     */
    @ApiOperation("商品树形")
    @ApiImplicitParam(name = "code", value = "上级编码(顶级传-1))")
    @GetMapping("/appGoodsTree/{code}/{type}")
    public TableDataInfo appList(@PathVariable("code") Integer code,@PathVariable("type") Integer type)
    {
        List<HashMap> maps=new ArrayList<HashMap>();
        GoodsType goodsType=new GoodsType();
        if(type==0){
            if(code==-1){
                goodsType.setGoodsTypePid(0);
                List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
                for(GoodsType info:list){
                    HashMap map=new HashMap();
                    map.put("id",info.getGoodsTypeId()+"");
                    map.put("pid",info.getGoodsTypePid()+"");
                    map.put("text",info.getGoodsTypeName());
                    map.put("type",0);//0  代表分类
                    maps.add(map);
                }
            }else{
                goodsType.setGoodsTypePid(code);
                List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
                for(GoodsType info:list){
                    HashMap map=new HashMap();
                    map.put("id",info.getGoodsTypeId()+"");
                    map.put("pid",info.getGoodsTypePid()+"");
                    map.put("text",info.getGoodsTypeName());
                    map.put("type",0);//0  代表分类
                    maps.add(map);
                }
                GoodsInfo goodsInfo=new GoodsInfo();
                goodsInfo.setGoodsType(code);
                List<GoodsInfo> infoList = goodsInfoService.selectGoodsInfoList(goodsInfo);
                for(GoodsInfo info:infoList){
                    HashMap map=new HashMap();
                    map.put("id",info.getGoodsCode());
                    map.put("pid",code);
                    map.put("text",info.getGoodsName());
                    map.put("type",1);//1 代表商品
                    maps.add(map);
                }
            }
        }
        if(type==1){
            List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
            for(GoodsType info:list){
                HashMap map=new HashMap();
                map.put("id",info.getGoodsTypeId()+"");
                map.put("pid",info.getGoodsTypePid()+"");
                map.put("text",info.getGoodsTypeName());
                map.put("type",0);//0  代表分类
                maps.add(map);
            }
            GoodsInfo goodsInfo=new GoodsInfo();
            List<GoodsInfo> infoList = goodsInfoService.selectGoodsInfoList(goodsInfo);
            for(GoodsInfo info:infoList){
                HashMap map=new HashMap();
                map.put("id",info.getGoodsCode());
                map.put("pid",code);
                map.put("text",info.getGoodsName());
                map.put("type",1);//1 代表商品
                maps.add(map);
            }
        }

        return getDataTable(maps);
    }
    /**
     * APP查询业户商品建档列表
     */
    @ApiOperation("内库商品列表")
    @GetMapping("/appGoodsList/{createBy}")
    public TableDataInfo appGoodsList(@PathVariable("createBy") String createBy)
    {
        GoodsInfoOwner goodsInfoOwner=new GoodsInfoOwner();
        goodsInfoOwner.setCreateBy(createBy);
        List<GoodsInfoOwner> list = goodsInfoOwnerService.selectGoodsInfoOwnerList(goodsInfoOwner);
        return getDataTable(list);
    }

    /**
     * APP新增业户商品建档
     */
    @ApiOperation("内库商品新增")
    @Log(title = "APP业户商品建档", businessType = BusinessType.INSERT)
    @PostMapping("/appGoodsAdd")
    public AjaxResult appGoodsAdd(GoodsInfoOwner goodsInfoOwner)
    {
        //goodsInfoOwner.setCreateBy(SecurityUtils.getUsername());
        GoodsInfoOwner info=goodsInfoOwnerService.selectGoodsInfoOwnerByName(-1,goodsInfoOwner.getGoodsName(),goodsInfoOwner.getCreateBy());
        if(info!=null) {
            return  toAjaxByError("该商品在内库档案中已存在");
        }else{
            goodsInfoOwner.setGoodsCode(StringUtils.getRandomCode("SP"));
            return toAjax(goodsInfoOwnerService.insertGoodsInfoOwner(goodsInfoOwner));
        }
    }

    /**
     * APP修改业户商品建档
     */
    @ApiOperation("内库商品修改")
    @Log(title = "APP业户商品建档", businessType = BusinessType.UPDATE)
    @PutMapping("/appGoodsEdit")
    public AjaxResult appGoodsEdit(GoodsInfoOwner goodsInfoOwner)
    {
        GoodsInfoOwner info=goodsInfoOwnerService.selectGoodsInfoOwnerByName(goodsInfoOwner.getId(),goodsInfoOwner.getGoodsName(),goodsInfoOwner.getCreateBy());
        if(info!=null) {
            return  toAjaxByError("该商品在内库档案中已存在");
        }else{
            goodsInfoOwner.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(goodsInfoOwnerService.updateGoodsInfoOwner(goodsInfoOwner));
        }
    }

    /**
     * APP删除业户商品建档
     */
    @ApiOperation("内库商品删除")
    @Log(title = "APP业户商品建档", businessType = BusinessType.DELETE)
    @DeleteMapping("appGoodsRemove/{ids}")
    public AjaxResult appGoodsRemove(@PathVariable Integer[] ids)
    {
        return toAjax(goodsInfoOwnerService.deleteGoodsInfoOwnerByIds(ids));
    }

    /**
     * APP查询供应商建档列表
     */
    @ApiOperation("APP供应商档案")
    @GetMapping("/appPersonList/{createBy}")
    public TableDataInfo appPersonList(@PathVariable String createBy)
    {
        PersonInfo personInfo=new PersonInfo();
        personInfo.setCreateBy(createBy);
        List<PersonInfo> list = personInfoService.selectPersonInfoList(personInfo);
        return getDataTable(list);
    }

    /*APP接口功能*/
    /**
     * 查询进货单列表
     */
    @ApiOperation("APP进货单列表")
    @GetMapping("/appRkdList/{createBy}")
    public TableDataInfo appList(@PathVariable String createBy)
    {
        CgRkd cgRkd=new CgRkd();
        cgRkd.setCreateBy(createBy);
        List<CgRkd> list = cgRkdService.selectCgRkdList(cgRkd);
        return getDataTable(list);
    }
    /**
     * 获取进货单详细信息
     */
    @ApiOperation("APP进货单单个明细")
    @GetMapping(value = "appRkdInfo/{id}")
    public AjaxResult appRkdInfo(@PathVariable("id") String id)
    {
        CgRkd info=cgRkdService.selectCgRkdById(id);
        info.setChildrenList(cgRkdChildService.selectCgRkdChildByNumber(info.getDjNumber()));
        return AjaxResult.success(info);
    }

    /**
     * 获取进货单详细信息
     */
    @ApiOperation("APP进货单明细信息")
    @GetMapping(value = "appRkdChildList/{dj_number}")
    public AjaxResult appRkdChildList(@PathVariable("dj_number") String dj_number)
    {
        return AjaxResult.success(cgRkdChildService.selectCgRkdChildByNumber(dj_number));
    }

    /**
     * 根据摊位获取进货单详细信息
     */
    @ApiOperation("根据摊位编码获取APP进货单明细信息")
    @GetMapping(value = "appRkdChildListByStall/{stallCode}")
    public AjaxResult appRkdChildListByStall(@PathVariable("stallCode") String stallCode)
    {
        return AjaxResult.success(cgRkdChildService.appRkdChildListByStall(stallCode));
    }

    /**
     * APP新增进货单
     */
    @ApiOperation("APP进货单新增")
    @Log(title = "进货单", businessType = BusinessType.INSERT)
    @PostMapping("/appRkdAdd")
    public AjaxResult rkdAdd(CgRkd cgRkd)
    {
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setDjNumber(StringUtils.getRandomCode("RKD"));
        cgRkd.setStatus(0);
       // cgRkd.setCreateBy(SecurityUtils.getUsername());
        cgRkd.setId(StringUtils.getId());
        List<CgRkdChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdChild.class);
        for(CgRkdChild child:childList){
            child.setCreateBy(cgRkd.getCreateBy());
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
    @Log(title = "进货单", businessType = BusinessType.UPDATE)
    @ApiOperation("APP进货单修改")
    @PostMapping("/appRkdEdit")
    public AjaxResult rkdEdit(CgRkd cgRkd)
    {
        //检查是否为已生效的合同
        if(cgRkd.getStatus()==1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setUpdateBy(cgRkd.getCreateBy());
        List<CgRkdChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdChild.class);
        for(CgRkdChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(cgRkd.getCreateBy());
                child.setDjNumber(cgRkd.getDjNumber());
                cgRkdChildService.updateCgRkdChild(child);
            }else{
                child.setCreateBy(cgRkd.getCreateBy());
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
    @ApiOperation("APP进货单删除")
    @Log(title = "进货单", businessType = BusinessType.DELETE)
    @DeleteMapping("appRkdRemove/{ids}")
    public AjaxResult rkdRemove(@PathVariable String[] ids)
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
     * 根据字典类型查询字典数据信息
     */
    @ApiOperation("APP字典档案")
    @GetMapping(value = "/dictType/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        return AjaxResult.success(dictDataService.selectDictDataByType(dictType));
    }
}
