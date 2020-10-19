package com.ruoyi.project.info.domain;

import com.ruoyi.project.system.domain.CgRkdChild;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 入库单对象 rk_info
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
public class RkInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private Integer djStatus;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 冷库编码 */
    @Excel(name = "冷库编码")
    private String storeNum;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String khCode;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String djType;

    /** 流水号 */
    @Excel(name = "流水号")
    private String lsNumber;

    /** 批次 */
    @Excel(name = "批次")
    private String rkPc;

    /** 冷库名称 */
    @Excel(name = "冷库名称")
    private String storeName;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String khName;

    private String rows;
    /*
       子表
    */
    private List<RkInfoChild> childrenList;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public List<RkInfoChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<RkInfoChild> childrenList) {
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
    public void setKhCode(String khCode) 
    {
        this.khCode = khCode;
    }

    public String getKhCode() 
    {
        return khCode;
    }
    public void setDjType(String djType) 
    {
        this.djType = djType;
    }

    public String getDjType() 
    {
        return djType;
    }
    public void setLsNumber(String lsNumber) 
    {
        this.lsNumber = lsNumber;
    }

    public String getLsNumber() 
    {
        return lsNumber;
    }
    public void setRkPc(String rkPc) 
    {
        this.rkPc = rkPc;
    }

    public String getRkPc() 
    {
        return rkPc;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setKhName(String khName) 
    {
        this.khName = khName;
    }

    public String getKhName() 
    {
        return khName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("djStatus", getDjStatus())
            .append("djTime", getDjTime())
            .append("storeNum", getStoreNum())
            .append("khCode", getKhCode())
            .append("djType", getDjType())
            .append("lsNumber", getLsNumber())
            .append("rkPc", getRkPc())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("storeName", getStoreName())
            .append("khName", getKhName())
            .toString();
    }
}
