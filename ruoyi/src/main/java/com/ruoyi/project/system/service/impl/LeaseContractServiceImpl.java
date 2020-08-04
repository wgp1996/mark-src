package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.LeaseContractMapper;
import com.ruoyi.project.system.domain.LeaseContract;
import com.ruoyi.project.system.service.ILeaseContractService;

/**
 * 租赁合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-30
 */
@Service
public class LeaseContractServiceImpl implements ILeaseContractService 
{
    @Autowired
    private LeaseContractMapper leaseContractMapper;

    /**
     * 查询租赁合同
     * 
     * @param id 租赁合同ID
     * @return 租赁合同
     */
    @Override
    public LeaseContract selectLeaseContractById(String id)
    {
        return leaseContractMapper.selectLeaseContractById(id);
    }

    /**
     * 查询租赁合同
     *
     * @param code 租赁合同编号
     * @param id   租赁合同ID
     * @return 租赁合同
     */
    @Override
    public LeaseContract selectLeaseContractByCode(String code, String id) {
        return leaseContractMapper.selectLeaseContractByCode(code, id);
    }

    /**
     * 查询租赁合同列表
     * 
     * @param leaseContract 租赁合同
     * @return 租赁合同
     */
    @Override
    public List<LeaseContract> selectLeaseContractList(LeaseContract leaseContract)
    {
        return leaseContractMapper.selectLeaseContractList(leaseContract);
    }

    /**
     * 新增租赁合同
     * 
     * @param leaseContract 租赁合同
     * @return 结果
     */
    @Override
    public int insertLeaseContract(LeaseContract leaseContract)
    {
        leaseContract.setCreateTime(DateUtils.getNowDate());
        return leaseContractMapper.insertLeaseContract(leaseContract);
    }

    /**
     * 修改租赁合同
     * 
     * @param leaseContract 租赁合同
     * @return 结果
     */
    @Override
    public int updateLeaseContract(LeaseContract leaseContract)
    {
        leaseContract.setUpdateTime(DateUtils.getNowDate());
        return leaseContractMapper.updateLeaseContract(leaseContract);
    }

    /**
     * 批量删除租赁合同
     * 
     * @param ids 需要删除的租赁合同ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractByIds(String[] ids)
    {
        return leaseContractMapper.deleteLeaseContractByIds(ids);
    }

    /**
     * 删除租赁合同信息
     * 
     * @param id 租赁合同ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractById(String id)
    {
        return leaseContractMapper.deleteLeaseContractById(id);
    }
}
