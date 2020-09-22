package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 取样地建档对象 samp_address
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public class SampAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 取样地点编码 */
    @Excel(name = "取样地点编码")
    private String sampAddressId;

    /** 取样地点名称 */
    @Excel(name = "取样地点名称")
    private String sampAddress;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String sampAddressDetail;

    /** 取样地业主 */
    @Excel(name = "取样地业主")
    private String sampAddressPerson;

    /** 备注 */
    @Excel(name = "备注")
    private String sampBz;

    /** 经度 */
    @Excel(name = "经度")
    private String sampAddressLat;

    /** 纬度 */
    @Excel(name = "纬度")
    private String sampAddressLng;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String sampAddressPersonTel;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setSampAddressId(String sampAddressId) 
    {
        this.sampAddressId = sampAddressId;
    }

    public String getSampAddressId() 
    {
        return sampAddressId;
    }
    public void setSampAddress(String sampAddress) 
    {
        this.sampAddress = sampAddress;
    }

    public String getSampAddress() 
    {
        return sampAddress;
    }
    public void setSampAddressDetail(String sampAddressDetail) 
    {
        this.sampAddressDetail = sampAddressDetail;
    }

    public String getSampAddressDetail() 
    {
        return sampAddressDetail;
    }
    public void setSampAddressPerson(String sampAddressPerson) 
    {
        this.sampAddressPerson = sampAddressPerson;
    }

    public String getSampAddressPerson() 
    {
        return sampAddressPerson;
    }
    public void setSampBz(String sampBz) 
    {
        this.sampBz = sampBz;
    }

    public String getSampBz() 
    {
        return sampBz;
    }
    public void setSampAddressLat(String sampAddressLat) 
    {
        this.sampAddressLat = sampAddressLat;
    }

    public String getSampAddressLat() 
    {
        return sampAddressLat;
    }
    public void setSampAddressLng(String sampAddressLng) 
    {
        this.sampAddressLng = sampAddressLng;
    }

    public String getSampAddressLng() 
    {
        return sampAddressLng;
    }
    public void setSampAddressPersonTel(String sampAddressPersonTel) 
    {
        this.sampAddressPersonTel = sampAddressPersonTel;
    }

    public String getSampAddressPersonTel() 
    {
        return sampAddressPersonTel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sampAddressId", getSampAddressId())
            .append("sampAddress", getSampAddress())
            .append("sampAddressDetail", getSampAddressDetail())
            .append("sampAddressPerson", getSampAddressPerson())
            .append("sampBz", getSampBz())
            .append("sampAddressLat", getSampAddressLat())
            .append("sampAddressLng", getSampAddressLng())
            .append("sampAddressPersonTel", getSampAddressPersonTel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
