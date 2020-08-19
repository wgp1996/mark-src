package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物业收款对象 property_collection
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
public class SalesCollection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收款单号 */
    @Excel(name = "收款单号")
    private String collectionCode;

    /** $column.columnComment */
    private String id;
    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractCode;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String contractName;
    /** 客户编码 */
    @Excel(name = "客户编码")
    private String ownerCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String ownerName;

    /** 摊位编码 */
    @Excel(name = "摊位编码")
    private String stallCode;

    /** 摊位名称 */
    @Excel(name = "摊位名称")
    private String stallName;

    /** 合同开始时间 */
    @Excel(name = "合同开始时间")
    private String contractStartTime;

    /** 合同结束时间 */
    @Excel(name = "合同结束时间")
    private String contractEndTime;

    /** 收款金额 */
    @Excel(name = "收款金额")
    private String collectionMoney;

    /** 收款方式 */
    @Excel(name = "收款方式")
    private String collectionPayType;

    private String leaseMxId;

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getLeaseMxId() {
        return leaseMxId;
    }

    public void setLeaseMxId(String leaseMxId) {
        this.leaseMxId = leaseMxId;
    }

    public void setCollectionCode(String collectionCode)
    {
        this.collectionCode = collectionCode;
    }

    public String getCollectionCode() 
    {
        return collectionCode;
    }
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOwnerCode(String ownerCode) 
    {
        this.ownerCode = ownerCode;
    }

    public String getOwnerCode() 
    {
        return ownerCode;
    }
    public void setOwnerName(String ownerName) 
    {
        this.ownerName = ownerName;
    }

    public String getOwnerName() 
    {
        return ownerName;
    }
    public void setStallCode(String stallCode) 
    {
        this.stallCode = stallCode;
    }

    public String getStallCode() 
    {
        return stallCode;
    }
    public void setStallName(String stallName) 
    {
        this.stallName = stallName;
    }

    public String getStallName() 
    {
        return stallName;
    }
    public void setContractStartTime(String contractStartTime) 
    {
        this.contractStartTime = contractStartTime;
    }

    public String getContractStartTime() 
    {
        return contractStartTime;
    }
    public void setContractEndTime(String contractEndTime) 
    {
        this.contractEndTime = contractEndTime;
    }

    public String getContractEndTime() 
    {
        return contractEndTime;
    }
    public void setCollectionMoney(String collectionMoney) 
    {
        this.collectionMoney = collectionMoney;
    }

    public String getCollectionMoney() 
    {
        return collectionMoney;
    }
    public void setCollectionPayType(String collectionPayType) 
    {
        this.collectionPayType = collectionPayType;
    }

    public String getCollectionPayType() 
    {
        return collectionPayType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("collectionCode", getCollectionCode())
            .append("id", getId())
            .append("ownerCode", getOwnerCode())
            .append("ownerName", getOwnerName())
            .append("stallCode", getStallCode())
            .append("stallName", getStallName())
            .append("contractStartTime", getContractStartTime())
            .append("contractEndTime", getContractEndTime())
            .append("collectionMoney", getCollectionMoney())
            .append("collectionPayType", getCollectionPayType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("leaseMxId", getLeaseMxId())
            .toString();
    }
}
