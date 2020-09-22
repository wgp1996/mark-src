package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SampAddressMapper;
import com.ruoyi.project.system.domain.SampAddress;
import com.ruoyi.project.system.service.ISampAddressService;

/**
 * 取样地建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Service
public class SampAddressServiceImpl implements ISampAddressService 
{
    @Autowired
    private SampAddressMapper sampAddressMapper;

    /**
     * 查询取样地建档
     * 
     * @param id 取样地建档ID
     * @return 取样地建档
     */
    @Override
    public SampAddress selectSampAddressById(String id)
    {
        return sampAddressMapper.selectSampAddressById(id);
    }

    /**
     * 查询取样地建档列表
     * 
     * @param sampAddress 取样地建档
     * @return 取样地建档
     */
    @Override
    public List<SampAddress> selectSampAddressList(SampAddress sampAddress)
    {
        return sampAddressMapper.selectSampAddressList(sampAddress);
    }

    /**
     * 新增取样地建档
     * 
     * @param sampAddress 取样地建档
     * @return 结果
     */
    @Override
    public int insertSampAddress(SampAddress sampAddress)
    {
        sampAddress.setCreateTime(DateUtils.getNowDate());
        return sampAddressMapper.insertSampAddress(sampAddress);
    }

    /**
     * 修改取样地建档
     * 
     * @param sampAddress 取样地建档
     * @return 结果
     */
    @Override
    public int updateSampAddress(SampAddress sampAddress)
    {
        sampAddress.setUpdateTime(DateUtils.getNowDate());
        return sampAddressMapper.updateSampAddress(sampAddress);
    }

    /**
     * 批量删除取样地建档
     * 
     * @param ids 需要删除的取样地建档ID
     * @return 结果
     */
    @Override
    public int deleteSampAddressByIds(String[] ids)
    {
        return sampAddressMapper.deleteSampAddressByIds(ids);
    }

    /**
     * 删除取样地建档信息
     * 
     * @param id 取样地建档ID
     * @return 结果
     */
    @Override
    public int deleteSampAddressById(String id)
    {
        return sampAddressMapper.deleteSampAddressById(id);
    }
}
