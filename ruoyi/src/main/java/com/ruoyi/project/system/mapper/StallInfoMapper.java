package com.ruoyi.project.system.mapper;

import java.util.List;

import com.ruoyi.project.system.domain.MarkChildInfo;
import com.ruoyi.project.system.domain.MarkInfo;
import com.ruoyi.project.system.domain.StallInfo;

/**
 * 市场摊位信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-07-29
 */
public interface StallInfoMapper {
    /**
     * 查询市场摊位信息
     *
     * @param id 市场摊位信息ID
     * @return 市场摊位信息
     */
    public StallInfo selectStallInfoById(String id);

    /**
     * 查询市场摊位信息
     *
     * @param code 市场摊位信息code
     * @return 市场摊位信息
     */
    public StallInfo selectStallInfoByCode(String code, String id);

    /**
     * 查询【二级市场基本信息】
     *
     * @return 【二级市场基本信息】
     */
    public List<MarkChildInfo> selectCMarkData();

    /**
     * 查询市场摊位信息列表
     *
     * @param stallInfo 市场摊位信息
     * @return 市场摊位信息集合
     */
    public List<StallInfo> selectStallInfoList(StallInfo stallInfo);

    /**
     * 新增市场摊位信息
     *
     * @param stallInfo 市场摊位信息
     * @return 结果
     */
    public int insertStallInfo(StallInfo stallInfo);

    /**
     * 修改市场摊位信息
     *
     * @param stallInfo 市场摊位信息
     * @return 结果
     */
    public int updateStallInfo(StallInfo stallInfo);

    /**
     * 删除市场摊位信息
     *
     * @param id 市场摊位信息ID
     * @return 结果
     */
    public int deleteStallInfoById(String id);

    /**
     * 批量删除市场摊位信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStallInfoByIds(String[] ids);
}
