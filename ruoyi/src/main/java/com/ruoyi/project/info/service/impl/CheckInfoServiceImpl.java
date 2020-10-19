package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.CheckInfoMapper;
import com.ruoyi.project.info.domain.CheckInfo;
import com.ruoyi.project.info.service.ICheckInfoService;

/**
 * 盘点单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
@Service
public class CheckInfoServiceImpl implements ICheckInfoService 
{
    @Autowired
    private CheckInfoMapper checkInfoMapper;

    /**
     * 查询盘点单
     * 
     * @param id 盘点单ID
     * @return 盘点单
     */
    @Override
    public CheckInfo selectCheckInfoById(String id)
    {
        return checkInfoMapper.selectCheckInfoById(id);
    }

    /**
     * 查询盘点单列表
     * 
     * @param checkInfo 盘点单
     * @return 盘点单
     */
    @Override
    public List<CheckInfo> selectCheckInfoList(CheckInfo checkInfo)
    {
        return checkInfoMapper.selectCheckInfoList(checkInfo);
    }

    /**
     * 新增盘点单
     * 
     * @param checkInfo 盘点单
     * @return 结果
     */
    @Override
    public int insertCheckInfo(CheckInfo checkInfo)
    {
        checkInfo.setCreateTime(DateUtils.getNowDate());
        return checkInfoMapper.insertCheckInfo(checkInfo);
    }

    /**
     * 修改盘点单
     * 
     * @param checkInfo 盘点单
     * @return 结果
     */
    @Override
    public int updateCheckInfo(CheckInfo checkInfo)
    {
        checkInfo.setUpdateTime(DateUtils.getNowDate());
        return checkInfoMapper.updateCheckInfo(checkInfo);
    }

    /**
     * 批量删除盘点单
     * 
     * @param ids 需要删除的盘点单ID
     * @return 结果
     */
    @Override
    public int deleteCheckInfoByIds(String[] ids)
    {
        return checkInfoMapper.deleteCheckInfoByIds(ids);
    }

    /**
     * 删除盘点单信息
     * 
     * @param id 盘点单ID
     * @return 结果
     */
    @Override
    public int deleteCheckInfoById(String id)
    {
        return checkInfoMapper.deleteCheckInfoById(id);
    }
}
