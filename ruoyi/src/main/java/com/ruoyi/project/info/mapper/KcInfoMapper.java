package com.ruoyi.project.info.mapper;

import java.util.List;
import com.ruoyi.project.info.domain.KcInfo;

/**
 * 收发存查询Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
public interface KcInfoMapper 
{
    /**
     * 查询收发存查询
     * 
     * @param id 收发存查询ID
     * @return 收发存查询
     */
    public KcInfo selectKcInfoById(String id);

    /**
     * 查询收发存查询列表
     * 
     * @param kcInfo 收发存查询
     * @return 收发存查询集合
     */
    public List<KcInfo> selectKcInfoList(KcInfo kcInfo);

    /**
     * 新增收发存查询
     * 
     * @param kcInfo 收发存查询
     * @return 结果
     */
    public int insertKcInfo(KcInfo kcInfo);

    /**
     * 修改收发存查询
     * 
     * @param kcInfo 收发存查询
     * @return 结果
     */
    public int updateKcInfo(KcInfo kcInfo);

    /**
     * 删除收发存查询
     * 
     * @param id 收发存查询ID
     * @return 结果
     */
    public int deleteKcInfoById(String id);

    /**
     * 批量删除收发存查询
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKcInfoByIds(String[] ids);
}
