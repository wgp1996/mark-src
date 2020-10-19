package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.WholeSalesChild;

/**
 * 批发销货一票通子表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public interface WholeSalesChildMapper 
{
    /**
     * 查询批发销货一票通子表
     * 
     * @param id 批发销货一票通子表ID
     * @return 批发销货一票通子表
     */
    public WholeSalesChild selectWholeSalesChildById(String id);
    /**
     * 查询批发销货单子表列表
     *
     * @param wholeSalesChild 批发销货单子表
     * @return 批发销货单子表集合
     */
    public List<WholeSalesChild> selectWholeSalesAllList(WholeSalesChild wholeSalesChild);
    /**
     * 查询批发单表列表
     *
     * @param wholeSalesChild 批发单子表
     * @return 批发销货单子表集合
     */
    public List<WholeSalesChild> selectWholeAllList(WholeSalesChild wholeSalesChild);
    /**
     * 查询批发销货一票通子表列表
     * 
     * @param wholeSalesChild 批发销货一票通子表
     * @return 批发销货一票通子表集合
     */
    public List<WholeSalesChild> selectWholeSalesChildList(WholeSalesChild wholeSalesChild);
    /**
     * 条件查询批发销货单子表列表
     *
     * @return 批发销货单子表集合
     */
    public List<WholeSalesChild> selectWholeSalesChildListByWhere( String createBy,  Integer dateType, String date, String goodsName,  String khName);
    /**
     * 新增批发销货一票通子表
     * 
     * @param wholeSalesChild 批发销货一票通子表
     * @return 结果
     */
    public int insertWholeSalesChild(WholeSalesChild wholeSalesChild);

    /**
     * 修改批发销货一票通子表
     * 
     * @param wholeSalesChild 批发销货一票通子表
     * @return 结果
     */
    public int updateWholeSalesChild(WholeSalesChild wholeSalesChild);

    /**
     * 删除批发销货一票通子表
     * 
     * @param id 批发销货一票通子表ID
     * @return 结果
     */
    public int deleteWholeSalesChildById(String id);

    /**
     * 批量删除批发销货一票通子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWholeSalesChildByIds(String[] ids);
    /**
     * 根据主表ID批量删除单据子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    public int deleteWholeSalesChildByPid(String[] ids);
}
