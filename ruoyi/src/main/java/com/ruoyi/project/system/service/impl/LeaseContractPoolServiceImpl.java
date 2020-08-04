package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.LeaseContractPool;
import com.ruoyi.project.system.mapper.LeaseContractPoolMapper;
import com.ruoyi.project.system.service.ILeaseContractPoolService;
import com.ruoyi.project.system.service.ILeaseContractPoolService;
import com.ruoyi.project.system.service.ILeaseContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联营合同Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@Service
public class LeaseContractPoolServiceImpl implements ILeaseContractPoolService {
    @Autowired
    private LeaseContractPoolMapper leaseContractMapper;

    /**
     * 查询联营合同
     *
     * @param id 联营合同ID
     * @return 联营合同
     */
    @Override
    public LeaseContractPool selectLeaseContractById(String id) {
        return leaseContractMapper.selectLeaseContractById(id);
    }

    /**
     * 查询联营合同
     *
     * @param code 联营合同编号
     * @param id   联营合同ID
     * @return 联营合同
     */
    @Override
    public LeaseContractPool selectLeaseContractByCode(String code, String id) {
        return leaseContractMapper.selectLeaseContractByCode(code, id);
    }

    /**
     * 查询联营合同列表
     *
     * @param leaseContract 联营合同
     * @return 联营合同
     */
    @Override
    public List<LeaseContractPool> selectLeaseContractList(LeaseContractPool leaseContract) {
        return leaseContractMapper.selectLeaseContractList(leaseContract);
    }

    /**
     * 新增联营合同
     *
     * @param leaseContract 联营合同
     * @return 结果
     */
    @Override
    public int insertLeaseContract(LeaseContractPool leaseContract) {
        leaseContract.setCreateTime(DateUtils.getNowDate());
        return leaseContractMapper.insertLeaseContract(leaseContract);
    }

    /**
     * 修改联营合同
     *
     * @param leaseContract 联营合同
     * @return 结果
     */
    @Override
    public int updateLeaseContract(LeaseContractPool leaseContract) {
        leaseContract.setUpdateTime(DateUtils.getNowDate());
        return leaseContractMapper.updateLeaseContract(leaseContract);
    }

    /**
     * 批量删除联营合同
     *
     * @param ids 需要删除的联营合同ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractByIds(String[] ids) {
        return leaseContractMapper.deleteLeaseContractByIds(ids);
    }

    /**
     * 删除联营合同信息
     *
     * @param id 联营合同ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractById(String id) {
        return leaseContractMapper.deleteLeaseContractById(id);
    }
}
