package com.ruoyi.project.info.mapper;

import java.util.List;
import com.ruoyi.project.info.domain.RkInfo;

/**
 * 入库单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
public interface RkInfoMapper 
{
    /**
     * 查询入库单
     * 
     * @param id 入库单ID
     * @return 入库单
     */
    public RkInfo selectRkInfoById(String id);

    /**
     * 查询入库单列表
     * 
     * @param rkInfo 入库单
     * @return 入库单集合
     */
    public List<RkInfo> selectRkInfoList(RkInfo rkInfo);

    /**
     * 新增入库单
     * 
     * @param rkInfo 入库单
     * @return 结果
     */
    public int insertRkInfo(RkInfo rkInfo);

    /**
     * 修改入库单
     * 
     * @param rkInfo 入库单
     * @return 结果
     */
    public int updateRkInfo(RkInfo rkInfo);

    /**
     * 删除入库单
     * 
     * @param id 入库单ID
     * @return 结果
     */
    public int deleteRkInfoById(String id);

    /**
     * 批量删除入库单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRkInfoByIds(String[] ids);
    /**
     * 批量生效入库单
     *
     * @param ids 需要生效的入库单ID
     * @return 结果
     */
    public int updateRkdStatus(String[] ids);
}
