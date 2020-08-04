package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.LeaseContractChildSales;

import java.util.List;

/**
 * 租赁合同子表信息Service接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface ILeaseContractChildSalesService {
    /**
     * 查询租赁合同子表信息
     *
     * @param id 租赁合同子表信息ID
     * @return 租赁合同子表信息
     */
    public LeaseContractChildSales selectLeaseContractChildById(String id);

    /**
     * 查询租赁合同子表信息
     *
     * @param code 租赁合同主表信息编码
     * @return 租赁合同子表信息
     */
    public List<LeaseContractChildSales> selectLeaseContractChildByCode(String code);

    /**
     * 查询租赁合同子表信息列表
     *
     * @param LeaseContractChildSales 租赁合同子表信息
     * @return 租赁合同子表信息集合
     */
    public List<LeaseContractChildSales> selectLeaseContractChildList(LeaseContractChildSales LeaseContractChildSales);

    /**
     * 新增租赁合同子表信息
     *
     * @param LeaseContractChildSales 租赁合同子表信息
     * @return 结果
     */
    public int insertLeaseContractChild(LeaseContractChildSales LeaseContractChildSales);

    /**
     * 修改租赁合同子表信息
     *
     * @param LeaseContractChildSales 租赁合同子表信息
     * @return 结果
     */
    public int updateLeaseContractChild(LeaseContractChildSales LeaseContractChildSales);

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
}
