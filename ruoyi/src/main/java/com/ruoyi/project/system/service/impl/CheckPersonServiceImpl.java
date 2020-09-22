package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CheckPersonMapper;
import com.ruoyi.project.system.domain.CheckPerson;
import com.ruoyi.project.system.service.ICheckPersonService;

/**
 * 检测人员建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Service
public class CheckPersonServiceImpl implements ICheckPersonService 
{
    @Autowired
    private CheckPersonMapper checkPersonMapper;

    /**
     * 查询检测人员建档
     * 
     * @param id 检测人员建档ID
     * @return 检测人员建档
     */
    @Override
    public CheckPerson selectCheckPersonById(String id)
    {
        return checkPersonMapper.selectCheckPersonById(id);
    }

    /**
     * 查询检测人员建档列表
     * 
     * @param checkPerson 检测人员建档
     * @return 检测人员建档
     */
    @Override
    public List<CheckPerson> selectCheckPersonList(CheckPerson checkPerson)
    {
        return checkPersonMapper.selectCheckPersonList(checkPerson);
    }

    /**
     * 新增检测人员建档
     * 
     * @param checkPerson 检测人员建档
     * @return 结果
     */
    @Override
    public int insertCheckPerson(CheckPerson checkPerson)
    {
        checkPerson.setCreateTime(DateUtils.getNowDate());
        return checkPersonMapper.insertCheckPerson(checkPerson);
    }

    /**
     * 修改检测人员建档
     * 
     * @param checkPerson 检测人员建档
     * @return 结果
     */
    @Override
    public int updateCheckPerson(CheckPerson checkPerson)
    {
        checkPerson.setUpdateTime(DateUtils.getNowDate());
        return checkPersonMapper.updateCheckPerson(checkPerson);
    }

    /**
     * 批量删除检测人员建档
     * 
     * @param ids 需要删除的检测人员建档ID
     * @return 结果
     */
    @Override
    public int deleteCheckPersonByIds(String[] ids)
    {
        return checkPersonMapper.deleteCheckPersonByIds(ids);
    }

    /**
     * 删除检测人员建档信息
     * 
     * @param id 检测人员建档ID
     * @return 结果
     */
    @Override
    public int deleteCheckPersonById(String id)
    {
        return checkPersonMapper.deleteCheckPersonById(id);
    }
}
