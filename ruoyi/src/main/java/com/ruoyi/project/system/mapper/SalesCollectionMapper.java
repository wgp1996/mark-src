package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SalesCollection;

import java.util.List;

/**
 * 销售收款Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
public interface SalesCollectionMapper 
{
    /**
     * 查询销售收款
     * 
     * @param collectionCode 销售收款ID
     * @return 销售收款
     */
    public SalesCollection selectSalesCollectionById(String collectionCode);

    /**
     * 查询销售收款列表
     * 
     * @param SalesCollection 销售收款
     * @return 销售收款集合
     */
    public List<SalesCollection> selectSalesCollectionList(SalesCollection SalesCollection);

    /**
     * 新增销售收款
     * 
     * @param SalesCollection 销售收款
     * @return 结果
     */
    public int insertSalesCollection(SalesCollection SalesCollection);

    /**
     * 修改销售收款
     * 
     * @param SalesCollection 销售收款
     * @return 结果
     */
    public int updateSalesCollection(SalesCollection SalesCollection);

    /**
     * 删除销售收款
     * 
     * @param collectionCode 销售收款ID
     * @return 结果
     */
    public int deleteSalesCollectionById(String collectionCode);

    /**
     * 批量删除销售收款
     * 
     * @param collectionCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesCollectionByIds(String[] collectionCodes);
}
