package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 设备建档对象 device_item
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public class DeviceItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String deviceId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 通道数量 */
    @Excel(name = "通道数量")
    private Long deviceTdNum;

    /** 测量经度 */
    @Excel(name = "测量经度")
    private Long deviceAccuracy;

    /** 品牌 */
    @Excel(name = "品牌")
    private String deviceBrand;

    /** 型号 */
    @Excel(name = "型号")
    private String deviceModel;

    /** 购置日期 */
    @Excel(name = "购置日期")
    private String deviceBuyTime;

    /** 供货单位 */
    @Excel(name = "供货单位")
    private String deviceSendUnit;

    /** 供货联系人 */
    @Excel(name = "供货联系人")
    private String deviceSendLxr;

    /** 供货联系电话 */
    @Excel(name = "供货联系电话")
    private String deviceSendLxrPhone;

    /** 售后联系人 */
    @Excel(name = "售后联系人")
    private String deviceSalesLxr;

    /** 售后联系电话 */
    @Excel(name = "售后联系电话")
    private String deviceSalesLxrPhone;

    /** 备注 */
    @Excel(name = "备注")
    private String deviceBz;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDeviceId(String deviceId) 
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId() 
    {
        return deviceId;
    }
    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }
    public void setDeviceTdNum(Long deviceTdNum) 
    {
        this.deviceTdNum = deviceTdNum;
    }

    public Long getDeviceTdNum() 
    {
        return deviceTdNum;
    }
    public void setDeviceAccuracy(Long deviceAccuracy) 
    {
        this.deviceAccuracy = deviceAccuracy;
    }

    public Long getDeviceAccuracy() 
    {
        return deviceAccuracy;
    }
    public void setDeviceBrand(String deviceBrand) 
    {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceBrand() 
    {
        return deviceBrand;
    }
    public void setDeviceModel(String deviceModel) 
    {
        this.deviceModel = deviceModel;
    }

    public String getDeviceModel() 
    {
        return deviceModel;
    }
    public void setDeviceBuyTime(String deviceBuyTime) 
    {
        this.deviceBuyTime = deviceBuyTime;
    }

    public String getDeviceBuyTime() 
    {
        return deviceBuyTime;
    }
    public void setDeviceSendUnit(String deviceSendUnit) 
    {
        this.deviceSendUnit = deviceSendUnit;
    }

    public String getDeviceSendUnit() 
    {
        return deviceSendUnit;
    }
    public void setDeviceSendLxr(String deviceSendLxr) 
    {
        this.deviceSendLxr = deviceSendLxr;
    }

    public String getDeviceSendLxr() 
    {
        return deviceSendLxr;
    }
    public void setDeviceSendLxrPhone(String deviceSendLxrPhone) 
    {
        this.deviceSendLxrPhone = deviceSendLxrPhone;
    }

    public String getDeviceSendLxrPhone() 
    {
        return deviceSendLxrPhone;
    }
    public void setDeviceSalesLxr(String deviceSalesLxr) 
    {
        this.deviceSalesLxr = deviceSalesLxr;
    }

    public String getDeviceSalesLxr() 
    {
        return deviceSalesLxr;
    }
    public void setDeviceSalesLxrPhone(String deviceSalesLxrPhone) 
    {
        this.deviceSalesLxrPhone = deviceSalesLxrPhone;
    }

    public String getDeviceSalesLxrPhone() 
    {
        return deviceSalesLxrPhone;
    }
    public void setDeviceBz(String deviceBz) 
    {
        this.deviceBz = deviceBz;
    }

    public String getDeviceBz() 
    {
        return deviceBz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceId", getDeviceId())
            .append("deviceName", getDeviceName())
            .append("deviceTdNum", getDeviceTdNum())
            .append("deviceAccuracy", getDeviceAccuracy())
            .append("deviceBrand", getDeviceBrand())
            .append("deviceModel", getDeviceModel())
            .append("deviceBuyTime", getDeviceBuyTime())
            .append("deviceSendUnit", getDeviceSendUnit())
            .append("deviceSendLxr", getDeviceSendLxr())
            .append("deviceSendLxrPhone", getDeviceSendLxrPhone())
            .append("deviceSalesLxr", getDeviceSalesLxr())
            .append("deviceSalesLxrPhone", getDeviceSalesLxrPhone())
            .append("deviceBz", getDeviceBz())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
