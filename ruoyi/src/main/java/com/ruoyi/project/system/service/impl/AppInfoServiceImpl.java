package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.AppInfoMapper;
import com.ruoyi.project.system.domain.AppInfo;
import com.ruoyi.project.system.service.IAppInfoService;

/**
 * APP更新Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-03
 */
@Service
public class AppInfoServiceImpl implements IAppInfoService 
{
    @Autowired
    private AppInfoMapper appInfoMapper;

    /**
     * 查询APP更新
     * 
     * @param id APP更新ID
     * @return APP更新
     */
    @Override
    public AppInfo selectAppInfoById(Long id)
    {
        return appInfoMapper.selectAppInfoById(id);
    }

    /**
     * 查询APP更新列表
     * 
     * @param appInfo APP更新
     * @return APP更新
     */
    @Override
    public List<AppInfo> selectAppInfoList(AppInfo appInfo)
    {
        return appInfoMapper.selectAppInfoList(appInfo);
    }

    /**
     * 新增APP更新
     * 
     * @param appInfo APP更新
     * @return 结果
     */
    @Override
    public int insertAppInfo(AppInfo appInfo)
    {
        return appInfoMapper.insertAppInfo(appInfo);
    }

    /**
     * 修改APP更新
     * 
     * @param appInfo APP更新
     * @return 结果
     */
    @Override
    public int updateAppInfo(AppInfo appInfo)
    {
        return appInfoMapper.updateAppInfo(appInfo);
    }

    /**
     * 批量删除APP更新
     * 
     * @param ids 需要删除的APP更新ID
     * @return 结果
     */
    @Override
    public int deleteAppInfoByIds(Long[] ids)
    {
        return appInfoMapper.deleteAppInfoByIds(ids);
    }

    /**
     * 删除APP更新信息
     * 
     * @param id APP更新ID
     * @return 结果
     */
    @Override
    public int deleteAppInfoById(Long id)
    {
        return appInfoMapper.deleteAppInfoById(id);
    }
}
