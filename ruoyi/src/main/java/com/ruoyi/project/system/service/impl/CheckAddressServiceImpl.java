package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CheckAddressMapper;
import com.ruoyi.project.system.domain.CheckAddress;
import com.ruoyi.project.system.service.ICheckAddressService;

/**
 * 检测地点建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Service
public class CheckAddressServiceImpl implements ICheckAddressService 
{
    @Autowired
    private CheckAddressMapper checkAddressMapper;

    /**
     * 查询检测地点建档
     * 
     * @param id 检测地点建档ID
     * @return 检测地点建档
     */
    @Override
    public CheckAddress selectCheckAddressById(String id)
    {
        return checkAddressMapper.selectCheckAddressById(id);
    }

    /**
     * 查询检测地点建档列表
     * 
     * @param checkAddress 检测地点建档
     * @return 检测地点建档
     */
    @Override
    public List<CheckAddress> selectCheckAddressList(CheckAddress checkAddress)
    {
        return checkAddressMapper.selectCheckAddressList(checkAddress);
    }

    /**
     * 新增检测地点建档
     * 
     * @param checkAddress 检测地点建档
     * @return 结果
     */
    @Override
    public int insertCheckAddress(CheckAddress checkAddress)
    {
        checkAddress.setCreateTime(DateUtils.getNowDate());
        return checkAddressMapper.insertCheckAddress(checkAddress);
    }

    /**
     * 修改检测地点建档
     * 
     * @param checkAddress 检测地点建档
     * @return 结果
     */
    @Override
    public int updateCheckAddress(CheckAddress checkAddress)
    {
        //checkAddress.setUpdateTime(DateUtils.getNowDate());
        return checkAddressMapper.updateCheckAddress(checkAddress);
    }

    /**
     * 新增检测地点是否可以删除
     *
     * @param id 检测地点id
     * @return 结果
     */
    @Override
    public int checkAddress(String id){
        return checkAddressMapper.checkAddress(id);
    }
    /**
     * 批量删除检测地点建档
     * 
     * @param ids 需要删除的检测地点建档ID
     * @return 结果
     */
    @Override
    public int deleteCheckAddressByIds(String[] ids)
    {
        return checkAddressMapper.deleteCheckAddressByIds(ids);
    }

    /**
     * 删除检测地点建档信息
     * 
     * @param id 检测地点建档ID
     * @return 结果
     */
    @Override
    public int deleteCheckAddressById(String id)
    {
        return checkAddressMapper.deleteCheckAddressById(id);
    }
}
