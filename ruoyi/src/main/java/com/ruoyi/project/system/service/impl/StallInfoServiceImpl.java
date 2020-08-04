package com.ruoyi.project.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.MarkChildInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.StallInfoMapper;
import com.ruoyi.project.system.domain.StallInfo;
import com.ruoyi.project.system.service.IStallInfoService;

/**
 * 市场摊位信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@Service
public class StallInfoServiceImpl implements IStallInfoService {
    @Autowired
    private StallInfoMapper stallInfoMapper;

    /**
     * 查询市场摊位信息
     *
     * @param id 市场摊位信息ID
     * @return 市场摊位信息
     */
    @Override
    public StallInfo selectStallInfoById(String id) {
        return stallInfoMapper.selectStallInfoById(id);
    }

    /**
     * 查询二级市场信息列表
     *
     * @return 二级市场信息集合
     */
    @Override
    public List<MarkChildInfo> selectCMarkData() {
        return stallInfoMapper.selectCMarkData();
    }

    ;

    /**
     * 查询市场摊位信息
     *
     * @param code 市场摊位信息编码
     * @return 市场摊位信息
     */
    @Override
    public StallInfo selectStallInfoByCode(String code, String id) {
        return stallInfoMapper.selectStallInfoByCode(code, id);
    }

    /**
     * 合同选择市场摊位信息列表
     *
     * @param stallInfo 市场摊位信息
     * @return 市场摊位信息
     */
    @Override
    public List<StallInfo> selectLeaseStallInfoList(StallInfo stallInfo) {
        return stallInfoMapper.selectLeaseStallInfoList(stallInfo);
    }

    /**
     * 查询市场摊位信息列表
     *
     * @param stallInfo 市场摊位信息
     * @return 市场摊位信息
     */
    @Override
    public List<StallInfo> selectStallInfoList(StallInfo stallInfo) {
        return stallInfoMapper.selectStallInfoList(stallInfo);
    }

    /**
     * 新增市场摊位信息
     *
     * @param stallInfo 市场摊位信息
     * @return 结果
     */
    @Override
    public int insertStallInfo(StallInfo stallInfo) {
        stallInfo.setCreateTime(DateUtils.getNowDate());
        return stallInfoMapper.insertStallInfo(stallInfo);
    }

    /**
     * 修改市场摊位信息
     *
     * @param stallInfo 市场摊位信息
     * @return 结果
     */
    @Override
    public int updateStallInfo(StallInfo stallInfo) {
        stallInfo.setUpdateTime(DateUtils.getNowDate());
        return stallInfoMapper.updateStallInfo(stallInfo);
    }

    /**
     * 批量删除市场摊位信息
     *
     * @param ids 需要删除的市场摊位信息ID
     * @return 结果
     */
    @Override
    public int deleteStallInfoByIds(String[] ids) {
        return stallInfoMapper.deleteStallInfoByIds(ids);
    }

    /**
     * 删除市场摊位信息信息
     *
     * @param id 市场摊位信息ID
     * @return 结果
     */
    @Override
    public int deleteStallInfoById(String id) {
        return stallInfoMapper.deleteStallInfoById(id);
    }
}
