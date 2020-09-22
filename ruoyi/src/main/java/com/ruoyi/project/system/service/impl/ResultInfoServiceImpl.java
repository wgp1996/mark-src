package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ResultInfoMapper;
import com.ruoyi.project.system.domain.ResultInfo;
import com.ruoyi.project.system.service.IResultInfoService;

/**
 * 订单退回Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-16
 */
@Service
public class ResultInfoServiceImpl implements IResultInfoService 
{
    @Autowired
    private ResultInfoMapper resultInfoMapper;

    /**
     * 查询订单退回
     * 
     * @param id 订单退回ID
     * @return 订单退回
     */
    @Override
    public ResultInfo selectResultInfoById(String id)
    {
        return resultInfoMapper.selectResultInfoById(id);
    }

    /**
     * 查询订单退回列表
     * 
     * @param resultInfo 订单退回
     * @return 订单退回
     */
    @Override
    public List<ResultInfo> selectResultInfoList(ResultInfo resultInfo)
    {
        return resultInfoMapper.selectResultInfoList(resultInfo);
    }

    /**
     * 新增订单退回
     * 
     * @param resultInfo 订单退回
     * @return 结果
     */
    @Override
    public int insertResultInfo(ResultInfo resultInfo)
    {
        return resultInfoMapper.insertResultInfo(resultInfo);
    }

    /**
     * 修改订单退回
     * 
     * @param resultInfo 订单退回
     * @return 结果
     */
    @Override
    public int updateResultInfo(ResultInfo resultInfo)
    {
        return resultInfoMapper.updateResultInfo(resultInfo);
    }

    /**
     * 批量删除订单退回
     * 
     * @param ids 需要删除的订单退回ID
     * @return 结果
     */
    @Override
    public int deleteResultInfoByIds(String[] ids)
    {
        return resultInfoMapper.deleteResultInfoByIds(ids);
    }

    /**
     * 删除订单退回信息
     * 
     * @param id 订单退回ID
     * @return 结果
     */
    @Override
    public int deleteResultInfoById(String id)
    {
        return resultInfoMapper.deleteResultInfoById(id);
    }
}
