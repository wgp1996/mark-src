package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 客户建档对象 kh_info
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public class KhInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String khCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String khName;

    /** 电话 */
    @Excel(name = "电话")
    private String khPhone;

    /** 微信 */
    @Excel(name = "微信")
    private String khWx;

    /** 地址 */
    @Excel(name = "地址")
    private String khAddress;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setKhCode(String khCode) 
    {
        this.khCode = khCode;
    }

    public String getKhCode() 
    {
        return khCode;
    }
    public void setKhName(String khName) 
    {
        this.khName = khName;
    }

    public String getKhName() 
    {
        return khName;
    }
    public void setKhPhone(String khPhone) 
    {
        this.khPhone = khPhone;
    }

    public String getKhPhone() 
    {
        return khPhone;
    }
    public void setKhWx(String khWx) 
    {
        this.khWx = khWx;
    }

    public String getKhWx() 
    {
        return khWx;
    }
    public void setKhAddress(String khAddress) 
    {
        this.khAddress = khAddress;
    }

    public String getKhAddress() 
    {
        return khAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("khCode", getKhCode())
            .append("khName", getKhName())
            .append("khPhone", getKhPhone())
            .append("khWx", getKhWx())
            .append("khAddress", getKhAddress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
