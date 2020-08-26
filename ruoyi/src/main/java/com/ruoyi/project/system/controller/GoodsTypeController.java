package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.SysDept;
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
import com.ruoyi.project.system.domain.GoodsType;
import com.ruoyi.project.system.service.IGoodsTypeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 商品分类Controller
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
@RestController
@RequestMapping("/system/goodsType")
public class GoodsTypeController extends BaseController
{
    @Autowired
    private IGoodsTypeService goodsTypeService;
    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(GoodsType good)
    {
        List<GoodsType> goods = goodsTypeService.selectGoodsTypeList(good);
        return AjaxResult.success(goodsTypeService.buildGoodsTreeSelect(goods));
    }
    /**
     * 查询商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsType:list')")
    @GetMapping("/list")
   // @DataScope(deptAlias = "d", userAlias = "u")
    public AjaxResult list(GoodsType goodsType)
    {
        List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
        return AjaxResult.success(list);
    }

    /**
     * 导出商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsType:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GoodsType goodsType)
    {
        List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
        ExcelUtil<GoodsType> util = new ExcelUtil<GoodsType>(GoodsType.class);
        return util.exportExcel(list, "goodsType");
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:goodsType:query')")
    @GetMapping(value = "/{goodsTypeId}")
    public AjaxResult getInfo(@PathVariable("goodsTypeId") Integer goodsTypeId)
    {
        return AjaxResult.success(goodsTypeService.selectGoodsTypeById(goodsTypeId));
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize("@ss.hasPermi('system:goodsType:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsType goodsType)
    {
        GoodsType info=goodsTypeService.selectGoodsTypeByName(goodsType.getGoodsTypeName(),-1);
        if(info!=null){
            return toAjaxByError("该分类已存在");
        }
        goodsType.setCreateBy(SecurityUtils.getUsername());
        goodsType.setType(0);
        return toAjax(goodsTypeService.insertGoodsType(goodsType));
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize("@ss.hasPermi('system:goodsType:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsType goodsType)
    {
        GoodsType info=goodsTypeService.selectGoodsTypeByName(goodsType.getGoodsTypeName(),goodsType.getGoodsTypeId());
        if(info!=null){
            return toAjaxByError("该分类已存在");
        }
        if(goodsType.getGoodsTypeId()==goodsType.getGoodsTypePid()){
            return toAjaxByError("上级分类不能是自己");
        }
        goodsType.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(goodsTypeService.updateGoodsType(goodsType));
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize("@ss.hasPermi('system:goodsType:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsTypeIds}")
    public AjaxResult remove(@PathVariable Integer[] goodsTypeIds)
    {
        if (goodsTypeService.hasChildGoodsTypeById(goodsTypeIds[0])>0)
        {
            return AjaxResult.error("存在下级分类,不允许删除");
        }
        return toAjax(goodsTypeService.deleteGoodsTypeByIds(goodsTypeIds));
    }
}
