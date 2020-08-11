package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.StoreInfoMapper;
import com.ruoyi.project.system.domain.StoreInfo;
import com.ruoyi.project.system.service.IStoreInfoService;

/**
 * 冷库建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-11
 */
@Service
public class StoreInfoServiceImpl implements IStoreInfoService {
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    /**
     * 查询冷库建档
     *
     * @param id 冷库建档ID
     * @return 冷库建档
     */
    @Override
    public StoreInfo selectStoreInfoById(String id) {
        return storeInfoMapper.selectStoreInfoById(id);
    }

    /**
     * 查询冷库建档
     *
     * @param name 冷库名称
     * @return 冷库建档
     */
    @Override
    public StoreInfo selectStoreInfoByName(String name,String id){
            return storeInfoMapper.selectStoreInfoByName(name,id);
    }
    /**
     * 查询冷库建档列表
     * 
     * @param storeInfo 冷库建档
     * @return 冷库建档
     */
    @Override
    public List<StoreInfo> selectStoreInfoList(StoreInfo storeInfo)
    {
        return storeInfoMapper.selectStoreInfoList(storeInfo);
    }

    /**
     * 新增冷库建档
     * 
     * @param storeInfo 冷库建档
     * @return 结果
     */
    @Override
    public int insertStoreInfo(StoreInfo storeInfo)
    {
        storeInfo.setCreateTime(DateUtils.getNowDate());
        return storeInfoMapper.insertStoreInfo(storeInfo);
    }

    /**
     * 修改冷库建档
     * 
     * @param storeInfo 冷库建档
     * @return 结果
     */
    @Override
    public int updateStoreInfo(StoreInfo storeInfo)
    {
        storeInfo.setUpdateTime(DateUtils.getNowDate());
        return storeInfoMapper.updateStoreInfo(storeInfo);
    }

    /**
     * 批量删除冷库建档
     * 
     * @param ids 需要删除的冷库建档ID
     * @return 结果
     */
    @Override
    public int deleteStoreInfoByIds(String[] ids)
    {
        return storeInfoMapper.deleteStoreInfoByIds(ids);
    }

    /**
     * 删除冷库建档信息
     * 
     * @param id 冷库建档ID
     * @return 结果
     */
    @Override
    public int deleteStoreInfoById(String id)
    {
        return storeInfoMapper.deleteStoreInfoById(id);
    }
}
