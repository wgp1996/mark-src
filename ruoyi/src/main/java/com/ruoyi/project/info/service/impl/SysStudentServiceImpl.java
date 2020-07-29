package com.ruoyi.project.info.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.SysStudentMapper;
import com.ruoyi.project.info.domain.SysStudent;
import com.ruoyi.project.info.service.ISysStudentService;

/**
 * 学生信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService 
{
    @Autowired
    private SysStudentMapper sysStudentMapper;



    /**
     * 查询学生信息
     * 
     * @param studentId 学生信息ID
     * @return 学生信息
     */
    @Override
    public SysStudent selectSysStudentById(Long studentId)
    {
        return sysStudentMapper.selectSysStudentById(studentId);
    }

    /**
     * 查询学生信息列表
     * 
     * @param sysStudent 学生信息
     * @return 学生信息
     */
    @Override
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent)
    {
        return sysStudentMapper.selectSysStudentList(sysStudent);
    }

    /**
     * 新增学生信息
     * 
     * @param sysStudent 学生信息
     * @return 结果
     */
    @Override
    public int insertSysStudent(SysStudent sysStudent)
    {
        //sysStudent.setStudentId(UUID.randomUUID().toString().replaceAll("-",""));
        return sysStudentMapper.insertSysStudent(sysStudent);
    }

    /**
     * 修改学生信息
     * 
     * @param sysStudent 学生信息
     * @return 结果
     */
    @Override
    public int updateSysStudent(SysStudent sysStudent)
    {
        return sysStudentMapper.updateSysStudent(sysStudent);
    }

    /**
     * 批量删除学生信息
     * 
     * @param studentIds 需要删除的学生信息ID
     * @return 结果
     */
    @Override
    public int deleteSysStudentByIds(Long[] studentIds)
    {
        return sysStudentMapper.deleteSysStudentByIds(studentIds);
    }

    /**
     * 删除学生信息信息
     * 
     * @param studentId 学生信息ID
     * @return 结果
     */
    @Override
    public int deleteSysStudentById(Long studentId)
    {
        return sysStudentMapper.deleteSysStudentById(studentId);
    }
}
