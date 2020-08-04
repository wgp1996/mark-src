package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.LeaseContractChildPool;
import com.ruoyi.project.system.mapper.LeaseContractChildPoolMapper;
import com.ruoyi.project.system.service.ILeaseContractChildPoolService;
import com.ruoyi.project.system.service.ILeaseContractChildPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联营合同子表信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@Service
public class LeaseContractChildPoolServiceImpl implements ILeaseContractChildPoolService {
    @Autowired
    private LeaseContractChildPoolMapper LeaseContractChildPoolMapper;

    /**
     * 查询联营合同子表信息
     *
     * @param id 联营合同子表信息ID
     * @return 联营合同子表信息
     */
    @Override
    public LeaseContractChildPool selectLeaseContractChildById(String id) {
        return LeaseContractChildPoolMapper.selectLeaseContractChildById(id);
    }

    /**
     * 查询联营合同子表信息
     *
     * @param code 联营合同主表信息编码
     * @return 联营合同子表信息
     */
    @Override
    public List<LeaseContractChildPool> selectLeaseContractChildByCode(String code) {
        return LeaseContractChildPoolMapper.selectLeaseContractChildByCode(code);
    }

    /**
     * 查询联营合同子表信息列表
     *
     * @param LeaseContractChildPool 联营合同子表信息
     * @return 联营合同子表信息
     */
    @Override
    public List<LeaseContractChildPool> selectLeaseContractChildList(LeaseContractChildPool LeaseContractChildPool) {
        return LeaseContractChildPoolMapper.selectLeaseContractChildList(LeaseContractChildPool);
    }

    /**
     * 新增联营合同子表信息
     *
     * @param LeaseContractChildPool 联营合同子表信息
     * @return 结果
     */
    @Override
    public int insertLeaseContractChild(LeaseContractChildPool LeaseContractChildPool) {
        LeaseContractChildPool.setCreateTime(DateUtils.getNowDate());
        return LeaseContractChildPoolMapper.insertLeaseContractChild(LeaseContractChildPool);
    }

    /**
     * 修改联营合同子表信息
     *
     * @param LeaseContractChildPool 联营合同子表信息
     * @return 结果
     */
    @Override
    public int updateLeaseContractChild(LeaseContractChildPool LeaseContractChildPool) {
        LeaseContractChildPool.setUpdateTime(DateUtils.getNowDate());
        return LeaseContractChildPoolMapper.updateLeaseContractChild(LeaseContractChildPool);
    }

    /**
     * 批量删除联营合同子表信息
     *
     * @param ids 需要删除的联营合同子表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildByIds(String[] ids) {
        return LeaseContractChildPoolMapper.deleteLeaseContractChildByIds(ids);
    }

    /**
     * 批量删除联营合同子表信息
     *
     * @param ids 需要删除的联营合同主表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildPid(String[] ids) {
        return LeaseContractChildPoolMapper.deleteLeaseContractChildPid(ids);
    }

    /**
     * 删除联营合同子表信息信息
     *
     * @param id 联营合同子表信息ID
     * @return 结果
     */
    @Override
    public int deleteLeaseContractChildById(String id) {
        return LeaseContractChildPoolMapper.deleteLeaseContractChildById(id);
    }
}
