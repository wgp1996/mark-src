package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 检测人员建档对象 check_person
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public class CheckPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 身份证 */
    @Excel(name = "身份证")
    private String checkPersonId;

    /** 检测人员名称 */
    @Excel(name = "检测人员名称")
    private String checkPersonName;

    /** 检测人员编码 */
    @Excel(name = "检测人员编码")
    private String checkPersonCode;

    /** 电话 */
    @Excel(name = "电话")
    private String checkPersonTel;

    /** 专业 */
    @Excel(name = "专业")
    private String checkPersonMajor;

    /** 备注 */
    @Excel(name = "备注")
    private String checkPersonBz;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCheckPersonId(String checkPersonId) 
    {
        this.checkPersonId = checkPersonId;
    }

    public String getCheckPersonId() 
    {
        return checkPersonId;
    }
    public void setCheckPersonName(String checkPersonName) 
    {
        this.checkPersonName = checkPersonName;
    }

    public String getCheckPersonName() 
    {
        return checkPersonName;
    }
    public void setCheckPersonCode(String checkPersonCode) 
    {
        this.checkPersonCode = checkPersonCode;
    }

    public String getCheckPersonCode() 
    {
        return checkPersonCode;
    }
    public void setCheckPersonTel(String checkPersonTel) 
    {
        this.checkPersonTel = checkPersonTel;
    }

    public String getCheckPersonTel() 
    {
        return checkPersonTel;
    }
    public void setCheckPersonMajor(String checkPersonMajor) 
    {
        this.checkPersonMajor = checkPersonMajor;
    }

    public String getCheckPersonMajor() 
    {
        return checkPersonMajor;
    }
    public void setCheckPersonBz(String checkPersonBz) 
    {
        this.checkPersonBz = checkPersonBz;
    }

    public String getCheckPersonBz() 
    {
        return checkPersonBz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkPersonId", getCheckPersonId())
            .append("checkPersonName", getCheckPersonName())
            .append("checkPersonCode", getCheckPersonCode())
            .append("checkPersonTel", getCheckPersonTel())
            .append("checkPersonMajor", getCheckPersonMajor())
            .append("checkPersonBz", getCheckPersonBz())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
