package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.MarkType;

/**
 * 市场分类Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-25
 */
public interface MarkTypeMapper 
{
    /**
     * 查询市场分类
     * 
     * @param markId 市场分类ID
     * @return 市场分类
     */
    public MarkType selectMarkTypeById(Integer markId);
    /**
     * 是否存在子节点
     *
     * @param id 分类ID
     * @return 结果
     */
    public int hasChildMarkTypeById(Integer id);
    /**
     * 查询市场分类列表
     * 
     * @param markType 市场分类
     * @return 市场分类集合
     */
    public List<MarkType> selectMarkTypeList(MarkType markType);
    /**
     * 查询市场分类列表
     *
     * @param markType 市场分类
     * @return 市场分类集合
     */
    public List<MarkType> selectMarkTypeListWhere(MarkType markType);

    /**
     * 新增市场分类
     * 
     * @param markType 市场分类
     * @return 结果
     */
    public int insertMarkType(MarkType markType);

    /**
     * 修改市场分类
     * 
     * @param markType 市场分类
     * @return 结果
     */
    public int updateMarkType(MarkType markType);

    /**
     * 删除市场分类
     * 
     * @param markId 市场分类ID
     * @return 结果
     */
    public int deleteMarkTypeById(Integer markId);

    /**
     * 批量删除市场分类
     * 
     * @param markIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarkTypeByIds(Integer[] markIds);
}
