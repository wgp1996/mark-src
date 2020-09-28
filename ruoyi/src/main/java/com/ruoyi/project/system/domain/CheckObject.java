package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 监测物体建档对象 check_object
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
public class CheckObject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 检测物编码 */
    @Excel(name = "检测物编码")
    private String objectNum;

    /** 检测物名称 */
    @Excel(name = "检测物名称")
    private String objectName;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setObjectNum(String objectNum) 
    {
        this.objectNum = objectNum;
    }

    public String getObjectNum() 
    {
        return objectNum;
    }
    public void setObjectName(String objectName) 
    {
        this.objectName = objectName;
    }

    public String getObjectName() 
    {
        return objectName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("objectNum", getObjectNum())
            .append("objectName", getObjectName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
