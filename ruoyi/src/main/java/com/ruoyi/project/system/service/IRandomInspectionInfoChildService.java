package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.RandomInspectionInfoChild;

/**
 * 检测单明细Service接口
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public interface IRandomInspectionInfoChildService 
{
    /**
     * 查询检测单明细
     * 
     * @param id 检测单明细ID
     * @return 检测单明细
     */
    public RandomInspectionInfoChild selectRandomInspectionInfoChildById(String id);

    /**
     * 查询检测单明细列表
     * 
     * @param randomInspectionInfoChild 检测单明细
     * @return 检测单明细集合
     */
    public List<RandomInspectionInfoChild> selectRandomInspectionInfoChildList(RandomInspectionInfoChild randomInspectionInfoChild);

    /**
     * 新增检测单明细
     * 
     * @param randomInspectionInfoChild 检测单明细
     * @return 结果
     */
    public int insertRandomInspectionInfoChild(RandomInspectionInfoChild randomInspectionInfoChild);

    /**
     * 修改检测单明细
     * 
     * @param randomInspectionInfoChild 检测单明细
     * @return 结果
     */
    public int updateRandomInspectionInfoChild(RandomInspectionInfoChild randomInspectionInfoChild);

    /**
     * 批量删除检测单明细
     * 
     * @param ids 需要删除的检测单明细ID
     * @return 结果
     */
    public int deleteRandomInspectionInfoChildByIds(String[] ids);

    /**
     * 删除检测单明细信息
     * 
     * @param id 检测单明细ID
     * @return 结果
     */
    public int deleteRandomInspectionInfoChildById(String id);
}
