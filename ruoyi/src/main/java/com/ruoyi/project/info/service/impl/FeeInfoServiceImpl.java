package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.FeeInfoMapper;
import com.ruoyi.project.info.domain.FeeInfo;
import com.ruoyi.project.info.service.IFeeInfoService;

/**
 * 帐夹Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-16
 */
@Service
public class FeeInfoServiceImpl implements IFeeInfoService 
{
    @Autowired
    private FeeInfoMapper feeInfoMapper;

    /**
     * 查询帐夹
     * 
     * @param id 帐夹ID
     * @return 帐夹
     */
    @Override
    public FeeInfo selectFeeInfoById(Long id)
    {
        return feeInfoMapper.selectFeeInfoById(id);
    }

    /**
     * 查询帐夹列表
     * 
     * @param feeInfo 帐夹
     * @return 帐夹
     */
    @Override
    public List<FeeInfo> selectFeeInfoList(FeeInfo feeInfo)
    {
        return feeInfoMapper.selectFeeInfoList(feeInfo);
    }

    /**
     * 新增帐夹
     * 
     * @param feeInfo 帐夹
     * @return 结果
     */
    @Override
    public int insertFeeInfo(FeeInfo feeInfo)
    {
        feeInfo.setCreateTime(DateUtils.getNowDate());
        return feeInfoMapper.insertFeeInfo(feeInfo);
    }

    /**
     * 修改帐夹
     * 
     * @param feeInfo 帐夹
     * @return 结果
     */
    @Override
    public int updateFeeInfo(FeeInfo feeInfo)
    {
        feeInfo.setUpdateTime(DateUtils.getNowDate());
        return feeInfoMapper.updateFeeInfo(feeInfo);
    }

    /**
     * 批量删除帐夹
     * 
     * @param ids 需要删除的帐夹ID
     * @return 结果
     */
    @Override
    public int deleteFeeInfoByIds(Long[] ids)
    {
        return feeInfoMapper.deleteFeeInfoByIds(ids);
    }

    /**
     * 删除帐夹信息
     * 
     * @param id 帐夹ID
     * @return 结果
     */
    @Override
    public int deleteFeeInfoById(Long id)
    {
        return feeInfoMapper.deleteFeeInfoById(id);
    }
}
