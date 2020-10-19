package com.ruoyi.project.info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帐夹对象 fee_info
 * 
 * @author ruoyi
 * @date 2020-10-16
 */
public class FeeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 费用项目名称 */
    @Excel(name = "费用项目名称")
    private String feeName;

    /** 费用项目编码 */
    @Excel(name = "费用项目编码")
    private String feeCode;

    /** 金额 */
    @Excel(name = "金额")
    private String feeMoney;

    /** 凭证张数 */
    @Excel(name = "凭证张数")
    private String feeNum;

    /** 消费日期 */
    @Excel(name = "消费日期")
    private String feeTime;

    /** 附件 */
    @Excel(name = "附件")
    private String fileName;

    private int source;

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFeeName(String feeName) 
    {
        this.feeName = feeName;
    }

    public String getFeeName() 
    {
        return feeName;
    }
    public void setFeeCode(String feeCode) 
    {
        this.feeCode = feeCode;
    }

    public String getFeeCode() 
    {
        return feeCode;
    }
    public void setFeeMoney(String feeMoney) 
    {
        this.feeMoney = feeMoney;
    }

    public String getFeeMoney() 
    {
        return feeMoney;
    }
    public void setFeeNum(String feeNum) 
    {
        this.feeNum = feeNum;
    }

    public String getFeeNum() 
    {
        return feeNum;
    }
    public void setFeeTime(String feeTime) 
    {
        this.feeTime = feeTime;
    }

    public String getFeeTime() 
    {
        return feeTime;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feeName", getFeeName())
            .append("feeCode", getFeeCode())
            .append("feeMoney", getFeeMoney())
            .append("feeNum", getFeeNum())
            .append("feeTime", getFeeTime())
            .append("fileName", getFileName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
