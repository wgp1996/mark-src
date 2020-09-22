package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.code.MatrixToImageWriter;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.project.system.domain.GoodsInfo;
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
import com.ruoyi.project.system.domain.GoodsInfoOwner;
import com.ruoyi.project.system.service.IGoodsInfoOwnerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 业户商品建档Controller
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
@RestController
@RequestMapping("/system/ownerGoods")
public class GoodsInfoOwnerController extends BaseController
{
    @Autowired
    private IGoodsInfoOwnerService goodsInfoOwnerService;
    @Autowired
    private ServerConfig serverConfig;
    /**
     * 查询业户商品建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:ownerGoods:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(GoodsInfoOwner goodsInfoOwner)
    {
        startPage();
        List<GoodsInfoOwner> list = goodsInfoOwnerService.selectGoodsInfoOwnerList(goodsInfoOwner);
        return getDataTable(list);
    }
    /**
     * 根据业主查询业户商品建档列表
     */
    @GetMapping("/ownerGoodsList")
    public TableDataInfo ownerGoodsList(GoodsInfoOwner goodsInfoOwner)
    {
        startPage();
        List<GoodsInfoOwner> list = goodsInfoOwnerService.selectGoodsInfoOwnerList(goodsInfoOwner);
        return getDataTable(list);
    }

    /**
     * 根据业主查询业户商品建档下拉列表
     */

    @GetMapping("/goodsList")
    public TableDataInfo goodsList(GoodsInfoOwner goodsInfoOwner)
    {
        goodsInfoOwner.setCreateBy(SecurityUtils.getUsername());
        List<GoodsInfoOwner> list = goodsInfoOwnerService.selectGoodsInfoOwnerList(goodsInfoOwner);
        return getDataTable(list);
    }

    /**
     * 导出业户商品建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:ownerGoods:export')")
    @Log(title = "业户商品建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GoodsInfoOwner goodsInfoOwner)
    {
        List<GoodsInfoOwner> list = goodsInfoOwnerService.selectGoodsInfoOwnerList(goodsInfoOwner);
        ExcelUtil<GoodsInfoOwner> util = new ExcelUtil<GoodsInfoOwner>(GoodsInfoOwner.class);
        return util.exportExcel(list, "ownerGoods");
    }

    /**
     * 获取业户商品建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ownerGoods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(goodsInfoOwnerService.selectGoodsInfoOwnerById(id));
    }

    /**
     * 新增业户商品建档
     */
    @PreAuthorize("@ss.hasPermi('system:ownerGoods:add')")
    @Log(title = "业户商品建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsInfoOwner goodsInfoOwner)
    {
        goodsInfoOwner.setCreateBy(SecurityUtils.getUsername());
        GoodsInfoOwner info=goodsInfoOwnerService.selectGoodsInfoOwnerByName(-1,goodsInfoOwner.getGoodsName(),goodsInfoOwner.getCreateBy());
        if(info!=null) {
            return  toAjaxByError("该商品在内库档案中已存在");
        }else{
            goodsInfoOwner.setGoodsCode(StringUtils.getRandomCode("SP"));
            String goodsCodeImg= MatrixToImageWriter.createCodeImg(goodsInfoOwner.getGoodsCode());
            if(goodsCodeImg!=""){
                goodsInfoOwner.setGoodsCodeImg(serverConfig.getUrl()+goodsCodeImg);
            }
            return toAjax(goodsInfoOwnerService.insertGoodsInfoOwner(goodsInfoOwner));
        }

    }

    /**
     * 修改业户商品建档
     */
    @PreAuthorize("@ss.hasPermi('system:ownerGoods:edit')")
    @Log(title = "业户商品建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsInfoOwner goodsInfoOwner)
    {
        GoodsInfoOwner info=goodsInfoOwnerService.selectGoodsInfoOwnerByName(goodsInfoOwner.getId(),goodsInfoOwner.getGoodsName(),goodsInfoOwner.getCreateBy());
        if(info!=null) {
            return  toAjaxByError("该商品在内库档案中已存在");
        }else{
            if(goodsInfoOwner.getGoodsCodeImg()==""){
                String goodsCodeImg= MatrixToImageWriter.createCodeImg(goodsInfoOwner.getGoodsCode());
                if(goodsCodeImg!=""){
                    goodsInfoOwner.setGoodsCodeImg(serverConfig.getUrl()+goodsCodeImg);
                }
            }
            goodsInfoOwner.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(goodsInfoOwnerService.updateGoodsInfoOwner(goodsInfoOwner));
        }
    }

    /**
     * 删除业户商品建档
     */
    @PreAuthorize("@ss.hasPermi('system:ownerGoods:remove')")
    @Log(title = "业户商品建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(goodsInfoOwnerService.deleteGoodsInfoOwnerByIds(ids));
    }




}
