package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.CheckInfoChildMapper;
import com.ruoyi.project.info.domain.CheckInfoChild;
import com.ruoyi.project.info.service.ICheckInfoChildService;

/**
 * 盘点单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
@Service
public class CheckInfoChildServiceImpl implements ICheckInfoChildService 
{
    @Autowired
    private CheckInfoChildMapper checkInfoChildMapper;

    /**
     * 查询盘点单子表
     * 
     * @param id 盘点单子表ID
     * @return 盘点单子表
     */
    @Override
    public CheckInfoChild selectCheckInfoChildById(String id)
    {
        return checkInfoChildMapper.selectCheckInfoChildById(id);
    }

    /**
     * 查询盘点单子表列表
     * 
     * @param checkInfoChild 盘点单子表
     * @return 盘点单子表
     */
    @Override
    public List<CheckInfoChild> selectCheckInfoChildList(CheckInfoChild checkInfoChild)
    {
        return checkInfoChildMapper.selectCheckInfoChildList(checkInfoChild);
    }

    /**
     * 新增盘点单子表
     * 
     * @param checkInfoChild 盘点单子表
     * @return 结果
     */
    @Override
    public int insertCheckInfoChild(CheckInfoChild checkInfoChild)
    {
        checkInfoChild.setCreateTime(DateUtils.getNowDate());
        return checkInfoChildMapper.insertCheckInfoChild(checkInfoChild);
    }

    /**
     * 修改盘点单子表
     * 
     * @param checkInfoChild 盘点单子表
     * @return 结果
     */
    @Override
    public int updateCheckInfoChild(CheckInfoChild checkInfoChild)
    {
        checkInfoChild.setUpdateTime(DateUtils.getNowDate());
        return checkInfoChildMapper.updateCheckInfoChild(checkInfoChild);
    }

    /**
     * 批量删除盘点单子表
     * 
     * @param ids 需要删除的盘点单子表ID
     * @return 结果
     */
    @Override
    public int deleteCheckInfoChildByIds(String[] ids)
    {
        return checkInfoChildMapper.deleteCheckInfoChildByIds(ids);
    }
    /**
     * 根据主表 id批量删除盘点单子表
     *
     * @param ids 需要删除的盘点单主表ID
     * @return 结果
     */
    @Override
    public int deleteCheckInfoChildByPid(String[] ids){
        return checkInfoChildMapper.deleteCheckInfoChildByPid(ids);
    }
    /**
     * 删除盘点单子表信息
     * 
     * @param id 盘点单子表ID
     * @return 结果
     */
    @Override
    public int deleteCheckInfoChildById(String id)
    {
        return checkInfoChildMapper.deleteCheckInfoChildById(id);
    }
}
