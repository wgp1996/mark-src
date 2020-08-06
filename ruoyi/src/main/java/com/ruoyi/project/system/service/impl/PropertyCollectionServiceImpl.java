package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PropertyCollectionMapper;
import com.ruoyi.project.system.domain.PropertyCollection;
import com.ruoyi.project.system.service.IPropertyCollectionService;

/**
 * 物业收款Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
@Service
public class PropertyCollectionServiceImpl implements IPropertyCollectionService 
{
    @Autowired
    private PropertyCollectionMapper propertyCollectionMapper;

    /**
     * 查询物业收款
     * 
     * @param collectionCode 物业收款ID
     * @return 物业收款
     */
    @Override
    public PropertyCollection selectPropertyCollectionById(String collectionCode)
    {
        return propertyCollectionMapper.selectPropertyCollectionById(collectionCode);
    }

    /**
     * 查询物业收款列表
     * 
     * @param propertyCollection 物业收款
     * @return 物业收款
     */
    @Override
    public List<PropertyCollection> selectPropertyCollectionList(PropertyCollection propertyCollection)
    {
        return propertyCollectionMapper.selectPropertyCollectionList(propertyCollection);
    }

    /**
     * 新增物业收款
     * 
     * @param propertyCollection 物业收款
     * @return 结果
     */
    @Override
    public int insertPropertyCollection(PropertyCollection propertyCollection)
    {
        propertyCollection.setCreateTime(DateUtils.getNowDate());
        return propertyCollectionMapper.insertPropertyCollection(propertyCollection);
    }

    /**
     * 修改物业收款
     * 
     * @param propertyCollection 物业收款
     * @return 结果
     */
    @Override
    public int updatePropertyCollection(PropertyCollection propertyCollection)
    {
        propertyCollection.setUpdateTime(DateUtils.getNowDate());
        return propertyCollectionMapper.updatePropertyCollection(propertyCollection);
    }

    /**
     * 批量删除物业收款
     * 
     * @param collectionCodes 需要删除的物业收款ID
     * @return 结果
     */
    @Override
    public int deletePropertyCollectionByIds(String[] collectionCodes)
    {
        return propertyCollectionMapper.deletePropertyCollectionByIds(collectionCodes);
    }

    /**
     * 删除物业收款信息
     * 
     * @param collectionCode 物业收款ID
     * @return 结果
     */
    @Override
    public int deletePropertyCollectionById(String collectionCode)
    {
        return propertyCollectionMapper.deletePropertyCollectionById(collectionCode);
    }
}
