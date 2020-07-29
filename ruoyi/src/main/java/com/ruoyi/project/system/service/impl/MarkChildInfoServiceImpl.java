package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.MarkInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.MarkChildInfoMapper;
import com.ruoyi.project.system.domain.MarkChildInfo;
import com.ruoyi.project.system.service.IMarkChildInfoService;

/**
 * 二级市场信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
@Service
public class MarkChildInfoServiceImpl implements IMarkChildInfoService 
{
    @Autowired
    private MarkChildInfoMapper markChildInfoMapper;

    /**
     * 查询二级市场信息
     * 
     * @param id 二级市场信息ID
     * @return 二级市场信息
     */
    @Override
    public MarkChildInfo selectMarkChildInfoById(String id)
    {
        return markChildInfoMapper.selectMarkChildInfoById(id);
    }
    /**
     * 查询【市场基本信息】
     *
     * @param name 【市场基本信息】名称
     * @return 【市场基本信息】
     */
    @Override
    public MarkChildInfo selectMarkChildInfoByName(String name, String id)
    {
        return markChildInfoMapper.selectMarkChildInfoByName(name,id);
    }

    /**
     * 查询【市场基本信息】
     *
     * @return 【市场基本信息】
     */
    @Override
    public List<MarkInfo> selectMarkData()
    {
        return markChildInfoMapper.selectMarkData();
    }

    /**
     * 查询二级市场信息列表
     * 
     * @param markChildInfo 二级市场信息
     * @return 二级市场信息
     */
    @Override
    public List<MarkChildInfo> selectMarkChildInfoList(MarkChildInfo markChildInfo)
    {
        return markChildInfoMapper.selectMarkChildInfoList(markChildInfo);
    }

    /**
     * 新增二级市场信息
     * 
     * @param markChildInfo 二级市场信息
     * @return 结果
     */
    @Override
    public int insertMarkChildInfo(MarkChildInfo markChildInfo)
    {
        markChildInfo.setCreateTime(DateUtils.getNowDate());
        return markChildInfoMapper.insertMarkChildInfo(markChildInfo);
    }

    /**
     * 修改二级市场信息
     * 
     * @param markChildInfo 二级市场信息
     * @return 结果
     */
    @Override
    public int updateMarkChildInfo(MarkChildInfo markChildInfo)
    {
        markChildInfo.setUpdateTime(DateUtils.getNowDate());
        return markChildInfoMapper.updateMarkChildInfo(markChildInfo);
    }

    /**
     * 批量删除二级市场信息
     * 
     * @param ids 需要删除的二级市场信息ID
     * @return 结果
     */
    @Override
    public int deleteMarkChildInfoByIds(String[] ids)
    {
        return markChildInfoMapper.deleteMarkChildInfoByIds(ids);
    }

    /**
     * 删除二级市场信息信息
     * 
     * @param id 二级市场信息ID
     * @return 结果
     */
    @Override
    public int deleteMarkChildInfoById(String id)
    {
        return markChildInfoMapper.deleteMarkChildInfoById(id);
    }
}
