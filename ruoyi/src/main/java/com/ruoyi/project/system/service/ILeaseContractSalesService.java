package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.LeaseContractSales;

import java.util.List;

/**
 * 销售合同Service接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface ILeaseContractSalesService {
    /**
     * 查询销售合同
     *
     * @param id 销售合同ID
     * @return 销售合同
     */
    public LeaseContractSales selectLeaseContractById(String id);

    /**
     * 查询销售合同
     *
     * @param code 销售合同编码
     * @return 销售合同
     */
    public LeaseContractSales selectLeaseContractByCode(String code, String id);

    /**
     * 查询销售合同列表
     *
     * @param LeaseContractSales 销售合同
     * @return 销售合同集合
     */
    public List<LeaseContractSales> selectLeaseContractList(LeaseContractSales LeaseContractSales);

    /**
     * 新增销售合同
     *
     * @param LeaseContractSales 销售合同
     * @return 结果
     */
    public int insertLeaseContract(LeaseContractSales LeaseContractSales);

    /**
     * 修改销售合同
     *
     * @param LeaseContractSales 销售合同
     * @return 结果
     */
    public int updateLeaseContract(LeaseContractSales LeaseContractSales);

    /**
     * 批量删除销售合同
     *
     * @param ids 需要删除的销售合同ID
     * @return 结果
     */
    public int deleteLeaseContractByIds(String[] ids);

    /**
     * 删除销售合同信息
     *
     * @param id 销售合同ID
     * @return 结果
     */
    public int deleteLeaseContractById(String id);
}
