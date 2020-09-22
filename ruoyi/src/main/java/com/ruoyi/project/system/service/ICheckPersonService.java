package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.CheckPerson;

/**
 * 检测人员建档Service接口
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public interface ICheckPersonService 
{
    /**
     * 查询检测人员建档
     * 
     * @param id 检测人员建档ID
     * @return 检测人员建档
     */
    public CheckPerson selectCheckPersonById(String id);

    /**
     * 查询检测人员建档列表
     * 
     * @param checkPerson 检测人员建档
     * @return 检测人员建档集合
     */
    public List<CheckPerson> selectCheckPersonList(CheckPerson checkPerson);

    /**
     * 新增检测人员建档
     * 
     * @param checkPerson 检测人员建档
     * @return 结果
     */
    public int insertCheckPerson(CheckPerson checkPerson);

    /**
     * 修改检测人员建档
     * 
     * @param checkPerson 检测人员建档
     * @return 结果
     */
    public int updateCheckPerson(CheckPerson checkPerson);

    /**
     * 批量删除检测人员建档
     * 
     * @param ids 需要删除的检测人员建档ID
     * @return 结果
     */
    public int deleteCheckPersonByIds(String[] ids);

    /**
     * 删除检测人员建档信息
     * 
     * @param id 检测人员建档ID
     * @return 结果
     */
    public int deleteCheckPersonById(String id);
}
