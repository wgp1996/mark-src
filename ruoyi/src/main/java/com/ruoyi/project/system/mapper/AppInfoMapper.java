package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.AppInfo;

/**
 * APP更新Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-03
 */
public interface AppInfoMapper 
{
    /**
     * 查询APP更新
     * 
     * @param id APP更新ID
     * @return APP更新
     */
    public AppInfo selectAppInfoById(Long id);

    /**
     * 查询APP更新列表
     * 
     * @param appInfo APP更新
     * @return APP更新集合
     */
    public List<AppInfo> selectAppInfoList(AppInfo appInfo);

    /**
     * 新增APP更新
     * 
     * @param appInfo APP更新
     * @return 结果
     */
    public int insertAppInfo(AppInfo appInfo);

    /**
     * 修改APP更新
     * 
     * @param appInfo APP更新
     * @return 结果
     */
    public int updateAppInfo(AppInfo appInfo);

    /**
     * 删除APP更新
     * 
     * @param id APP更新ID
     * @return 结果
     */
    public int deleteAppInfoById(Long id);

    /**
     * 批量删除APP更新
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppInfoByIds(Long[] ids);
}
