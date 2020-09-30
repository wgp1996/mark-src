package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TestApplicationFormChildMapper;
import com.ruoyi.project.system.domain.TestApplicationFormChild;
import com.ruoyi.project.system.service.ITestApplicationFormChildService;

/**
 * 检测申请单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-30
 */
@Service
public class TestApplicationFormChildServiceImpl implements ITestApplicationFormChildService 
{
    @Autowired
    private TestApplicationFormChildMapper testApplicationFormChildMapper;
    /**
     * 查询检测申请单列表
     *
     * @param testApplicationFormChild 检测申请单子表
     * @return 检测申请单子表集合
     */
    @Override
    public List<TestApplicationFormChild> selectTestApplicationForm(TestApplicationFormChild testApplicationFormChild){
        return testApplicationFormChildMapper.selectTestApplicationForm(testApplicationFormChild);
    }
    /**
     * 查询检测申请单子表
     * 
     * @param id 检测申请单子表ID
     * @return 检测申请单子表
     */
    @Override
    public TestApplicationFormChild selectTestApplicationFormChildById(String id)
    {
        return testApplicationFormChildMapper.selectTestApplicationFormChildById(id);
    }

    /**
     * 查询检测申请单子表列表
     * 
     * @param testApplicationFormChild 检测申请单子表
     * @return 检测申请单子表
     */
    @Override
    public List<TestApplicationFormChild> selectTestApplicationFormChildList(TestApplicationFormChild testApplicationFormChild)
    {
        return testApplicationFormChildMapper.selectTestApplicationFormChildList(testApplicationFormChild);
    }

    /**
     * 新增检测申请单子表
     * 
     * @param testApplicationFormChild 检测申请单子表
     * @return 结果
     */
    @Override
    public int insertTestApplicationFormChild(TestApplicationFormChild testApplicationFormChild)
    {
        testApplicationFormChild.setCreateTime(DateUtils.getNowDate());
        return testApplicationFormChildMapper.insertTestApplicationFormChild(testApplicationFormChild);
    }

    /**
     * 修改检测申请单子表
     * 
     * @param testApplicationFormChild 检测申请单子表
     * @return 结果
     */
    @Override
    public int updateTestApplicationFormChild(TestApplicationFormChild testApplicationFormChild)
    {
        testApplicationFormChild.setUpdateTime(DateUtils.getNowDate());
        return testApplicationFormChildMapper.updateTestApplicationFormChild(testApplicationFormChild);
    }

    /**
     * 批量删除检测申请单子表
     * 
     * @param ids 需要删除的检测申请单子表ID
     * @return 结果
     */
    @Override
    public int deleteTestApplicationFormChildByIds(String[] ids)
    {
        return testApplicationFormChildMapper.deleteTestApplicationFormChildByIds(ids);
    }

    /**
     * 删除检测申请单子表信息
     * 
     * @param id 检测申请单子表ID
     * @return 结果
     */
    @Override
    public int deleteTestApplicationFormChildById(String id)
    {
        return testApplicationFormChildMapper.deleteTestApplicationFormChildById(id);
    }
}
