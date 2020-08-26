package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.WholeSalesMapper;
import com.ruoyi.project.system.domain.WholeSales;
import com.ruoyi.project.system.service.IWholeSalesService;

/**
 * 批发销货一票通Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
@Service
public class WholeSalesServiceImpl implements IWholeSalesService 
{
    @Autowired
    private WholeSalesMapper wholeSalesMapper;

    /**
     * 查询批发销货一票通
     * 
     * @param id 批发销货一票通ID
     * @return 批发销货一票通
     */
    @Override
    public WholeSales selectWholeSalesById(String id)
    {
        return wholeSalesMapper.selectWholeSalesById(id);
    }

    /**
     * 查询批发销货一票通列表
     * 
     * @param wholeSales 批发销货一票通
     * @return 批发销货一票通
     */
    @Override
    public List<WholeSales> selectWholeSalesList(WholeSales wholeSales)
    {
        return wholeSalesMapper.selectWholeSalesList(wholeSales);
    }

    /**
     * 新增批发销货一票通
     * 
     * @param wholeSales 批发销货一票通
     * @return 结果
     */
    @Override
    public int insertWholeSales(WholeSales wholeSales)
    {
        wholeSales.setCreateTime(DateUtils.getNowDate());
        return wholeSalesMapper.insertWholeSales(wholeSales);
    }

    /**
     * 修改批发销货一票通
     * 
     * @param wholeSales 批发销货一票通
     * @return 结果
     */
    @Override
    public int updateWholeSales(WholeSales wholeSales)
    {
        wholeSales.setUpdateTime(DateUtils.getNowDate());
        return wholeSalesMapper.updateWholeSales(wholeSales);
    }

    /**
     * 批量删除批发销货一票通
     * 
     * @param ids 需要删除的批发销货一票通ID
     * @return 结果
     */
    @Override
    public int deleteWholeSalesByIds(String[] ids)
    {
        return wholeSalesMapper.deleteWholeSalesByIds(ids);
    }

    /**
     * 删除批发销货一票通信息
     * 
     * @param id 批发销货一票通ID
     * @return 结果
     */
    @Override
    public int deleteWholeSalesById(String id)
    {
        return wholeSalesMapper.deleteWholeSalesById(id);
    }
    /**
     * 批量生效单据
     *
     * @param ids 需要生效的单据ID
     * @return 结果
     */
    @Override
    public int updateWholeSalesStatus(String[] ids){
        return wholeSalesMapper.updateWholeSalesStatus(ids);
    }
}
