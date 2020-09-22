package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.CgRkd;

/**
 * 进货单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
public interface CgRkdMapper 
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
     * 查询进货单汇总列表
     *
     * @param cgRkd 进货单
     * @return 进货单集合
     */
    public List<CgRkd> rkdSummaryList(CgRkd cgRkd);
    /**
     * 查询业户接收订单列表
     *
     * @param createBy 业户代码
     * @return 进货单集合
     */
    public List<CgRkd> selectCgRkdAllListByStatus(String createBy,Integer type);
    /**
     * 查询进货单列表
     *
     * @param cgRkd 进货单
     * @return 进货单集合
     */
    public List<CgRkd> selectCgRkdAllList(CgRkd cgRkd);
    /**
     * 查询业户接收订单数量
     *
     * @param createBy 业户代码
     * @return 进货单待接收数量
     */
    public Integer selectCgRkdByStatusCount(String createBy);
    /**
     * 查询百大待审核总数
     *
     * @param createBy 业户代码
     * @return 进货单待接收数量
     */
    public Integer selectCgRkdByShStatusCount(String createBy);
    /**
     * 新增进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    public int insertCgRkd(CgRkd cgRkd);
    /**
     * 检查进货单是否全部确认完毕
     *
     * @return 结果
     */
    public int checkRkdAllConfirm(String djNumber);
    /**
     * 查询百大审核订单列表
     *
     * @param createBy 业户代码
     * @return 进货单集合
     */
    public List<CgRkd> selectCgRkdAllListByShStatus(String createBy,Integer type,Integer status);
    /**
     * 修改进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    public int updateCgRkd(CgRkd cgRkd);

    /**
     * 删除进货单
     * 
     * @param id 进货单ID
     * @return 结果
     */
    public int deleteCgRkdById(String id);

    /**
     * 批量删除进货单
     * 
     * @param ids 需要删除的数据ID
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
     * 修改进货单状态
     *
     * @return 结果
     */
    public int updateCgRkdStatusByNumber(CgRkd cgRkd);
    /**
     * 检查进货单是否全部接收完毕
     *
     * @return 结果
     */
    public int checkRkdAllReceive(String djNumber);

}
