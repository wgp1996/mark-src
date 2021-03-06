package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.WholeSales;

/**
 * 批发销货一票通Service接口
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public interface IWholeSalesService 
{
    /**
     * 查询批发销货一票通
     * 
     * @param id 批发销货一票通ID
     * @return 批发销货一票通
     */
    public WholeSales selectWholeSalesById(String id);

    /**
     * 查询批发销货一票通列表
     * 
     * @param wholeSales 批发销货一票通
     * @return 批发销货一票通集合
     */
    public List<WholeSales> selectWholeSalesList(WholeSales wholeSales);

    /**
     * 新增批发销货一票通
     * 
     * @param wholeSales 批发销货一票通
     * @return 结果
     */
    public int insertWholeSales(WholeSales wholeSales);

    /**
     * 修改批发销货一票通
     * 
     * @param wholeSales 批发销货一票通
     * @return 结果
     */
    public int updateWholeSales(WholeSales wholeSales);

    /**
     * 批量删除批发销货一票通
     * 
     * @param ids 需要删除的批发销货一票通ID
     * @return 结果
     */
    public int deleteWholeSalesByIds(String[] ids);

    /**
     * 删除批发销货一票通信息
     * 
     * @param id 批发销货一票通ID
     * @return 结果
     */
    public int deleteWholeSalesById(String id);

    /**
     * 批量生效单据
     *
     * @param ids 需要生效的单据ID
     * @return 结果
     */
    public int updateWholeSalesStatus(String[] ids);
}
