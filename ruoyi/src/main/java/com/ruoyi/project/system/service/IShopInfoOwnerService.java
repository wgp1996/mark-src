package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.ShopInfoOwner;

import java.util.List;

/**
 * 门店管理Service接口
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public interface IShopInfoOwnerService
{
    /**
     * 查询门店管理
     * 
     * @param id 门店管理ID
     * @return 门店管理
     */
    public ShopInfoOwner selectShopInfoOwnerById(String id);

    /**
     * 查询门店管理列表
     * 
     * @param shopInfo 门店管理
     * @return 门店管理集合
     */
    public List<ShopInfoOwner> selectShopInfoOwnerList(ShopInfoOwner shopInfo);

    /**
     * 新增门店管理
     * 
     * @param shopInfo 门店管理
     * @return 结果
     */
    public int insertShopInfoOwner(ShopInfoOwner shopInfo);

    /**
     * 监测门店是否重复
     *
     * @param storeId 门店ID
     * @return 结果
     */
    public int checkShopInfoOwner(String storeId, String id);

    /**
     * 修改门店管理
     * 
     * @param shopInfo 门店管理
     * @return 结果
     */
    public int updateShopInfoOwner(ShopInfoOwner shopInfo);

    /**
     * 是否存在商品
     *
     * @param storeId 门店id
     * @return 结果
     */
    public int checkHasGoods(String storeId);
    /**
     * 是否存在价签
     *
     * @param storeId 门店id
     * @return 结果
     */
    public int checkHasLabel(String storeId);

    /**
     * 批量删除门店管理
     * 
     * @param ids 需要删除的门店管理ID
     * @return 结果
     */
    public int deleteShopInfoOwnerByIds(String[] ids);

    /**
     * 删除门店管理信息
     * 
     * @param id 门店管理ID
     * @return 结果
     */
    public int deleteShopInfoOwnerById(String id);
}
