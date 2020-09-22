package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.ResultInfo;

/**
 * 订单退回Service接口
 * 
 * @author ruoyi
 * @date 2020-09-16
 */
public interface IResultInfoService 
{
    /**
     * 查询订单退回
     * 
     * @param id 订单退回ID
     * @return 订单退回
     */
    public ResultInfo selectResultInfoById(String id);

    /**
     * 查询订单退回列表
     * 
     * @param resultInfo 订单退回
     * @return 订单退回集合
     */
    public List<ResultInfo> selectResultInfoList(ResultInfo resultInfo);

    /**
     * 新增订单退回
     * 
     * @param resultInfo 订单退回
     * @return 结果
     */
    public int insertResultInfo(ResultInfo resultInfo);

    /**
     * 修改订单退回
     * 
     * @param resultInfo 订单退回
     * @return 结果
     */
    public int updateResultInfo(ResultInfo resultInfo);

    /**
     * 批量删除订单退回
     * 
     * @param ids 需要删除的订单退回ID
     * @return 结果
     */
    public int deleteResultInfoByIds(String[] ids);

    /**
     * 删除订单退回信息
     * 
     * @param id 订单退回ID
     * @return 结果
     */
    public int deleteResultInfoById(String id);
}
