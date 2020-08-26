package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 批发销货一票通对象 whole_sales
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public class WholeRetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 单据编号 */
    @Excel(name = "客户编号")
    private String khCode;

    /** 销售类型 */
    @Excel(name = "销售类型")
    private Long wholeType;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String khName;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private Integer status;

    /** 来源方式 */
    @Excel(name = "来源方式")
    private Integer from;

    private String rows;

    private String djStatusName;

     /*
       子表
    */
    private List<WholeRetailChild> childrenList;

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public List<WholeRetailChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<WholeRetailChild> childrenList) {
        this.childrenList = childrenList;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setkhCode(String khCode) 
    {
        this.khCode = khCode;
    }

    public String getkhCode() 
    {
        return khCode;
    }
    public void setWholeType(Long wholeType) 
    {
        this.wholeType = wholeType;
    }

    public Long getWholeType() 
    {
        return wholeType;
    }
    public void setDjNumber(String djNumber) 
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setkhName(String khName) 
    {
        this.khName = khName;
    }

    public String getkhName() 
    {
        return khName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setFrom(Integer from) 
    {
        this.from = from;
    }

    public Integer getFrom() 
    {
        return from;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("khCode", getkhCode())
            .append("wholeType", getWholeType())
            .append("djNumber", getDjNumber())
            .append("djTime", getDjTime())
            .append("khName", getkhName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("from", getFrom())
            .toString();
    }
}
