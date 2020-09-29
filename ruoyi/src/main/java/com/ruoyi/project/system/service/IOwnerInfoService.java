package com.ruoyi.project.system.service;

import java.util.List;

import com.ruoyi.project.system.domain.OwnerInfo;

/**
 * 业户建档Service接口
 *
 * @author ruoyi
 * @date 2020-07-29
 */
public interface IOwnerInfoService {
    /**
     * 查询业户建档
     *
     * @param id 业户建档ID
     * @return 业户建档
     */
    public OwnerInfo selectOwnerInfoById(String id);

    /**
     * 查询业户建档
     *
     * @param id   业户建档ID
     * @param code 业户建档code
     * @return 业户建档
     */
    public OwnerInfo selectOwnerInfoByCode(String code, String id);


    /**
     * 查询业户建档列表
     *
     * @param ownerInfo 业户建档
     * @return 业户建档集合
     */
    public List<OwnerInfo> selectOwnerInfoList(OwnerInfo ownerInfo);

    /**
     * 匹配业户建档列表
     *
     * @param ownerInfo 业户建档
     * @return 业户建档集合
     */
    public List<OwnerInfo> selectOwnerInfoListLike(OwnerInfo ownerInfo);

    /**
     * 市平台查询业户建档列表
     *
     * @param ownerInfo 业户建档
     * @return 业户建档集合
     */
    public List<OwnerInfo> selectOwnerStallInfoList(OwnerInfo ownerInfo);

    /**
     * 新增业户建档
     *
     * @param ownerInfo 业户建档
     * @return 结果
     */
    public int insertOwnerInfo(OwnerInfo ownerInfo);

    /**
     * 修改业户建档
     *
     * @param ownerInfo 业户建档
     * @return 结果
     */
    public int updateOwnerInfo(OwnerInfo ownerInfo);

    /**
     * 批量删除业户建档
     *
     * @param ids 需要删除的业户建档ID
     * @return 结果
     */
    public int deleteOwnerInfoByIds(String[] ids);

    /**
     * 删除业户建档信息
     *
     * @param id 业户建档ID
     * @return 结果
     */
    public int deleteOwnerInfoById(String id);
}
