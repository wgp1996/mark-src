package com.ruoyi.project.system.api;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.code.MatrixToImageWriter;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private ISysUserService userService;
    @Autowired
    private IOwnerInfoService ownerInfoService;
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
    @Autowired
    private IWholeSalesService wholeSalesService;
    @Autowired
    private IWholeSalesChildService wholeSalesChildService;
    @Autowired
    private IWholeRetailService wholeRetailService;
    @Autowired
    private IWholeRetailChildService wholeRetailChildService;
    @Autowired
    private IKhInfoService khInfoService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ICarInfoService carInfoService;
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
                map.put("pid",info.getGoodsType());
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
        }else {
            goodsInfoOwner.setGoodsCode(StringUtils.getRandomCode("SP"));
            int result = goodsInfoOwnerService.insertGoodsInfoOwner(goodsInfoOwner);
            if (result > 0) {
                AjaxResult ajaxResult = new AjaxResult();
                ajaxResult.put("msg", "操作成功!");
                ajaxResult.put("code", 200);
                ajaxResult.put("data", goodsInfoOwner);
                return ajaxResult;
            } else{
                return toAjaxByError("操作失败!");
            }
        }
    }

    /**
     * APP客户建档
     */
    @ApiOperation("APP客户建档")
    @Log(title = "APP客户建档", businessType = BusinessType.INSERT)
    @PostMapping("/appKhAdd")
    public AjaxResult appKhAdd(KhInfo khInfo)
    {
        KhInfo info=khInfoService.selectKhInfoByName(khInfo.getKhName(),khInfo.getCreateBy(),-1);
        if(info!=null) {
            return  toAjaxByError("该客户在系统中已存在");
        }else {
            khInfo.setKhCode(StringUtils.getRandomCode("KH"));
            int result=khInfoService.insertKhInfo(khInfo);
            if(result>0){
                AjaxResult ajaxResult = new AjaxResult();
                ajaxResult.put("msg", "操作成功!");
                ajaxResult.put("code", 200);
                ajaxResult.put("data", khInfo);
                return ajaxResult;
            }else{
                return toAjaxByError("操作失败!");
            }
        }
    }

    /**
     * APP供应商建档
     */
    @ApiOperation("APP供应商建档")
    @Log(title = "APP供应商建档", businessType = BusinessType.INSERT)
    @PostMapping("/appPersonAdd")
    public AjaxResult appPersonAdd(PersonInfo personInfo)
    {
        PersonInfo info=personInfoService.selectPersonInfoByName(personInfo.getPersonName(),personInfo.getCreateBy(),-1);
        if(info!=null) {
            return  toAjaxByError("该供应商在系统中已存在");
        }else {
            personInfo.setPersonCode(StringUtils.getRandomCode("PCM"));
            int result=personInfoService.insertPersonInfo(personInfo);
            if(result>0){
                AjaxResult ajaxResult = new AjaxResult();
                ajaxResult.put("msg", "操作成功!");
                ajaxResult.put("code", 200);
                ajaxResult.put("data", personInfo);
                return ajaxResult;
            }else{
                return toAjaxByError("操作失败!");
            }
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
    @GetMapping("appGoodsRemove/{ids}")
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

    /**
     * APP查询客户建档列表
     */
    @ApiOperation("APP客户档案")
    @GetMapping("/appKhList/{createBy}")
    public TableDataInfo appKhList(@PathVariable String createBy)
    {
        KhInfo khInfo=new KhInfo();
        khInfo.setCreateBy(createBy);
        List<KhInfo> list = khInfoService.selectKhInfoList(khInfo);
        return getDataTable(list);
    }

    /*APP接口功能*/

    /**
     * 查询销货单列表
     */
    @ApiOperation("APP销货单列表")
    @GetMapping("/appWholeSalesList/{createBy}")
    public TableDataInfo appWholeSalesList(@PathVariable String createBy)
    {
        WholeSales sales=new WholeSales();
        sales.setCreateBy(createBy);
        List<WholeSales> list = wholeSalesService.selectWholeSalesList(sales);
        return getDataTable(list);
    }

    /**
     * 条件查询销货单列表
     */
    @ApiOperation("APP销货单列表条件查询")
    @GetMapping("/appWholeSalesListByWhere/{createBy}/{dateType}/{date}/{goodsName}/{khName}")
    public TableDataInfo appWholeSalesListByWhere(@PathVariable String createBy,@PathVariable Integer dateType,@PathVariable String date,@PathVariable String goodsName,@PathVariable String khName)
    {
        List<WholeSalesChild> list = wholeSalesChildService.selectWholeSalesChildListByWhere(createBy,dateType,date,goodsName,khName);
        return getDataTable(list);
    }

    /**
     * 获取销货单详细信息
     */
    @ApiOperation("APP销货单单个明细")
    @GetMapping(value = "appWholeSalesInfo/{id}")
    public AjaxResult appWholeSalesInfo(@PathVariable("id") String id)
    {
        WholeSales info=wholeSalesService.selectWholeSalesById(id);
        if(info!=null){
            WholeSalesChild child=new WholeSalesChild();
            child.setDjNumber(info.getDjNumber());
            info.setChildrenList(wholeSalesChildService.selectWholeSalesChildList(child));
        }
        return AjaxResult.success(info);
    }

    /**
     * APP新增销货单
     */
    @ApiOperation("APP销货单新增")
    @Log(title = "APP销货单新增", businessType = BusinessType.INSERT)
    @PostMapping("/appWholeSalesAdd")
    public AjaxResult appWholeSalesAdd(WholeSales wholeSales)
    {
        if(wholeSales.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        wholeSales.setDjNumber(StringUtils.getRandomCode("LXH"));
        wholeSales.setStatus(0);
        wholeSales.setFrom(1);//app
        wholeSales.setId(StringUtils.getId());
        List<WholeSalesChild> childList= JSONArray.parseArray(wholeSales.getRows(),WholeSalesChild.class);
        for(WholeSalesChild child:childList){
            child.setCreateBy(wholeSales.getCreateBy());
            child.setId(StringUtils.getId());
            child.setDjNumber(wholeSales.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            wholeSalesChildService.insertWholeSalesChild(child);
        }
        return toAjax(wholeSalesService.insertWholeSales(wholeSales));
    }

    /**
     * APP修改销货单
     */
    @ApiOperation("APP销货单修改")
    @Log(title = "APP销货单修改", businessType = BusinessType.INSERT)
    @PostMapping("/appWholeSalesEdit")
    public AjaxResult appWholeSalesEdit(WholeSales wholeSales)
    {
        //检查是否为已生效的单据
        if(wholeSales.getStatus()==1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(wholeSales.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        List<WholeSalesChild> childList= JSONArray.parseArray(wholeSales.getRows(),WholeSalesChild.class);
        for(WholeSalesChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(wholeSales.getCreateBy());
                child.setDjNumber(wholeSales.getDjNumber());
                wholeSalesChildService.updateWholeSalesChild(child);
            }else{
                child.setCreateBy(wholeSales.getCreateBy());
                child.setId(StringUtils.getId());
                child.setDjNumber(wholeSales.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                wholeSalesChildService.insertWholeSalesChild(child);
            }
        }
        return toAjax(wholeSalesService.updateWholeSales(wholeSales));
    }

    /**
     * 删除批发销货单
     */
    @ApiOperation("APP销货单删除")
    @Log(title = "APP销货单删除", businessType = BusinessType.DELETE)
    @GetMapping("/appWholeSalesRemove/{ids}")
    public AjaxResult appWholeSalesRemove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            WholeSales info = wholeSalesService.selectWholeSalesById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=wholeSalesChildService.deleteWholeSalesChildByPid(ids);
        if(result>0){
            wholeSalesService.deleteWholeSalesByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }

    /**
     * 删除批发销货单明细
     */
    @ApiOperation("APP销货单明细删除")
    @Log(title = "APP销货单明细删除", businessType = BusinessType.DELETE)
    @GetMapping("/appWholeSalesChildRemove/{ids}")
    public AjaxResult appWholeSalesChildRemove(@PathVariable String[] ids)
    {
        //删除子表信息
        int result=wholeSalesChildService.deleteWholeSalesChildByIds(ids);
        if(result>0){
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }

    /**
     * 查询销售单列表
     */
    @ApiOperation("APP销售单列表")
    @GetMapping("/appWholeRetailList/{createBy}")
    public TableDataInfo appWholeRetailList(@PathVariable String createBy)
    {
        WholeRetail sales=new WholeRetail();
        sales.setCreateBy(createBy);
        List<WholeRetail> list = wholeRetailService.selectWholeRetailList(sales);
        return getDataTable(list);
    }
    /**
     * 条件查询销售单列表
     */
    @ApiOperation("APP销售单列表条件查询")
    @GetMapping("/appWholeRetailListByWhere/{createBy}/{dateType}/{date}/{goodsName}/{khName}")
    public TableDataInfo appWholeRetailListByWhere(@PathVariable String createBy,@PathVariable Integer dateType,@PathVariable String date,@PathVariable String goodsName,@PathVariable String khName)
    {
        List<WholeRetailChild> list = wholeRetailChildService.selectWholeRetailChildListByWhere(createBy,dateType,date,goodsName,khName);
        return getDataTable(list);
    }
    /**
     * 获取销售单详细信息
     */
    @ApiOperation("APP销售单单个明细")
    @GetMapping(value = "appWholeRetailInfo/{id}")
    public AjaxResult appWholeRetailInfo(@PathVariable("id") String id)
    {
        WholeRetail info=wholeRetailService.selectWholeRetailById(id);
        if(info!=null) {
            WholeRetailChild child = new WholeRetailChild();
            child.setDjNumber(info.getDjNumber());
            info.setChildrenList(wholeRetailChildService.selectWholeRetailChildList(child));
        }
        return AjaxResult.success(info);
    }

    /**
     * APP新增销售单
     */
    @ApiOperation("APP销售单新增")
    @Log(title = "APP新增销售单", businessType = BusinessType.INSERT)
    @PostMapping("/appWholeRetailAdd")
    public AjaxResult appWholeRetailAdd(WholeRetail wholeRetail)
    {
        if(wholeRetail.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        wholeRetail.setDjNumber(StringUtils.getRandomCode("LSH"));
        wholeRetail.setStatus(0);
        wholeRetail.setFrom(1);//web
        wholeRetail.setId(StringUtils.getId());
        List<WholeRetailChild> childList= JSONArray.parseArray(wholeRetail.getRows(),WholeRetailChild.class);
        for(WholeRetailChild child:childList){
            child.setCreateBy(wholeRetail.getCreateBy());
            child.setId(StringUtils.getId());
            child.setDjNumber(wholeRetail.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            wholeRetailChildService.insertWholeRetailChild(child);
        }
        return toAjax(wholeRetailService.insertWholeRetail(wholeRetail));
    }

    /**
     * APP修改销售单
     */
    @ApiOperation("APP销售单修改")
    @Log(title = "APP修改销售单", businessType = BusinessType.INSERT)
    @PostMapping("/appWholeRetailEdit")
    public AjaxResult appWholeRetailEdit(WholeRetail wholeRetail)
    {
        //检查是否为已生效的单据
        if(wholeRetail.getStatus()==1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(wholeRetail.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        List<WholeRetailChild> childList= JSONArray.parseArray(wholeRetail.getRows(),WholeRetailChild.class);
        for(WholeRetailChild child:childList){
            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
                child.setCreateBy(wholeRetail.getCreateBy());
                child.setDjNumber(wholeRetail.getDjNumber());
                wholeRetailChildService.updateWholeRetailChild(child);
            }else{
                child.setCreateBy(wholeRetail.getCreateBy());
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
    @ApiOperation("删除APP零售单")
    @Log(title = "APP零售单删除", businessType = BusinessType.DELETE)
    @GetMapping("/appWholeRetailRemove/{ids}")
    public AjaxResult appWholeRetailRemove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            WholeRetail info = wholeRetailService.selectWholeRetailById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=wholeRetailChildService.deleteWholeRetailChildByPid(ids);
        if(result>0){
            wholeRetailService.deleteWholeRetailByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }

    /**
     * 删除零售销货单明细
     */
    @ApiOperation("删除APP零售单明细")
    @Log(title = "APP零售单删除明细", businessType = BusinessType.DELETE)
    @GetMapping("/appWholeRetailChildRemove/{ids}")
    public AjaxResult appWholeRetailChildRemove(@PathVariable String[] ids)
    {
        //删除子表信息
        int result=wholeRetailChildService.deleteWholeRetailChildByIds(ids);
        if(result>0){
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
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
    @GetMapping("appRkdRemove/{ids}")
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
     * 删除进货单明细
     */
    @ApiOperation("APP进货单明细删除")
    @Log(title = "APP进货单明细删除", businessType = BusinessType.DELETE)
    @GetMapping("rkdChildRemove/{ids}")
    public AjaxResult rkdChildRemove(@PathVariable String[] ids)
    {
        //删除子表信息
        int result=cgRkdChildService.deleteCgRkdChildByIds(ids);
        if(result>0){
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

    /**
     * 导入功能测试
     */
    @ApiOperation("导入测试")
    @GetMapping(value = "/importOwner")
    public AjaxResult importOwner()
    {
        System.out.println("1!");
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        String filePath = "D:\\test.xlsx";
        wb = ExcelUtil.readExcel(filePath);
        if(wb != null){
            System.out.println("2!");
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 2; i<rownum; i++) {
                System.out.println(i);
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    //添加摊位信息
                    StallInfo info=new StallInfo();
                    String code=(String)ExcelUtil.getCellFormatValue(row.getCell(1)).toString();
                    info.setCreateBy(code.substring(0,code.indexOf(".")));
                    info.setStallName((String)ExcelUtil.getCellFormatValue(row.getCell(2)));
                    info.setId(StringUtils.getId());
                    info.setStallCode(StringUtils.getRandomCode("STL"));
                    info.setCreateTime(DateUtils.getNowDate());
                    info.setStallStatus("1");
                    stallInfoService.insertStallInfo(info);

                    //添加默认供应商信息
                    PersonInfo personInfo=new PersonInfo();
                    personInfo.setCreateBy(code.substring(0,code.indexOf(".")));
                    personInfo.setPersonName("测试供应商"+"("+code.substring(0,code.indexOf("."))+")");
                    personInfo.setPersonCode(StringUtils.getRandomCode("PCM"));
                    personInfo.setPersonGoodsAddress("山东省聊城市东昌府区");
                    personInfo.setPersonAddress("山东省聊城市东昌府区");
                    personInfoService.insertPersonInfo(personInfo);
                    //添加默认客户信息
                    KhInfo khInfo=new KhInfo();
                    khInfo.setCreateBy(code.substring(0,code.indexOf(".")));
                    khInfo.setKhName("测试客户"+"("+code.substring(0,code.indexOf("."))+")");
                    khInfo.setKhAddress("山东省聊城市东昌府区");
                    khInfo.setKhCode(StringUtils.getRandomCode("KH"));
                    khInfoService.insertKhInfo(khInfo);
                    //添加默认商品信息
                    GoodsInfoOwner goodsInfoOwner=new GoodsInfoOwner();
                    goodsInfoOwner.setCreateBy(code.substring(0,code.indexOf(".")));
                    goodsInfoOwner.setGoodsCode(StringUtils.getRandomCode("SP"));
                    goodsInfoOwner.setGoodsDw("斤");
                    goodsInfoOwner.setGoodsName("大虾("+code.substring(0,code.indexOf("."))+"测试)");
                    goodsInfoOwner.setGoodsViceDw("公斤");
                    goodsInfoOwner.setGoodsAddress("山东省聊城市东昌府区");
                    goodsInfoOwner.setGoodsGg("1");
                    goodsInfoOwnerService.insertGoodsInfoOwner(goodsInfoOwner);

                    /*CarInfo carInfo=new CarInfo();
                    carInfo.setCarNumber("鲁P88888("+code.substring(0,code.indexOf("."))+"测试)");
                    carInfo.setCreateBy(SecurityUtils.getUsername());
                    carInfoService.insertCarInfo(carInfo);*/
                   /* String code=(String)ExcelUtil.getCellFormatValue(row.getCell(1)).toString();
                    if(code==""||"".equals(code)){
                        break;
                    }
                    OwnerInfo info = ownerInfoService.selectOwnerInfoByCode(code.substring(0,code.indexOf(".")), "");
                    if (info != null) {
                        continue;
                    }
                    OwnerInfo ownerInfo=new OwnerInfo();
                    ownerInfo.setCreateBy("admin");
                    ownerInfo.setId(StringUtils.getId());
                    ownerInfo.setOwnerCode(code.substring(0,code.indexOf(".")));
                    ownerInfo.setUserName(code.substring(0,code.indexOf(".")));
                    ownerInfo.setOwnerName((String)ExcelUtil.getCellFormatValue(row.getCell(3)));
                    ownerInfo.setOwnerPersonId((String)ExcelUtil.getCellFormatValue(row.getCell(4)));
                    ownerInfo.setOwnerLxr((String)ExcelUtil.getCellFormatValue(row.getCell(5)));
                    ownerInfo.setOwnerLxrPhone((String)ExcelUtil.getCellFormatValue(row.getCell(6)));
                    ownerInfo.setCreateTime(DateUtils.getNowDate());
                    System.out.println(ownerInfo.getId());
                    int result=ownerInfoService.insertOwnerInfo(ownerInfo);

                    //添加默认帐号
                    SysUser user=new SysUser();
                    Long[] roleIds=new Long[1];
                    roleIds[0]=3L;
                    user.setDeptId(104L);
                    user.setRoleIds(roleIds);
                    user.setUserName(code.substring(0,code.indexOf(".")));
                    user.setPassword(SecurityUtils.encryptPassword("123"));
                    user.setPhonenumber((String)ExcelUtil.getCellFormatValue(row.getCell(6)));
                    user.setNickName((String)ExcelUtil.getCellFormatValue(row.getCell(3)));
                    user.setCreateBy("admin");
                    userService.insertUser(user);*/
                }else{
                    break;
                }
            }
        }
        System.out.println("导入完成!");
        return AjaxResult.success("导入成功");
    }
}
