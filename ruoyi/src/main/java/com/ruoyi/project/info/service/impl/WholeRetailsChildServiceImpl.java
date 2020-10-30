package com.ruoyi.project.info.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.info.mapper.WholeRetailsChildMapper;
import com.ruoyi.project.info.service.IWholeRetailsChildService;
import com.ruoyi.project.info.domain.WholeRetailsChild;
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
public class WholeRetailsChildServiceImpl implements IWholeRetailsChildService
{
    @Autowired
    private WholeRetailsChildMapper WholeRetailChildMapper;

    /**
     * 查询批批发销货单子表
     *
     * @param id 批批发销货单子表ID
     * @return 批批发销货单子表
     */
    @Override
    public WholeRetailsChild selectWholeRetailChildById(String id)
    {
        return WholeRetailChildMapper.selectWholeRetailChildById(id);
    }
    /**
     * 查询批发销货单子表列表
     *
     * @param WholeRetailsChild 批发销货单子表
     * @return 批发销货单子表集合
     */
    @Override
    public List<WholeRetailsChild> selectWholeRetailAllList(WholeRetailsChild WholeRetailsChild){
        return WholeRetailChildMapper.selectWholeRetailAllList(WholeRetailsChild);
    }

    /**
     * 查询批批发销货单子表列表
     *
     * @param WholeRetailsChild 批批发销货单子表
     * @return 批批发销货单子表
     */
    @Override
    public List<WholeRetailsChild> selectWholeRetailChildList(WholeRetailsChild WholeRetailsChild)
    {
        return WholeRetailChildMapper.selectWholeRetailChildList(WholeRetailsChild);
    }
    /**
     * 条件查询批发销货单子表列表
     *
     * @return 批发销货单子表集合
     */
    @Override
    public List<WholeRetailsChild> selectWholeRetailChildListByWhere(String createBy, Integer dateType,String date, String goodsName, String khName){
        return WholeRetailChildMapper.selectWholeRetailChildListByWhere(createBy,dateType,date,goodsName,khName);
    }
    /**
     * 新增批批发销货单子表
     *
     * @param WholeRetailsChild 批批发销货单子表
     * @return 结果
     */
    @Override
    public int insertWholeRetailChild(WholeRetailsChild WholeRetailsChild)
    {
        WholeRetailsChild.setCreateTime(DateUtils.getNowDate());
        return WholeRetailChildMapper.insertWholeRetailChild(WholeRetailsChild);
    }

    /**
     * 修改批批发销货单子表
     *
     * @param WholeRetailsChild 批批发销货单子表
     * @return 结果
     */
    @Override
    public int updateWholeRetailChild(WholeRetailsChild WholeRetailsChild)
    {
        WholeRetailsChild.setUpdateTime(DateUtils.getNowDate());
        return WholeRetailChildMapper.updateWholeRetailChild(WholeRetailsChild);
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
