package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.ShopInfo;

/**
 * 门店管理Service接口
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public interface IShopInfoService 
{
    /**
     * 查询门店管理
     * 
     * @param id 门店管理ID
     * @return 门店管理
     */
    public ShopInfo selectShopInfoById(String id);

    /**
     * 查询门店管理列表
     * 
     * @param shopInfo 门店管理
     * @return 门店管理集合
     */
    public List<ShopInfo> selectShopInfoList(ShopInfo shopInfo);

    /**
     * 新增门店管理
     * 
     * @param shopInfo 门店管理
     * @return 结果
     */
    public int insertShopInfo(ShopInfo shopInfo);

    /**
     * 监测门店是否重复
     *
     * @param storeId 门店ID
     * @return 结果
     */
    public int checkShopInfo(String storeId,String id);

    /**
     * 修改门店管理
     * 
     * @param shopInfo 门店管理
     * @return 结果
     */
    public int updateShopInfo(ShopInfo shopInfo);

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
    public int deleteShopInfoByIds(String[] ids);

    /**
     * 删除门店管理信息
     * 
     * @param id 门店管理ID
     * @return 结果
     */
    public int deleteShopInfoById(String id);
}
