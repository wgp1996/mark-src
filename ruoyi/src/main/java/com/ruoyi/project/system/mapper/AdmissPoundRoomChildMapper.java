package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.AdmissPoundRoomChild;

/**
 * 磅房入场单明细Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-20
 */
public interface AdmissPoundRoomChildMapper 
{
    /**
     * 查询磅房入场单明细
     * 
     * @param id 磅房入场单明细ID
     * @return 磅房入场单明细
     */
    public AdmissPoundRoomChild selectAdmissPoundRoomChildById(String id);

    /**
     * 查询磅房入场单明细列表
     * 
     * @param admissPoundRoomChild 磅房入场单明细
     * @return 磅房入场单明细集合
     */
    public List<AdmissPoundRoomChild> selectAdmissPoundRoomChildList(AdmissPoundRoomChild admissPoundRoomChild);

    /**
     * 新增磅房入场单明细
     * 
     * @param admissPoundRoomChild 磅房入场单明细
     * @return 结果
     */
    public int insertAdmissPoundRoomChild(AdmissPoundRoomChild admissPoundRoomChild);

    /**
     * 修改磅房入场单明细
     * 
     * @param admissPoundRoomChild 磅房入场单明细
     * @return 结果
     */
    public int updateAdmissPoundRoomChild(AdmissPoundRoomChild admissPoundRoomChild);

    /**
     * 删除磅房入场单明细
     * 
     * @param id 磅房入场单明细ID
     * @return 结果
     */
    public int deleteAdmissPoundRoomChildById(String id);

    /**
     * 批量删除磅房入场单明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAdmissPoundRoomChildByIds(String[] ids);
    /**
     * 批量删除磅房入场单明细
     *
     * @param ids 需要删除的磅房入场单父ID
     * @return 结果
     */
    public int deleteAdmissPoundRoomChildByPid(String[] ids);
}
