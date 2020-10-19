package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.LkckInfoMapper;
import com.ruoyi.project.info.domain.LkckInfo;
import com.ruoyi.project.info.service.ILkckInfoService;

/**
 * 出库单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
@Service
public class LkckInfoServiceImpl implements ILkckInfoService 
{
    @Autowired
    private LkckInfoMapper lkckInfoMapper;

    /**
     * 查询出库单
     * 
     * @param id 出库单ID
     * @return 出库单
     */
    @Override
    public LkckInfo selectLkckInfoById(String id)
    {
        return lkckInfoMapper.selectLkckInfoById(id);
    }

    /**
     * 查询出库单列表
     * 
     * @param lkckInfo 出库单
     * @return 出库单
     */
    @Override
    public List<LkckInfo> selectLkckInfoList(LkckInfo lkckInfo)
    {
        return lkckInfoMapper.selectLkckInfoList(lkckInfo);
    }

    /**
     * 新增出库单
     * 
     * @param lkckInfo 出库单
     * @return 结果
     */
    @Override
    public int insertLkckInfo(LkckInfo lkckInfo)
    {
        lkckInfo.setCreateTime(DateUtils.getNowDate());
        return lkckInfoMapper.insertLkckInfo(lkckInfo);
    }

    /**
     * 修改出库单
     * 
     * @param lkckInfo 出库单
     * @return 结果
     */
    @Override
    public int updateLkckInfo(LkckInfo lkckInfo)
    {
        lkckInfo.setUpdateTime(DateUtils.getNowDate());
        return lkckInfoMapper.updateLkckInfo(lkckInfo);
    }

    /**
     * 批量删除出库单
     * 
     * @param ids 需要删除的出库单ID
     * @return 结果
     */
    @Override
    public int deleteLkckInfoByIds(String[] ids)
    {
        return lkckInfoMapper.deleteLkckInfoByIds(ids);
    }

    /**
     * 删除出库单信息
     * 
     * @param id 出库单ID
     * @return 结果
     */
    @Override
    public int deleteLkckInfoById(String id)
    {
        return lkckInfoMapper.deleteLkckInfoById(id);
    }
    /**
     * 批量生效出库单
     *
     * @param ids 需要生效的出库单ID
     * @return 结果
     */
    public int updateCkdStatus(String[] ids){
        return lkckInfoMapper.updateCkdStatus(ids);
    }
}
