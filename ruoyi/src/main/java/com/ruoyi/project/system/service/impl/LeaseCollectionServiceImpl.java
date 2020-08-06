package com.ruoyi.project.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.LeaseCollectionMapper;
import com.ruoyi.project.system.domain.LeaseCollection;
import com.ruoyi.project.system.service.ILeaseCollectionService;

/**
 * 租赁收款Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-05
 */
@Service
public class LeaseCollectionServiceImpl implements ILeaseCollectionService {
    @Autowired
    private LeaseCollectionMapper leaseCollectionMapper;

    /**
     * 查询租赁收款
     *
     * @param collectionCode 租赁收款ID
     * @return 租赁收款
     */
    @Override
    public LeaseCollection selectLeaseCollectionById(String collectionCode) {
        return leaseCollectionMapper.selectLeaseCollectionById(collectionCode);
    }

    /**
     * 查询租赁收款列表
     *
     * @param leaseCollection 租赁收款
     * @return 租赁收款
     */
    @Override
    public List<LeaseCollection> selectLeaseCollectionList(LeaseCollection leaseCollection) {
        return leaseCollectionMapper.selectLeaseCollectionList(leaseCollection);
    }

    /**
     * 新增租赁收款
     *
     * @param leaseCollection 租赁收款
     * @return 结果
     */
    @Override
    public int insertLeaseCollection(LeaseCollection leaseCollection) {
        leaseCollection.setCreateTime(DateUtils.getNowDate());
        return leaseCollectionMapper.insertLeaseCollection(leaseCollection);
    }

    /**
     * 修改租赁收款
     *
     * @param leaseCollection 租赁收款
     * @return 结果
     */
    @Override
    public int updateLeaseCollection(LeaseCollection leaseCollection) {
        leaseCollection.setUpdateTime(DateUtils.getNowDate());
        return leaseCollectionMapper.updateLeaseCollection(leaseCollection);
    }

    /**
     * 批量删除租赁收款
     *
     * @param collectionCodes 需要删除的租赁收款ID
     * @return 结果
     */
    @Override
    public int deleteLeaseCollectionByIds(String[] collectionCodes) {
        return leaseCollectionMapper.deleteLeaseCollectionByIds(collectionCodes);
    }

    /**
     * 删除租赁收款信息
     *
     * @param collectionCode 租赁收款ID
     * @return 结果
     */
    @Override
    public int deleteLeaseCollectionById(String collectionCode) {
        return leaseCollectionMapper.deleteLeaseCollectionById(collectionCode);
    }
}
