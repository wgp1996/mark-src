package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.GoodsInfoOwnerMapper;
import com.ruoyi.project.system.domain.GoodsInfoOwner;
import com.ruoyi.project.system.service.IGoodsInfoOwnerService;

/**
 * 业户商品建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
@Service
public class GoodsInfoOwnerServiceImpl implements IGoodsInfoOwnerService 
{
    @Autowired
    private GoodsInfoOwnerMapper goodsInfoOwnerMapper;

    /**
     * 查询业户商品建档
     * 
     * @param id 业户商品建档ID
     * @return 业户商品建档
     */
    @Override
    public GoodsInfoOwner selectGoodsInfoOwnerById(Integer id)
    {
        return goodsInfoOwnerMapper.selectGoodsInfoOwnerById(id);
    }
    /**
     * 根据名称查询业户商品建档
     *
     * @param id 业户商品建档ID
     * @return 业户商品建档
     */
    @Override
    public GoodsInfoOwner selectGoodsInfoOwnerByName(Integer id,String goodsName,String createBy){
        return goodsInfoOwnerMapper.selectGoodsInfoOwnerByName(id,goodsName,createBy);
    }

    /**
     * 根据编码查询业户商品建档
     *
     * @param goodsCode 业户商品建档编码
     * @return 业户商品建档
     */
    @Override
    public GoodsInfoOwner selectGoodsInfoOwnerByCode(Integer id,String goodsCode,String createBy){
        return goodsInfoOwnerMapper.selectGoodsInfoOwnerByCode(id,goodsCode,createBy);
    }
    /**
     * 查询业户商品建档列表
     * 
     * @param goodsInfoOwner 业户商品建档
     * @return 业户商品建档
     */
    @Override
    public List<GoodsInfoOwner> selectGoodsInfoOwnerList(GoodsInfoOwner goodsInfoOwner)
    {
        return goodsInfoOwnerMapper.selectGoodsInfoOwnerList(goodsInfoOwner);
    }
    /**
     * 市平台查询业户商品建档列表
     *
     * @param goodsInfoOwner 业户商品建档
     * @return 业户商品建档集合
     */
    @Override
    public List<GoodsInfoOwner> selectGoodsInfoOwnerListBySpt(GoodsInfoOwner goodsInfoOwner){
        return goodsInfoOwnerMapper.selectGoodsInfoOwnerListBySpt(goodsInfoOwner);
    }
    /**
     * 新增业户商品建档
     * 
     * @param goodsInfoOwner 业户商品建档
     * @return 结果
     */
    @Override
    public int insertGoodsInfoOwner(GoodsInfoOwner goodsInfoOwner)
    {
        goodsInfoOwner.setCreateTime(DateUtils.getNowDate());
        return goodsInfoOwnerMapper.insertGoodsInfoOwner(goodsInfoOwner);
    }

    /**
     * 修改业户商品建档
     * 
     * @param goodsInfoOwner 业户商品建档
     * @return 结果
     */
    @Override
    public int updateGoodsInfoOwner(GoodsInfoOwner goodsInfoOwner)
    {
        goodsInfoOwner.setUpdateTime(DateUtils.getNowDate());
        return goodsInfoOwnerMapper.updateGoodsInfoOwner(goodsInfoOwner);
    }

    /**
     * 批量删除业户商品建档
     * 
     * @param ids 需要删除的业户商品建档ID
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoOwnerByIds(Integer[] ids)
    {
        return goodsInfoOwnerMapper.deleteGoodsInfoOwnerByIds(ids);
    }

    /**
     * 删除业户商品建档信息
     * 
     * @param id 业户商品建档ID
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoOwnerById(Integer id)
    {
        return goodsInfoOwnerMapper.deleteGoodsInfoOwnerById(id);
    }
}
