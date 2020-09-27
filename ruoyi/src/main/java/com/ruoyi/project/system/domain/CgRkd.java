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
    private String storeCode;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 摊位名称 */
    @Excel(name = "摊位名称")
    private String storeName;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private Integer status;

    /** $column.columnComment */
    @Excel(name = "文件名")
    private String fileName;
    private String rows;
    private String djStatusName;

    private Integer isRate;

    private String sumNum;

    private String goodsName;
    private String goodsDw;
    private String goodsNum;
    private String createName;
    private String goodsAddress;
    private String personName;
    private String personCode;
    private String personLxr;
    private Integer type;
    private String statusName;
    private String djId;
    private String createCount;

    public String getCreateCount() {
        return createCount;
    }

    public void setCreateCount(String createCount) {
        this.createCount = createCount;
    }

    public String getDjId() {
        return djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPersonLxr() {
        return personLxr;
    }

    public void setPersonLxr(String personLxr) {
        this.personLxr = personLxr;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDw() {
        return goodsDw;
    }

    public void setGoodsDw(String goodsDw) {
        this.goodsDw = goodsDw;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public Integer getIsRate() {
        return isRate;
    }

    public void setIsRate(Integer isRate) {
        this.isRate = isRate;
    }

    public String getSumNum() {
        return sumNum;
    }

    public void setSumNum(String sumNum) {
        this.sumNum = sumNum;
    }

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
    public void setStoreCode(String storeCode) 
    {
        this.storeCode = storeCode;
    }

    public String getStoreCode() 
    {
        return storeCode;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
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
            .append("storeCode", getStoreCode())
            .append("djTime", getDjTime())
            .append("storeName", getStoreName())
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
