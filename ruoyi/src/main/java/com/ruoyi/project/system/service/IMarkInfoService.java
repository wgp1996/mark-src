package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.MarkInfo;

/**
 * 【市场基本信息】Service接口
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
public interface IMarkInfoService 
{
    /**
     * 查询【市场基本信息】
     * 
     * @param id 【市场基本信息】ID
     * @return 【市场基本信息】
     */
    public MarkInfo selectMarkInfoById(String id);

    /**
     * 查询【市场基本信息】
     *
     * @param name 【市场基本信息】名称
     * @return 【市场基本信息】
     */
    public MarkInfo selectMarkInfoByName(String name,String id);

    /**
     * 查询【市场基本信息】列表
     * 
     * @param markInfo 【市场基本信息】
     * @return 【市场基本信息】集合
     */
    public List<MarkInfo> selectMarkInfoList(MarkInfo markInfo);

    /**
     * 新增【市场基本信息】
     * 
     * @param markInfo 【市场基本信息】
     * @return 结果
     */
    public int insertMarkInfo(MarkInfo markInfo);

    /**
     * 修改【市场基本信息】
     * 
     * @param markInfo 【市场基本信息】
     * @return 结果
     */
    public int updateMarkInfo(MarkInfo markInfo);

    /**
     * 批量删除【市场基本信息】
     * 
     * @param ids 需要删除的【市场基本信息】ID
     * @return 结果
     */
    public int deleteMarkInfoByIds(String[] ids);

    /**
     * 删除【市场基本信息】信息
     * 
     * @param id 【市场基本信息】ID
     * @return 结果
     */
    public int deleteMarkInfoById(String id);
}
