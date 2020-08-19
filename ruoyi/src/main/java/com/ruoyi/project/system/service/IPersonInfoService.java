package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.PersonInfo;

/**
 * 供应商建档Service接口
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public interface IPersonInfoService 
{
    /**
     * 查询供应商建档
     * 
     * @param id 供应商建档ID
     * @return 供应商建档
     */
    public PersonInfo selectPersonInfoById(Integer id);

    /**
     * 查询供应商建档
     *
     * @param id 供应商建档ID
     * @return 供应商建档
     */
    public PersonInfo selectPersonInfoByName(String name,Integer id);

    /**
     * 查询供应商建档列表
     * 
     * @param personInfo 供应商建档
     * @return 供应商建档集合
     */
    public List<PersonInfo> selectPersonInfoList(PersonInfo personInfo);

    /**
     * 新增供应商建档
     * 
     * @param personInfo 供应商建档
     * @return 结果
     */
    public int insertPersonInfo(PersonInfo personInfo);

    /**
     * 修改供应商建档
     * 
     * @param personInfo 供应商建档
     * @return 结果
     */
    public int updatePersonInfo(PersonInfo personInfo);

    /**
     * 批量删除供应商建档
     * 
     * @param ids 需要删除的供应商建档ID
     * @return 结果
     */
    public int deletePersonInfoByIds(Integer[] ids);

    /**
     * 删除供应商建档信息
     * 
     * @param id 供应商建档ID
     * @return 结果
     */
    public int deletePersonInfoById(Integer id);
}
