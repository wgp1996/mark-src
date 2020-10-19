package com.ruoyi.project.info.mapper;

import java.util.List;
import com.ruoyi.project.info.domain.CheckInfo;

/**
 * 盘点单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
public interface CheckInfoMapper 
{
    /**
     * 查询盘点单
     * 
     * @param id 盘点单ID
     * @return 盘点单
     */
    public CheckInfo selectCheckInfoById(String id);

    /**
     * 查询盘点单列表
     * 
     * @param checkInfo 盘点单
     * @return 盘点单集合
     */
    public List<CheckInfo> selectCheckInfoList(CheckInfo checkInfo);

    /**
     * 新增盘点单
     * 
     * @param checkInfo 盘点单
     * @return 结果
     */
    public int insertCheckInfo(CheckInfo checkInfo);

    /**
     * 修改盘点单
     * 
     * @param checkInfo 盘点单
     * @return 结果
     */
    public int updateCheckInfo(CheckInfo checkInfo);

    /**
     * 删除盘点单
     * 
     * @param id 盘点单ID
     * @return 结果
     */
    public int deleteCheckInfoById(String id);

    /**
     * 批量删除盘点单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckInfoByIds(String[] ids);
}
