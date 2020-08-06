package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.LeaseContractChildSales;
import com.ruoyi.project.system.mapper.LeaseContractChildSalesMapper;
import com.ruoyi.project.system.mapper.LeaseContractChildSalesMapper;
import com.ruoyi.project.system.service.ILeaseContractChildSalesService;
import com.ruoyi.project.system.service.ILeaseContractChildSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售合同子表信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@Service
public class LeaseContractChildSalesServiceImpl implements ILeaseContractChildSalesService {
    @Autowired
    private LeaseContractChildSalesMapper LeaseContractChildSalesMapper;

    /**
     * 查询销售合同子表信息
     *
     * @param id 销售合同子表信息ID
     * @return 销售合同子表信息
     */
    @Override
    public LeaseContractChildSales selectLeaseContractChildById(String id) {
        return LeaseContractChildSalesMapper.selectLeaseContractChildById(id);
    }

    /**
     * 查询销售合同子表信息
     *
     * @param code 销售合同主表信息编码
     * @return 销售合同子表信息
     */
    @Override
    public List<LeaseContractChildSales> selectLeaseContractChildByCode(String code) {
        return LeaseContractChildSalesMapper.selectLeaseContractChildByCode(code);
    }

    /**
     * 查询销售合同子表信息列表
     *
     * @param LeaseContractChildSales 销售合同子表信息
     * @return 销售合同子表信息
     */
    @Override
    public List<LeaseContractChildSales> selectLeaseContractChildList(LeaseContractChildSales LeaseContractChildSales) {
        return LeaseContractChildSalesMapper.selectLeaseContractChildList(LeaseContractChildSales);
    }

    /**
     * 新增销售合同子表信息
     *
     * @param LeaseContractChildSales 销售合同子表信息
     * @return 结果
     */
    @Override
    public int insertLeaseContractChild(LeaseContractChildSales LeaseContractChildSales) {
        LeaseContractChildSales.setCreateTime(DateUtils.getNowDate());
        return LeaseContractChildSalesMapper.insertLeaseContractChild(LeaseContractChildSales);
    }

    /**
     * 修改销售合同子表信息
     *
     * @param LeaseContractChildSales 销售合同子表信息
     * @return 结果
     */
    @Override
    public int updateLeaseContractChild(LeaseContractChildSales LeaseContractChildSales) {
        LeaseContractChildSales.setUpdateTime(DateUtils.getNowDate());
        return LeaseContractChildSalesMapper.updateLeaseContractChild(LeaseContractChildSales);
    }

    /**
     * 批量删除销售合同子表信息
     *
     * @param ids 需要删除的销售合同子表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildByIds(String[] ids) {
        return LeaseContractChildSalesMapper.deleteLeaseContractChildByIds(ids);
    }

    /**
     * 批量删除销售合同子表信息
     *
     * @param ids 需要删除的销售合同主表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildPid(String[] ids) {
        return LeaseContractChildSalesMapper.deleteLeaseContractChildPid(ids);
    }

    /**
     * 删除销售合同子表信息信息
     *
     * @param id 销售合同子表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildById(String id) {
        return LeaseContractChildSalesMapper.deleteLeaseContractChildById(id);
    }
    /**
     * 根据子表ID修改摊位信息
     *
     * @param id 租赁合同子表信息ID
     * @return 结果
     */
    @Override
    public int updateStallInfoById(String id){
        return LeaseContractChildSalesMapper.updateStallInfoById(id);
    };


    /**
     * 根据主表ID修改摊位信息
     *
     * @param ids 需要删除的租赁合同主表信息ID
     * @return 结果
     */
    @Override
    public int updateStallInfoByPids(String[] ids){
        return LeaseContractChildSalesMapper.updateStallInfoByPids(ids);
    };
}
