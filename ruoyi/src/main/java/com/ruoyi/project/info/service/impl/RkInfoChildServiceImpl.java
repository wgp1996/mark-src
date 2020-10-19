package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.RkInfoChildMapper;
import com.ruoyi.project.info.domain.RkInfoChild;
import com.ruoyi.project.info.service.IRkInfoChildService;

/**
 * 入库单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
@Service
public class RkInfoChildServiceImpl implements IRkInfoChildService 
{
    @Autowired
    private RkInfoChildMapper rkInfoChildMapper;

    /**
     * 查询入库单子表
     * 
     * @param id 入库单子表ID
     * @return 入库单子表
     */
    @Override
    public RkInfoChild selectRkInfoChildById(String id)
    {
        return rkInfoChildMapper.selectRkInfoChildById(id);
    }

    /**
     * 查询入库单子表列表
     * 
     * @param rkInfoChild 入库单子表
     * @return 入库单子表
     */
    @Override
    public List<RkInfoChild> selectRkInfoChildList(RkInfoChild rkInfoChild)
    {
        return rkInfoChildMapper.selectRkInfoChildList(rkInfoChild);
    }

    /**
     * 出库单选择入库单
     *
     * @param rkInfoChild 入库单子表
     * @return 入库单子表集合
     */
    @Override
    public List<RkInfoChild> selectRkInfoByCkd(RkInfoChild rkInfoChild){
        return rkInfoChildMapper.selectRkInfoByCkd(rkInfoChild);
    }

    /**
     * 新增入库单子表
     * 
     * @param rkInfoChild 入库单子表
     * @return 结果
     */
    @Override
    public int insertRkInfoChild(RkInfoChild rkInfoChild)
    {
        rkInfoChild.setCreateTime(DateUtils.getNowDate());
        return rkInfoChildMapper.insertRkInfoChild(rkInfoChild);
    }

    /**
     * 修改入库单子表
     * 
     * @param rkInfoChild 入库单子表
     * @return 结果
     */
    @Override
    public int updateRkInfoChild(RkInfoChild rkInfoChild)
    {
        rkInfoChild.setUpdateTime(DateUtils.getNowDate());
        return rkInfoChildMapper.updateRkInfoChild(rkInfoChild);
    }

    /**
     * 批量删除入库单子表
     * 
     * @param ids 需要删除的入库单子表ID
     * @return 结果
     */
    @Override
    public int deleteRkInfoChildByIds(String[] ids)
    {
        return rkInfoChildMapper.deleteRkInfoChildByIds(ids);
    }
    /**
     * 根据主表id批量删除入库单子表
     *
     * @param ids 需要删除的入库单主表ID
     * @return 结果
     */
    @Override
    public int deleteRkInfoChildByPid(String[] ids){
        return rkInfoChildMapper.deleteRkInfoChildByPid(ids);
    }
    /**
     * 删除入库单子表信息
     * 
     * @param id 入库单子表ID
     * @return 结果
     */
    @Override
    public int deleteRkInfoChildById(String id)
    {
        return rkInfoChildMapper.deleteRkInfoChildById(id);
    }
}
