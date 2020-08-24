package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.KhInfoMapper;
import com.ruoyi.project.system.domain.KhInfo;
import com.ruoyi.project.system.service.IKhInfoService;

/**
 * 客户建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
@Service
public class KhInfoServiceImpl implements IKhInfoService 
{
    @Autowired
    private KhInfoMapper khInfoMapper;

    /**
     * 查询客户建档
     * 
     * @param id 客户建档ID
     * @return 客户建档
     */
    @Override
    public KhInfo selectKhInfoById(Integer id)
    {
        return khInfoMapper.selectKhInfoById(id);
    }
    /**
     * 查询客户建档
     *
     * @param id 供应商建档ID
     * @return 供应商建档
     */
    @Override
    public KhInfo selectKhInfoByName(String name, String createBy, Integer id){
        return khInfoMapper.selectKhInfoByName(name,createBy,id);
    }
    /**
     * 查询客户建档列表
     * 
     * @param khInfo 客户建档
     * @return 客户建档
     */
    @Override
    public List<KhInfo> selectKhInfoList(KhInfo khInfo)
    {
        return khInfoMapper.selectKhInfoList(khInfo);
    }

    /**
     * 新增客户建档
     * 
     * @param khInfo 客户建档
     * @return 结果
     */
    @Override
    public int insertKhInfo(KhInfo khInfo)
    {
        khInfo.setCreateTime(DateUtils.getNowDate());
        return khInfoMapper.insertKhInfo(khInfo);
    }

    /**
     * 修改客户建档
     * 
     * @param khInfo 客户建档
     * @return 结果
     */
    @Override
    public int updateKhInfo(KhInfo khInfo)
    {
        khInfo.setUpdateTime(DateUtils.getNowDate());
        return khInfoMapper.updateKhInfo(khInfo);
    }

    /**
     * 批量删除客户建档
     * 
     * @param ids 需要删除的客户建档ID
     * @return 结果
     */
    @Override
    public int deleteKhInfoByIds(Integer[] ids)
    {
        return khInfoMapper.deleteKhInfoByIds(ids);
    }

    /**
     * 删除客户建档信息
     * 
     * @param id 客户建档ID
     * @return 结果
     */
    @Override
    public int deleteKhInfoById(Integer id)
    {
        return khInfoMapper.deleteKhInfoById(id);
    }
}
