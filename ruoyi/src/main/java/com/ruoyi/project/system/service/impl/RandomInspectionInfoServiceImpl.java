package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.RandomInspectionInfoMapper;
import com.ruoyi.project.system.domain.RandomInspectionInfo;
import com.ruoyi.project.system.service.IRandomInspectionInfoService;

/**
 * 随机检测单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Service
public class RandomInspectionInfoServiceImpl implements IRandomInspectionInfoService 
{
    @Autowired
    private RandomInspectionInfoMapper randomInspectionInfoMapper;

    /**
     * 查询随机检测单
     * 
     * @param id 随机检测单ID
     * @return 随机检测单
     */
    @Override
    public RandomInspectionInfo selectRandomInspectionInfoById(String id)
    {
        return randomInspectionInfoMapper.selectRandomInspectionInfoById(id);
    }

    /**
     * 查询随机检测单列表
     * 
     * @param randomInspectionInfo 随机检测单
     * @return 随机检测单
     */
    @Override
    public List<RandomInspectionInfo> selectRandomInspectionInfoList(RandomInspectionInfo randomInspectionInfo)
    {
        return randomInspectionInfoMapper.selectRandomInspectionInfoList(randomInspectionInfo);
    }

    /**
     * 新增随机检测单
     * 
     * @param randomInspectionInfo 随机检测单
     * @return 结果
     */
    @Override
    public int insertRandomInspectionInfo(RandomInspectionInfo randomInspectionInfo)
    {
        randomInspectionInfo.setCreateTime(DateUtils.getNowDate());
        return randomInspectionInfoMapper.insertRandomInspectionInfo(randomInspectionInfo);
    }

    /**
     * 修改随机检测单
     * 
     * @param randomInspectionInfo 随机检测单
     * @return 结果
     */
    @Override
    public int updateRandomInspectionInfo(RandomInspectionInfo randomInspectionInfo)
    {
        randomInspectionInfo.setUpdateTime(DateUtils.getNowDate());
        return randomInspectionInfoMapper.updateRandomInspectionInfo(randomInspectionInfo);
    }

    /**
     * 批量删除随机检测单
     * 
     * @param ids 需要删除的随机检测单ID
     * @return 结果
     */
    @Override
    public int deleteRandomInspectionInfoByIds(String[] ids)
    {
        return randomInspectionInfoMapper.deleteRandomInspectionInfoByIds(ids);
    }

    /**
     * 删除随机检测单信息
     * 
     * @param id 随机检测单ID
     * @return 结果
     */
    @Override
    public int deleteRandomInspectionInfoById(String id)
    {
        return randomInspectionInfoMapper.deleteRandomInspectionInfoById(id);
    }
}
