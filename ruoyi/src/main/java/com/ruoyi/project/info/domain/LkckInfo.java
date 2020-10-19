package com.ruoyi.project.info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 出库单对象 lkck_info
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
public class LkckInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 状态 */
    @Excel(name = "状态")
    private Integer djStatus;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 冷库编码 */
    @Excel(name = "冷库编码")
    private String storeNum;

    /** 实际出库日期 */
    @Excel(name = "实际出库日期")
    private String ckTime;

    /** 冷库名称 */
    @Excel(name = "冷库名称")
    private String storeName;

    private String rows;
    /*
       子表
    */
    private List<LkckInfoChild> childrenList;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public List<LkckInfoChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<LkckInfoChild> childrenList) {
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
    public void setDjStatus(Integer djStatus) 
    {
        this.djStatus = djStatus;
    }

    public Integer getDjStatus() 
    {
        return djStatus;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setStoreNum(String storeNum) 
    {
        this.storeNum = storeNum;
    }

    public String getStoreNum() 
    {
        return storeNum;
    }
    public void setCkTime(String ckTime) 
    {
        this.ckTime = ckTime;
    }

    public String getCkTime() 
    {
        return ckTime;
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
            .append("djStatus", getDjStatus())
            .append("djTime", getDjTime())
            .append("storeNum", getStoreNum())
            .append("ckTime", getCkTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("storeName", getStoreName())
            .toString();
    }
}
