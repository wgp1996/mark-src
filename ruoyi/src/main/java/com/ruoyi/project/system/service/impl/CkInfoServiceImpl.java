package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.CkInfo;
import com.ruoyi.project.system.mapper.CkInfoMapper;
import com.ruoyi.project.system.service.ICkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 仓库建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-11
 */
@Service
public class CkInfoServiceImpl implements ICkInfoService {
    @Autowired
    private CkInfoMapper storeInfoMapper;

    /**
     * 查询仓库建档
     *
     * @param id 仓库建档ID
     * @return 仓库建档
     */
    @Override
    public CkInfo selectCkInfoById(String id) {
        return storeInfoMapper.selectCkInfoById(id);
    }

    /**
     * 查询仓库建档
     *
     * @param name 仓库名称
     * @return 仓库建档
     */
    @Override
    public CkInfo selectCkInfoByName(String name,String id){
            return storeInfoMapper.selectCkInfoByName(name,id);
    }
    /**
     * 查询仓库建档列表
     * 
     * @param storeInfo 仓库建档
     * @return 仓库建档
     */
    @Override
    public List<CkInfo> selectCkInfoList(CkInfo storeInfo)
    {
        return storeInfoMapper.selectCkInfoList(storeInfo);
    }

    /**
     * 新增仓库建档
     * 
     * @param storeInfo 仓库建档
     * @return 结果
     */
    @Override
    public int insertCkInfo(CkInfo storeInfo)
    {
        storeInfo.setCreateTime(DateUtils.getNowDate());
        return storeInfoMapper.insertCkInfo(storeInfo);
    }

    /**
     * 修改仓库建档
     * 
     * @param storeInfo 仓库建档
     * @return 结果
     */
    @Override
    public int updateCkInfo(CkInfo storeInfo)
    {
        storeInfo.setUpdateTime(DateUtils.getNowDate());
        return storeInfoMapper.updateCkInfo(storeInfo);
    }

    /**
     * 批量删除仓库建档
     * 
     * @param ids 需要删除的仓库建档ID
     * @return 结果
     */
    @Override
    public int deleteCkInfoByIds(String[] ids)
    {
        return storeInfoMapper.deleteCkInfoByIds(ids);
    }

    /**
     * 删除仓库建档信息
     * 
     * @param id 仓库建档ID
     * @return 结果
     */
    @Override
    public int deleteCkInfoById(String id)
    {
        return storeInfoMapper.deleteCkInfoById(id);
    }
}
