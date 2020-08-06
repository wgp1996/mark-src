package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.LeaseContractChild;

/**
 * 租赁合同子表信息Service接口
 * 
 * @author ruoyi
 * @date 2020-07-30
 */
public interface ILeaseContractChildService 
{
    /**
     * 查询租赁合同子表信息
     * 
     * @param id 租赁合同子表信息ID
     * @return 租赁合同子表信息
     */
    public LeaseContractChild selectLeaseContractChildById(String id);

    /**
     * 查询租赁合同子表信息
     *
     * @param code 租赁合同主表信息编码
     * @return 租赁合同子表信息
     */
    public List<LeaseContractChild> selectLeaseContractChildByCode(String code);

    /**
     * 查询租赁合同子表信息列表
     * 
     * @param leaseContractChild 租赁合同子表信息
     * @return 租赁合同子表信息集合
     */
    public List<LeaseContractChild> selectLeaseContractChildList(LeaseContractChild leaseContractChild);

    /**
     * 租赁收款查询租赁合同信息列表
     *
     * @param leaseContractChild 租赁合同子表信息
     * @return 租赁合同子表信息集合
     */
    public List<LeaseContractChild> selectLeaseByCollection(LeaseContractChild leaseContractChild);

    /**
     * 新增租赁合同子表信息
     * 
     * @param leaseContractChild 租赁合同子表信息
     * @return 结果
     */
    public int insertLeaseContractChild(LeaseContractChild leaseContractChild);

    /**
     * 修改租赁合同子表信息
     * 
     * @param leaseContractChild 租赁合同子表信息
     * @return 结果
     */
    public int updateLeaseContractChild(LeaseContractChild leaseContractChild);

    /**
     * 批量删除租赁合同子表信息
     * 
     * @param ids 需要删除的租赁合同子表信息ID
     * @return 结果
     */
    public int deleteLeaseContractChildByIds(String[] ids);

    /**
     * 根据主表ID批量删除租赁合同子表信息
     *
     * @param ids 需要删除的租赁合同主表信息ID
     * @return 结果
     */
    public int deleteLeaseContractChildPid(String[] ids);

    /**
     * 删除租赁合同子表信息信息
     * 
     * @param id 租赁合同子表信息ID
     * @return 结果
     */
    public int deleteLeaseContractChildById(String id);

    /**
     * 根据子表ID修改摊位信息
     *
     * @param id 租赁合同子表信息ID
     * @return 结果
     */
    public int updateStallInfoById(String id);


    /**
     * 根据主表ID修改摊位信息
     *
     * @param ids 需要删除的租赁合同主表信息ID
     * @return 结果
     */
    public int updateStallInfoByPids(String[] ids);

}
