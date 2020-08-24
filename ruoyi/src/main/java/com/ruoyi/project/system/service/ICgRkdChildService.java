package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.CgRkdChild;

/**
 * 进货单子表Service接口
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
public interface ICgRkdChildService 
{
    /**
     * 查询进货单子表
     * 
     * @param id 进货单子表ID
     * @return 进货单子表
     */
    public CgRkdChild selectCgRkdChildById(String id);

    /**
     * 查询进货单子表
     *
     * @param dj_number 进货单子表编码
     * @return 进货单子表
     */
    public List<CgRkdChild> selectCgRkdChildByNumber(String dj_number);

       /**
     * 查询进货单子表
     *
     * @param stallCode 主表摊位编码
     * @return 进货单子表
     */
    public List<CgRkdChild> appRkdChildListByStall(String stallCode);

    /**
     * 查询进货单子表列表
     * 
     * @param cgRkdChild 进货单子表
     * @return 进货单子表集合
     */
    public List<CgRkdChild> selectCgRkdChildList(CgRkdChild cgRkdChild);

    /**
     * 新增进货单子表
     * 
     * @param cgRkdChild 进货单子表
     * @return 结果
     */
    public int insertCgRkdChild(CgRkdChild cgRkdChild);

    /**
     * 修改进货单子表
     * 
     * @param cgRkdChild 进货单子表
     * @return 结果
     */
    public int updateCgRkdChild(CgRkdChild cgRkdChild);

    /**
     * 批量删除进货单子表
     * 
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    public int deleteCgRkdChildByIds(String[] ids);

    /**
     * 根据主表ID批量删除进货单子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    public int deleteCgRkdChildByPid(String[] ids);

    /**
     * 删除进货单子表信息
     * 
     * @param id 进货单子表ID
     * @return 结果
     */
    public int deleteCgRkdChildById(String id);

}
