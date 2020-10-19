package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.RandomInspectionInfoChildMapper;
import com.ruoyi.project.system.domain.RandomInspectionInfoChild;
import com.ruoyi.project.system.service.IRandomInspectionInfoChildService;

/**
 * 检测单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Service
public class RandomInspectionInfoChildServiceImpl implements IRandomInspectionInfoChildService 
{
    @Autowired
    private RandomInspectionInfoChildMapper randomInspectionInfoChildMapper;

    /**
     * 查询检测单明细
     * 
     * @param id 检测单明细ID
     * @return 检测单明细
     */
    @Override
    public RandomInspectionInfoChild selectRandomInspectionInfoChildById(String id)
    {
        return randomInspectionInfoChildMapper.selectRandomInspectionInfoChildById(id);
    }

    /**
     * 查询检测单所有明细列表
     *
     * @param randomInspectionInfoChild 检测单明细
     * @return 检测单明细集合
     */
    @Override
    public List<RandomInspectionInfoChild> selectRandomInspectionInfoAllList(RandomInspectionInfoChild randomInspectionInfoChild){
        return randomInspectionInfoChildMapper.selectRandomInspectionInfoAllList(randomInspectionInfoChild);
    }

    /**
     * 查询检测单明细列表
     * 
     * @param randomInspectionInfoChild 检测单明细
     * @return 检测单明细
     */
    @Override
    public List<RandomInspectionInfoChild> selectRandomInspectionInfoChildList(RandomInspectionInfoChild randomInspectionInfoChild)
    {
        return randomInspectionInfoChildMapper.selectRandomInspectionInfoChildList(randomInspectionInfoChild);
    }

    /**
     * 新增检测单明细
     * 
     * @param randomInspectionInfoChild 检测单明细
     * @return 结果
     */
    @Override
    public int insertRandomInspectionInfoChild(RandomInspectionInfoChild randomInspectionInfoChild)
    {
        randomInspectionInfoChild.setCreateTime(DateUtils.getNowDate());
        return randomInspectionInfoChildMapper.insertRandomInspectionInfoChild(randomInspectionInfoChild);
    }

    /**
     * 修改检测单明细
     * 
     * @param randomInspectionInfoChild 检测单明细
     * @return 结果
     */
    @Override
    public int updateRandomInspectionInfoChild(RandomInspectionInfoChild randomInspectionInfoChild)
    {
        randomInspectionInfoChild.setUpdateTime(DateUtils.getNowDate());
        return randomInspectionInfoChildMapper.updateRandomInspectionInfoChild(randomInspectionInfoChild);
    }

    /**
     * 批量删除检测单明细
     * 
     * @param ids 需要删除的检测单明细ID
     * @return 结果
     */
    @Override
    public int deleteRandomInspectionInfoChildByIds(String[] ids)
    {
        return randomInspectionInfoChildMapper.deleteRandomInspectionInfoChildByIds(ids);
    }
    /**
     * 批量删除检测单明细
     *
     * @param ids 需要删除的检测单ID
     * @return 结果
     */
    public int deleteRandomInspectionInfoChildByPid(String[] ids){
        return randomInspectionInfoChildMapper.deleteRandomInspectionInfoChildByPid(ids);
    }

    /**
     * 删除检测单明细信息
     * 
     * @param id 检测单明细ID
     * @return 结果
     */
    @Override
    public int deleteRandomInspectionInfoChildById(String id)
    {
        return randomInspectionInfoChildMapper.deleteRandomInspectionInfoChildById(id);
    }
}
