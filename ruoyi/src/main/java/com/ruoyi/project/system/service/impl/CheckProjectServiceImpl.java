package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.CheckObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CheckProjectMapper;
import com.ruoyi.project.system.domain.CheckProject;
import com.ruoyi.project.system.service.ICheckProjectService;

/**
 * 监测项目建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
@Service
public class CheckProjectServiceImpl implements ICheckProjectService 
{
    @Autowired
    private CheckProjectMapper checkProjectMapper;

    /**
     * 查询监测项目建档
     * 
     * @param id 监测项目建档ID
     * @return 监测项目建档
     */
    @Override
    public CheckProject selectCheckProjectById(Integer id)
    {
        return checkProjectMapper.selectCheckProjectById(id);
    }

    /**
     * 查询监测项目建档列表
     * 
     * @param checkProject 监测项目建档
     * @return 监测项目建档
     */
    @Override
    public List<CheckProject> selectCheckProjectList(CheckProject checkProject)
    {
        return checkProjectMapper.selectCheckProjectList(checkProject);
    }

    /**
     * 新增监测项目建档
     * 
     * @param checkProject 监测项目建档
     * @return 结果
     */
    @Override
    public int insertCheckProject(CheckProject checkProject)
    {
        checkProject.setCreateTime(DateUtils.getNowDate());
        return checkProjectMapper.insertCheckProject(checkProject);
    }
    /**
     * 检测项目是否重复
     *
     * @param checkProject 检测项目
     * @return 结果
     */
    @Override
    public int checkProjectIsContain(CheckProject checkProject){
        return checkProjectMapper.checkProjectIsContain(checkProject);
    }
    /**
     * 修改默认项目
     *
     * @param checkProject 监测项目建档
     * @return 结果
     */
    @Override
    public int updateCheckProjectDefault(CheckProject checkProject){
        return checkProjectMapper.updateCheckProjectDefault(checkProject);
    }
    /**
     * 修改监测项目建档
     * 
     * @param checkProject 监测项目建档
     * @return 结果
     */
    @Override
    public int updateCheckProject(CheckProject checkProject)
    {
        checkProject.setUpdateTime(DateUtils.getNowDate());
        return checkProjectMapper.updateCheckProject(checkProject);
    }

    /**
     * 批量删除监测项目建档
     * 
     * @param ids 需要删除的监测项目建档ID
     * @return 结果
     */
    @Override
    public int deleteCheckProjectByIds(Integer[] ids)
    {
        return checkProjectMapper.deleteCheckProjectByIds(ids);
    }

    /**
     * 删除监测项目建档信息
     * 
     * @param id 监测项目建档ID
     * @return 结果
     */
    @Override
    public int deleteCheckProjectById(Integer id)
    {
        return checkProjectMapper.deleteCheckProjectById(id);
    }
}
