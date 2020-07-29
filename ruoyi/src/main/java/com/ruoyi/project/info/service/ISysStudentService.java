package com.ruoyi.project.info.service;

import java.util.List;
import com.ruoyi.project.info.domain.SysStudent;

/**
 * 学生信息Service接口
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
public interface ISysStudentService 
{
    /**
     * 查询学生信息
     * 
     * @param studentId 学生信息ID
     * @return 学生信息
     */
    public SysStudent selectSysStudentById(Long studentId);

    /**
     * 查询学生信息列表
     * 
     * @param sysStudent 学生信息
     * @return 学生信息集合
     */
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent);

    /**
     * 新增学生信息
     * 
     * @param sysStudent 学生信息
     * @return 结果
     */
    public int insertSysStudent(SysStudent sysStudent);

    /**
     * 修改学生信息
     * 
     * @param sysStudent 学生信息
     * @return 结果
     */
    public int updateSysStudent(SysStudent sysStudent);

    /**
     * 批量删除学生信息
     * 
     * @param studentIds 需要删除的学生信息ID
     * @return 结果
     */
    public int deleteSysStudentByIds(Long[] studentIds);

    /**
     * 删除学生信息信息
     * 
     * @param studentId 学生信息ID
     * @return 结果
     */
    public int deleteSysStudentById(Long studentId);
}
