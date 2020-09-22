package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.CkInfo;

import java.util.List;

/**
 * 仓库建档Service接口
 * 
 * @author ruoyi
 * @date 2020-08-11
 */
public interface ICkInfoService
{
    /**
     * 查询仓库建档
     *
     * @param id 仓库建档ID
     * @return 仓库建档
     */
    public CkInfo selectCkInfoById(String id);

    /**
     * 查询仓库建档
     *
     * @param name 仓库名称
     * @return 仓库建档
     */
    public CkInfo selectCkInfoByName(String name, String id);

    /**
     * 查询仓库建档列表
     * 
     * @param storeInfo 仓库建档
     * @return 仓库建档集合
     */
    public List<CkInfo> selectCkInfoList(CkInfo storeInfo);

    /**
     * 新增仓库建档
     * 
     * @param storeInfo 仓库建档
     * @return 结果
     */
    public int insertCkInfo(CkInfo storeInfo);

    /**
     * 修改仓库建档
     * 
     * @param storeInfo 仓库建档
     * @return 结果
     */
    public int updateCkInfo(CkInfo storeInfo);

    /**
     * 批量删除仓库建档
     * 
     * @param ids 需要删除的仓库建档ID
     * @return 结果
     */
    public int deleteCkInfoByIds(String[] ids);

    /**
     * 删除仓库建档信息
     * 
     * @param id 仓库建档ID
     * @return 结果
     */
    public int deleteCkInfoById(String id);
}
