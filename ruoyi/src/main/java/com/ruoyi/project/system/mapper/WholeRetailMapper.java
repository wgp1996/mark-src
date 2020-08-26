package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.WholeRetail;

import java.util.List;

/**
 * 批发销货一票通Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public interface WholeRetailMapper 
{
    /**
     * 查询批发销货一票通
     * 
     * @param id 批发销货一票通ID
     * @return 批发销货一票通
     */
    public WholeRetail selectWholeRetailById(String id);

    /**
     * 查询批发销货一票通列表
     * 
     * @param WholeRetail 批发销货一票通
     * @return 批发销货一票通集合
     */
    public List<WholeRetail> selectWholeRetailList(WholeRetail WholeRetail);

    /**
     * 新增批发销货一票通
     * 
     * @param WholeRetail 批发销货一票通
     * @return 结果
     */
    public int insertWholeRetail(WholeRetail WholeRetail);

    /**
     * 修改批发销货一票通
     * 
     * @param WholeRetail 批发销货一票通
     * @return 结果
     */
    public int updateWholeRetail(WholeRetail WholeRetail);

    /**
     * 删除批发销货一票通
     * 
     * @param id 批发销货一票通ID
     * @return 结果
     */
    public int deleteWholeRetailById(String id);

    /**
     * 批量删除批发销货一票通
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWholeRetailByIds(String[] ids);
    /**
     * 批量生效单据
     *
     * @param ids 需要生效的单据ID
     * @return 结果
     */
    public int updateWholeRetailStatus(String[] ids);
}
