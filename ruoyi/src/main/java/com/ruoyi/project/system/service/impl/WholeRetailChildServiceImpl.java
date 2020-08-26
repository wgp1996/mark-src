package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.WholeRetailChild;
import com.ruoyi.project.system.mapper.WholeRetailChildMapper;
import com.ruoyi.project.system.service.IWholeRetailChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批批发销货单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
@Service
public class WholeRetailChildServiceImpl implements IWholeRetailChildService 
{
    @Autowired
    private WholeRetailChildMapper WholeRetailChildMapper;

    /**
     * 查询批批发销货单子表
     * 
     * @param id 批批发销货单子表ID
     * @return 批批发销货单子表
     */
    @Override
    public WholeRetailChild selectWholeRetailChildById(String id)
    {
        return WholeRetailChildMapper.selectWholeRetailChildById(id);
    }

    /**
     * 查询批批发销货单子表列表
     * 
     * @param WholeRetailChild 批批发销货单子表
     * @return 批批发销货单子表
     */
    @Override
    public List<WholeRetailChild> selectWholeRetailChildList(WholeRetailChild WholeRetailChild)
    {
        return WholeRetailChildMapper.selectWholeRetailChildList(WholeRetailChild);
    }
    /**
     * 条件查询批发销货单子表列表
     *
     * @return 批发销货单子表集合
     */
    @Override
    public List<WholeRetailChild> selectWholeRetailChildListByWhere(String createBy, Integer dateType,String date, String goodsName, String khName){
        return WholeRetailChildMapper.selectWholeRetailChildListByWhere(createBy,dateType,date,goodsName,khName);
    }
    /**
     * 新增批批发销货单子表
     * 
     * @param WholeRetailChild 批批发销货单子表
     * @return 结果
     */
    @Override
    public int insertWholeRetailChild(WholeRetailChild WholeRetailChild)
    {
        WholeRetailChild.setCreateTime(DateUtils.getNowDate());
        return WholeRetailChildMapper.insertWholeRetailChild(WholeRetailChild);
    }

    /**
     * 修改批批发销货单子表
     * 
     * @param WholeRetailChild 批批发销货单子表
     * @return 结果
     */
    @Override
    public int updateWholeRetailChild(WholeRetailChild WholeRetailChild)
    {
        WholeRetailChild.setUpdateTime(DateUtils.getNowDate());
        return WholeRetailChildMapper.updateWholeRetailChild(WholeRetailChild);
    }

    /**
     * 批量删除批批发销货单子表
     * 
     * @param ids 需要删除的批批发销货单子表ID
     * @return 结果
     */
    @Override
    public int deleteWholeRetailChildByIds(String[] ids)
    {
        return WholeRetailChildMapper.deleteWholeRetailChildByIds(ids);
    }

    /**
     * 删除批批发销货单子表信息
     * 
     * @param id 批批发销货单子表ID
     * @return 结果
     */
    @Override
    public int deleteWholeRetailChildById(String id)
    {
        return WholeRetailChildMapper.deleteWholeRetailChildById(id);
    }
    /**
     * 根据主表ID批量删除单据子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    @Override
    public int deleteWholeRetailChildByPid(String[] ids){
        return WholeRetailChildMapper.deleteWholeRetailChildByPid(ids);
    }
}
