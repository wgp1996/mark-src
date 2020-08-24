package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.GoodsInfoOwner;

/**
 * 业户商品建档Service接口
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
public interface IGoodsInfoOwnerService 
{
    /**
     * 查询业户商品建档
     * 
     * @param id 业户商品建档ID
     * @return 业户商品建档
     */
    public GoodsInfoOwner selectGoodsInfoOwnerById(Integer id);

    /**
     * 根据名称查询业户商品建档
     *
     * @param id 业户商品建档ID
     * @return 业户商品建档
     */
    public GoodsInfoOwner selectGoodsInfoOwnerByName(Integer id,String goodsName,String createBy);

    /**
     * 查询业户商品建档列表
     * 
     * @param goodsInfoOwner 业户商品建档
     * @return 业户商品建档集合
     */
    public List<GoodsInfoOwner> selectGoodsInfoOwnerList(GoodsInfoOwner goodsInfoOwner);

    /**
     * 新增业户商品建档
     * 
     * @param goodsInfoOwner 业户商品建档
     * @return 结果
     */
    public int insertGoodsInfoOwner(GoodsInfoOwner goodsInfoOwner);

    /**
     * 修改业户商品建档
     * 
     * @param goodsInfoOwner 业户商品建档
     * @return 结果
     */
    public int updateGoodsInfoOwner(GoodsInfoOwner goodsInfoOwner);

    /**
     * 批量删除业户商品建档
     * 
     * @param ids 需要删除的业户商品建档ID
     * @return 结果
     */
    public int deleteGoodsInfoOwnerByIds(Integer[] ids);

    /**
     * 删除业户商品建档信息
     * 
     * @param id 业户商品建档ID
     * @return 结果
     */
    public int deleteGoodsInfoOwnerById(Integer id);
}
