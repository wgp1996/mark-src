package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.DeviceItemMapper;
import com.ruoyi.project.system.domain.DeviceItem;
import com.ruoyi.project.system.service.IDeviceItemService;

/**
 * 设备建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Service
public class DeviceItemServiceImpl implements IDeviceItemService 
{
    @Autowired
    private DeviceItemMapper deviceItemMapper;

    /**
     * 查询设备建档
     * 
     * @param id 设备建档ID
     * @return 设备建档
     */
    @Override
    public DeviceItem selectDeviceItemById(String id)
    {
        return deviceItemMapper.selectDeviceItemById(id);
    }

    /**
     * 查询设备建档列表
     * 
     * @param deviceItem 设备建档
     * @return 设备建档
     */
    @Override
    public List<DeviceItem> selectDeviceItemList(DeviceItem deviceItem)
    {
        return deviceItemMapper.selectDeviceItemList(deviceItem);
    }

    /**
     * 新增设备建档
     * 
     * @param deviceItem 设备建档
     * @return 结果
     */
    @Override
    public int insertDeviceItem(DeviceItem deviceItem)
    {
        deviceItem.setCreateTime(DateUtils.getNowDate());
        return deviceItemMapper.insertDeviceItem(deviceItem);
    }

    /**
     * 修改设备建档
     * 
     * @param deviceItem 设备建档
     * @return 结果
     */
    @Override
    public int updateDeviceItem(DeviceItem deviceItem)
    {
        deviceItem.setUpdateTime(DateUtils.getNowDate());
        return deviceItemMapper.updateDeviceItem(deviceItem);
    }

    /**
     * 批量删除设备建档
     * 
     * @param ids 需要删除的设备建档ID
     * @return 结果
     */
    @Override
    public int deleteDeviceItemByIds(String[] ids)
    {
        return deviceItemMapper.deleteDeviceItemByIds(ids);
    }

    /**
     * 删除设备建档信息
     * 
     * @param id 设备建档ID
     * @return 结果
     */
    @Override
    public int deleteDeviceItemById(String id)
    {
        return deviceItemMapper.deleteDeviceItemById(id);
    }
}
