package com.ruoyi.project.system.service;

import java.util.List;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.GoodsType;
import com.ruoyi.project.system.domain.SysDept;
import io.swagger.models.auth.In;

/**
 * 商品分类Service接口
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public interface IGoodsTypeService 
{
    /**
     * 查询商品分类
     * 
     * @param goodsTypeId 商品分类ID
     * @return 商品分类
     */
    public GoodsType selectGoodsTypeById(Integer goodsTypeId);

    /**
     * 查询商品分类
     *
     * @param goodsTypePid 商品分类PID
     * @return 商品分类
     */
    public List<GoodsType> selectGoodsTypeByPid(Integer goodsTypePid);

    /**
     * 查询商品分类
     *
     * @param goodsTypeId 商品分类ID
     * @return 商品分类
     */
    public GoodsType selectGoodsTypeByName(String name,Integer goodsTypeId);

    /**
     * 查询商品分类
     *
     * @param pid 商品分类父ID
     * @return 商品分类
     */
    public GoodsType checkGoodsTypeNameUnique(String name,Integer pid);
    /**
     * 是否存在子节点
     *
     * @param id 分类ID
     * @return 结果
     */
    public int hasChildGoodsTypeById(Integer id);

    /**
     * 构建前端所需要树结构
     *
     * @param goods 树形列表
     * @return 树结构列表
     */
    public List<GoodsType> buildGoodsTree(List<GoodsType> goods);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildGoodsTreeSelect(List<GoodsType> depts);
    /**
     * 查询商品分类列表
     * 
     * @param goodsType 商品分类
     * @return 商品分类集合
     */
    public List<GoodsType> selectGoodsTypeList(GoodsType goodsType);

    /**
     * 新增商品分类
     * 
     * @param goodsType 商品分类
     * @return 结果
     */
    public int insertGoodsType(GoodsType goodsType);

    /**
     * 修改商品分类
     * 
     * @param goodsType 商品分类
     * @return 结果
     */
    public int updateGoodsType(GoodsType goodsType);

    /**
     * 批量删除商品分类
     * 
     * @param goodsTypeIds 需要删除的商品分类ID
     * @return 结果
     */
    public int deleteGoodsTypeByIds(Integer[] goodsTypeIds);

    /**
     * 删除商品分类信息
     * 
     * @param goodsTypeId 商品分类ID
     * @return 结果
     */
    public int deleteGoodsTypeById(Integer goodsTypeId);
}
