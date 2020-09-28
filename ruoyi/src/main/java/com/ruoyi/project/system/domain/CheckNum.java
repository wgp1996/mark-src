package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 检测标准设定对象 check_num
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
public class CheckNum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 抑制率标准 */
    @Excel(name = "抑制率标准")
    private String inhibitionNum;

    /** 检测标准 */
    @Excel(name = "检测标准")
    private String checkNum;

    /** 类型 0 抑制率 1 监测标准 */
    private Integer type;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setInhibitionNum(String inhibitionNum) 
    {
        this.inhibitionNum = inhibitionNum;
    }

    public String getInhibitionNum() 
    {
        return inhibitionNum;
    }
    public void setCheckNum(String checkNum) 
    {
        this.checkNum = checkNum;
    }

    public String getCheckNum() 
    {
        return checkNum;
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
            .append("inhibitionNum", getInhibitionNum())
            .append("checkNum", getCheckNum())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
