package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sys_area
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
public class SysArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String code;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String pcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String tagsCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long level;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lat;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lng;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long order;

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPcode(String pcode) 
    {
        this.pcode = pcode;
    }

    public String getPcode() 
    {
        return pcode;
    }
    public void setTagsCode(String tagsCode) 
    {
        this.tagsCode = tagsCode;
    }

    public String getTagsCode() 
    {
        return tagsCode;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
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
    public void setOrder(Long order) 
    {
        this.order = order;
    }

    public Long getOrder() 
    {
        return order;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("code", getCode())
            .append("name", getName())
            .append("pcode", getPcode())
            .append("tagsCode", getTagsCode())
            .append("level", getLevel())
            .append("lat", getLat())
            .append("lng", getLng())
            .append("order", getOrder())
            .toString();
    }
}
