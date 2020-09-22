package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.RandomInspectionInfo;

/**
 * 随机检测单Service接口
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public interface IRandomInspectionInfoService 
{
    /**
     * 查询随机检测单
     * 
     * @param id 随机检测单ID
     * @return 随机检测单
     */
    public RandomInspectionInfo selectRandomInspectionInfoById(String id);

    /**
     * 查询随机检测单列表
     * 
     * @param randomInspectionInfo 随机检测单
     * @return 随机检测单集合
     */
    public List<RandomInspectionInfo> selectRandomInspectionInfoList(RandomInspectionInfo randomInspectionInfo);

    /**
     * 新增随机检测单
     * 
     * @param randomInspectionInfo 随机检测单
     * @return 结果
     */
    public int insertRandomInspectionInfo(RandomInspectionInfo randomInspectionInfo);

    /**
     * 修改随机检测单
     * 
     * @param randomInspectionInfo 随机检测单
     * @return 结果
     */
    public int updateRandomInspectionInfo(RandomInspectionInfo randomInspectionInfo);

    /**
     * 批量删除随机检测单
     * 
     * @param ids 需要删除的随机检测单ID
     * @return 结果
     */
    public int deleteRandomInspectionInfoByIds(String[] ids);

    /**
     * 删除随机检测单信息
     * 
     * @param id 随机检测单ID
     * @return 结果
     */
    public int deleteRandomInspectionInfoById(String id);
}
