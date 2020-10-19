package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.WholeRetailChild;
import com.ruoyi.project.system.domain.WholeSalesChild;

import java.util.List;

/**
 * 批发销货一票通子表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public interface WholeRetailChildMapper 
{
    /**
     * 查询批发销货一票通子表
     * 
     * @param id 批发销货一票通子表ID
     * @return 批发销货一票通子表
     */
    public WholeRetailChild selectWholeRetailChildById(String id);

    /**
     * 查询批发销货一票通子表列表
     * 
     * @param WholeRetailChild 批发销货一票通子表
     * @return 批发销货一票通子表集合
     */
    public List<WholeRetailChild> selectWholeRetailChildList(WholeRetailChild WholeRetailChild);
    /**
     * 查询批发销货单子表列表
     *
     * @param WholeRetailChild 批发销货单子表
     * @return 批发销货单子表集合
     */
    public List<WholeRetailChild> selectWholeRetailAllList(WholeRetailChild WholeRetailChild);
    /**
     * 条件查询批发销货单子表列表
     *
     * @return 批发销货单子表集合
     */
    public List<WholeRetailChild> selectWholeRetailChildListByWhere(String createBy, Integer dateType,String date, String goodsName, String khName);
    /**
     * 新增批发销货一票通子表
     * 
     * @param WholeRetailChild 批发销货一票通子表
     * @return 结果
     */
    public int insertWholeRetailChild(WholeRetailChild WholeRetailChild);

    /**
     * 修改批发销货一票通子表
     * 
     * @param WholeRetailChild 批发销货一票通子表
     * @return 结果
     */
    public int updateWholeRetailChild(WholeRetailChild WholeRetailChild);

    /**
     * 删除批发销货一票通子表
     * 
     * @param id 批发销货一票通子表ID
     * @return 结果
     */
    public int deleteWholeRetailChildById(String id);

    /**
     * 批量删除批发销货一票通子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWholeRetailChildByIds(String[] ids);
    /**
     * 根据主表ID批量删除单据子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    public int deleteWholeRetailChildByPid(String[] ids);
}
