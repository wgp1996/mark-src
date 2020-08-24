package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PersonInfoMapper;
import com.ruoyi.project.system.domain.PersonInfo;
import com.ruoyi.project.system.service.IPersonInfoService;

/**
 * 供应商建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
@Service
public class PersonInfoServiceImpl implements IPersonInfoService 
{
    @Autowired
    private PersonInfoMapper personInfoMapper;

    /**
     * 查询供应商建档
     * 
     * @param id 供应商建档ID
     * @return 供应商建档
     */
    @Override
    public PersonInfo selectPersonInfoById(Integer id)
    {
        return personInfoMapper.selectPersonInfoById(id);
    }
    /**
     * 查询供应商建档
     *
     * @param id 供应商建档ID
     * @return 供应商建档
     */
    @Override
    public PersonInfo selectPersonInfoByName(String name,String createBy,Integer id){
        return personInfoMapper.selectPersonInfoByName(name,createBy,id);
    }
    /**
     * 查询供应商建档列表
     * 
     * @param personInfo 供应商建档
     * @return 供应商建档
     */
    @Override
    public List<PersonInfo> selectPersonInfoList(PersonInfo personInfo)
    {
        return personInfoMapper.selectPersonInfoList(personInfo);
    }

    /**
     * 新增供应商建档
     * 
     * @param personInfo 供应商建档
     * @return 结果
     */
    @Override
    public int insertPersonInfo(PersonInfo personInfo)
    {
        personInfo.setCreateTime(DateUtils.getNowDate());
        return personInfoMapper.insertPersonInfo(personInfo);
    }

    /**
     * 修改供应商建档
     * 
     * @param personInfo 供应商建档
     * @return 结果
     */
    @Override
    public int updatePersonInfo(PersonInfo personInfo)
    {
        personInfo.setUpdateTime(DateUtils.getNowDate());
        return personInfoMapper.updatePersonInfo(personInfo);
    }

    /**
     * 批量删除供应商建档
     * 
     * @param ids 需要删除的供应商建档ID
     * @return 结果
     */
    @Override
    public int deletePersonInfoByIds(Integer[] ids)
    {
        return personInfoMapper.deletePersonInfoByIds(ids);
    }

    /**
     * 删除供应商建档信息
     * 
     * @param id 供应商建档ID
     * @return 结果
     */
    @Override
    public int deletePersonInfoById(Integer id)
    {
        return personInfoMapper.deletePersonInfoById(id);
    }
}
