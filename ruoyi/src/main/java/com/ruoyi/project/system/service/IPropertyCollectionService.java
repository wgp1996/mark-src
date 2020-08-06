package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.PropertyCollection;

/**
 * 物业收款Service接口
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
public interface IPropertyCollectionService 
{
    /**
     * 查询物业收款
     * 
     * @param collectionCode 物业收款ID
     * @return 物业收款
     */
    public PropertyCollection selectPropertyCollectionById(String collectionCode);

    /**
     * 查询物业收款列表
     * 
     * @param propertyCollection 物业收款
     * @return 物业收款集合
     */
    public List<PropertyCollection> selectPropertyCollectionList(PropertyCollection propertyCollection);

    /**
     * 新增物业收款
     * 
     * @param propertyCollection 物业收款
     * @return 结果
     */
    public int insertPropertyCollection(PropertyCollection propertyCollection);

    /**
     * 修改物业收款
     * 
     * @param propertyCollection 物业收款
     * @return 结果
     */
    public int updatePropertyCollection(PropertyCollection propertyCollection);

    /**
     * 批量删除物业收款
     * 
     * @param collectionCodes 需要删除的物业收款ID
     * @return 结果
     */
    public int deletePropertyCollectionByIds(String[] collectionCodes);

    /**
     * 删除物业收款信息
     * 
     * @param collectionCode 物业收款ID
     * @return 结果
     */
    public int deletePropertyCollectionById(String collectionCode);
}
