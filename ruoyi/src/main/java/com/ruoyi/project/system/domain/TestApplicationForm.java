package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 检测申请单对象 test_application_form
 * 
 * @author ruoyi
 * @date 2020-09-30
 */
public class TestApplicationForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 监测类型 */
    @Excel(name = "监测类型")
    private String checkType;

    /** 检测说明 */
    @Excel(name = "检测说明")
    private String checkNote;

    /** 检测日期 */
    @Excel(name = "检测日期")
    private String checkTime;

    /** 检测申请单号 */
    @Excel(name = "检测申请单号")
    private String djNumber;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;
    /*
        子表
    */
    private List<TestApplicationFormChild> childrenList;
    private String rows;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public List<TestApplicationFormChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<TestApplicationFormChild> childrenList) {
        this.childrenList = childrenList;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setCheckType(String checkType) 
    {
        this.checkType = checkType;
    }

    public String getCheckType() 
    {
        return checkType;
    }
    public void setCheckNote(String checkNote) 
    {
        this.checkNote = checkNote;
    }

    public String getCheckNote() 
    {
        return checkNote;
    }
    public void setCheckTime(String checkTime) 
    {
        this.checkTime = checkTime;
    }

    public String getCheckTime() 
    {
        return checkTime;
    }
    public void setDjNumber(String djNumber) 
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkType", getCheckType())
            .append("checkNote", getCheckNote())
            .append("checkTime", getCheckTime())
            .append("djNumber", getDjNumber())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
