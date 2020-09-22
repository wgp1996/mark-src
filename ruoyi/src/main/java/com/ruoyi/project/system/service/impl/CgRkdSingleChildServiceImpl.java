package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.CgRkdSingleChild;
import com.ruoyi.project.system.mapper.CgRkdSingleChildMapper;
import com.ruoyi.project.system.service.ICgRkdSingleChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进货单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@Service
public class CgRkdSingleChildServiceImpl implements ICgRkdSingleChildService
{
    @Autowired
    private CgRkdSingleChildMapper cgRkdChildMapper;

    /**
     * 查询进货单子表
     * 
     * @param id 进货单子表ID
     * @return 进货单子表
     */
    @Override
    public CgRkdSingleChild selectCgRkdSingleChildById(String id)
    {
        return cgRkdChildMapper.selectCgRkdSingleChildById(id);
    }
    /**
     * 查询进货单子表
     *
     * @param createBy 用户编码
     * @return 进货单子表
     */
    @Override
    public List<CgRkdSingleChild> selectCgRkdSingleChildByUser(String createBy){
        return cgRkdChildMapper.selectCgRkdSingleChildByUser(createBy);
    }
    /**
     * 查询进货单子表
     *
     * @param dj_number 进货单子表编码
     * @return 进货单子表
     */
    @Override
    public List<CgRkdSingleChild> selectCgRkdSingleChildByNumber(String dj_number){
        return cgRkdChildMapper.selectCgRkdSingleChildByNumber(dj_number);
    }
    /**
     * 查询进货单子表
     *
     * @param stallCode 主表摊位编码
     * @return 进货单子表
     */
    public List<CgRkdSingleChild> appRkdChildListByStall(String stallCode){
        return cgRkdChildMapper.appRkdChildListByStall(stallCode);
    }

    /**
     * 查询进货单子表列表
     * 
     * @param cgRkdChild 进货单子表
     * @return 进货单子表
     */
    @Override
    public List<CgRkdSingleChild> selectCgRkdSingleChildList(CgRkdSingleChild cgRkdChild)
    {
        return cgRkdChildMapper.selectCgRkdSingleChildList(cgRkdChild);
    }

    /**
     * 新增进货单子表
     * 
     * @param cgRkdChild 进货单子表
     * @return 结果
     */
    @Override
    public int insertCgRkdSingleChild(CgRkdSingleChild cgRkdChild)
    {
        cgRkdChild.setCreateTime(DateUtils.getNowDate());
        return cgRkdChildMapper.insertCgRkdSingleChild(cgRkdChild);
    }

    /**
     * 修改进货单子表
     * 
     * @param cgRkdChild 进货单子表
     * @return 结果
     */
    @Override
    public int updateCgRkdSingleChild(CgRkdSingleChild cgRkdChild)
    {
        cgRkdChild.setUpdateTime(DateUtils.getNowDate());
        return cgRkdChildMapper.updateCgRkdSingleChild(cgRkdChild);
    }

    /**
     * 批量删除进货单子表
     * 
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdSingleChildByIds(String[] ids)
    {
        return cgRkdChildMapper.deleteCgRkdSingleChildByIds(ids);
    }

    /**
     * 删除进货单子表信息
     * 
     * @param id 进货单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdSingleChildById(String id)
    {
        return cgRkdChildMapper.deleteCgRkdSingleChildById(id);
    }

    /**
     * 根据主表ID批量删除进货单子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdSingleChildByPid(String[] ids){
        return cgRkdChildMapper.deleteCgRkdSingleChildByPid(ids);
    }
}
