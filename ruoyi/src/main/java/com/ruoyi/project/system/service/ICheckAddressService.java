package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.CheckAddress;

/**
 * 检测地点建档Service接口
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public interface ICheckAddressService 
{
    /**
     * 查询检测地点建档
     * 
     * @param id 检测地点建档ID
     * @return 检测地点建档
     */
    public CheckAddress selectCheckAddressById(String id);

    /**
     * 查询检测地点建档列表
     * 
     * @param checkAddress 检测地点建档
     * @return 检测地点建档集合
     */
    public List<CheckAddress> selectCheckAddressList(CheckAddress checkAddress);

    /**
     * 新增检测地点建档
     * 
     * @param checkAddress 检测地点建档
     * @return 结果
     */
    public int insertCheckAddress(CheckAddress checkAddress);

    /**
     * 新增检测地点是否可以删除
     *
     * @param id 检测地点id
     * @return 结果
     */
    public int checkAddress(String id);

    /**
     * 修改检测地点建档
     * 
     * @param checkAddress 检测地点建档
     * @return 结果
     */
    public int updateCheckAddress(CheckAddress checkAddress);

    /**
     * 批量删除检测地点建档
     * 
     * @param ids 需要删除的检测地点建档ID
     * @return 结果
     */
    public int deleteCheckAddressByIds(String[] ids);

    /**
     * 删除检测地点建档信息
     * 
     * @param id 检测地点建档ID
     * @return 结果
     */
    public int deleteCheckAddressById(String id);
}
