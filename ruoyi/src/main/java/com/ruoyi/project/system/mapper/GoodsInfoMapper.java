package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.GoodsInfo;

/**
 * 商品建档Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public interface GoodsInfoMapper 
{
    /**
     * 查询商品建档
     * 
     * @param id 商品建档ID
     * @return 商品建档
     */
    public GoodsInfo selectGoodsInfoById(Integer id);
    /**
     * 查询商品建档
     *
     * @param name 商品建档名称
     * @return 商品建档
     */
    public GoodsInfo selectGoodsInfoByName(String name,Integer id);
    /**
     * 查询商品建档列表
     * 
     * @param goodsInfo 商品建档
     * @return 商品建档集合
     */
    public List<GoodsInfo> selectGoodsInfoList(GoodsInfo goodsInfo);

    /**
     * 新增商品建档
     * 
     * @param goodsInfo 商品建档
     * @return 结果
     */
    public int insertGoodsInfo(GoodsInfo goodsInfo);

    /**
     * 修改商品建档
     * 
     * @param goodsInfo 商品建档
     * @return 结果
     */
    public int updateGoodsInfo(GoodsInfo goodsInfo);

    /**
     * 删除商品建档
     * 
     * @param id 商品建档ID
     * @return 结果
     */
    public int deleteGoodsInfoById(Integer id);

    /**
     * 批量删除商品建档
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsInfoByIds(Integer[] ids);
}
