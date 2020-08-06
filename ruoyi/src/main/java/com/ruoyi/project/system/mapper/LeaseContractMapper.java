package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.LeaseContract;

/**
 * 租赁合同Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-30
 */
public interface LeaseContractMapper 
{
    /**
     * 查询租赁合同
     * 
     * @param id 租赁合同ID
     * @return 租赁合同
     */
    public LeaseContract selectLeaseContractById(String id);

    /**
     * 查询租赁合同
     *
     * @param id   租赁合同ID
     * @param code 租赁合同编号
     * @return 租赁合同
     */
    public LeaseContract selectLeaseContractByCode(String code, String id);

    /**
     * 查询租赁合同列表
     * 
     * @param leaseContract 租赁合同
     * @return 租赁合同集合
     */
    public List<LeaseContract> selectLeaseContractList(LeaseContract leaseContract);

    /**
     * 新增租赁合同
     * 
     * @param leaseContract 租赁合同
     * @return 结果
     */
    public int insertLeaseContract(LeaseContract leaseContract);

    /**
     * 修改租赁合同
     * 
     * @param leaseContract 租赁合同
     * @return 结果
     */
    public int updateLeaseContract(LeaseContract leaseContract);

    /**
     * 删除租赁合同
     * 
     * @param id 租赁合同ID
     * @return 结果
     */
    public int deleteLeaseContractById(String id);

    /**
     * 批量删除租赁合同
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeaseContractByIds(String[] ids);
    /**
     * 批量修改合同状态
     *
     * @param ids 需要修改的合同ID
     * @return 结果
     */
    public int updateLeaseContractStatus(String[] ids);
}
