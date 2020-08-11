package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 冷库建档对象 store_info
 * 
 * @author ruoyi
 * @date 2020-08-11
 */
public class StoreInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 冷库名称 */
    @Excel(name = "冷库名称")
    private String storeName;

    /** 冷库编码 */
    @Excel(name = "冷库编码")
    private String storeCode;

    /** 冷库面积 */
    @Excel(name = "冷库面积")
    private String storeArea;

    /** 冷库容积 */
    @Excel(name = "冷库容积")
    private String storeVolume;

    /** 冷库类型 */
    @Excel(name = "冷库类型")
    private String storeType;

    /** 冷库地址 */
    @Excel(name = "冷库地址")
    private String storeAddress;

    /** 经度 */
    @Excel(name = "经度")
    private String lat;

    /** 纬度 */
    @Excel(name = "纬度")
    private String lng;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setStoreCode(String storeCode) 
    {
        this.storeCode = storeCode;
    }

    public String getStoreCode() 
    {
        return storeCode;
    }
    public void setStoreArea(String storeArea) 
    {
        this.storeArea = storeArea;
    }

    public String getStoreArea() 
    {
        return storeArea;
    }
    public void setStoreVolume(String storeVolume) 
    {
        this.storeVolume = storeVolume;
    }

    public String getStoreVolume() 
    {
        return storeVolume;
    }
    public void setStoreType(String storeType) 
    {
        this.storeType = storeType;
    }

    public String getStoreType() 
    {
        return storeType;
    }
    public void setStoreAddress(String storeAddress) 
    {
        this.storeAddress = storeAddress;
    }

    public String getStoreAddress() 
    {
        return storeAddress;
    }
    public void setLat(String lat) 
    {
        this.lat = lat;
    }

    public String getLat() 
    {
        return lat;
    }
    public void setLng(String lng) 
    {
        this.lng = lng;
    }

    public String getLng() 
    {
        return lng;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeName", getStoreName())
            .append("storeCode", getStoreCode())
            .append("storeArea", getStoreArea())
            .append("storeVolume", getStoreVolume())
            .append("storeType", getStoreType())
            .append("storeAddress", getStoreAddress())
            .append("lat", getLat())
            .append("lng", getLng())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
