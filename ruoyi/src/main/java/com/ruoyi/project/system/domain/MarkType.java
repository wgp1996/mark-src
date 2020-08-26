package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.TreeEntity;

/**
 * 市场分类对象 mark_type
 * 
 * @author ruoyi
 * @date 2020-08-25
 */
public class MarkType extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 市场分类id */
    private Integer markId;

    private int id;

    /** 市场分类名称 */
    @Excel(name = "市场分类名称")
    private String markTypeName;

    /** 档案类型 0业户 1市场 */
    private Integer type;

    public void setMarkId(Integer markId)
    {
        this.markId = markId;
    }

    public Integer getMarkId()
    {
        return markId;
    }
    public void setMarkTypeName(String markTypeName) 
    {
        this.markTypeName = markTypeName;
    }

    public String getMarkTypeName() 
    {
        return markTypeName;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("markId", getMarkId())
            .append("parentId", getParentId())
            .append("markTypeName", getMarkTypeName())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("type", getType())
            .toString();
    }
}
