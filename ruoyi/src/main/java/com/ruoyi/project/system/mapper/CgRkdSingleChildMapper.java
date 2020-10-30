package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.CgRkdSingleChild;

import java.util.List;

/**
 * 进货单子表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
public interface CgRkdSingleChildMapper
{
    /**
     * 查询进货单子表
     * 
     * @param id 进货单子表ID
     * @return 进货单子表
     */
    public CgRkdSingleChild selectCgRkdSingleChildById(String id);
    /**
     * 查询进货单子表
     *
     * @param dj_number 进货单子表编码
     * @return 进货单子表
     */
    public List<CgRkdSingleChild> selectCgRkdSingleChildByNumber(String dj_number);
    /**
     * 查询进货单子表
     *
     * @param dj_number 进货单子表编码
     * @return 进货单子表
     */
    public List<CgRkdSingleChild> selectCgRkdSingleChildByNum(String dj_number);
    /**
     * 查询进货单子表
     *
     * @param createBy 用户编码
     * @return 进货单子表
     */
    public List<CgRkdSingleChild> selectCgRkdSingleChildByUser(String createBy);
    /**
     * 查询进货单子表
     *
     * @param stallCode 主表摊位编码
     * @return 进货单子表
     */
    public List<CgRkdSingleChild> appRkdChildListByStall(String stallCode);

    /**
     * 查询进货单子表列表
     * 
     * @param cgRkdChild 进货单子表
     * @return 进货单子表集合
     */
    public List<CgRkdSingleChild> selectCgRkdSingleChildList(CgRkdSingleChild cgRkdChild);

    /**
     * 新增进货单子表
     * 
     * @param cgRkdChild 进货单子表
     * @return 结果
     */
    public int insertCgRkdSingleChild(CgRkdSingleChild cgRkdChild);

    /**
     * 修改进货单子表
     * 
     * @param cgRkdChild 进货单子表
     * @return 结果
     */
    public int updateCgRkdSingleChild(CgRkdSingleChild cgRkdChild);

    /**
     * 删除进货单子表
     * 
     * @param id 进货单子表ID
     * @return 结果
     */
    public int deleteCgRkdSingleChildById(String id);

    /**
     * 批量删除进货单子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCgRkdSingleChildByIds(String[] ids);
    /**
     * 根据主表ID批量删除进货单子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    public int deleteCgRkdSingleChildByPid(String[] ids);
}
