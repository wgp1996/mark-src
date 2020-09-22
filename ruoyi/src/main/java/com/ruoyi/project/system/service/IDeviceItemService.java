package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.DeviceItem;

/**
 * 设备建档Service接口
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public interface IDeviceItemService 
{
    /**
     * 查询设备建档
     * 
     * @param id 设备建档ID
     * @return 设备建档
     */
    public DeviceItem selectDeviceItemById(String id);

    /**
     * 查询设备建档列表
     * 
     * @param deviceItem 设备建档
     * @return 设备建档集合
     */
    public List<DeviceItem> selectDeviceItemList(DeviceItem deviceItem);

    /**
     * 新增设备建档
     * 
     * @param deviceItem 设备建档
     * @return 结果
     */
    public int insertDeviceItem(DeviceItem deviceItem);

    /**
     * 修改设备建档
     * 
     * @param deviceItem 设备建档
     * @return 结果
     */
    public int updateDeviceItem(DeviceItem deviceItem);

    /**
     * 批量删除设备建档
     * 
     * @param ids 需要删除的设备建档ID
     * @return 结果
     */
    public int deleteDeviceItemByIds(String[] ids);

    /**
     * 删除设备建档信息
     * 
     * @param id 设备建档ID
     * @return 结果
     */
    public int deleteDeviceItemById(String id);
}
