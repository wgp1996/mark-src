package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.SalesCollection;
import com.ruoyi.project.system.mapper.SalesCollectionMapper;
import com.ruoyi.project.system.service.ISalesCollectionService;
import com.ruoyi.project.system.service.ISalesCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售收款Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
@Service
public class SalesCollectionServiceImpl implements ISalesCollectionService
{
    @Autowired
    private SalesCollectionMapper SalesCollectionMapper;

    /**
     * 查询销售收款
     * 
     * @param collectionCode 销售收款ID
     * @return 销售收款
     */
    @Override
    public SalesCollection selectSalesCollectionById(String collectionCode)
    {
        return SalesCollectionMapper.selectSalesCollectionById(collectionCode);
    }

    /**
     * 查询销售收款列表
     * 
     * @param SalesCollection 销售收款
     * @return 销售收款
     */
    @Override
    public List<SalesCollection> selectSalesCollectionList(SalesCollection SalesCollection)
    {
        return SalesCollectionMapper.selectSalesCollectionList(SalesCollection);
    }

    /**
     * 新增销售收款
     * 
     * @param SalesCollection 销售收款
     * @return 结果
     */
    @Override
    public int insertSalesCollection(SalesCollection SalesCollection)
    {
        SalesCollection.setCreateTime(DateUtils.getNowDate());
        return SalesCollectionMapper.insertSalesCollection(SalesCollection);
    }

    /**
     * 修改销售收款
     * 
     * @param SalesCollection 销售收款
     * @return 结果
     */
    @Override
    public int updateSalesCollection(SalesCollection SalesCollection)
    {
        SalesCollection.setUpdateTime(DateUtils.getNowDate());
        return SalesCollectionMapper.updateSalesCollection(SalesCollection);
    }

    /**
     * 批量删除销售收款
     * 
     * @param collectionCodes 需要删除的销售收款ID
     * @return 结果
     */
    @Override
    public int deleteSalesCollectionByIds(String[] collectionCodes)
    {
        return SalesCollectionMapper.deleteSalesCollectionByIds(collectionCodes);
    }

    /**
     * 删除销售收款信息
     * 
     * @param collectionCode 销售收款ID
     * @return 结果
     */
    @Override
    public int deleteSalesCollectionById(String collectionCode)
    {
        return SalesCollectionMapper.deleteSalesCollectionById(collectionCode);
    }
}
