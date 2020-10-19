package com.ruoyi.project.info.mapper;

import java.util.List;
import com.ruoyi.project.info.domain.CheckInfoChild;

/**
 * 盘点单子表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
public interface CheckInfoChildMapper 
{
    /**
     * 查询盘点单子表
     * 
     * @param id 盘点单子表ID
     * @return 盘点单子表
     */
    public CheckInfoChild selectCheckInfoChildById(String id);

    /**
     * 查询盘点单子表列表
     * 
     * @param checkInfoChild 盘点单子表
     * @return 盘点单子表集合
     */
    public List<CheckInfoChild> selectCheckInfoChildList(CheckInfoChild checkInfoChild);

    /**
     * 新增盘点单子表
     * 
     * @param checkInfoChild 盘点单子表
     * @return 结果
     */
    public int insertCheckInfoChild(CheckInfoChild checkInfoChild);

    /**
     * 修改盘点单子表
     * 
     * @param checkInfoChild 盘点单子表
     * @return 结果
     */
    public int updateCheckInfoChild(CheckInfoChild checkInfoChild);

    /**
     * 删除盘点单子表
     * 
     * @param id 盘点单子表ID
     * @return 结果
     */
    public int deleteCheckInfoChildById(String id);
    /**
     * 根据主表 id批量删除盘点单子表
     *
     * @param ids 需要删除的盘点单主表ID
     * @return 结果
     */
    public int deleteCheckInfoChildByPid(String[] ids);
    /**
     * 批量删除盘点单子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckInfoChildByIds(String[] ids);
}
