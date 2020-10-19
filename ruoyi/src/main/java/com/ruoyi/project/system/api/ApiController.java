package com.ruoyi.project.system.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.code.MatrixToImageWriter;
import com.ruoyi.common.utils.file.FileToBase64;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.push.JPush;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.info.domain.FeeInfo;
import com.ruoyi.project.info.domain.FeeItem;
import com.ruoyi.project.info.service.IFeeInfoService;
import com.ruoyi.project.info.service.IFeeItemService;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private ICgRkdSingleService cgRkdSingleService;
    @Autowired
    private ICgRkdSingleChildService cgRkdSingleChildService;
    @Autowired
    private ICkInfoService ckInfoService;
    @Autowired
    private IAppInfoService appInfoService;
    @Autowired
    private IShopInfoOwnerService shopInfoOwnerService;
    @Autowired
    private IResultInfoService resultInfoService;
    @Autowired
    private ITestApplicationFormService testApplicationFormService;
    @Autowired
    private ITestApplicationFormChildService testApplicationFormChildService;
    @Autowired
    private IRandomInspectionInfoChildService randomInspectionInfoChildService;
    @Autowired
    private ISysNoticeService noticeService;
    @Autowired
    private IFeeItemService feeItemService;
    @Autowired
    private IFeeInfoService feeInfoService;
    /**
     * APP版本列表
     */
    @ApiOperation("APP版本列表")
    @GetMapping("/version")
    public AjaxResult version()
    {
        AppInfo appInfo=new AppInfo();
        appInfo.setType(0);
        //appInfo.setId();
        List<AppInfo> list = appInfoService.selectAppInfoList(appInfo);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("msg", "操作成功!");
        ajaxResult.put("code", 200);
        ajaxResult.put("data", list);
        return ajaxResult;
    }

    /**
     * 百大APP版本列表
     */
    @ApiOperation("APP版本列表")
    @GetMapping("/bversion/{type}/{role}")
    public AjaxResult bversion(@PathVariable int type,@PathVariable int role)
    {
        AppInfo appInfo=new AppInfo();
        appInfo.setType(type);
        appInfo.setId(Long.valueOf(role));
        List<AppInfo> list = appInfoService.selectAppInfoList(appInfo);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("msg", "操作成功!");
        ajaxResult.put("code", 200);
        ajaxResult.put("data", list);
        return ajaxResult;
    }

    /**
     * 查询费用项目
     */
    @ApiOperation("APP查询费用项目")
    @GetMapping("/appFeeItemList/{createBy}")
    public TableDataInfo appFeeItemList(@PathVariable String createBy)
    {
        FeeItem item=new FeeItem();
        item.setCreateBy(createBy);
        List<FeeItem> list = feeItemService.selectFeeItemList(item);
        return getDataTable(list);
    }

    /**
     * APP账夹列表
     */
    @ApiOperation("APP账夹列表")
    @GetMapping("/appFeeInfoList/{createBy}")
    public TableDataInfo appFeeInfoList(@PathVariable String createBy)
    {
        FeeInfo item=new FeeInfo();
        item.setCreateBy(createBy);
        List<FeeInfo> list = feeInfoService.selectFeeInfoList(item);
        return getDataTable(list);
    }

    /**
     * APP账夹单个明细
     */
    @ApiOperation("APP账夹单个明细")
    @GetMapping(value = "appFeeInfo/{id}")
    public AjaxResult appFeeInfo(@PathVariable("id") Long id)
    {
        FeeInfo info=feeInfoService.selectFeeInfoById(id);
        return AjaxResult.success(info);
    }

    /**
     * APP账夹新增/修改
     */
    @ApiOperation("APP账夹新增/修改")
    @Log(title = "APP账夹新增/修改", businessType = BusinessType.INSERT)
    @PostMapping("/appFeeInfoAdd")
    public AjaxResult appFeeInfoAdd(FeeInfo info)
    {
        info.setSource(1);
        if(info.getId()!=null&&info.getId()!=-1){
            info.setUpdateBy(info.getCreateBy());
            return toAjax(feeInfoService.updateFeeInfo(info));
        }else{
            return toAjax(feeInfoService.insertFeeInfo(info));

        }
    }

    /**
     * 删除账夹
     */
    @ApiOperation("APP删除账夹")
    @Log(title = "APP删除账夹", businessType = BusinessType.DELETE)
    @GetMapping("appFeeInfoRemove/{ids}")
    public AjaxResult appFeeInfoRemove(@PathVariable Long[] ids)
    {
        //删除信息
        int result=feeItemService.deleteFeeItemByIds(ids);
        if(result>0){
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }



    /**
     * 查询检测单列表
     */
    @ApiOperation("APP检测单列表")
    @GetMapping("/appRandomInspList/{createBy}/{status}")
    public TableDataInfo appRandomInspList(@PathVariable String createBy,@PathVariable Integer status)
    {
        RandomInspectionInfoChild item=new RandomInspectionInfoChild();
        item.setOwnerCode(createBy);
        if(status==-1){
            status=null;
        }else {
            item.setCheckResult(status);
        }
        List<RandomInspectionInfoChild> list =randomInspectionInfoChildService.selectRandomInspectionInfoChildList(item);
        return getDataTable(list);
    }

    /**
     * 查询检测申请单列表
     */
    @ApiOperation("APP检测申请单列表")
    @GetMapping("/appTestFormList/{createBy}/{status}")
    public TableDataInfo appTestFormList(@PathVariable String createBy,@PathVariable Integer status)
    {
        TestApplicationFormChild item=new TestApplicationFormChild();
        item.setCreateBy(createBy);
        item.setStatus(status);
        List<TestApplicationFormChild> list = testApplicationFormChildService.selectTestApplicationForm(item);
        return getDataTable(list);
    }

    /**
     * 获取APP检测申请单详细信息
     */
    @ApiOperation("APP检测申请单列表单个明细")
    @GetMapping(value = "appTestFormInfo/{id}")
    public AjaxResult appTestFormInfo(@PathVariable("id") Integer id)
    {
        TestApplicationForm info=testApplicationFormService.selectTestApplicationFormById(id);
        TestApplicationFormChild TestApplicationFormChild=new TestApplicationFormChild();
        TestApplicationFormChild.setDjNumber(info.getDjNumber());
        info.setChildrenList(testApplicationFormChildService.selectTestApplicationFormChildList(TestApplicationFormChild));
        TestApplicationFormChild=null;
        return AjaxResult.success(info);
    }

    /**
     * APP检测申请单新增
     */
    @ApiOperation("APP检测申请单新增(单)")
    @Log(title = "APP检测申请单新增(单)", businessType = BusinessType.INSERT)
    @PostMapping("/appTestFormAdd")
    public AjaxResult appTestFormAdd(TestApplicationForm cgRkd)
    {
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        List<TestApplicationFormChild> childList= JSONArray.parseArray(cgRkd.getRows(),TestApplicationFormChild.class);
        if(cgRkd.getId()!=null&&cgRkd.getId()!=-1&&!"".equals(cgRkd.getId())){
            if(cgRkd.getStatus()!=null&&cgRkd.getStatus()==1){
                return AjaxResult.error("该状态禁止修改!");
            }
            cgRkd.setUpdateBy(cgRkd.getCreateBy());
            //删除明细
            for(TestApplicationFormChild child:childList){
                if(child.getId()!=""){
                    testApplicationFormChildService.deleteTestApplicationFormChildById(child.getId());
                }
            }
            for(TestApplicationFormChild child:childList){
                child.setCreateBy(cgRkd.getCreateBy());
                child.setId(StringUtils.getId());
                child.setDjNumber(cgRkd.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                testApplicationFormChildService.insertTestApplicationFormChild(child);
            }
            return toAjax(testApplicationFormService.updateTestApplicationForm(cgRkd));
        }else{
            cgRkd.setDjNumber(StringUtils.getRandomCode("TAF"));
            cgRkd.setStatus(0);
            for(TestApplicationFormChild child:childList){
                child.setCreateBy(cgRkd.getCreateBy());
                child.setId(StringUtils.getId());
                child.setDjNumber(cgRkd.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                testApplicationFormChildService.insertTestApplicationFormChild(child);
            }
            return toAjax(testApplicationFormService.insertTestApplicationForm(cgRkd));

        }
    }

    /**
     * 删除检测申请单
     */
    @ApiOperation("APP删除检测申请单")
    @Log(title = "APP删除检测申请单", businessType = BusinessType.DELETE)
    @GetMapping("appTestFormRemove/{ids}")
    public AjaxResult appTestFormRemove(@PathVariable Integer[] ids)
    {
        for(int i=0;i<ids.length;i++){
            TestApplicationForm info = testApplicationFormService.selectTestApplicationFormById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=testApplicationFormService.deleteTestApplicationFormByPid(ids);
        if(result>0){
            testApplicationFormService.deleteTestApplicationFormByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }



    /**
     * 查询采购单列表
     */
    @ApiOperation("APP单采购单列表")
    @GetMapping("/appRkdSingleList/{createBy}")
    public TableDataInfo appRkdSingleList(@PathVariable String createBy)
    {
        CgRkdSingle cgRkd=new CgRkdSingle();
        cgRkd.setCreateBy(createBy);
        List<CgRkdSingle> list = cgRkdSingleService.selectCgRkdSingleList(cgRkd);
        return getDataTable(list);
    }
    /**
     * 获取采购单详细信息
     */
    @ApiOperation("APP采购单单个明细")
    @GetMapping(value = "appRkdSingleInfo/{id}")
    public AjaxResult appRkdSingleInfo(@PathVariable("id") String id)
    {
        CgRkdSingle info=cgRkdSingleService.selectCgRkdSingleById(id);
        info.setChildrenList(cgRkdSingleChildService.selectCgRkdSingleChildByNumber(info.getDjNumber()));
        return AjaxResult.success(info);
    }

    /**
     * 获取采购单详细信息
     */
    @ApiOperation("APP采购单明细信息")
    @GetMapping(value = "appRkdSingleChildList/{dj_number}")
    public AjaxResult appRkdSingleChildList(@PathVariable("dj_number") String dj_number)
    {
        return AjaxResult.success(cgRkdSingleChildService.selectCgRkdSingleChildByNumber(dj_number));
    }


    /**
     * APP新增采购单
     */
    @ApiOperation("APP采购单新增(单)")
    @Log(title = "采购单新增(单)", businessType = BusinessType.INSERT)
    @PostMapping("/appRkdSingleAdd")
    public AjaxResult appRkdSingleAdd(CgRkdSingle cgRkd)
    {
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setDjNumber(StringUtils.getRandomCode("PO"));
        cgRkd.setStatus(0);
        // cgRkd.setCreateBy(SecurityUtils.getUsername());
        cgRkd.setId(StringUtils.getId());
        List<CgRkdSingleChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdSingleChild.class);
        for(CgRkdSingleChild child:childList){
            if("".equals(child.getGoodsRate())){
                child.setGoodsRate("0");
            }
            child.setCreateBy(cgRkd.getCreateBy());
            child.setId(StringUtils.getId());
            child.setDjNumber(cgRkd.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            cgRkdSingleChildService.insertCgRkdSingleChild(child);
        }
        return toAjax(cgRkdSingleService.insertCgRkdSingle(cgRkd));
    }

    /**
     * 修改采购单(单)
     */
    @Log(title = "采购单(单)", businessType = BusinessType.UPDATE)
    @ApiOperation("APP采购单修改(单)")
    @PostMapping("/appRkdSingleEdit")
    public AjaxResult appRkdSingleEdit(CgRkdSingle cgRkd)
    {
        //检查是否为已生效的合同
        if(cgRkd.getStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setUpdateBy(cgRkd.getCreateBy());
        List<CgRkdSingleChild> childLists= cgRkdSingleChildService.selectCgRkdSingleChildByNumber(cgRkd.getDjNumber());
        List<CgRkdSingleChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdSingleChild.class);
        //删除明细
        for(CgRkdSingleChild child:childLists){
            if(child.getId()!=""){
                cgRkdSingleChildService.deleteCgRkdSingleChildById(child.getId());
            }
        }
        for(CgRkdSingleChild child:childList){
            if("".equals(child.getGoodsRate())){
                child.setGoodsRate("0");
            }
//            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
//                child.setCreateBy(cgRkd.getCreateBy());
//                child.setDjNumber(cgRkd.getDjNumber());
//                cgRkdSingleChildService.updateCgRkdSingleChild(child);
//            }else{
                child.setCreateBy(cgRkd.getCreateBy());
                child.setId(StringUtils.getId());
                child.setDjNumber(cgRkd.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                cgRkdSingleChildService.insertCgRkdSingleChild(child);
        //    }
        }
        //取差集
//        childLists.removeAll(childList);
//        //删除不存在
//        for(CgRkdSingleChild child:childLists){
//            if(child.getId()!=""){
//                cgRkdSingleChildService.deleteCgRkdSingleChildById(child.getId());
//            }
//        }
        return toAjax(cgRkdSingleService.updateCgRkdSingle(cgRkd));
    }

    /**
     * 删除进货单
     */
    @ApiOperation("APP进货单删除")
    @Log(title = "进货单", businessType = BusinessType.DELETE)
    @GetMapping("appRkdSingleRemove/{ids}")
    public AjaxResult appRkdSingleRemove(@PathVariable String[] ids)
    {
        for(int i=0;i<ids.length;i++){
            CgRkdSingle info = cgRkdSingleService.selectCgRkdSingleById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        int result=cgRkdSingleChildService.deleteCgRkdSingleChildByPid(ids);
        if(result>0){
            cgRkdSingleService.deleteCgRkdSingleByIds(ids);
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }

    /**
     * 提交单供应商采购单
     */
    @ApiOperation("APP单供应商采购单提交")
    @Log(title = "APP单供应商采购单提交", businessType = BusinessType.DELETE)
    @GetMapping("appRkdSingleSub/{ids}")
    public AjaxResult appRkdSingleSub(@PathVariable String[] ids)
    {
        boolean lag=true;
        List<String> ownerCodeList=new ArrayList<>();
        for(int i=0;i<ids.length;i++){
            CgRkdSingle info = cgRkdSingleService.selectCgRkdSingleById(ids[i]);
            ownerCodeList.add(info.getPersonCode());
            if(info.getStatus()!=0){
                lag=false;
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止提交!");
            }
        }
        int result=cgRkdSingleService.updateCgRkdSingleStatus(ids);
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

    /**
     * 删除采购单明细
     */
    @ApiOperation("APP采购单明细删除")
    @Log(title = "APP采购单明细删除", businessType = BusinessType.DELETE)
    @GetMapping("cgSingleChildRemove/{ids}")
    public AjaxResult cgSingleChildRemove(@PathVariable String[] ids)
    {
        //删除子表信息
        int result=cgRkdSingleChildService.deleteCgRkdSingleChildByIds(ids);
        if(result>0){
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }

    }

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
     * 门店信息列表
     * @param createBy
     * @return
     */
    @ApiOperation("查询门店信息")
    @GetMapping("/appShopList/{createBy}")
    public TableDataInfo appShopList(@PathVariable("createBy") String createBy)
    {
        ShopInfoOwner info=new ShopInfoOwner();
        info.setCreateBy(createBy);
        List<ShopInfoOwner> list = shopInfoOwnerService.selectShopInfoOwnerList(info);
        info=null;
        return getDataTable(list);
    }

    /**
     * 业户信息列表
     * @param ownerName
     * @param ownerCode
     * @return
     */
    @ApiOperation("查询业户信息")
    @GetMapping("/appOwnerList/{ownerName}/{ownerCode}")
    public TableDataInfo appOwnerList(@PathVariable("ownerName") String ownerName,@PathVariable("ownerCode") String ownerCode)
    {
        OwnerInfo info=new OwnerInfo();
        if(!"-1".equals(ownerName)){
            info.setOwnerName(ownerName);
        }
        if(!"-1".equals(ownerCode)){
            info.setOwnerCode(ownerCode);
        }
        List<OwnerInfo> list = ownerInfoService.selectOwnerInfoList(info);
        info=null;
        return getDataTable(list);
    }


    /**
     * 仓库列表
     */
    @ApiOperation("选择仓库信息")
    @GetMapping("/appCkList/{createBy}")
    public TableDataInfo appCkList(@PathVariable("createBy") String createBy)
    {
        CkInfo ckInfo=new CkInfo();
       // ckInfo.setCreateBy(createBy);
        List<CkInfo> list = ckInfoService.selectCkInfoList(ckInfo);
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
        if(wholeSales.getStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(wholeSales.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        WholeSalesChild whole=new WholeSalesChild();
        whole.setDjNumber(wholeSales.getDjNumber());
        //List<WholeSalesChild> childLists=wholeSalesChildService.selectWholeSalesChildList(whole);
        List<WholeSalesChild> childList= JSONArray.parseArray(wholeSales.getRows(),WholeSalesChild.class);
        /*for(WholeSalesChild child:childLists){
            if(child.getId()!=""){
                wholeSalesChildService.deleteWholeSalesChildById(child.getId());
            }
        }*/
        String [] ids=new String[1];
        if(wholeSales.getId()!=null&&!"".equals(wholeSales.getId())){
            ids[0]=wholeSales.getId();
            wholeRetailChildService.deleteWholeRetailChildByPid(ids);
        }
        for(WholeSalesChild child:childList){
//            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
//                child.setCreateBy(wholeSales.getCreateBy());
//                child.setDjNumber(wholeSales.getDjNumber());
//                wholeSalesChildService.updateWholeSalesChild(child);
//            }else{
                child.setCreateBy(wholeSales.getCreateBy());
                child.setId(StringUtils.getId());
                child.setDjNumber(wholeSales.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                wholeSalesChildService.insertWholeSalesChild(child);
        //    }
        }
        //取差集
//        childLists.removeAll(childList);
//        //删除不存在
//        for(WholeSalesChild child:childLists){
//            if(child.getId()!=""){
//                wholeSalesChildService.deleteWholeSalesChildById(child.getId());
//            }
//        }
        whole=null;
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
     *
     *
     */
    @ApiOperation("APP销售单修改")
    @Log(title = "APP修改销售单", businessType = BusinessType.INSERT)
    @PostMapping("/appWholeRetailEdit")
    public AjaxResult appWholeRetailEdit(WholeRetail wholeRetail)
    {
        //检查是否为已生效的单据
        if(wholeRetail.getStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(wholeRetail.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        WholeRetailChild whole=new WholeRetailChild();
       // List<WholeRetailChild> childLists= wholeRetailChildService.selectWholeRetailChildList(whole);
        List<WholeRetailChild> childList= JSONArray.parseArray(wholeRetail.getRows(),WholeRetailChild.class);
        /*for(WholeRetailChild child:childLists){
            if(child.getId()!=""){
                wholeRetailChildService.deleteWholeRetailChildById(child.getId());
            }
        }*/
        String [] ids=new String[1];
        if(wholeRetail.getId()!=null&&!"".equals(wholeRetail.getId())){
            ids[0]=wholeRetail.getId();
            wholeRetailChildService.deleteWholeRetailChildByPid(ids);
        }
        for(WholeRetailChild child:childList){
//            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
//                child.setCreateBy(wholeRetail.getCreateBy());
//                child.setDjNumber(wholeRetail.getDjNumber());
//                wholeRetailChildService.updateWholeRetailChild(child);
//            }else{
                child.setCreateBy(wholeRetail.getCreateBy());
                child.setId(StringUtils.getId());
                child.setDjNumber(wholeRetail.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                wholeRetailChildService.insertWholeRetailChild(child);
        //    }
        }
        //取差集
//        childLists.removeAll(childList);
//        //删除不存在
//        for(WholeRetailChild child:childLists){
//            if(child.getId()!=""){
//                wholeRetailChildService.deleteWholeRetailChildById(child.getId());
//            }
//        }
        whole=null;
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
     * APP百大审核订单列表
     */
    @ApiOperation("APP百大审核订单列表")
    @GetMapping("/appRkdListByShStatus/{createBy}/{type}/{status}")
    public TableDataInfo appRkdListByShStatus(@PathVariable String createBy,@PathVariable Integer type,@PathVariable Integer status)
    {
        List<CgRkd> list = cgRkdService.selectCgRkdAllListByShStatus(createBy,type,status);
        return getDataTable(list);
    }


    /**
     * APP业户接收订单列表
     */
    @ApiOperation("APP业户接收订单列表")
    @GetMapping("/appRkdListByStatus/{createBy}/{type}")
    public TableDataInfo appRkdListByStatus(@PathVariable String createBy,@PathVariable Integer type)
    {
        List<CgRkd> list = cgRkdService.selectCgRkdAllListByStatus(createBy,type);
        return getDataTable(list);
    }

    /**
     * APP业户获取接收订单数量
     */
    @ApiOperation("APP业户待接收订单数量")
    @GetMapping("/appRkdByStatusCount/{createBy}")
    public AjaxResult appRkdByStatusCount(@PathVariable String createBy)
    {
        int count=cgRkdService.selectCgRkdByStatusCount(createBy);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("msg", "操作成功!");
        ajaxResult.put("code", 200);
        ajaxResult.put("rows", count);
        return ajaxResult;
    }

    /**
     * APP百大获取接收订单数量
     */
    @ApiOperation("APP百大待审核订单数量")
    @GetMapping("/appRkdByShStatusCount/{createBy}")
    public AjaxResult appRkdByShStatusCount(@PathVariable String createBy)
    {
        int count=cgRkdService.selectCgRkdByShStatusCount(createBy);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("msg", "操作成功!");
        ajaxResult.put("code", 200);
        ajaxResult.put("rows", count);
        return ajaxResult;
    }

    /**
     * APP业户接收订单列表明细信息
     */
    @ApiOperation("APP业户接收订单列表明细信息")
    @GetMapping("/appRkdChildListByStatus/{id}/{type}")
    public TableDataInfo appRkdChildListByStatus(@PathVariable String id,@PathVariable Integer type)
    {
        //单供应商
        if(type==0){
            CgRkdSingle CgRkd=cgRkdSingleService.selectCgRkdSingleById(id);
            return getDataTable(cgRkdSingleChildService.selectCgRkdSingleChildByNumber(CgRkd.getDjNumber()));
        }else{
            CgRkdChild cgRkdChild= cgRkdChildService.selectCgRkdChildById(id);
            List<CgRkdChild> CgRkdChild=new ArrayList<>();
            CgRkdChild.add(cgRkdChild);
            return getDataTable(CgRkdChild);
        }
    }

    /**
     * APP查看拒绝原因
     */
    @ApiOperation("APP查看拒绝原因")
    @GetMapping("/appSelectResultInfo/{djId}/{type}")
    public TableDataInfo appSelectResultInfo(@PathVariable String djId,@PathVariable Integer type)
    {
      ResultInfo info=new ResultInfo();
      info.setDjId(djId);
      info.setType(type);
      return getDataTable(resultInfoService.selectResultInfoList(info));
    }

    /**
     * 业户接收订单
     */
    @Log(title = "接收订单", businessType = BusinessType.UPDATE)
    @ApiOperation("APP接收订单")
    @PostMapping("/appRkdReceive")
    public AjaxResult appRkdReceive(CgRkd cgRkd)
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
                   /* CgRkdSingleChild CgRkdChild=new CgRkdSingleChild();
                    CgRkdChild.setGoodsAddress(child.getGoodsAddress());
                    CgRkdChild.setGoodsPrice(child.getGoodsPrice());
                    CgRkdChild.setId(child.getId());*/
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
                    /*CgRkdChild CgRkdChild=new CgRkdChild();
                    CgRkdChild.setGoodsAddress(child.getGoodsAddress());
                    CgRkdChild.setGoodsPrice(child.getGoodsPrice());
                    CgRkdChild.setId(child.getId());*/
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
     * 确认订单
     */
    @Log(title = "确认订单", businessType = BusinessType.UPDATE)
    @ApiOperation("APP确认订单")
    @PostMapping("/appRkdConfirm")
    public AjaxResult appRkdConfirm(CgRkd cgRkd)
    {
        try{
            //单供应商
            if(cgRkd.getType()==0){
                //修改订单状态
                CgRkdSingle CgRkdSingle=new CgRkdSingle();
                CgRkdSingle.setDjNumber(cgRkd.getDjNumber());
                CgRkdSingle.setStatus(3);
                cgRkdSingleService.updateCgRkdSingleStatusByNumber(CgRkdSingle);
                //修改明细产地跟单价
               /* List<CgRkdSingleChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdSingleChild.class);
                for(CgRkdSingleChild child:childList){
                    CgRkdSingleChild CgRkdChild=new CgRkdSingleChild();
                    CgRkdChild.setGoodsAddress(child.getGoodsAddress());
                    CgRkdChild.setGoodsPrice(child.getGoodsPrice());
                    CgRkdChild.setId(child.getId());
                    cgRkdSingleChildService.updateCgRkdSingleChild(CgRkdChild);
                }*/
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
    @ApiOperation("APP拒绝确认订单")
    @PostMapping("/appRkdCancel")
    public AjaxResult appRkdCancel(ResultInfo resultInfo)
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

    /**
     * 拒绝接收订单
     */
    @Log(title = "APP拒绝接收订单", businessType = BusinessType.UPDATE)
    @ApiOperation("APP拒绝接收订单")
    @PostMapping("/appRkdRefuse")
    public AjaxResult appRkdRefuse(ResultInfo resultInfo)
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
     * 获取通知公告列表
     */
    @ApiOperation("获取通知公告列表")
    @GetMapping("/selectNoticeList")
    public TableDataInfo list(SysNotice notice)
    {
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }
    /**
     * 市平台查询所有进货单列表
     */
    @ApiOperation("市平台查询所有进货单列表")
    @GetMapping("/rkdAllListBySpt")
    public TableDataInfo rkdAllListBySpt(CgRkd cgRkd, HttpServletResponse response)
    {
        startPage();
        List<CgRkd> list = cgRkdService.selectCgRkdAllListBySpt(cgRkd);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return getDataTable(list);
    }
    /**
     *市平台查询周公河数量信息
     */
    @ApiOperation("市平台查询周公河数量信息")
    @GetMapping("/selectMatkIndexNum")
    public AjaxResult selectMatkIndexNum()
    {
        CgRkd rkd=cgRkdService.selectMatkIndexNum();
        HashMap map=new HashMap();
        map.put("ownerCount",rkd.getDjStatusName());
        map.put("goodsCount",rkd.getGoodsName());
        map.put("rkdCount",rkd.getCreateName());
        map.put("addressCount",rkd.getPersonName());
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("msg", "操作成功!");
        ajaxResult.put("code", 200);
        ajaxResult.put("rows", map);
        return ajaxResult;
    }

    /**
     *查询周公河收支信息
     */
    @ApiOperation("市平台查询周公河数量信息")
    @GetMapping("/selectMatkSum/{createBy}/{createTime}")
    public AjaxResult selectMatkSum(@PathVariable String createBy,@PathVariable String createTime)
    {
        CgRkd rkd=cgRkdService.selectMatkSum(createBy,createTime);
        HashMap map=new HashMap();
        map.put("incomeNum",rkd.getDjStatusName());//收入
        map.put("expendNum",rkd.getGoodsName());//支出
        map.put("profitNum",Float.parseFloat(rkd.getDjStatusName()==null?"0":rkd.getDjStatusName())-
                Float.parseFloat(rkd.getGoodsName()==null?"0":rkd.getGoodsName()));//利润
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("msg", "操作成功!");
        ajaxResult.put("code", 200);
        ajaxResult.put("data", map);
        return ajaxResult;
    }

    /**
     * 市平台查询业户主营商品列表
     */
    @ApiOperation("市平台查询业户商品列表")
    @GetMapping("/ownerAllGoodsList/{createBy}")
    public TableDataInfo ownerAllGoodsList(@PathVariable("createBy") String createBy, HttpServletResponse response)
    {
        GoodsInfoOwner goodsInfoOwner=new GoodsInfoOwner();
        goodsInfoOwner.setCreateBy(createBy);
        List<GoodsInfoOwner> list = goodsInfoOwnerService.selectGoodsInfoOwnerList(goodsInfoOwner);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return getDataTable(list);
    }

    /**
     * 市平台查询业户主营商品列表
     */
    @ApiOperation("市平台查询业户商品列表")
    @GetMapping("/ownerAllGoodsOwnerList")
    public TableDataInfo ownerAllGoodsOwnerList(GoodsInfoOwner goodsInfoOwner,HttpServletResponse response)
    {
        startPage();
        List<GoodsInfoOwner> list = goodsInfoOwnerService.selectGoodsInfoOwnerListBySpt(goodsInfoOwner);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return getDataTable(list);
    }
    /**
     * 市平台查询业户信息列表
     */
    @ApiOperation("市平台查询业户信息")
    @GetMapping("/ownerAllList")
    public TableDataInfo ownerAllList(OwnerInfo ownerInfo, HttpServletResponse response) {
        startPage();
        List<OwnerInfo> list = ownerInfoService.selectOwnerStallInfoList(ownerInfo);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
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
     * 获取所有进货单详细信息
     */
    @ApiOperation("获取所有多供应商采购单详细信息")
    @GetMapping(value = "appRkdChildListByAll/{createBy}")
    public TableDataInfo appRkdChildListByAll(@PathVariable("createBy") String createBy)
    {
        return getDataTable(cgRkdChildService.selectCgRkdChildByUser(createBy));
    }
    /**
     * 获取所有进货单详细信息
     */
    @ApiOperation("获取所有单供应商采购单详细信息")
    @GetMapping(value = "appRkdSingleChildListByAll/{createBy}")
    public TableDataInfo appRkdSingleChildListByAll(@PathVariable("createBy") String createBy)
    {
        List<CgRkdSingleChild> list=cgRkdSingleChildService.selectCgRkdSingleChildByUser(createBy);
        return getDataTable(list);
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
            if("".equals(child.getGoodsRate())){
                child.setGoodsRate("0");
            }
            child.setCreateBy(cgRkd.getCreateBy());
            child.setId(StringUtils.getId());
            child.setDjNumber(cgRkd.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            cgRkdChildService.insertCgRkdChild(child);
        }
        int result=cgRkdService.insertCgRkd(cgRkd);
        if(result>0){
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.put("msg", "操作成功!");
            ajaxResult.put("code", 200);
            ajaxResult.put("data", cgRkd.getId());
            return  ajaxResult;
        }else{
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.put("msg", "操作失败!");
            ajaxResult.put("code", 500);
            ajaxResult.put("data", "");
            return  ajaxResult;
        }
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
        if(cgRkd.getStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(cgRkd.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgRkd.setUpdateBy(cgRkd.getCreateBy());
        List<CgRkdChild> childLists=cgRkdChildService.selectCgRkdChildByNumber(cgRkd.getDjNumber());
        List<CgRkdChild> childList= JSONArray.parseArray(cgRkd.getRows(),CgRkdChild.class);
        //删除明细
        for(CgRkdChild child:childLists){
            if(child.getId()!=""){
                cgRkdChildService.deleteCgRkdChildById(child.getId());
            }
        }
        for(CgRkdChild child:childList){
            if("".equals(child.getGoodsRate())){
                child.setGoodsRate("0");
            }
//            if(child.getId()!=""&&child.getId()!=null&&!"".equals(child.getId())){
//                child.setCreateBy(cgRkd.getCreateBy());
//                child.setDjNumber(cgRkd.getDjNumber());
//                cgRkdChildService.updateCgRkdChild(child);
//            }else{
                child.setCreateBy(cgRkd.getCreateBy());
                child.setId(StringUtils.getId());
                child.setDjNumber(cgRkd.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                cgRkdChildService.insertCgRkdChild(child);
          //  }
        }
//        //取差集
//        childLists.removeAll(childList);
//        //删除不存在
//        for(CgRkdChild child:childLists){
//            if(child.getId()!=""){
//                cgRkdChildService.deleteCgRkdChildById(child.getId());
//            }
//        }
        int result=cgRkdService.updateCgRkd(cgRkd);
        if(result>0){
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.put("msg", "操作成功!");
            ajaxResult.put("code", 200);
            ajaxResult.put("data", cgRkd.getId());
            return  ajaxResult;
        }else{
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.put("msg", "操作失败!");
            ajaxResult.put("code", 500);
            ajaxResult.put("data", "");
            return  ajaxResult;
        }
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
     * 提交进货单
     */
    @ApiOperation("APP进货单提交")
    @Log(title = "APP进货单提交", businessType = BusinessType.DELETE)
    @GetMapping("appRkdSub/{ids}")
    public AjaxResult appRkdSub(@PathVariable String[] ids)
    {
        boolean lag=true;
        List<String> ownerCodeList=new ArrayList<>();
        List<String> numberList=new ArrayList<>();
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            CgRkd info = cgRkdService.selectCgRkdById(ids[i]);
            numberList.add(info.getDjNumber());
            if(info.getStatus()!=0){
                lag=false;
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
        if(result>0){
            //进行消息推送
            if(lag){
                for(String info:ownerCodeList){
                    Map<String, String> parm=new HashMap<>();
                    parm.put("id",info);
                    parm.put("msg","您有新的订单待接收!");
                    JPush.jpushAndroid(parm,0);
                    JPush.jpushIOS(parm,0);
                }
            }
            return toAjaxBySuccess("提交成功!");
        }else{
            return  toAjaxByError("提交失败!");
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
                    String code=(String)ExcelUtil.getCellFormatValue(row.getCell(1)).toString();
                    /*//添加摊位信息
                    StallInfo info=new StallInfo();

                    info.setCreateBy(code);
                    info.setStallName((String)ExcelUtil.getCellFormatValue(row.getCell(2)));
                    info.setId(StringUtils.getId());
                    info.setStallCode(StringUtils.getRandomCode("STL"));
                    info.setCreateTime(DateUtils.getNowDate());
                    info.setStallStatus("1");
                    stallInfoService.insertStallInfo(info);

                    //添加默认供应商信息
                    PersonInfo personInfo=new PersonInfo();
                    personInfo.setCreateBy(code);
                    personInfo.setPersonName("测试供应商"+"("+code+")");
                    personInfo.setPersonCode(StringUtils.getRandomCode("PCM"));
                    personInfo.setPersonGoodsAddress("山东省聊城市东昌府区");
                    personInfo.setPersonAddress("山东省聊城市东昌府区");
                    personInfoService.insertPersonInfo(personInfo);
                    //添加默认客户信息
                    KhInfo khInfo=new KhInfo();
                    khInfo.setCreateBy(code);
                    khInfo.setKhName("测试客户"+"("+code+")");
                    khInfo.setKhAddress("山东省聊城市东昌府区");
                    khInfo.setKhCode(StringUtils.getRandomCode("KH"));
                    khInfoService.insertKhInfo(khInfo);
                    //添加默认商品信息
                    GoodsInfoOwner goodsInfoOwner=new GoodsInfoOwner();
                    goodsInfoOwner.setCreateBy("lgh");
                    goodsInfoOwner.setGoodsCode(StringUtils.getRandomCode("SP"));
                    goodsInfoOwner.setGoodsDw((String)ExcelUtil.getCellFormatValue(row.getCell(3)).toString());
                    goodsInfoOwner.setGoodsNbCode((String)ExcelUtil.getCellFormatValue(row.getCell(1)).toString());
                    goodsInfoOwner.setGoodsSptCode((String)ExcelUtil.getCellFormatValue(row.getCell(2)).toString());
                    goodsInfoOwner.setIsSend(0);
                    goodsInfoOwner.setGoodsName((String)ExcelUtil.getCellFormatValue(row.getCell(0)).toString());
                    goodsInfoOwner.setGoodsViceDw("公斤");
                    goodsInfoOwner.setGoodsAddress((String)ExcelUtil.getCellFormatValue(row.getCell(7)).toString());
                    goodsInfoOwner.setGoodsGg((String)ExcelUtil.getCellFormatValue(row.getCell(4)).toString());
                    goodsInfoOwnerService.insertGoodsInfoOwner(goodsInfoOwner);*/

                    /*CarInfo carInfo=new CarInfo();
                    carInfo.setCarNumber("鲁P88888("+code+"测试)");
                    carInfo.setCreateBy(SecurityUtils.getUsername());
                    carInfoService.insertCarInfo(carInfo);*/
                    //String code=(String)ExcelUtil.getCellFormatValue(row.getCell(1)).toString();
                    if(code==""||"".equals(code)){
                        break;
                    }
                    OwnerInfo infos = ownerInfoService.selectOwnerInfoByCode(code, "");
                    if (infos != null) {
                        continue;
                    }
                    OwnerInfo ownerInfo=new OwnerInfo();
                    ownerInfo.setCreateBy("admin");
                    ownerInfo.setId(StringUtils.getId());
                    ownerInfo.setOwnerCode(code);
                    ownerInfo.setUserName(code);
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
                    user.setUserName(code);
                    user.setPassword(SecurityUtils.encryptPassword("123"));
                    user.setPhonenumber((String)ExcelUtil.getCellFormatValue(row.getCell(6)));
                    user.setNickName((String)ExcelUtil.getCellFormatValue(row.getCell(3)));
                    user.setCreateBy("admin");
                    userService.insertUser(user);
                }else{
                    break;
                }
            }
        }
        System.out.println("导入完成!");
        return AjaxResult.success("导入成功");
    }


    /**
     * 导入功能测试
     */
    @ApiOperation("导入销货单")
    @GetMapping(value = "/importXhd")
    public AjaxResult importXhd()
    {
        System.out.println("1!");
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        String filePath = "D:\\test123.xlsx";
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
            for (int i = 3; i<rownum; i++) {
                System.out.println(i);
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    String code=(String)ExcelUtil.getCellFormatValue(row.getCell(1)).toString();
                    if(code==""||"".equals(code)){
                        break;
                    }
                    GoodsInfoOwner goodsInfoOwner=new GoodsInfoOwner();
                    goodsInfoOwner.setCreateBy("20101");

                    goodsInfoOwner.setGoodsName((String)ExcelUtil.getCellFormatValue(row.getCell(3)).toString());
                    GoodsInfoOwner info=goodsInfoOwnerService.selectGoodsInfoOwnerByName(-1,goodsInfoOwner.getGoodsName(),goodsInfoOwner.getCreateBy());
                    if(info!=null) {
                        continue;
                    }else{
                        goodsInfoOwner.setGoodsDw("公斤");
                        goodsInfoOwner.setGoodsAssistDw("公斤");
                        goodsInfoOwner.setGoodsViceDw("斤");
                        goodsInfoOwner.setGoodsAddress("聊城");
                        goodsInfoOwner.setGoodsCode(StringUtils.getRandomCode("SP"));
                        goodsInfoOwnerService.insertGoodsInfoOwner(goodsInfoOwner);
                    }
                }else{
                    break;
                }
            }
        }
        System.out.println("导入完成!");
        return AjaxResult.success("导入成功");
    }
}
