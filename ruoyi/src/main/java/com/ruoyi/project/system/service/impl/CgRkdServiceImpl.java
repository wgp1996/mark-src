package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CgRkdMapper;
import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.service.ICgRkdService;

/**
 * 进货单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@Service
public class CgRkdServiceImpl implements ICgRkdService 
{
    @Autowired
    private CgRkdMapper cgRkdMapper;

    /**
     * 查询进货单
     * 
     * @param id 进货单ID
     * @return 进货单
     */
    @Override
    public CgRkd selectCgRkdById(String id)
    {
        return cgRkdMapper.selectCgRkdById(id);
    }

    /**
     * 查询进货单列表
     * 
     * @param cgRkd 进货单
     * @return 进货单
     */
    @Override
    public List<CgRkd> selectCgRkdList(CgRkd cgRkd)
    {
        return cgRkdMapper.selectCgRkdList(cgRkd);
    }

    /**
     * 新增进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    @Override
    public int insertCgRkd(CgRkd cgRkd)
    {
        cgRkd.setCreateTime(DateUtils.getNowDate());
        return cgRkdMapper.insertCgRkd(cgRkd);
    }

    /**
     * 修改进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    @Override
    public int updateCgRkd(CgRkd cgRkd)
    {
        cgRkd.setUpdateTime(DateUtils.getNowDate());
        return cgRkdMapper.updateCgRkd(cgRkd);
    }

    /**
     * 批量删除进货单
     * 
     * @param ids 需要删除的进货单ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdByIds(String[] ids)
    {
        return cgRkdMapper.deleteCgRkdByIds(ids);
    }

    /**
     * 删除进货单信息
     * 
     * @param id 进货单ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdById(String id)
    {
        return cgRkdMapper.deleteCgRkdById(id);
    }
    /**
     * 批量生效进货单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    @Override
    public int updateCgRkdStatus(String[] ids){
        return cgRkdMapper.updateCgRkdStatus(ids);
    }

}
