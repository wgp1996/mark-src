package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 供应商建档对象 person_info
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public class PersonInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String personCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String personName;

    /** 产地 */
    @Excel(name = "产地")
    private String personGoodsAddress;

    /** 电话 */
    @Excel(name = "电话")
    private String personPhone;

    /** 微信 */
    @Excel(name = "微信")
    private String personWx;

    /** 地址 */
    @Excel(name = "地址")
    private String personAddress;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setPersonCode(String personCode) 
    {
        this.personCode = personCode;
    }

    public String getPersonCode() 
    {
        return personCode;
    }
    public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getPersonName() 
    {
        return personName;
    }
    public void setPersonGoodsAddress(String personGoodsAddress) 
    {
        this.personGoodsAddress = personGoodsAddress;
    }

    public String getPersonGoodsAddress() 
    {
        return personGoodsAddress;
    }
    public void setPersonPhone(String personPhone) 
    {
        this.personPhone = personPhone;
    }

    public String getPersonPhone() 
    {
        return personPhone;
    }
    public void setPersonWx(String personWx) 
    {
        this.personWx = personWx;
    }

    public String getPersonWx() 
    {
        return personWx;
    }
    public void setPersonAddress(String personAddress) 
    {
        this.personAddress = personAddress;
    }

    public String getPersonAddress() 
    {
        return personAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("personCode", getPersonCode())
            .append("personName", getPersonName())
            .append("personGoodsAddress", getPersonGoodsAddress())
            .append("personPhone", getPersonPhone())
            .append("personWx", getPersonWx())
            .append("personAddress", getPersonAddress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
