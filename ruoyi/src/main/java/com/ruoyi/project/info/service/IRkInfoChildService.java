package com.ruoyi.project.info.service;

import java.util.List;
import com.ruoyi.project.info.domain.RkInfoChild;

/**
 * 入库单子表Service接口
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
public interface IRkInfoChildService 
{
    /**
     * 查询入库单子表
     * 
     * @param id 入库单子表ID
     * @return 入库单子表
     */
    public RkInfoChild selectRkInfoChildById(String id);

    /**
     * 查询入库单子表列表
     * 
     * @param rkInfoChild 入库单子表
     * @return 入库单子表集合
     */
    public List<RkInfoChild> selectRkInfoChildList(RkInfoChild rkInfoChild);

    /**
     * 出库单选择入库单
     *
     * @param rkInfoChild 入库单子表
     * @return 入库单子表集合
     */
    public List<RkInfoChild> selectRkInfoByCkd(RkInfoChild rkInfoChild);

    /**
     * 新增入库单子表
     * 
     * @param rkInfoChild 入库单子表
     * @return 结果
     */
    public int insertRkInfoChild(RkInfoChild rkInfoChild);

    /**
     * 修改入库单子表
     * 
     * @param rkInfoChild 入库单子表
     * @return 结果
     */
    public int updateRkInfoChild(RkInfoChild rkInfoChild);

    /**
     * 批量删除入库单子表
     * 
     * @param ids 需要删除的入库单子表ID
     * @return 结果
     */
    public int deleteRkInfoChildByIds(String[] ids);
    /**
     * 根据主表id批量删除入库单子表
     *
     * @param ids 需要删除的入库单主表ID
     * @return 结果
     */
    public int deleteRkInfoChildByPid(String[] ids);

    /**
     * 删除入库单子表信息
     * 
     * @param id 入库单子表ID
     * @return 结果
     */
    public int deleteRkInfoChildById(String id);
}
