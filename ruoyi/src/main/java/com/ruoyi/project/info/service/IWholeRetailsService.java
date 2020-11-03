package com.ruoyi.project.info.service;

import com.ruoyi.project.info.domain.WholeRetails;

import java.util.List;

/**
 * 批发销货一票通Service接口
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public interface IWholeRetailsService
{
    /**
     * 查询批发销货一票通
     * 
     * @param id 批发销货一票通ID
     * @return 批发销货一票通
     */
    public WholeRetails selectWholeRetailById(String id);

    /**
     * 查询首页数量信息
     *
     */
    public WholeRetails selectNumlList();

    /**
     * 查询批发销货一票通列表
     * 
     * @param WholeRetail 批发销货一票通
     * @return 批发销货一票通集合
     */
    public List<WholeRetails> selectWholeRetailList(WholeRetails WholeRetail);

    /**
     * 新增批发销货一票通
     * 
     * @param WholeRetail 批发销货一票通
     * @return 结果
     */
    public int insertWholeRetail(WholeRetails WholeRetail);

    /**
     * 修改批发销货一票通
     * 
     * @param WholeRetail 批发销货一票通
     * @return 结果
     */
    public int updateWholeRetail(WholeRetails WholeRetail);

    /**
     * 批量删除批发销货一票通
     * 
     * @param ids 需要删除的批发销货一票通ID
     * @return 结果
     */
    public int deleteWholeRetailByIds(String[] ids);

    /**
     * 删除批发销货一票通信息
     * 
     * @param id 批发销货一票通ID
     * @return 结果
     */
    public int deleteWholeRetailById(String id);
    /**
     * 批量生效单据
     *
     * @param ids 需要生效的单据ID
     * @return 结果
     */
    public int updateWholeRetailStatus(String[] ids);
}
