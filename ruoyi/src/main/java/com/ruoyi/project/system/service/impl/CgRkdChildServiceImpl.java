package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.CgRkdSingleChild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CgRkdChildMapper;
import com.ruoyi.project.system.domain.CgRkdChild;
import com.ruoyi.project.system.service.ICgRkdChildService;

/**
 * 进货单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@Service
public class CgRkdChildServiceImpl implements ICgRkdChildService 
{
    @Autowired
    private CgRkdChildMapper cgRkdChildMapper;

    /**
     * 查询进货单子表
     * 
     * @param id 进货单子表ID
     * @return 进货单子表
     */
    @Override
    public CgRkdChild selectCgRkdChildById(String id)
    {
        return cgRkdChildMapper.selectCgRkdChildById(id);
    }
    /**
     * 查询进货单子表
     *
     * @param dj_number 进货单子表编码
     * @return 进货单子表
     */
    @Override
    public List<CgRkdChild> selectCgRkdChildByNum(String dj_number){
        return cgRkdChildMapper.selectCgRkdChildByNum(dj_number);
    }
    /**
     * 查询进货单子表
     *
     * @param dj_number 进货单子表编码
     * @return 进货单子表
     */
    @Override
    public List<CgRkdChild> selectCgRkdChildByNumber(String dj_number){
        return cgRkdChildMapper.selectCgRkdChildByNumber(dj_number);
    }
    /**
     * 查询进货单子表
     *
     * @param dj_number 进货单子表编码
     * @return 进货单子表
     */
    @Override
    public List<CgRkdChild> selectCgRkdSingleChildByNumber(String dj_number){
        return cgRkdChildMapper.selectCgRkdSingleChildByNumber(dj_number);
    }

    /**
     * 查询进货单子表
     *
     * @param createBy 用户编码
     * @return 进货单子表
     */
    @Override
    public List<CgRkdChild> selectCgRkdChildByUser(String createBy){
        return cgRkdChildMapper.selectCgRkdChildByUser(createBy);
    }
    /**
     * 查询进货单子表
     *
     * @param stallCode 主表摊位编码
     * @return 进货单子表
     */
    public List<CgRkdChild> appRkdChildListByStall(String stallCode){
        return cgRkdChildMapper.appRkdChildListByStall(stallCode);
    }

    /**
     * 查询进货单子表列表
     * 
     * @param cgRkdChild 进货单子表
     * @return 进货单子表
     */
    @Override
    public List<CgRkdChild> selectCgRkdChildList(CgRkdChild cgRkdChild)
    {
        return cgRkdChildMapper.selectCgRkdChildList(cgRkdChild);
    }

    /**
     * 新增进货单子表
     * 
     * @param cgRkdChild 进货单子表
     * @return 结果
     */
    @Override
    public int insertCgRkdChild(CgRkdChild cgRkdChild)
    {
        cgRkdChild.setCreateTime(DateUtils.getNowDate());
        return cgRkdChildMapper.insertCgRkdChild(cgRkdChild);
    }

    /**
     * 修改进货单子表
     * 
     * @param cgRkdChild 进货单子表
     * @return 结果
     */
    @Override
    public int updateCgRkdChild(CgRkdChild cgRkdChild)
    {
        cgRkdChild.setUpdateTime(DateUtils.getNowDate());
        return cgRkdChildMapper.updateCgRkdChild(cgRkdChild);
    }

    /**
     * 批量删除进货单子表
     * 
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdChildByIds(String[] ids)
    {
        return cgRkdChildMapper.deleteCgRkdChildByIds(ids);
    }

    /**
     * 删除进货单子表信息
     * 
     * @param id 进货单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdChildById(String id)
    {
        return cgRkdChildMapper.deleteCgRkdChildById(id);
    }

    /**
     * 根据主表ID批量删除进货单子表
     *
     * @param ids 需要删除的进货单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdChildByPid(String[] ids){
        return cgRkdChildMapper.deleteCgRkdChildByPid(ids);
    }
}
