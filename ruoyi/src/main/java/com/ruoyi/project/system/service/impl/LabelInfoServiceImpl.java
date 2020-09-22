package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.LabelInfoMapper;
import com.ruoyi.project.system.domain.LabelInfo;
import com.ruoyi.project.system.service.ILabelInfoService;

/**
 * 电子价签管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@Service
public class LabelInfoServiceImpl implements ILabelInfoService 
{
    @Autowired
    private LabelInfoMapper labelInfoMapper;

    /**
     * 查询电子价签管理
     * 
     * @param id 电子价签管理ID
     * @return 电子价签管理
     */
    @Override
    public LabelInfo selectLabelInfoById(String id)
    {
        return labelInfoMapper.selectLabelInfoById(id);
    }

    /**
     * 查询电子价签管理列表
     * 
     * @param labelInfo 电子价签管理
     * @return 电子价签管理
     */
    @Override
    public List<LabelInfo> selectLabelInfoList(LabelInfo labelInfo)
    {
        return labelInfoMapper.selectLabelInfoList(labelInfo);
    }
    /**
     * 根据商品编码查询电子价签
     * @return 电子价签
     */
    @Override
    public LabelInfo selectLabelInfoByGoodsCode(String goodsCode,String storeId){
        return labelInfoMapper.selectLabelInfoByGoodsCode(goodsCode,storeId);
    }
    /**
     * 批量修改电子价签管理
     *
     * @param goodsCodes 需要删除的电子价签管理编码
     * @return 结果
     */
    @Override
    public int updateLabelInfoStatus(String[] goodsCodes,String storeId){
        return labelInfoMapper.updateLabelInfoStatus(goodsCodes,storeId);
    }
    /**
     * 检查电子价签是否重复
     *
     * @param mac 电子价签MACd地址
     * @return 结果
     */
    @Override
    public int checkLabel(String mac,String id){
        return labelInfoMapper.checkLabel(mac,id);
    }

    /**
     * 批量修改电子价签绑定网关状态
     *
     * @param ids 需要删除的电子价签管理MAC地址
     * @return 结果
     */
    @Override
    public int bindGateWayMacs(String[] ids){
        return labelInfoMapper.bindGateWayMacs(ids);
    }
    /**
     * 新增电子价签管理
     * 
     * @param labelInfo 电子价签管理
     * @return 结果
     */
    @Override
    public int insertLabelInfo(LabelInfo labelInfo)
    {
        labelInfo.setCreateTime(DateUtils.getNowDate());
        return labelInfoMapper.insertLabelInfo(labelInfo);
    }

    /**
     * 修改电子价签管理
     * 
     * @param labelInfo 电子价签管理
     * @return 结果
     */
    @Override
    public int updateLabelInfo(LabelInfo labelInfo)
    {
        labelInfo.setUpdateTime(DateUtils.getNowDate());
        return labelInfoMapper.updateLabelInfo(labelInfo);
    }


    /**
     * 修改电子价签管理
     *
     * @param labelInfo 电子价签管理
     * @return 结果
     */
    @Override
    public int updateLabelInfoByMac(LabelInfo labelInfo)
    {
        labelInfo.setUpdateTime(DateUtils.getNowDate());
        return labelInfoMapper.updateLabelInfoByMac(labelInfo);
    }


    /**
     * 批量删除电子价签管理
     * 
     * @param ids 需要删除的电子价签管理ID
     * @return 结果
     */
    @Override
    public int deleteLabelInfoByIds(String[] ids)
    {
        return labelInfoMapper.deleteLabelInfoByIds(ids);
    }

    /**
     * 删除电子价签管理信息
     * 
     * @param id 电子价签管理ID
     * @return 结果
     */
    @Override
    public int deleteLabelInfoById(String id)
    {
        return labelInfoMapper.deleteLabelInfoById(id);
    }
}
