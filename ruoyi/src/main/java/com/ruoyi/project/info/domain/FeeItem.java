package com.ruoyi.project.info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 费用项目对象 fee_item
 * 
 * @author ruoyi
 * @date 2020-10-16
 */
public class FeeItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 费用项目编码 */
    @Excel(name = "费用项目编码")
    private String feeCode;

    /** 费用项目名称 */
    @Excel(name = "费用项目名称")
    private String feeName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFeeCode(String feeCode) 
    {
        this.feeCode = feeCode;
    }

    public String getFeeCode() 
    {
        return feeCode;
    }
    public void setFeeName(String feeName) 
    {
        this.feeName = feeName;
    }

    public String getFeeName() 
    {
        return feeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feeCode", getFeeCode())
            .append("feeName", getFeeName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
