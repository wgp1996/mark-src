package com.ruoyi.project.info.service;

import java.util.List;
import com.ruoyi.project.info.domain.LkckInfo;

/**
 * 出库单Service接口
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
public interface ILkckInfoService 
{
    /**
     * 查询出库单
     * 
     * @param id 出库单ID
     * @return 出库单
     */
    public LkckInfo selectLkckInfoById(String id);

    /**
     * 查询出库单列表
     * 
     * @param lkckInfo 出库单
     * @return 出库单集合
     */
    public List<LkckInfo> selectLkckInfoList(LkckInfo lkckInfo);

    /**
     * 新增出库单
     * 
     * @param lkckInfo 出库单
     * @return 结果
     */
    public int insertLkckInfo(LkckInfo lkckInfo);

    /**
     * 修改出库单
     * 
     * @param lkckInfo 出库单
     * @return 结果
     */
    public int updateLkckInfo(LkckInfo lkckInfo);

    /**
     * 批量删除出库单
     * 
     * @param ids 需要删除的出库单ID
     * @return 结果
     */
    public int deleteLkckInfoByIds(String[] ids);

    /**
     * 批量生效出库单
     *
     * @param ids 需要生效的出库单ID
     * @return 结果
     */
    public int updateCkdStatus(String[] ids);

    /**
     * 删除出库单信息
     * 
     * @param id 出库单ID
     * @return 结果
     */
    public int deleteLkckInfoById(String id);
}
