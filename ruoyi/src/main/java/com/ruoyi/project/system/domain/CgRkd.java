package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 进货单对象 cg_rkd
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
public class CgRkd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 单据编码 */
    @Excel(name = "单据编码")
    private String djNumber;

    /** 摊位编码 */
    @Excel(name = "摊位编码")
    private String stallCode;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 摊位名称 */
    @Excel(name = "摊位名称")
    private String stallName;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private Integer status;

    /** $column.columnComment */
    @Excel(name = "文件名")
    private String fileName;
    private String rows;
    private String djStatusName;

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    /*
               子表
             */
    private List<CgRkdChild> childrenList;
    public String getRows() {
        return rows;
    }

    public List<CgRkdChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<CgRkdChild> childrenList) {
        this.childrenList = childrenList;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

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
    public void setStallCode(String stallCode) 
    {
        this.stallCode = stallCode;
    }

    public String getStallCode() 
    {
        return stallCode;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setStallName(String stallName) 
    {
        this.stallName = stallName;
    }

    public String getStallName() 
    {
        return stallName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
            .append("djNumber", getDjNumber())
            .append("stallCode", getStallCode())
            .append("djTime", getDjTime())
            .append("stallName", getStallName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("fileName", getFileName())
            .toString();
    }
}
