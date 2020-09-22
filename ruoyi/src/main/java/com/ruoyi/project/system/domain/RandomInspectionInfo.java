package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 随机检测单对象 random_inspection_info
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public class RandomInspectionInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 单据标题 */
    @Excel(name = "单据标题")
    private String djTitle;

    /** 采用地点 */
    @Excel(name = "采用地点")
    private String checkAddress;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private Long djStatus;

    /** 附件 */
    @Excel(name = "附件")
    private String fileName;

    /** 抑制率标准值 */
    @Excel(name = "抑制率标准值")
    private Long inhibitionNum;
    /*
         子表
      */
    private List<RandomInspectionInfoChild> childrenList;

    public List<RandomInspectionInfoChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<RandomInspectionInfoChild> childrenList) {
        this.childrenList = childrenList;
    }

    private String rows;

    public String getRows() {
        return rows;
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
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setDjTitle(String djTitle) 
    {
        this.djTitle = djTitle;
    }

    public String getDjTitle() 
    {
        return djTitle;
    }
    public void setCheckAddress(String checkAddress) 
    {
        this.checkAddress = checkAddress;
    }

    public String getCheckAddress() 
    {
        return checkAddress;
    }
    public void setDjStatus(Long djStatus) 
    {
        this.djStatus = djStatus;
    }

    public Long getDjStatus() 
    {
        return djStatus;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setInhibitionNum(Long inhibitionNum) 
    {
        this.inhibitionNum = inhibitionNum;
    }

    public Long getInhibitionNum() 
    {
        return inhibitionNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("djTime", getDjTime())
            .append("djTitle", getDjTitle())
            .append("createTime", getCreateTime())
            .append("checkAddress", getCheckAddress())
            .append("djStatus", getDjStatus())
            .append("fileName", getFileName())
            .append("inhibitionNum", getInhibitionNum())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
