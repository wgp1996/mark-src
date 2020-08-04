package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 租赁合同对象 lease_contract
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public class LeaseContractPool extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 合同编号
     */
    @Excel(name = "合同编号")
    private String contractCode;

    /**
     * 合同名称
     */
    @Excel(name = "合同名称")
    private String contractName;

    /**
     * 业主编号
     */
    @Excel(name = "业主编号")
    private String ownerCode;

    /**
     * 业主名称
     */
    @Excel(name = "业主名称")
    private String ownerName;

    /**
     * 联营模式
     */
    @Excel(name = "联营模式")
    private String poolModel;
    /**
     * 抽成率
     */
    @Excel(name = "抽成率")
    private String takeNum;
    /**
     * 抽成结算方式
     */
    @Excel(name = "抽成结算方式")
    private String takePayType;

    /**
     * 签约地点
     */
    @Excel(name = "签约地点")
    private String signAddress;

    /**
     * 签约时间
     */
    @Excel(name = "签约时间")
    private String signTime;
    /**
     * 子表json字符串
     */
    private String rows;

    /*附件*/
    private String fileName;

    /*
       合同子表
     */
    private List<LeaseContractChildPool> childrenList;

    public List<LeaseContractChildPool> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<LeaseContractChildPool> childrenList) {
        this.childrenList = childrenList;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPoolModel() {
        return poolModel;
    }

    public void setPoolModel(String poolModel) {
        this.poolModel = poolModel;
    }

    public String getTakeNum() {
        return takeNum;
    }

    public void setTakeNum(String takeNum) {
        this.takeNum = takeNum;
    }

    public String getTakePayType() {
        return takePayType;
    }

    public void setTakePayType(String takePayType) {
        this.takePayType = takePayType;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignTime() {
        return signTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("contractCode", getContractCode())
                .append("contractName", getContractName())
                .append("ownerCode", getOwnerCode())
                .append("ownerName", getOwnerName())
                .append("poolModel", getPoolModel())
                .append("signAddress", getSignAddress())
                .append("signTime", getSignTime())
                .append("fileName", getFileName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("takeNum", getTakeNum())
                .append("takePayType", getTakePayType())
                .toString();
    }
}
