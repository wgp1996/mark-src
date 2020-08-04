package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.LeaseContractSales;

import java.util.List;

/**
 * 租赁合同Service接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface ILeaseContractSalesService {
    /**
     * 查询租赁合同
     *
     * @param id 租赁合同ID
     * @return 租赁合同
     */
    public LeaseContractSales selectLeaseContractById(String id);

    /**
     * 查询租赁合同
     *
     * @param code 租赁合同编码
     * @return 租赁合同
     */
    public LeaseContractSales selectLeaseContractByCode(String code, String id);

    /**
     * 查询租赁合同列表
     *
     * @param LeaseContractSales 租赁合同
     * @return 租赁合同集合
     */
    public List<LeaseContractSales> selectLeaseContractList(LeaseContractSales LeaseContractSales);

    /**
     * 新增租赁合同
     *
     * @param LeaseContractSales 租赁合同
     * @return 结果
     */
    public int insertLeaseContract(LeaseContractSales LeaseContractSales);

    /**
     * 修改租赁合同
     *
     * @param LeaseContractSales 租赁合同
     * @return 结果
     */
    public int updateLeaseContract(LeaseContractSales LeaseContractSales);

    /**
     * 批量删除租赁合同
     *
     * @param ids 需要删除的租赁合同ID
     * @return 结果
     */
    public int deleteLeaseContractByIds(String[] ids);

    /**
     * 删除租赁合同信息
     *
     * @param id 租赁合同ID
     * @return 结果
     */
    public int deleteLeaseContractById(String id);
}
