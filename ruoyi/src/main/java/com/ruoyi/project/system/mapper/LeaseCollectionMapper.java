package com.ruoyi.project.system.mapper;

import java.util.List;

import com.ruoyi.project.system.domain.LeaseCollection;

/**
 * 租赁收款Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-05
 */
public interface LeaseCollectionMapper {
    /**
     * 查询租赁收款
     *
     * @param collectionCode 租赁收款ID
     * @return 租赁收款
     */
    public LeaseCollection selectLeaseCollectionById(String collectionCode);

    /**
     * 查询租赁收款列表
     *
     * @param leaseCollection 租赁收款
     * @return 租赁收款集合
     */
    public List<LeaseCollection> selectLeaseCollectionList(LeaseCollection leaseCollection);

    /**
     * 新增租赁收款
     *
     * @param leaseCollection 租赁收款
     * @return 结果
     */
    public int insertLeaseCollection(LeaseCollection leaseCollection);

    /**
     * 修改租赁收款
     *
     * @param leaseCollection 租赁收款
     * @return 结果
     */
    public int updateLeaseCollection(LeaseCollection leaseCollection);

    /**
     * 删除租赁收款
     *
     * @param collectionCode 租赁收款ID
     * @return 结果
     */
    public int deleteLeaseCollectionById(String collectionCode);

    /**
     * 批量删除租赁收款
     *
     * @param collectionCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeaseCollectionByIds(String[] collectionCodes);
}
