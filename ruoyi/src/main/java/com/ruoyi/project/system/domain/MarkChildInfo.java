package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 二级市场信息对象 mark_child_info
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
public class MarkChildInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 主市场编码 */
    @Excel(name = "主市场编码")
    private String markCode;

    /** 二级市场编码 */
    @Excel(name = "二级市场编码")
    private String markChildCode;

    /** 二级市场名称 */
    @Excel(name = "二级市场名称")
    private String markChildName;

    /** 运营主体名称 */
    @Excel(name = "运营主体名称")
    private String perationName;

    /** 运营主体性质 */
    @Excel(name = "运营主体性质")
    private String perationNature;

    /** 社会信用代码 */
    @Excel(name = "社会信用代码")
    private String socialCreditCode;

    /** 运营方式 */
    @Excel(name = "运营方式")
    private String operateType;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String operateStartTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String operateEndTime;

    /** 租金 */
    @Excel(name = "租金")
    private String operateMoney;

    /** 现有商户数量 */
    @Excel(name = "现有商户数量")
    private String markMerchantsCount;

    /** 说明 */
    @Excel(name = "说明")
    private String markNote;

    /** $column.columnComment */
    @Excel(name = "说明")
    private String createUser;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMarkCode(String markCode) 
    {
        this.markCode = markCode;
    }

    public String getMarkCode() 
    {
        return markCode;
    }
    public void setMarkChildCode(String markChildCode) 
    {
        this.markChildCode = markChildCode;
    }

    public String getMarkChildCode() 
    {
        return markChildCode;
    }
    public void setMarkChildName(String markChildName) 
    {
        this.markChildName = markChildName;
    }

    public String getMarkChildName() 
    {
        return markChildName;
    }
    public void setPerationName(String perationName) 
    {
        this.perationName = perationName;
    }

    public String getPerationName() 
    {
        return perationName;
    }
    public void setPerationNature(String perationNature) 
    {
        this.perationNature = perationNature;
    }

    public String getPerationNature() 
    {
        return perationNature;
    }
    public void setSocialCreditCode(String socialCreditCode) 
    {
        this.socialCreditCode = socialCreditCode;
    }

    public String getSocialCreditCode() 
    {
        return socialCreditCode;
    }
    public void setOperateType(String operateType) 
    {
        this.operateType = operateType;
    }

    public String getOperateType() 
    {
        return operateType;
    }
    public void setOperateStartTime(String operateStartTime) 
    {
        this.operateStartTime = operateStartTime;
    }

    public String getOperateStartTime() 
    {
        return operateStartTime;
    }
    public void setOperateEndTime(String operateEndTime) 
    {
        this.operateEndTime = operateEndTime;
    }

    public String getOperateEndTime() 
    {
        return operateEndTime;
    }
    public void setOperateMoney(String operateMoney) 
    {
        this.operateMoney = operateMoney;
    }

    public String getOperateMoney() 
    {
        return operateMoney;
    }
    public void setMarkMerchantsCount(String markMerchantsCount) 
    {
        this.markMerchantsCount = markMerchantsCount;
    }

    public String getMarkMerchantsCount() 
    {
        return markMerchantsCount;
    }
    public void setMarkNote(String markNote) 
    {
        this.markNote = markNote;
    }

    public String getMarkNote() 
    {
        return markNote;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("markCode", getMarkCode())
            .append("markChildCode", getMarkChildCode())
            .append("markChildName", getMarkChildName())
            .append("perationName", getPerationName())
            .append("perationNature", getPerationNature())
            .append("socialCreditCode", getSocialCreditCode())
            .append("operateType", getOperateType())
            .append("operateStartTime", getOperateStartTime())
            .append("operateEndTime", getOperateEndTime())
            .append("operateMoney", getOperateMoney())
            .append("markMerchantsCount", getMarkMerchantsCount())
            .append("markNote", getMarkNote())
            .append("createUser", getCreateUser())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
