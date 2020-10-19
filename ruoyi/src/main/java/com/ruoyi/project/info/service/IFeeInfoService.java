package com.ruoyi.project.info.service;

import java.util.List;
import com.ruoyi.project.info.domain.FeeInfo;

/**
 * 帐夹Service接口
 * 
 * @author ruoyi
 * @date 2020-10-16
 */
public interface IFeeInfoService 
{
    /**
     * 查询帐夹
     * 
     * @param id 帐夹ID
     * @return 帐夹
     */
    public FeeInfo selectFeeInfoById(Long id);

    /**
     * 查询帐夹列表
     * 
     * @param feeInfo 帐夹
     * @return 帐夹集合
     */
    public List<FeeInfo> selectFeeInfoList(FeeInfo feeInfo);

    /**
     * 新增帐夹
     * 
     * @param feeInfo 帐夹
     * @return 结果
     */
    public int insertFeeInfo(FeeInfo feeInfo);

    /**
     * 修改帐夹
     * 
     * @param feeInfo 帐夹
     * @return 结果
     */
    public int updateFeeInfo(FeeInfo feeInfo);

    /**
     * 批量删除帐夹
     * 
     * @param ids 需要删除的帐夹ID
     * @return 结果
     */
    public int deleteFeeInfoByIds(Long[] ids);

    /**
     * 删除帐夹信息
     * 
     * @param id 帐夹ID
     * @return 结果
     */
    public int deleteFeeInfoById(Long id);
}
