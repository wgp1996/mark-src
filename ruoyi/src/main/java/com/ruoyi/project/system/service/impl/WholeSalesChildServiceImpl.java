package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.WholeSalesChildMapper;
import com.ruoyi.project.system.domain.WholeSalesChild;
import com.ruoyi.project.system.service.IWholeSalesChildService;

/**
 * 批批发销货单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
@Service
public class WholeSalesChildServiceImpl implements IWholeSalesChildService 
{
    @Autowired
    private WholeSalesChildMapper wholeSalesChildMapper;

    /**
     * 查询批批发销货单子表
     * 
     * @param id 批批发销货单子表ID
     * @return 批批发销货单子表
     */
    @Override
    public WholeSalesChild selectWholeSalesChildById(String id)
    {
        return wholeSalesChildMapper.selectWholeSalesChildById(id);
    }

    /**
     * 查询批批发销货单子表列表
     * 
     * @param wholeSalesChild 批批发销货单子表
     * @return 批批发销货单子表
     */
    @Override
    public List<WholeSalesChild> selectWholeSalesChildList(WholeSalesChild wholeSalesChild)
    {
        return wholeSalesChildMapper.selectWholeSalesChildList(wholeSalesChild);
    }

    /**
     * 新增批批发销货单子表
     * 
     * @param wholeSalesChild 批批发销货单子表
     * @return 结果
     */
    @Override
    public int insertWholeSalesChild(WholeSalesChild wholeSalesChild)
    {
        wholeSalesChild.setCreateTime(DateUtils.getNowDate());
        return wholeSalesChildMapper.insertWholeSalesChild(wholeSalesChild);
    }

    /**
     * 修改批批发销货单子表
     * 
     * @param wholeSalesChild 批批发销货单子表
     * @return 结果
     */
    @Override
    public int updateWholeSalesChild(WholeSalesChild wholeSalesChild)
    {
        wholeSalesChild.setUpdateTime(DateUtils.getNowDate());
        return wholeSalesChildMapper.updateWholeSalesChild(wholeSalesChild);
    }

    /**
     * 批量删除批批发销货单子表
     * 
     * @param ids 需要删除的批批发销货单子表ID
     * @return 结果
     */
    @Override
    public int deleteWholeSalesChildByIds(String[] ids)
    {
        return wholeSalesChildMapper.deleteWholeSalesChildByIds(ids);
    }

    /**
     * 删除批批发销货单子表信息
     * 
     * @param id 批批发销货单子表ID
     * @return 结果
     */
    @Override
    public int deleteWholeSalesChildById(String id)
    {
        return wholeSalesChildMapper.deleteWholeSalesChildById(id);
    }
    /**
     * 根据主表ID批量删除单据子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    @Override
    public int deleteWholeSalesChildByPid(String[] ids){
        return wholeSalesChildMapper.deleteWholeSalesChildByPid(ids);
    }
}
