package com.ruoyi.project.info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 盘点单对象 check_info
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
public class CheckInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private Integer djStatus;

    /** 冷库编码 */
    @Excel(name = "冷库编码")
    private String storeNum;

    /** 开始日期 */
    @Excel(name = "开始日期")
    private String checkStartTime;

    /** 结束日期 */
    @Excel(name = "结束日期")
    private String checkEndTime;

    /** 冷库名称 */
    @Excel(name = "冷库名称")
    private String storeName;

    private String rows;
    /*
       子表
    */
    private List<CheckInfoChild> childrenList;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public List<CheckInfoChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<CheckInfoChild> childrenList) {
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
    public void setDjStatus(Integer djStatus) 
    {
        this.djStatus = djStatus;
    }

    public Integer getDjStatus() 
    {
        return djStatus;
    }
    public void setStoreNum(String storeNum) 
    {
        this.storeNum = storeNum;
    }

    public String getStoreNum() 
    {
        return storeNum;
    }
    public void setCheckStartTime(String checkStartTime) 
    {
        this.checkStartTime = checkStartTime;
    }

    public String getCheckStartTime() 
    {
        return checkStartTime;
    }
    public void setCheckEndTime(String checkEndTime) 
    {
        this.checkEndTime = checkEndTime;
    }

    public String getCheckEndTime() 
    {
        return checkEndTime;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("djTime", getDjTime())
            .append("djStatus", getDjStatus())
            .append("storeNum", getStoreNum())
            .append("checkStartTime", getCheckStartTime())
            .append("checkEndTime", getCheckEndTime())
            .append("storeName", getStoreName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
