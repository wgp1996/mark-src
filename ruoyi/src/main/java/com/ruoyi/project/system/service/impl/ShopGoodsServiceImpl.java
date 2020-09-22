package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ShopGoodsMapper;
import com.ruoyi.project.system.domain.ShopGoods;
import com.ruoyi.project.system.service.IShopGoodsService;

/**
 * 门店商品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@Service
public class ShopGoodsServiceImpl implements IShopGoodsService 
{
    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    /**
     * 查询门店商品管理
     * 
     * @param id 门店商品管理ID
     * @return 门店商品管理
     */
    @Override
    public ShopGoods selectShopGoodsById(String id)
    {
        return shopGoodsMapper.selectShopGoodsById(id);
    }
    /**
     * 查询该商品是否存在
     *
     * @return 结果
     */
    @Override
    public int checkShopGoods(String storeId,String goodsCode){
        return shopGoodsMapper.checkShopGoods(storeId,goodsCode);
    }

    /**
     * 查询该商品是否绑定了价签 如果绑定则刷新模板
     *
     * @return 结果
     */
    @Override
    public int checkShopGoodsLabel(String goodsCode){
        return shopGoodsMapper.checkShopGoodsLabel(goodsCode);
    }
    /**
     * 查询门店商品管理列表
     * 
     * @param shopGoods 门店商品管理
     * @return 门店商品管理
     */
    @Override
    public List<ShopGoods> selectShopGoodsList(ShopGoods shopGoods)
    {
        return shopGoodsMapper.selectShopGoodsList(shopGoods);
    }

    /**
     * 新增门店商品管理
     * 
     * @param shopGoods 门店商品管理
     * @return 结果
     */
    @Override
    public int insertShopGoods(ShopGoods shopGoods)
    {
        shopGoods.setCreateTime(DateUtils.getNowDate());
        return shopGoodsMapper.insertShopGoods(shopGoods);
    }

    /**
     * 修改门店商品管理
     * 
     * @param shopGoods 门店商品管理
     * @return 结果
     */
    @Override
    public int updateShopGoods(ShopGoods shopGoods)
    {
        shopGoods.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsMapper.updateShopGoods(shopGoods);
    }

    /**
     * 批量删除门店商品管理
     * 
     * @param ids 需要删除的门店商品管理ID
     * @return 结果
     */
    @Override
    public int deleteShopGoodsByIds(String[] ids)
    {
        return shopGoodsMapper.deleteShopGoodsByIds(ids);
    }

    /**
     * 删除门店商品管理信息
     * 
     * @param id 门店商品管理ID
     * @return 结果
     */
    @Override
    public int deleteShopGoodsById(String id)
    {
        return shopGoodsMapper.deleteShopGoodsById(id);
    }
}
