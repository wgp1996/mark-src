package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.TestApplicationForm;

/**
 * 检测申请单Service接口
 * 
 * @author ruoyi
 * @date 2020-09-30
 */
public interface ITestApplicationFormService 
{
    /**
     * 查询检测申请单
     * 
     * @param id 检测申请单ID
     * @return 检测申请单
     */
    public TestApplicationForm selectTestApplicationFormById(Integer id);

    /**
     * 查询检测申请单列表
     * 
     * @param testApplicationForm 检测申请单
     * @return 检测申请单集合
     */
    public List<TestApplicationForm> selectTestApplicationFormList(TestApplicationForm testApplicationForm);

    /**
     * 新增检测申请单
     * 
     * @param testApplicationForm 检测申请单
     * @return 结果
     */
    public int insertTestApplicationForm(TestApplicationForm testApplicationForm);

    /**
     * 修改检测申请单
     * 
     * @param testApplicationForm 检测申请单
     * @return 结果
     */
    public int updateTestApplicationForm(TestApplicationForm testApplicationForm);

    /**
     * 批量删除检测申请单
     * 
     * @param ids 需要删除的检测申请单ID
     * @return 结果
     */
    public int deleteTestApplicationFormByIds(Integer[] ids);

    /**
     * 批量删除检测申请单明细
     *
     * @param ids 需要删除的检测申请单ID
     * @return 结果
     */
    public int deleteTestApplicationFormByPid(Integer[] ids);

    /**
     * 删除检测申请单信息
     * 
     * @param id 检测申请单ID
     * @return 结果
     */
    public int deleteTestApplicationFormById(Integer id);
}
