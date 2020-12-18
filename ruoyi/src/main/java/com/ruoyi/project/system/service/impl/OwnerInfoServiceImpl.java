package com.ruoyi.project.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.OwnerInfoMapper;
import com.ruoyi.project.system.domain.OwnerInfo;
import com.ruoyi.project.system.service.IOwnerInfoService;

/**
 * 业户建档Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@Service
public class OwnerInfoServiceImpl implements IOwnerInfoService {
    @Autowired
    private OwnerInfoMapper ownerInfoMapper;

    /**
     * 查询业户建档
     *
     * @param id 业户建档ID
     * @return 业户建档
     */
    @Override
    public OwnerInfo selectOwnerInfoById(String id) {
        return ownerInfoMapper.selectOwnerInfoById(id);
    }
    /**
     * 市平台查询业户建档列表
     *
     * @param ownerInfo 业户建档
     * @return 业户建档集合
     */
    @Override
    public List<OwnerInfo> selectOwnerStallInfoList(OwnerInfo ownerInfo){
        return ownerInfoMapper.selectOwnerStallInfoList(ownerInfo);
    }
    /**
     * 匹配业户建档列表
     *
     * @param ownerInfo 业户建档
     * @return 业户建档集合
     */
    @Override
    public List<OwnerInfo> selectOwnerInfoListLike(OwnerInfo ownerInfo){
        return ownerInfoMapper.selectOwnerInfoListLike(ownerInfo);
    }
    /**
     * 查询业户建档
     *
     * @param id   业户建档ID
     * @param code 业户建档code
     * @return 业户建档
     */
    @Override
    public OwnerInfo selectOwnerInfoByCode(String code, String id) {
        return ownerInfoMapper.selectOwnerInfoByCode(code, id);
    }

    /**
     * 查询业户建档列表
     *
     * @param ownerInfo 业户建档
     * @return 业户建档
     */
    @Override
    public List<OwnerInfo> selectOwnerInfoList(OwnerInfo ownerInfo) {
        return ownerInfoMapper.selectOwnerInfoList(ownerInfo);
    }

    /**
     * 新增业户建档
     *
     * @param ownerInfo 业户建档
     * @return 结果
     */
    @Override
    public int insertOwnerInfo(OwnerInfo ownerInfo) {
        ownerInfo.setCreateTime(DateUtils.getNowDate());
        return ownerInfoMapper.insertOwnerInfo(ownerInfo);
    }

    /**
     * 修改业户建档
     *
     * @param ownerInfo 业户建档
     * @return 结果
     */
    @Override
    public int updateOwnerInfo(OwnerInfo ownerInfo) {
        ownerInfo.setUpdateTime(DateUtils.getNowDate());
        return ownerInfoMapper.updateOwnerInfo(ownerInfo);
    }
    /**
     * 修改业户营业执照
     *
     * @param ownerInfo 业户建档
     * @return 结果
     */
    @Override
    public int updateOwnerByOwnerCode(OwnerInfo ownerInfo){
        return ownerInfoMapper.updateOwnerByOwnerCode(ownerInfo);
    }
    /**
     * 修改上传省平台状态
     *
     * @return 结果
     */
    @Override
    public int updateOwnerIsUpload(String id){
        return ownerInfoMapper.updateOwnerIsUpload(id);
    }
    /**
     * 批量删除业户建档
     *
     * @param ids 需要删除的业户建档ID
     * @return 结果
     */
    @Override
    public int deleteOwnerInfoByIds(String[] ids) {
        return ownerInfoMapper.deleteOwnerInfoByIds(ids);
    }

    /**
     * 删除业户建档信息
     *
     * @param id 业户建档ID
     * @return 结果
     */
    @Override
    public int deleteOwnerInfoById(String id) {
        return ownerInfoMapper.deleteOwnerInfoById(id);
    }
}
