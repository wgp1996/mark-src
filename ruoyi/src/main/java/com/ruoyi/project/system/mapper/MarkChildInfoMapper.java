package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.MarkChildInfo;
import com.ruoyi.project.system.domain.MarkInfo;

/**
 * 二级市场信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
public interface MarkChildInfoMapper 
{
    /**
     * 查询二级市场信息
     * 
     * @param id 二级市场信息ID
     * @return 二级市场信息
     */
    public MarkChildInfo selectMarkChildInfoById(String id);
    /**
     * 查询【二级市场基本信息】
     *
     * @param name 【二级市场基本信息】名称
     * @return 【二级市场基本信息】
     */
    public MarkChildInfo selectMarkChildInfoByName(String name, String id);

    /**
     * 查询【市场基本信息】
     *
     * @return 【市场基本信息】
     */
    public List<MarkInfo> selectMarkData();

    /**
     * 查询二级市场信息列表
     * 
     * @param markChildInfo 二级市场信息
     * @return 二级市场信息集合
     */
    public List<MarkChildInfo> selectMarkChildInfoList(MarkChildInfo markChildInfo);

    /**
     * 新增二级市场信息
     * 
     * @param markChildInfo 二级市场信息
     * @return 结果
     */
    public int insertMarkChildInfo(MarkChildInfo markChildInfo);

    /**
     * 修改二级市场信息
     * 
     * @param markChildInfo 二级市场信息
     * @return 结果
     */
    public int updateMarkChildInfo(MarkChildInfo markChildInfo);

    /**
     * 删除二级市场信息
     * 
     * @param id 二级市场信息ID
     * @return 结果
     */
    public int deleteMarkChildInfoById(String id);

    /**
     * 批量删除二级市场信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarkChildInfoByIds(String[] ids);
}
