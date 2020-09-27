package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CgRkdMapper;
import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.service.ICgRkdService;

/**
 * 进货单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@Service
public class CgRkdServiceImpl implements ICgRkdService 
{
    @Autowired
    private CgRkdMapper cgRkdMapper;

    /**
     * 查询进货单
     * 
     * @param id 进货单ID
     * @return 进货单
     */
    @Override
    public CgRkd selectCgRkdById(String id)
    {
        return cgRkdMapper.selectCgRkdById(id);
    }

    /**
     * 查询业户接收订单列表
     *
     * @param createBy 业户代码
     * @return 进货单集合
     */
    @Override
    public List<CgRkd> selectCgRkdAllListByStatus(String createBy,Integer type){
        return cgRkdMapper.selectCgRkdAllListByStatus(createBy,type);
    }
    /**
     * 查询百大审核订单列表
     *
     * @param createBy 业户代码
     * @return 进货单集合
     */
    @Override
    public List<CgRkd> selectCgRkdAllListByShStatus(String createBy,Integer type,Integer status){
        return cgRkdMapper.selectCgRkdAllListByShStatus(createBy,type,status);
    }
    /**
     * 检查进货单是否全部接收完毕
     *
     * @return 结果
     */
    @Override
    public int checkRkdAllReceive(String djNumber){
        return cgRkdMapper.checkRkdAllReceive(djNumber);
    }
    @Override
    /**
     * 检查进货单是否全部确认完毕
     *
     * @return 结果
     */
    public int checkRkdAllConfirm(String djNumber){
        return cgRkdMapper.checkRkdAllConfirm(djNumber);
    }

    /**
     * 修改进货单状态
     *
     * @return 结果
     */
    @Override
    public int updateCgRkdStatusByNumber(CgRkd cgRkd){
        return cgRkdMapper.updateCgRkdStatusByNumber(cgRkd);
    }
    /**
     * 查询业户接收订单数量
     *
     * @param createBy 业户代码
     * @return 进货单待接收数量
     */
    @Override
    public Integer selectCgRkdByStatusCount(String createBy){
        return cgRkdMapper.selectCgRkdByStatusCount(createBy);
    }
    /**
     * 查询百大待审核总数
     *
     * @param createBy 业户代码
     * @return 进货单待接收数量
     */
    @Override
    public Integer selectCgRkdByShStatusCount(String createBy){
        return cgRkdMapper.selectCgRkdByShStatusCount(createBy);
    }
    /**
     * 市平台查询所有进货单列表
     *
     * @param cgRkd 进货单
     * @return 进货单集合
     */
    @Override
    public List<CgRkd> selectCgRkdAllListBySpt(CgRkd cgRkd){
        return cgRkdMapper.selectCgRkdAllListBySpt(cgRkd);
    }
    /**
     * 市平台查询周公河数量信息
     *
     */
    @Override
    public CgRkd selectMatkIndexNum(){
        return cgRkdMapper.selectMatkIndexNum();
    }
    /**
     * 查询进货单列表
     * 
     * @param cgRkd 进货单
     * @return 进货单
     */
    @Override
    public List<CgRkd> selectCgRkdList(CgRkd cgRkd)
    {
        return cgRkdMapper.selectCgRkdList(cgRkd);
    }
    /**
     * 查询进货单汇总列表
     *
     * @param cgRkd 进货单
     * @return 进货单集合
     */
    @Override
    public List<CgRkd> rkdSummaryList(CgRkd cgRkd){
        return cgRkdMapper.rkdSummaryList(cgRkd);
    }
    /**
     * 查询进货单列表
     *
     * @param cgRkd 进货单
     * @return 进货单
     */
    @Override
    public List<CgRkd> selectCgRkdAllList(CgRkd cgRkd)
    {
        return cgRkdMapper.selectCgRkdAllList(cgRkd);
    }

    /**
     * 新增进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    @Override
    public int insertCgRkd(CgRkd cgRkd)
    {
        cgRkd.setCreateTime(DateUtils.getNowDate());
        return cgRkdMapper.insertCgRkd(cgRkd);
    }

    /**
     * 修改进货单
     * 
     * @param cgRkd 进货单
     * @return 结果
     */
    @Override
    public int updateCgRkd(CgRkd cgRkd)
    {
        cgRkd.setUpdateTime(DateUtils.getNowDate());
        return cgRkdMapper.updateCgRkd(cgRkd);
    }

    /**
     * 批量删除进货单
     * 
     * @param ids 需要删除的进货单ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdByIds(String[] ids)
    {
        return cgRkdMapper.deleteCgRkdByIds(ids);
    }

    /**
     * 删除进货单信息
     * 
     * @param id 进货单ID
     * @return 结果
     */
    @Override
    public int deleteCgRkdById(String id)
    {
        return cgRkdMapper.deleteCgRkdById(id);
    }
    /**
     * 批量生效进货单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    @Override
    public int updateCgRkdStatus(String[] ids){
        return cgRkdMapper.updateCgRkdStatus(ids);
    }

}
