package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.AdmissPoundRoom;

/**
 * 磅房入场单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-20
 */
public interface AdmissPoundRoomMapper 
{
    /**
     * 查询磅房入场单
     * 
     * @param id 磅房入场单ID
     * @return 磅房入场单
     */
    public AdmissPoundRoom selectAdmissPoundRoomById(String id);

    /**
     * 查询磅房入场单列表
     * 
     * @param admissPoundRoom 磅房入场单
     * @return 磅房入场单集合
     */
    public List<AdmissPoundRoom> selectAdmissPoundRoomList(AdmissPoundRoom admissPoundRoom);

    /**
     * 新增磅房入场单
     * 
     * @param admissPoundRoom 磅房入场单
     * @return 结果
     */
    public int insertAdmissPoundRoom(AdmissPoundRoom admissPoundRoom);

    /**
     * 修改磅房入场单
     * 
     * @param admissPoundRoom 磅房入场单
     * @return 结果
     */
    public int updateAdmissPoundRoom(AdmissPoundRoom admissPoundRoom);

    /**
     * 删除磅房入场单
     * 
     * @param id 磅房入场单ID
     * @return 结果
     */
    public int deleteAdmissPoundRoomById(String id);

    /**
     * 批量删除磅房入场单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAdmissPoundRoomByIds(String[] ids);
    /**
     * 批量生效磅房入场单
     *
     * @param ids 需要生效的入场单ID
     * @return 结果
     */
    public int updateAdmissPoundRoomServiceStatus(String[] ids);
}
