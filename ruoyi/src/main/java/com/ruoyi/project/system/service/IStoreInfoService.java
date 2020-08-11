package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.StoreInfo;

/**
 * 冷库建档Service接口
 * 
 * @author ruoyi
 * @date 2020-08-11
 */
public interface IStoreInfoService 
{
    /**
     * 查询冷库建档
     * 
     * @param id 冷库建档ID
     * @return 冷库建档
     */
    public StoreInfo selectStoreInfoById(String id);

    /**
     * 查询冷库建档
     *
     * @param name 冷库名称
     * @return 冷库建档
     */
    public StoreInfo selectStoreInfoByName(String name,String id);

    /**
     * 查询冷库建档列表
     * 
     * @param storeInfo 冷库建档
     * @return 冷库建档集合
     */
    public List<StoreInfo> selectStoreInfoList(StoreInfo storeInfo);

    /**
     * 新增冷库建档
     * 
     * @param storeInfo 冷库建档
     * @return 结果
     */
    public int insertStoreInfo(StoreInfo storeInfo);

    /**
     * 修改冷库建档
     * 
     * @param storeInfo 冷库建档
     * @return 结果
     */
    public int updateStoreInfo(StoreInfo storeInfo);

    /**
     * 批量删除冷库建档
     * 
     * @param ids 需要删除的冷库建档ID
     * @return 结果
     */
    public int deleteStoreInfoByIds(String[] ids);

    /**
     * 删除冷库建档信息
     * 
     * @param id 冷库建档ID
     * @return 结果
     */
    public int deleteStoreInfoById(String id);
}
