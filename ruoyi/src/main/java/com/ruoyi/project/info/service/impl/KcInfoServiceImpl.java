package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.KcInfoMapper;
import com.ruoyi.project.info.domain.KcInfo;
import com.ruoyi.project.info.service.IKcInfoService;

/**
 * 收发存查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
@Service
public class KcInfoServiceImpl implements IKcInfoService 
{
    @Autowired
    private KcInfoMapper kcInfoMapper;

    /**
     * 查询收发存查询
     * 
     * @param id 收发存查询ID
     * @return 收发存查询
     */
    @Override
    public KcInfo selectKcInfoById(String id)
    {
        return kcInfoMapper.selectKcInfoById(id);
    }

    /**
     * 查询收发存查询列表
     * 
     * @param kcInfo 收发存查询
     * @return 收发存查询
     */
    @Override
    public List<KcInfo> selectKcInfoList(KcInfo kcInfo)
    {
        return kcInfoMapper.selectKcInfoList(kcInfo);
    }

    /**
     * 新增收发存查询
     * 
     * @param kcInfo 收发存查询
     * @return 结果
     */
    @Override
    public int insertKcInfo(KcInfo kcInfo)
    {
      //  kcInfo.setCreateTime(DateUtils.getNowDate());
        return kcInfoMapper.insertKcInfo(kcInfo);
    }

    /**
     * 修改收发存查询
     * 
     * @param kcInfo 收发存查询
     * @return 结果
     */
    @Override
    public int updateKcInfo(KcInfo kcInfo)
    {
        return kcInfoMapper.updateKcInfo(kcInfo);
    }

    /**
     * 批量删除收发存查询
     * 
     * @param ids 需要删除的收发存查询ID
     * @return 结果
     */
    @Override
    public int deleteKcInfoByIds(String[] ids)
    {
        return kcInfoMapper.deleteKcInfoByIds(ids);
    }

    /**
     * 删除收发存查询信息
     * 
     * @param id 收发存查询ID
     * @return 结果
     */
    @Override
    public int deleteKcInfoById(String id)
    {
        return kcInfoMapper.deleteKcInfoById(id);
    }
}
