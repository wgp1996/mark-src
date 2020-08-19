package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.GoodsInfoMapper;
import com.ruoyi.project.system.domain.GoodsInfo;
import com.ruoyi.project.system.service.IGoodsInfoService;

/**
 * 商品建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
@Service
public class GoodsInfoServiceImpl implements IGoodsInfoService 
{
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    /**
     * 查询商品建档
     * 
     * @param id 商品建档ID
     * @return 商品建档
     */
    @Override
    public GoodsInfo selectGoodsInfoById(Integer id)
    {
        return goodsInfoMapper.selectGoodsInfoById(id);
    }
    /**
     * 查询商品建档
     *
     * @param name 商品建档名称
     * @return 商品建档
     */
    public GoodsInfo selectGoodsInfoByName(String name,Integer id){
        return goodsInfoMapper.selectGoodsInfoByName(name,id);
    }
    /**
     * 查询商品建档列表
     * 
     * @param goodsInfo 商品建档
     * @return 商品建档
     */
    @Override
    public List<GoodsInfo> selectGoodsInfoList(GoodsInfo goodsInfo)
    {
        return goodsInfoMapper.selectGoodsInfoList(goodsInfo);
    }

    /**
     * 新增商品建档
     * 
     * @param goodsInfo 商品建档
     * @return 结果
     */
    @Override
    public int insertGoodsInfo(GoodsInfo goodsInfo)
    {
        goodsInfo.setCreateTime(DateUtils.getNowDate());
        return goodsInfoMapper.insertGoodsInfo(goodsInfo);
    }

    /**
     * 修改商品建档
     * 
     * @param goodsInfo 商品建档
     * @return 结果
     */
    @Override
    public int updateGoodsInfo(GoodsInfo goodsInfo)
    {
        goodsInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsInfoMapper.updateGoodsInfo(goodsInfo);
    }

    /**
     * 批量删除商品建档
     * 
     * @param ids 需要删除的商品建档ID
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoByIds(Integer[] ids)
    {
        return goodsInfoMapper.deleteGoodsInfoByIds(ids);
    }

    /**
     * 删除商品建档信息
     * 
     * @param id 商品建档ID
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoById(Integer id)
    {
        return goodsInfoMapper.deleteGoodsInfoById(id);
    }
}
