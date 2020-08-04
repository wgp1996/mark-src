package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 租赁合同子表信息对象 lease_contract_child
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public class LeaseContractChildPool extends BaseEntity {
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
     * 摊位编号
     */
    @Excel(name = "摊位编号")
    private String stallCode;

    /**
     * 摊位名称
     */
    @Excel(name = "摊位名称")
    private String stallName;

    /**
     * 摊位面积
     */
    @Excel(name = "摊位面积")
    private String stallArea;

    /**
     * 开始计费日期
     */
    @Excel(name = "计费日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String leaseTime;


    /**
     * 抽成比例
     */
    @Excel(name = "抽成比例")
    private String takeNum;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String contractBz;

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

    public void setStallCode(String stallCode) {
        this.stallCode = stallCode;
    }

    public String getStallCode() {
        return stallCode;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallArea(String stallArea) {
        this.stallArea = stallArea;
    }

    public String getStallArea() {
        return stallArea;
    }

    public String getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(String leaseTime) {
        this.leaseTime = leaseTime;
    }

    public String getTakeNum() {
        return takeNum;
    }

    public void setTakeNum(String takeNum) {
        this.takeNum = takeNum;
    }

    public void setContractBz(String contractBz) {
        this.contractBz = contractBz;
    }

    public String getContractBz() {
        return contractBz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("contractCode", getContractCode())
                .append("stallCode", getStallCode())
                .append("stallName", getStallName())
                .append("stallArea", getStallArea())
                .append("leaseTime", getLeaseTime())
                .append("takeNum", getTakeNum())
                .append("contractBz", getContractBz())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
