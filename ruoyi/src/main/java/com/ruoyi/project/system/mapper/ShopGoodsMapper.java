package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ShopGoods;

/**
 * 门店商品管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public interface ShopGoodsMapper 
{
    /**
     * 查询门店商品管理
     * 
     * @param id 门店商品管理ID
     * @return 门店商品管理
     */
    public ShopGoods selectShopGoodsById(String id);
    /**
     * 根据商品编码查询门店商品信息
     *
     */
    public ShopGoods selectShopGoodsByStore(String storeId,String goodsCode);
    /**
     * 查询该商品是否存在
     *
     * @return 结果
     */
    public int checkShopGoods(String storeId,String goodsCode);

    /**
     * 查询该商品是否绑定了价签 如果绑定则刷新模板
     *
     * @return 结果
     */
    public int checkShopGoodsLabel(String goodsCode);
    /**
     * 查询门店商品管理列表
     * 
     * @param shopGoods 门店商品管理
     * @return 门店商品管理集合
     */
    public List<ShopGoods> selectShopGoodsList(ShopGoods shopGoods);

    /**
     * 新增门店商品管理
     * 
     * @param shopGoods 门店商品管理
     * @return 结果
     */
    public int insertShopGoods(ShopGoods shopGoods);

    /**
     * 修改门店商品管理
     * 
     * @param shopGoods 门店商品管理
     * @return 结果
     */
    public int updateShopGoods(ShopGoods shopGoods);

    /**
     * 删除门店商品管理
     * 
     * @param id 门店商品管理ID
     * @return 结果
     */
    public int deleteShopGoodsById(String id);

    /**
     * 批量删除门店商品管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteShopGoodsByIds(String[] ids);
}
