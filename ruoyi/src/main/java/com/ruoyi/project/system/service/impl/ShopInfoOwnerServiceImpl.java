package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.ShopInfoOwner;
import com.ruoyi.project.system.mapper.ShopInfoOwnerMapper;
import com.ruoyi.project.system.service.IShopInfoOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@Service
public class ShopInfoOwnerServiceImpl implements IShopInfoOwnerService
{
    @Autowired
    private ShopInfoOwnerMapper shopInfoMapper;

    /**
     * 查询门店管理
     * 
     * @param id 门店管理ID
     * @return 门店管理
     */
    @Override
    public ShopInfoOwner selectShopInfoOwnerById(String id)
    {
        return shopInfoMapper.selectShopInfoOwnerById(id);
    }
    /**
     * 监测门店是否重复
     *
     * @param storeId 门店ID
     * @return 结果
     */
    @Override
    public int checkShopInfoOwner(String storeId,String id){
        return shopInfoMapper.checkShopInfoOwner(storeId,id);
    }
    /**
     * 查询门店管理列表
     * 
     * @param shopInfo 门店管理
     * @return 门店管理
     */
    @Override
    public List<ShopInfoOwner> selectShopInfoOwnerList(ShopInfoOwner shopInfo)
    {
        return shopInfoMapper.selectShopInfoOwnerList(shopInfo);
    }

    /**
     * 新增门店管理
     * 
     * @param shopInfo 门店管理
     * @return 结果
     */
    @Override
    public int insertShopInfoOwner(ShopInfoOwner shopInfo)
    {
        shopInfo.setCreateTime(DateUtils.getNowDate());
        return shopInfoMapper.insertShopInfoOwner(shopInfo);
    }

    /**
     * 修改门店管理
     * 
     * @param shopInfo 门店管理
     * @return 结果
     */
    @Override
    public int updateShopInfoOwner(ShopInfoOwner shopInfo)
    {
        shopInfo.setUpdateTime(DateUtils.getNowDate());
        return shopInfoMapper.updateShopInfoOwner(shopInfo);
    }

    /**
     * 批量删除门店管理
     * 
     * @param ids 需要删除的门店管理ID
     * @return 结果
     */
    @Override
    public int deleteShopInfoOwnerByIds(String[] ids)
    {
        return shopInfoMapper.deleteShopInfoOwnerByIds(ids);
    }

    /**
     * 删除门店管理信息
     * 
     * @param id 门店管理ID
     * @return 结果
     */
    @Override
    public int deleteShopInfoOwnerById(String id)
    {
        return shopInfoMapper.deleteShopInfoOwnerById(id);
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
