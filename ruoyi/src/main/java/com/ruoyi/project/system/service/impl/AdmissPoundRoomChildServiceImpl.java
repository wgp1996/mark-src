package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.AdmissPoundRoomChildMapper;
import com.ruoyi.project.system.domain.AdmissPoundRoomChild;
import com.ruoyi.project.system.service.IAdmissPoundRoomChildService;

/**
 * 磅房入场单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-20
 */
@Service
public class AdmissPoundRoomChildServiceImpl implements IAdmissPoundRoomChildService 
{
    @Autowired
    private AdmissPoundRoomChildMapper admissPoundRoomChildMapper;

    /**
     * 查询磅房入场单明细
     * 
     * @param id 磅房入场单明细ID
     * @return 磅房入场单明细
     */
    @Override
    public AdmissPoundRoomChild selectAdmissPoundRoomChildById(String id)
    {
        return admissPoundRoomChildMapper.selectAdmissPoundRoomChildById(id);
    }

    /**
     * 查询磅房入场单明细列表
     * 
     * @param admissPoundRoomChild 磅房入场单明细
     * @return 磅房入场单明细
     */
    @Override
    public List<AdmissPoundRoomChild> selectAdmissPoundRoomChildList(AdmissPoundRoomChild admissPoundRoomChild)
    {
        return admissPoundRoomChildMapper.selectAdmissPoundRoomChildList(admissPoundRoomChild);
    }

    /**
     * 新增磅房入场单明细
     * 
     * @param admissPoundRoomChild 磅房入场单明细
     * @return 结果
     */
    @Override
    public int insertAdmissPoundRoomChild(AdmissPoundRoomChild admissPoundRoomChild)
    {
        admissPoundRoomChild.setCreateTime(DateUtils.getNowDate());
        return admissPoundRoomChildMapper.insertAdmissPoundRoomChild(admissPoundRoomChild);
    }

    /**
     * 修改磅房入场单明细
     * 
     * @param admissPoundRoomChild 磅房入场单明细
     * @return 结果
     */
    @Override
    public int updateAdmissPoundRoomChild(AdmissPoundRoomChild admissPoundRoomChild)
    {
        admissPoundRoomChild.setUpdateTime(DateUtils.getNowDate());
        return admissPoundRoomChildMapper.updateAdmissPoundRoomChild(admissPoundRoomChild);
    }

    /**
     * 批量删除磅房入场单明细
     * 
     * @param ids 需要删除的磅房入场单明细ID
     * @return 结果
     */
    @Override
    public int deleteAdmissPoundRoomChildByIds(String[] ids)
    {
        return admissPoundRoomChildMapper.deleteAdmissPoundRoomChildByIds(ids);
    }
    /**
     * 批量删除磅房入场单明细
     *
     * @param ids 需要删除的磅房入场单父ID
     * @return 结果
     */
    public int deleteAdmissPoundRoomChildByPid(String[] ids){
        return admissPoundRoomChildMapper.deleteAdmissPoundRoomChildByPid(ids);
    }
    /**
     * 删除磅房入场单明细信息
     * 
     * @param id 磅房入场单明细ID
     * @return 结果
     */
    @Override
    public int deleteAdmissPoundRoomChildById(String id)
    {
        return admissPoundRoomChildMapper.deleteAdmissPoundRoomChildById(id);
    }
}
