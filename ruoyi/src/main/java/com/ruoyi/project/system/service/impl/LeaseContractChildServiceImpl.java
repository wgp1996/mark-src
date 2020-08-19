package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.LeaseContractChildMapper;
import com.ruoyi.project.system.domain.LeaseContractChild;
import com.ruoyi.project.system.service.ILeaseContractChildService;

/**
 * 租赁合同子表信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-30
 */
@Service
public class LeaseContractChildServiceImpl implements ILeaseContractChildService 
{
    @Autowired
    private LeaseContractChildMapper leaseContractChildMapper;

    /**
     * 查询租赁合同子表信息
     * 
     * @param id 租赁合同子表信息ID
     * @return 租赁合同子表信息
     */
    @Override
    public LeaseContractChild selectLeaseContractChildById(String id)
    {
        return leaseContractChildMapper.selectLeaseContractChildById(id);
    }
    /**
     * 查询租赁合同子表信息
     *
     * @param code 租赁合同主表信息编码
     * @return 租赁合同子表信息
     */
    @Override
    public List<LeaseContractChild> selectLeaseContractChildByCode(String code)
    {
        return leaseContractChildMapper.selectLeaseContractChildByCode(code);
    }

    /**
     * 查询租赁合同子表信息列表
     * 
     * @param leaseContractChild 租赁合同子表信息
     * @return 租赁合同子表信息
     */
    @Override
    public List<LeaseContractChild> selectLeaseContractChildList(LeaseContractChild leaseContractChild)
    {
        return leaseContractChildMapper.selectLeaseContractChildList(leaseContractChild);
    }

    /**
     * 租赁收款查询租赁合同子表信息列表
     *
     * @param leaseContractChild 租赁合同子表信息
     * @return 租赁合同子表信息
     */
    @Override
    public List<LeaseContractChild> selectLeaseByCollection(LeaseContractChild leaseContractChild) {
        return leaseContractChildMapper.selectLeaseByCollection(leaseContractChild);
    }
    /**
     * 销售收款查询销售合同信息列表
     *
     * @param leaseContractChild 销售合同子表信息
     * @return 销售合同子表信息集合
     */
    @Override
    public List<LeaseContractChild> selectLeaseByCollectionSales(LeaseContractChild leaseContractChild){
        return leaseContractChildMapper.selectLeaseByCollectionSales(leaseContractChild);
    }
    /**
     * 物业收款查询合同信息列表
     *
     * @param leaseContractChild 合同信息
     * @return 合同信息
     */
    @Override
    public List<LeaseContractChild> selectLeaseByCollectionAll(LeaseContractChild leaseContractChild) {
        return leaseContractChildMapper.selectLeaseByCollectionAll(leaseContractChild);
    }

    /**
     * 新增租赁合同子表信息
     * 
     * @param leaseContractChild 租赁合同子表信息
     * @return 结果
     */
    @Override
    public int insertLeaseContractChild(LeaseContractChild leaseContractChild)
    {
        leaseContractChild.setCreateTime(DateUtils.getNowDate());
        return leaseContractChildMapper.insertLeaseContractChild(leaseContractChild);
    }

    /**
     * 修改租赁合同子表信息
     * 
     * @param leaseContractChild 租赁合同子表信息
     * @return 结果
     */
    @Override
    public int updateLeaseContractChild(LeaseContractChild leaseContractChild)
    {
        leaseContractChild.setUpdateTime(DateUtils.getNowDate());
        return leaseContractChildMapper.updateLeaseContractChild(leaseContractChild);
    }

    /**
     * 批量删除租赁合同子表信息
     * 
     * @param ids 需要删除的租赁合同子表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildByIds(String[] ids)
    {
        return leaseContractChildMapper.deleteLeaseContractChildByIds(ids);
    }
    /**
     * 批量删除租赁合同子表信息
     *
     * @param ids 需要删除的租赁合同主表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildPid(String[] ids)
    {
        return leaseContractChildMapper.deleteLeaseContractChildPid(ids);
    }

    /**
     * 删除租赁合同子表信息信息
     * 
     * @param id 租赁合同子表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildById(String id)
    {
        return leaseContractChildMapper.deleteLeaseContractChildById(id);
    }
    /**
     * 根据子表ID修改摊位信息
     *
     * @param id 租赁合同子表信息ID
     * @return 结果
     */
    @Override
    public int updateStallInfoById(String id){
        return leaseContractChildMapper.updateStallInfoById(id);
    };


    /**
     * 根据主表ID修改摊位信息
     *
     * @param ids 需要删除的租赁合同主表信息ID
     * @return 结果
     */
    @Override
    public int updateStallInfoByPids(String[] ids){
        return leaseContractChildMapper.updateStallInfoByPids(ids);
    };

}
