package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ShopInfoMapper;
import com.ruoyi.project.system.domain.ShopInfo;
import com.ruoyi.project.system.service.IShopInfoService;

/**
 * 门店管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@Service
public class ShopInfoServiceImpl implements IShopInfoService 
{
    @Autowired
    private ShopInfoMapper shopInfoMapper;

    /**
     * 查询门店管理
     * 
     * @param id 门店管理ID
     * @return 门店管理
     */
    @Override
    public ShopInfo selectShopInfoById(String id)
    {
        return shopInfoMapper.selectShopInfoById(id);
    }
    /**
     * 监测门店是否重复
     *
     * @param storeId 门店ID
     * @return 结果
     */
    @Override
    public int checkShopInfo(String storeId,String id){
        return shopInfoMapper.checkShopInfo(storeId,id);
    }
    /**
     * 查询门店管理列表
     * 
     * @param shopInfo 门店管理
     * @return 门店管理
     */
    @Override
    public List<ShopInfo> selectShopInfoList(ShopInfo shopInfo)
    {
        return shopInfoMapper.selectShopInfoList(shopInfo);
    }

    /**
     * 新增门店管理
     * 
     * @param shopInfo 门店管理
     * @return 结果
     */
    @Override
    public int insertShopInfo(ShopInfo shopInfo)
    {
        shopInfo.setCreateTime(DateUtils.getNowDate());
        return shopInfoMapper.insertShopInfo(shopInfo);
    }

    /**
     * 修改门店管理
     * 
     * @param shopInfo 门店管理
     * @return 结果
     */
    @Override
    public int updateShopInfo(ShopInfo shopInfo)
    {
        shopInfo.setUpdateTime(DateUtils.getNowDate());
        return shopInfoMapper.updateShopInfo(shopInfo);
    }

    /**
     * 批量删除门店管理
     * 
     * @param ids 需要删除的门店管理ID
     * @return 结果
     */
    @Override
    public int deleteShopInfoByIds(String[] ids)
    {
        return shopInfoMapper.deleteShopInfoByIds(ids);
    }

    /**
     * 删除门店管理信息
     * 
     * @param id 门店管理ID
     * @return 结果
     */
    @Override
    public int deleteShopInfoById(String id)
    {
        return shopInfoMapper.deleteShopInfoById(id);
    }

    /**
     * 是否存在商品
     *
     * @param storeId 门店id
     * @return 结果
     */
    @Override
    public int checkHasGoods(String storeId){
        return shopInfoMapper.checkHasGoods(storeId);
    }
    /**
     * 是否存在价签
     *
     * @param storeId 门店id
     * @return 结果
     */
    @Override
    public int checkHasLabel(String storeId){
        return shopInfoMapper.checkHasLabel(storeId);
    }
}
