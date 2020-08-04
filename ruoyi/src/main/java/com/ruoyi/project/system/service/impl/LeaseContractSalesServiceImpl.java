package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.LeaseContract;
import com.ruoyi.project.system.domain.LeaseContractSales;
import com.ruoyi.project.system.mapper.LeaseContractMapper;
import com.ruoyi.project.system.mapper.LeaseContractSalesMapper;
import com.ruoyi.project.system.service.ILeaseContractSalesService;
import com.ruoyi.project.system.service.ILeaseContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售合同Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@Service
public class LeaseContractSalesServiceImpl implements ILeaseContractSalesService {
    @Autowired
    private LeaseContractSalesMapper leaseContractMapper;

    /**
     * 查询销售合同
     *
     * @param id 销售合同ID
     * @return 销售合同
     */
    @Override
    public LeaseContractSales selectLeaseContractById(String id) {
        return leaseContractMapper.selectLeaseContractById(id);
    }

    /**
     * 查询销售合同
     *
     * @param code 销售合同编号
     * @param id   销售合同ID
     * @return 销售合同
     */
    @Override
    public LeaseContractSales selectLeaseContractByCode(String code, String id) {
        return leaseContractMapper.selectLeaseContractByCode(code, id);
    }

    /**
     * 查询销售合同列表
     *
     * @param leaseContract 销售合同
     * @return 销售合同
     */
    @Override
    public List<LeaseContractSales> selectLeaseContractList(LeaseContractSales leaseContract) {
        return leaseContractMapper.selectLeaseContractList(leaseContract);
    }

    /**
     * 新增销售合同
     *
     * @param leaseContract 销售合同
     * @return 结果
     */
    @Override
    public int insertLeaseContract(LeaseContractSales leaseContract) {
        leaseContract.setCreateTime(DateUtils.getNowDate());
        return leaseContractMapper.insertLeaseContract(leaseContract);
    }

    /**
     * 修改销售合同
     *
     * @param leaseContract 销售合同
     * @return 结果
     */
    @Override
    public int updateLeaseContract(LeaseContractSales leaseContract) {
        leaseContract.setUpdateTime(DateUtils.getNowDate());
        return leaseContractMapper.updateLeaseContract(leaseContract);
    }

    /**
     * 批量删除销售合同
     *
     * @param ids 需要删除的销售合同ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractByIds(String[] ids) {
        return leaseContractMapper.deleteLeaseContractByIds(ids);
    }

    /**
     * 删除销售合同信息
     *
     * @param id 销售合同ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractById(String id) {
        return leaseContractMapper.deleteLeaseContractById(id);
    }
}
