package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.TestApplicationFormChild;

/**
 * 检测申请单子表Service接口
 * 
 * @author ruoyi
 * @date 2020-09-30
 */
public interface ITestApplicationFormChildService 
{
    /**
     * 查询检测申请单子表
     * 
     * @param id 检测申请单子表ID
     * @return 检测申请单子表
     */
    public TestApplicationFormChild selectTestApplicationFormChildById(String id);

    /**
     * 查询检测申请单子表列表
     * 
     * @param testApplicationFormChild 检测申请单子表
     * @return 检测申请单子表集合
     */
    public List<TestApplicationFormChild> selectTestApplicationFormChildList(TestApplicationFormChild testApplicationFormChild);

    /**
     * 查询检测申请单列表
     *
     * @param testApplicationFormChild 检测申请单子表
     * @return 检测申请单子表集合
     */
    public List<TestApplicationFormChild> selectTestApplicationForm(TestApplicationFormChild testApplicationFormChild);

    /**
     * 新增检测申请单子表
     * 
     * @param testApplicationFormChild 检测申请单子表
     * @return 结果
     */
    public int insertTestApplicationFormChild(TestApplicationFormChild testApplicationFormChild);

    /**
     * 修改检测申请单子表
     * 
     * @param testApplicationFormChild 检测申请单子表
     * @return 结果
     */
    public int updateTestApplicationFormChild(TestApplicationFormChild testApplicationFormChild);

    /**
     * 批量删除检测申请单子表
     * 
     * @param ids 需要删除的检测申请单子表ID
     * @return 结果
     */
    public int deleteTestApplicationFormChildByIds(String[] ids);

    /**
     * 删除检测申请单子表信息
     * 
     * @param id 检测申请单子表ID
     * @return 结果
     */
    public int deleteTestApplicationFormChildById(String id);
}
