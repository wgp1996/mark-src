package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.RkInfoMapper;
import com.ruoyi.project.info.domain.RkInfo;
import com.ruoyi.project.info.service.IRkInfoService;

/**
 * 入库单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
@Service
public class RkInfoServiceImpl implements IRkInfoService 
{
    @Autowired
    private RkInfoMapper rkInfoMapper;

    /**
     * 查询入库单
     * 
     * @param id 入库单ID
     * @return 入库单
     */
    @Override
    public RkInfo selectRkInfoById(String id)
    {
        return rkInfoMapper.selectRkInfoById(id);
    }

    /**
     * 查询入库单列表
     * 
     * @param rkInfo 入库单
     * @return 入库单
     */
    @Override
    public List<RkInfo> selectRkInfoList(RkInfo rkInfo)
    {
        return rkInfoMapper.selectRkInfoList(rkInfo);
    }

    /**
     * 新增入库单
     * 
     * @param rkInfo 入库单
     * @return 结果
     */
    @Override
    public int insertRkInfo(RkInfo rkInfo)
    {
        rkInfo.setCreateTime(DateUtils.getNowDate());
        return rkInfoMapper.insertRkInfo(rkInfo);
    }

    /**
     * 修改入库单
     * 
     * @param rkInfo 入库单
     * @return 结果
     */
    @Override
    public int updateRkInfo(RkInfo rkInfo)
    {
        rkInfo.setUpdateTime(DateUtils.getNowDate());
        return rkInfoMapper.updateRkInfo(rkInfo);
    }

    /**
     * 批量删除入库单
     * 
     * @param ids 需要删除的入库单ID
     * @return 结果
     */
    @Override
    public int deleteRkInfoByIds(String[] ids)
    {
        return rkInfoMapper.deleteRkInfoByIds(ids);
    }
    /**
     * 批量生效入库单
     *
     * @param ids 需要生效的入库单ID
     * @return 结果
     */
    @Override
    public int updateRkdStatus(String[] ids){
        return rkInfoMapper.updateRkdStatus(ids);
    }
    /**
     * 删除入库单信息
     * 
     * @param id 入库单ID
     * @return 结果
     */
    @Override
    public int deleteRkInfoById(String id)
    {
        return rkInfoMapper.deleteRkInfoById(id);
    }
}
