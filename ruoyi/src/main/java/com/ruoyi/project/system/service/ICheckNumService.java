package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.CheckNum;

/**
 * 检测标准设定Service接口
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
public interface ICheckNumService 
{
    /**
     * 查询检测标准设定
     * 
     * @param id 检测标准设定ID
     * @return 检测标准设定
     */
    public CheckNum selectCheckNumById(Integer id);

    /**
     * 查询检测标准设定列表
     * 
     * @param checkNum 检测标准设定
     * @return 检测标准设定集合
     */
    public List<CheckNum> selectCheckNumList(CheckNum checkNum);

    /**
     * 新增检测标准设定
     * 
     * @param checkNum 检测标准设定
     * @return 结果
     */
    public int insertCheckNum(CheckNum checkNum);

    /**
     * 检测标准是否存在
     *
     * @param checkNum 检测标准设定
     * @return 结果
     */
    public int checkNumIsContain(CheckNum checkNum);

    /**
     * 修改检测标准设定
     * 
     * @param checkNum 检测标准设定
     * @return 结果
     */
    public int updateCheckNum(CheckNum checkNum);

    /**
     * 批量删除检测标准设定
     * 
     * @param ids 需要删除的检测标准设定ID
     * @return 结果
     */
    public int deleteCheckNumByIds(Integer[] ids);

    /**
     * 删除检测标准设定信息
     * 
     * @param id 检测标准设定ID
     * @return 结果
     */
    public int deleteCheckNumById(Integer id);
}
