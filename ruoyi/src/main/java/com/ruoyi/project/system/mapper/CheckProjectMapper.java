package com.ruoyi.project.system.mapper;

import java.util.List;

import com.ruoyi.project.system.domain.CheckObject;
import com.ruoyi.project.system.domain.CheckProject;

/**
 * 监测项目建档Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
public interface CheckProjectMapper 
{
    /**
     * 查询监测项目建档
     * 
     * @param id 监测项目建档ID
     * @return 监测项目建档
     */
    public CheckProject selectCheckProjectById(Integer id);

    /**
     * 查询监测项目建档列表
     * 
     * @param checkProject 监测项目建档
     * @return 监测项目建档集合
     */
    public List<CheckProject> selectCheckProjectList(CheckProject checkProject);

    /**
     * 新增监测项目建档
     * 
     * @param checkProject 监测项目建档
     * @return 结果
     */
    public int insertCheckProject(CheckProject checkProject);
    /**
     * 检测项目是否重复
     *
     * @param checkProject 检测项目
     * @return 结果
     */
    public int checkProjectIsContain(CheckProject checkProject);
    /**
     * 修改监测项目建档
     * 
     * @param checkProject 监测项目建档
     * @return 结果
     */
    public int updateCheckProject(CheckProject checkProject);
    /**
     * 修改默认项目
     *
     * @param checkProject 监测项目建档
     * @return 结果
     */
    public int updateCheckProjectDefault(CheckProject checkProject);

    /**
     * 删除监测项目建档
     * 
     * @param id 监测项目建档ID
     * @return 结果
     */
    public int deleteCheckProjectById(Integer id);

    /**
     * 批量删除监测项目建档
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckProjectByIds(Integer[] ids);
}
