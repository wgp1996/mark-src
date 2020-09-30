package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TestApplicationFormMapper;
import com.ruoyi.project.system.domain.TestApplicationForm;
import com.ruoyi.project.system.service.ITestApplicationFormService;

/**
 * 检测申请单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-30
 */
@Service
public class TestApplicationFormServiceImpl implements ITestApplicationFormService 
{
    @Autowired
    private TestApplicationFormMapper testApplicationFormMapper;

    /**
     * 查询检测申请单
     * 
     * @param id 检测申请单ID
     * @return 检测申请单
     */
    @Override
    public TestApplicationForm selectTestApplicationFormById(Integer id)
    {
        return testApplicationFormMapper.selectTestApplicationFormById(id);
    }

    /**
     * 查询检测申请单列表
     * 
     * @param testApplicationForm 检测申请单
     * @return 检测申请单
     */
    @Override
    public List<TestApplicationForm> selectTestApplicationFormList(TestApplicationForm testApplicationForm)
    {
        return testApplicationFormMapper.selectTestApplicationFormList(testApplicationForm);
    }

    /**
     * 新增检测申请单
     * 
     * @param testApplicationForm 检测申请单
     * @return 结果
     */
    @Override
    public int insertTestApplicationForm(TestApplicationForm testApplicationForm)
    {
        testApplicationForm.setCreateTime(DateUtils.getNowDate());
        return testApplicationFormMapper.insertTestApplicationForm(testApplicationForm);
    }

    /**
     * 修改检测申请单
     * 
     * @param testApplicationForm 检测申请单
     * @return 结果
     */
    @Override
    public int updateTestApplicationForm(TestApplicationForm testApplicationForm)
    {
        testApplicationForm.setUpdateTime(DateUtils.getNowDate());
        return testApplicationFormMapper.updateTestApplicationForm(testApplicationForm);
    }

    /**
     * 批量删除检测申请单
     * 
     * @param ids 需要删除的检测申请单ID
     * @return 结果
     */
    @Override
    public int deleteTestApplicationFormByIds(Integer[] ids)
    {
        return testApplicationFormMapper.deleteTestApplicationFormByIds(ids);
    }

    /**
     * 删除检测申请单信息
     * 
     * @param id 检测申请单ID
     * @return 结果
     */
    @Override
    public int deleteTestApplicationFormById(Integer id)
    {
        return testApplicationFormMapper.deleteTestApplicationFormById(id);
    }
    /**
     * 批量删除检测申请单明细
     *
     * @param ids 需要删除的检测申请单ID
     * @return 结果
     */
    @Override
    public int deleteTestApplicationFormByPid(Integer[] ids){
        return testApplicationFormMapper.deleteTestApplicationFormByPid(ids);
    }
}
