package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.MarkInfoMapper;
import com.ruoyi.project.system.domain.MarkInfo;
import com.ruoyi.project.system.service.IMarkInfoService;

/**
 * 【市场基本信息】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
@Service
public class MarkInfoServiceImpl implements IMarkInfoService 
{
    @Autowired
    private MarkInfoMapper markInfoMapper;

    /**
     * 查询【市场基本信息】
     * 
     * @param name 【市场基本信息】名称
     * @return 【市场基本信息】
     */
    @Override
    public MarkInfo selectMarkInfoByName(String name,String id)
    {
        return markInfoMapper.selectMarkInfoByName(name,id);
    }
    /**
     * 查询【市场基本信息】
     *
     * @param id 【市场基本信息】ID
     * @return 【市场基本信息】
     */
    @Override
    public MarkInfo selectMarkInfoById(String id)
    {
        return markInfoMapper.selectMarkInfoById(id);
    }

    /**
     * 查询【市场基本信息】列表
     * 
     * @param markInfo 【市场基本信息】
     * @return 【市场基本信息】
     */
    @Override
    public List<MarkInfo> selectMarkInfoList(MarkInfo markInfo)
    {
        return markInfoMapper.selectMarkInfoList(markInfo);
    }

    /**
     * 新增【市场基本信息】
     * 
     * @param markInfo 【市场基本信息】
     * @return 结果
     */
    @Override
    public int insertMarkInfo(MarkInfo markInfo)
    {
        markInfo.setCreateTime(DateUtils.getNowDate());
        return markInfoMapper.insertMarkInfo(markInfo);
    }

    /**
     * 修改【市场基本信息】
     * 
     * @param markInfo 【市场基本信息】
     * @return 结果
     */
    @Override
    public int updateMarkInfo(MarkInfo markInfo)
    {
        return markInfoMapper.updateMarkInfo(markInfo);
    }

    /**
     * 批量删除【市场基本信息】
     * 
     * @param ids 需要删除的【市场基本信息】ID
     * @return 结果
     */
    @Override
    public int deleteMarkInfoByIds(String[] ids)
    {
        return markInfoMapper.deleteMarkInfoByIds(ids);
    }

    /**
     * 删除【市场基本信息】信息
     * 
     * @param id 【市场基本信息】ID
     * @return 结果
     */
    @Override
    public int deleteMarkInfoById(String id)
    {
        return markInfoMapper.deleteMarkInfoById(id);
    }
}
