package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.CgRkdSingle;
import com.ruoyi.project.system.mapper.CgRkdSingleMapper;
import com.ruoyi.project.system.service.ICgRkdSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进货单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@Service
public class CgRkdSingleServiceImpl implements ICgRkdSingleService
{
    @Autowired
    private CgRkdSingleMapper cgRkdMapper;

    /**
     * 查询进货单
     * 
     * @param id 进货单ID
     * @return 进货单
     */
    @Override
    public CgRkdSingle selectCgRkdSingleById(String id)
    {
        return cgRkdMapper.selectCgRkdSingleById(id);
    }

    /**
     * 查询进货单列表
     * 
     * @param cgRkd 进货单
     * @return 进货单
     */
    @Override
    public List<CgRkdSingle> selectCgRkdSingleList(CgRkdSingle cgRkd)
    {
        return cgRkdMapper.selectCgRkdSingleList(cgRkd);
    }

    /**
     * 查询进货单列表
     *
     * @param cgRkd 进货单
     * @return 进货单
     */
    @Override
    public List<CgRkdSingle> selectCgRkdSingleAllList(CgRkdSingle cgRkd)
    {
        return cgRkdMapper.selectCgRkdSingleAllList(cgRkd);
    }

    /**
     * 新增进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    @Override
    public int insertCgRkdSingle(CgRkdSingle cgRkd)
    {
        cgRkd.setCreateTime(DateUtils.getNowDate());
        return cgRkdMapper.insertCgRkdSingle(cgRkd);
    }

    /**
     * 修改进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    @Override
    public int updateCgRkdSingle(CgRkdSingle cgRkd)
    {
        cgRkd.setUpdateTime(DateUtils.getNowDate());
        return cgRkdMapper.updateCgRkdSingle(cgRkd);
    }

    /**
     * 批量删除进货单
     * 
     * @param ids 需要删除的进货单ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdSingleByIds(String[] ids)
    {
        return cgRkdMapper.deleteCgRkdSingleByIds(ids);
    }

    /**
     * 删除进货单信息
     * 
     * @param id 进货单ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdSingleById(String id)
    {
        return cgRkdMapper.deleteCgRkdSingleById(id);
    }
    /**
     * 批量生效进货单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    @Override
    public int updateCgRkdSingleStatus(String[] ids){
        return cgRkdMapper.updateCgRkdSingleStatus(ids);
    }
    /**
     * 根据编号修改生效进货单状态
     *
     * @return 结果
     */
    @Override
    public int updateCgRkdSingleStatusByNumber(CgRkdSingle cgRkd){
        return cgRkdMapper.updateCgRkdSingleStatusByNumber(cgRkd);
    }
}
