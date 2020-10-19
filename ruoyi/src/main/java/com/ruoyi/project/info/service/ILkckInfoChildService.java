package com.ruoyi.project.info.service;

import java.util.List;
import com.ruoyi.project.info.domain.LkckInfoChild;

/**
 * 出库单明细Service接口
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
public interface ILkckInfoChildService 
{
    /**
     * 查询出库单明细
     * 
     * @param id 出库单明细ID
     * @return 出库单明细
     */
    public LkckInfoChild selectLkckInfoChildById(String id);

    /**
     * 查询出库单明细列表
     * 
     * @param lkckInfoChild 出库单明细
     * @return 出库单明细集合
     */
    public List<LkckInfoChild> selectLkckInfoChildList(LkckInfoChild lkckInfoChild);

    /**
     * 新增出库单明细
     * 
     * @param lkckInfoChild 出库单明细
     * @return 结果
     */
    public int insertLkckInfoChild(LkckInfoChild lkckInfoChild);

    /**
     * 修改出库单明细
     * 
     * @param lkckInfoChild 出库单明细
     * @return 结果
     */
    public int updateLkckInfoChild(LkckInfoChild lkckInfoChild);

    /**
     * 批量删除出库单明细
     * 
     * @param ids 需要删除的出库单明细ID
     * @return 结果
     */
    public int deleteLkckInfoChildByIds(String[] ids);

    /**
     * 根据主表id批量删除出库单明细
     *
     * @param ids 需要删除的出库单主表ID
     * @return 结果
     */
    public int deleteLkckInfoChildByPid(String[] ids);

    /**
     * 删除出库单明细信息
     * 
     * @param id 出库单明细ID
     * @return 结果
     */
    public int deleteLkckInfoChildById(String id);
}
