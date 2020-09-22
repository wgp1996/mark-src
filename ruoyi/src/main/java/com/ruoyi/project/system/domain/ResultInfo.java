package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 订单退回对象 result_info
 * 
 * @author ruoyi
 * @date 2020-09-16
 */
public class ResultInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 明细id */
    @Excel(name = "明细id")
    private String djId;

    /** 退回类型 */
    @Excel(name = "退回类型")
    private Integer returnType;

    /** 退回原因 */
    @Excel(name = "退回原因")
    private String returnNote;

    /** 退回日期 */
    @Excel(name = "退回日期")
    private String returnTime;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer type;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDjNumber(String djNumber) 
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
    }
    public void setDjId(String djId) 
    {
        this.djId = djId;
    }

    public String getDjId() 
    {
        return djId;
    }
    public void setReturnType(Integer returnType) 
    {
        this.returnType = returnType;
    }

    public Integer getReturnType() 
    {
        return returnType;
    }
    public void setReturnNote(String returnNote) 
    {
        this.returnNote = returnNote;
    }

    public String getReturnNote() 
    {
        return returnNote;
    }
    public void setReturnTime(String returnTime) 
    {
        this.returnTime = returnTime;
    }

    public String getReturnTime() 
    {
        return returnTime;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("djId", getDjId())
            .append("returnType", getReturnType())
            .append("returnNote", getReturnNote())
            .append("returnTime", getReturnTime())
            .append("type", getType())
            .toString();
    }
}
