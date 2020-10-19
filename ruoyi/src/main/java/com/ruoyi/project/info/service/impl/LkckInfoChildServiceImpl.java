package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.LkckInfoChildMapper;
import com.ruoyi.project.info.domain.LkckInfoChild;
import com.ruoyi.project.info.service.ILkckInfoChildService;

/**
 * 出库单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
@Service
public class LkckInfoChildServiceImpl implements ILkckInfoChildService 
{
    @Autowired
    private LkckInfoChildMapper lkckInfoChildMapper;

    /**
     * 查询出库单明细
     * 
     * @param id 出库单明细ID
     * @return 出库单明细
     */
    @Override
    public LkckInfoChild selectLkckInfoChildById(String id)
    {
        return lkckInfoChildMapper.selectLkckInfoChildById(id);
    }

    /**
     * 查询出库单明细列表
     * 
     * @param lkckInfoChild 出库单明细
     * @return 出库单明细
     */
    @Override
    public List<LkckInfoChild> selectLkckInfoChildList(LkckInfoChild lkckInfoChild)
    {
        return lkckInfoChildMapper.selectLkckInfoChildList(lkckInfoChild);
    }

    /**
     * 新增出库单明细
     * 
     * @param lkckInfoChild 出库单明细
     * @return 结果
     */
    @Override
    public int insertLkckInfoChild(LkckInfoChild lkckInfoChild)
    {
        lkckInfoChild.setCreateTime(DateUtils.getNowDate());
        return lkckInfoChildMapper.insertLkckInfoChild(lkckInfoChild);
    }

    /**
     * 修改出库单明细
     * 
     * @param lkckInfoChild 出库单明细
     * @return 结果
     */
    @Override
    public int updateLkckInfoChild(LkckInfoChild lkckInfoChild)
    {
        lkckInfoChild.setUpdateTime(DateUtils.getNowDate());
        return lkckInfoChildMapper.updateLkckInfoChild(lkckInfoChild);
    }

    /**
     * 批量删除出库单明细
     * 
     * @param ids 需要删除的出库单明细ID
     * @return 结果
     */
    @Override
    public int deleteLkckInfoChildByIds(String[] ids)
    {
        return lkckInfoChildMapper.deleteLkckInfoChildByIds(ids);
    }

    /**
     * 删除出库单明细信息
     * 
     * @param id 出库单明细ID
     * @return 结果
     */
    @Override
    public int deleteLkckInfoChildById(String id)
    {
        return lkckInfoChildMapper.deleteLkckInfoChildById(id);
    }
    /**
     * 根据主表id批量删除出库单明细
     *
     * @param ids 需要删除的出库单主表ID
     * @return 结果
     */
    @Override
    public int deleteLkckInfoChildByPid(String[] ids){
        return lkckInfoChildMapper.deleteLkckInfoChildByPid(ids);
    }
}
