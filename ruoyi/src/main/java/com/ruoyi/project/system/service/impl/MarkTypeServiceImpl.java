package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.MarkTypeMapper;
import com.ruoyi.project.system.domain.MarkType;
import com.ruoyi.project.system.service.IMarkTypeService;

/**
 * 市场分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-25
 */
@Service
public class MarkTypeServiceImpl implements IMarkTypeService 
{
    @Autowired
    private MarkTypeMapper markTypeMapper;

    /**
     * 查询市场分类
     * 
     * @param markId 市场分类ID
     * @return 市场分类
     */
    @Override
    public MarkType selectMarkTypeById(Integer markId)
    {
        return markTypeMapper.selectMarkTypeById(markId);
    }

    /**
     * 查询市场分类列表
     * 
     * @param markType 市场分类
     * @return 市场分类
     */
    @Override
    public List<MarkType> selectMarkTypeList(MarkType markType)
    {
        return markTypeMapper.selectMarkTypeList(markType);
    }
    @Override
    public List<MarkType> selectMarkTypeListWhere(MarkType markType)
    {
        return markTypeMapper.selectMarkTypeListWhere(markType);
    }

    /**
     * 新增市场分类
     * 
     * @param markType 市场分类
     * @return 结果
     */
    @Override
    public int insertMarkType(MarkType markType)
    {
        markType.setCreateTime(DateUtils.getNowDate());
        return markTypeMapper.insertMarkType(markType);
    }

    /**
     * 修改市场分类
     * 
     * @param markType 市场分类
     * @return 结果
     */
    @Override
    public int updateMarkType(MarkType markType)
    {
        markType.setUpdateTime(DateUtils.getNowDate());
        return markTypeMapper.updateMarkType(markType);
    }

    /**
     * 批量删除市场分类
     * 
     * @param markIds 需要删除的市场分类ID
     * @return 结果
     */
    @Override
    public int deleteMarkTypeByIds(Integer[] markIds)
    {
        return markTypeMapper.deleteMarkTypeByIds(markIds);
    }

    /**
     * 删除市场分类信息
     * 
     * @param markId 市场分类ID
     * @return 结果
     */
    @Override
    public int deleteMarkTypeById(Integer markId)
    {
        return markTypeMapper.deleteMarkTypeById(markId);
    }
    /**
     * 是否存在子节点
     *
     * @param id 分类ID
     * @return 结果
     */
    @Override
    public int hasChildMarkTypeById(Integer id){
        return markTypeMapper.hasChildMarkTypeById(id);
    }
}
