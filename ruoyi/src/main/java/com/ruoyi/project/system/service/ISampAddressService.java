package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.SampAddress;

/**
 * 取样地建档Service接口
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public interface ISampAddressService 
{
    /**
     * 查询取样地建档
     * 
     * @param id 取样地建档ID
     * @return 取样地建档
     */
    public SampAddress selectSampAddressById(String id);

    /**
     * 查询取样地建档列表
     * 
     * @param sampAddress 取样地建档
     * @return 取样地建档集合
     */
    public List<SampAddress> selectSampAddressList(SampAddress sampAddress);

    /**
     * 新增取样地建档
     * 
     * @param sampAddress 取样地建档
     * @return 结果
     */
    public int insertSampAddress(SampAddress sampAddress);

    /**
     * 修改取样地建档
     * 
     * @param sampAddress 取样地建档
     * @return 结果
     */
    public int updateSampAddress(SampAddress sampAddress);

    /**
     * 批量删除取样地建档
     * 
     * @param ids 需要删除的取样地建档ID
     * @return 结果
     */
    public int deleteSampAddressByIds(String[] ids);

    /**
     * 删除取样地建档信息
     * 
     * @param id 取样地建档ID
     * @return 结果
     */
    public int deleteSampAddressById(String id);
}
