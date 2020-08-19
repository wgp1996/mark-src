package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.GoodsType;

/**
 * 商品分类Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public interface GoodsTypeMapper 
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
     * @param id 商品分类ID
     * @return 商品分类
     */
    public GoodsType selectGoodsTypeByName(String name,Integer id);
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
    int hasChildGoodsTypeById(Integer id);
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
     * 删除商品分类
     * 
     * @param goodsTypeId 商品分类ID
     * @return 结果
     */
    public int deleteGoodsTypeById(Integer goodsTypeId);

    /**
     * 批量删除商品分类
     * 
     * @param goodsTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsTypeByIds(Integer[] goodsTypeIds);
}
