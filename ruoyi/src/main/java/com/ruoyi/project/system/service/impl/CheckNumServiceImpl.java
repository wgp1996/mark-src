package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CheckNumMapper;
import com.ruoyi.project.system.domain.CheckNum;
import com.ruoyi.project.system.service.ICheckNumService;

/**
 * 检测标准设定Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
@Service
public class CheckNumServiceImpl implements ICheckNumService 
{
    @Autowired
    private CheckNumMapper checkNumMapper;

    /**
     * 查询检测标准设定
     * 
     * @param id 检测标准设定ID
     * @return 检测标准设定
     */
    @Override
    public CheckNum selectCheckNumById(Integer id)
    {
        return checkNumMapper.selectCheckNumById(id);
    }

    /**
     * 查询检测标准设定列表
     * 
     * @param checkNum 检测标准设定
     * @return 检测标准设定
     */
    @Override
    public List<CheckNum> selectCheckNumList(CheckNum checkNum)
    {
        return checkNumMapper.selectCheckNumList(checkNum);
    }

    /**
     * 新增检测标准设定
     * 
     * @param checkNum 检测标准设定
     * @return 结果
     */
    @Override
    public int insertCheckNum(CheckNum checkNum)
    {
        checkNum.setCreateTime(DateUtils.getNowDate());
        return checkNumMapper.insertCheckNum(checkNum);
    }
    /**
     * 检测标准是否存在
     *
     * @param checkNum 检测标准设定
     * @return 结果
     */
    @Override
    public int checkNumIsContain(CheckNum checkNum){
        return checkNumMapper.checkNumIsContain(checkNum);
    }

    /**
     * 修改检测标准设定
     * 
     * @param checkNum 检测标准设定
     * @return 结果
     */
    @Override
    public int updateCheckNum(CheckNum checkNum)
    {
        checkNum.setUpdateTime(DateUtils.getNowDate());
        return checkNumMapper.updateCheckNum(checkNum);
    }

    /**
     * 批量删除检测标准设定
     * 
     * @param ids 需要删除的检测标准设定ID
     * @return 结果
     */
    @Override
    public int deleteCheckNumByIds(Integer[] ids)
    {
        return checkNumMapper.deleteCheckNumByIds(ids);
    }

    /**
     * 删除检测标准设定信息
     * 
     * @param id 检测标准设定ID
     * @return 结果
     */
    @Override
    public int deleteCheckNumById(Integer id)
    {
        return checkNumMapper.deleteCheckNumById(id);
    }
}
