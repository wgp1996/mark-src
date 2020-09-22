package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 仓库建档对象 ck_info
 * 
 * @author ruoyi
 * @date 2020-08-11
 */
public class CkInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String ckName;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String ckCode;

    /** 仓库面积 */
    @Excel(name = "仓库面积")
    private String ckArea;

    /** 仓库容积 */
    @Excel(name = "仓库容积")
    private String ckVolume;

    /** 仓库类型 */
    @Excel(name = "仓库类型")
    private String ckType;

    /** 仓库地址 */
    @Excel(name = "仓库地址")
    private String ckAddress;

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
    public void setCkName(String ckName) 
    {
        this.ckName = ckName;
    }

    public String getCkName() 
    {
        return ckName;
    }
    public void setCkCode(String ckCode) 
    {
        this.ckCode = ckCode;
    }

    public String getCkCode() 
    {
        return ckCode;
    }
    public void setCkArea(String ckArea) 
    {
        this.ckArea = ckArea;
    }

    public String getCkArea() 
    {
        return ckArea;
    }
    public void setCkVolume(String ckVolume) 
    {
        this.ckVolume = ckVolume;
    }

    public String getCkVolume() 
    {
        return ckVolume;
    }
    public void setCkType(String ckType) 
    {
        this.ckType = ckType;
    }

    public String getCkType() 
    {
        return ckType;
    }
    public void setCkAddress(String ckAddress) 
    {
        this.ckAddress = ckAddress;
    }

    public String getCkAddress() 
    {
        return ckAddress;
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
            .append("ckName", getCkName())
            .append("ckCode", getCkCode())
            .append("ckArea", getCkArea())
            .append("ckVolume", getCkVolume())
            .append("ckType", getCkType())
            .append("ckAddress", getCkAddress())
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
