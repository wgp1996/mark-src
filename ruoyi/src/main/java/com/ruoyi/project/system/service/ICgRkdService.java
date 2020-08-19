package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.CgRkd;

/**
 * 进货单Service接口
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
public interface ICgRkdService 
{
    /**
     * 查询进货单
     * 
     * @param id 进货单ID
     * @return 进货单
     */
    public CgRkd selectCgRkdById(String id);

    /**
     * 查询进货单列表
     * 
     * @param cgRkd 进货单
     * @return 进货单集合
     */
    public List<CgRkd> selectCgRkdList(CgRkd cgRkd);

    /**
     * 新增进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    public int insertCgRkd(CgRkd cgRkd);

    /**
     * 修改进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    public int updateCgRkd(CgRkd cgRkd);

    /**
     * 批量删除进货单
     * 
     * @param ids 需要删除的进货单ID
     * @return 结果
     */
    public int deleteCgRkdByIds(String[] ids);
    /**
     * 批量生效进货单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    public int updateCgRkdStatus(String[] ids);

    /**
     * 删除进货单信息
     * 
     * @param id 进货单ID
     * @return 结果
     */
    public int deleteCgRkdById(String id);
}
