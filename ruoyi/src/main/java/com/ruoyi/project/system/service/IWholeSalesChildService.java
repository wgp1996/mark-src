package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.WholeSalesChild;

/**
 * 批发销货单子表Service接口
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public interface IWholeSalesChildService 
{
    /**
     * 查询批发销货单子表
     * 
     * @param id 批发销货单子表ID
     * @return 批发销货单子表
     */
    public WholeSalesChild selectWholeSalesChildById(String id);

    /**
     * 查询批发销货单子表列表
     * 
     * @param wholeSalesChild 批发销货单子表
     * @return 批发销货单子表集合
     */
    public List<WholeSalesChild> selectWholeSalesChildList(WholeSalesChild wholeSalesChild);

    /**
     * 新增批发销货单子表
     * 
     * @param wholeSalesChild 批发销货单子表
     * @return 结果
     */
    public int insertWholeSalesChild(WholeSalesChild wholeSalesChild);

    /**
     * 修改批发销货单子表
     * 
     * @param wholeSalesChild 批发销货单子表
     * @return 结果
     */
    public int updateWholeSalesChild(WholeSalesChild wholeSalesChild);

    /**
     * 批量删除批发销货单子表
     * 
     * @param ids 需要删除的批发销货单子表ID
     * @return 结果
     */
    public int deleteWholeSalesChildByIds(String[] ids);

    /**
     * 删除批发销货单子表信息
     * 
     * @param id 批发销货单子表ID
     * @return 结果
     */
    public int deleteWholeSalesChildById(String id);
    /**
     * 根据主表ID批量删除单据子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    public int deleteWholeSalesChildByPid(String[] ids);
}
