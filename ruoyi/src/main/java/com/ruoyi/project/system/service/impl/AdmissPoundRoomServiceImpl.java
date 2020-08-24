package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.AdmissPoundRoomMapper;
import com.ruoyi.project.system.domain.AdmissPoundRoom;
import com.ruoyi.project.system.service.IAdmissPoundRoomService;

/**
 * 磅房入场单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-20
 */
@Service
public class AdmissPoundRoomServiceImpl implements IAdmissPoundRoomService 
{
    @Autowired
    private AdmissPoundRoomMapper admissPoundRoomMapper;

    /**
     * 查询磅房入场单
     * 
     * @param id 磅房入场单ID
     * @return 磅房入场单
     */
    @Override
    public AdmissPoundRoom selectAdmissPoundRoomById(String id)
    {
        return admissPoundRoomMapper.selectAdmissPoundRoomById(id);
    }

    /**
     * 查询磅房入场单列表
     * 
     * @param admissPoundRoom 磅房入场单
     * @return 磅房入场单
     */
    @Override
    public List<AdmissPoundRoom> selectAdmissPoundRoomList(AdmissPoundRoom admissPoundRoom)
    {
        return admissPoundRoomMapper.selectAdmissPoundRoomList(admissPoundRoom);
    }

    /**
     * 新增磅房入场单
     * 
     * @param admissPoundRoom 磅房入场单
     * @return 结果
     */
    @Override
    public int insertAdmissPoundRoom(AdmissPoundRoom admissPoundRoom)
    {
        admissPoundRoom.setCreateTime(DateUtils.getNowDate());
        return admissPoundRoomMapper.insertAdmissPoundRoom(admissPoundRoom);
    }

    /**
     * 修改磅房入场单
     * 
     * @param admissPoundRoom 磅房入场单
     * @return 结果
     */
    @Override
    public int updateAdmissPoundRoom(AdmissPoundRoom admissPoundRoom)
    {
        admissPoundRoom.setUpdateTime(DateUtils.getNowDate());
        return admissPoundRoomMapper.updateAdmissPoundRoom(admissPoundRoom);
    }

    /**
     * 批量删除磅房入场单
     * 
     * @param ids 需要删除的磅房入场单ID
     * @return 结果
     */
    @Override
    public int deleteAdmissPoundRoomByIds(String[] ids)
    {
        return admissPoundRoomMapper.deleteAdmissPoundRoomByIds(ids);
    }

    /**
     * 删除磅房入场单信息
     * 
     * @param id 磅房入场单ID
     * @return 结果
     */
    @Override
    public int deleteAdmissPoundRoomById(String id)
    {
        return admissPoundRoomMapper.deleteAdmissPoundRoomById(id);
    }
    /**
     * 批量生效磅房入场单
     *
     * @param ids 需要生效的入场单ID
     * @return 结果
     */
    @Override
    public int updateAdmissPoundRoomServiceStatus(String[] ids){
        return admissPoundRoomMapper.updateAdmissPoundRoomServiceStatus(ids);
    }
}
