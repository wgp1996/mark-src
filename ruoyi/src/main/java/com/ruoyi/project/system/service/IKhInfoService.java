package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.KhInfo;
import com.ruoyi.project.system.domain.PersonInfo;

/**
 * 客户建档Service接口
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public interface IKhInfoService 
{
    /**
     * 查询客户建档
     * 
     * @param id 客户建档ID
     * @return 客户建档
     */
    public KhInfo selectKhInfoById(Integer id);

    /**
     * 查询客户建档
     *
     * @param id 客户建档ID
     * @return 客户建档
     */
    public KhInfo selectKhInfoByName(String name, String createBy, Integer id);

    /**
     * 查询客户建档列表
     * 
     * @param khInfo 客户建档
     * @return 客户建档集合
     */
    public List<KhInfo> selectKhInfoList(KhInfo khInfo);

    /**
     * 新增客户建档
     * 
     * @param khInfo 客户建档
     * @return 结果
     */
    public int insertKhInfo(KhInfo khInfo);

    /**
     * 修改客户建档
     * 
     * @param khInfo 客户建档
     * @return 结果
     */
    public int updateKhInfo(KhInfo khInfo);

    /**
     * 批量删除客户建档
     * 
     * @param ids 需要删除的客户建档ID
     * @return 结果
     */
    public int deleteKhInfoByIds(Integer[] ids);

    /**
     * 删除客户建档信息
     * 
     * @param id 客户建档ID
     * @return 结果
     */
    public int deleteKhInfoById(Integer id);
}
