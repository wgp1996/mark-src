package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.domain.CgRkdSingle;

import java.util.List;

/**
 * 进货单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
public interface CgRkdSingleMapper
{
    /**
     * 查询进货单
     * 
     * @param id 进货单ID
     * @return 进货单
     */
    public CgRkdSingle selectCgRkdSingleById(String id);
    /**
     * 百大查询进货单列表
     *
     * @param cgRkd 进货单
     * @return 进货单集合
     */
    public List<CgRkdSingle> selectCgRkdBdList(CgRkdSingle cgRkd);

    /**
     * 查询进货单列表
     * 
     * @param cgRkd 进货单
     * @return 进货单集合
     */
    public List<CgRkdSingle> selectCgRkdSingleList(CgRkdSingle cgRkd);
    /**
     * 查询进货单列表
     *
     * @param cgRkd 进货单
     * @return 进货单集合
     */
    public List<CgRkdSingle> selectCgRkdSingleAllList(CgRkdSingle cgRkd);

    /**
     * 新增进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    public int insertCgRkdSingle(CgRkdSingle cgRkd);

    /**
     * 修改进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    public int updateCgRkdSingle(CgRkdSingle cgRkd);

    /**
     * 删除进货单
     * 
     * @param id 进货单ID
     * @return 结果
     */
    public int deleteCgRkdSingleById(String id);

    /**
     * 批量删除进货单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCgRkdSingleByIds(String[] ids);
    /**
     * 批量生效进货单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    public int updateCgRkdSingleStatus(String[] ids);
    /**
     * 根据编号修改生效进货单状态
     *
     * @return 结果
     */
    public int updateCgRkdSingleStatusByNumber(CgRkdSingle cgRkd);


}
