package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 批发销货一票通子表对象 whole_sales_child
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public class WholeSalesChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 单据号 */
    @Excel(name = "单据号")
    private String djNumber;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String khName;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String khCode;

    /** 销售量 */
    @Excel(name = "销售量")
    private String wholeNum;

    /** 单位 */
    @Excel(name = "单位")
    private String wholeDw;

    /** 单价 */
    @Excel(name = "单价")
    private String wholePrice;

    /** $column.columnComment */
    @Excel(name = "单价")
    private String wholeBz;
    private Integer status;
    /** 总价 */
    @Excel(name = "总价")
    private String wholeMoney;
    private String goodsName;

    private String wholeSum;
    private String markType;

    public String getMarkType() {
        return markType;
    }

    public void setMarkType(String markType) {
        this.markType = markType;
    }

    public String getWholeMoney() {
        return wholeMoney;
    }

    public void setWholeMoney(String wholeMoney) {
        this.wholeMoney = wholeMoney;
    }

    public String getWholeSum() {
        return wholeSum;
    }

    public void setWholeSum(String wholeSum) {
        this.wholeSum = wholeSum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getKhName() {
        return khName;
    }

    public void setKhName(String khName) {
        this.khName = khName;
    }

    public void setKhCode(String khCode)
    {
        this.khCode = khCode;
    }

    public String getKhCode() 
    {
        return khCode;
    }
    public void setWholeNum(String wholeNum) 
    {
        this.wholeNum = wholeNum;
    }

    public String getWholeNum() 
    {
        return wholeNum;
    }
    public void setWholeDw(String wholeDw) 
    {
        this.wholeDw = wholeDw;
    }

    public String getWholeDw() 
    {
        return wholeDw;
    }
    public void setWholePrice(String wholePrice) 
    {
        this.wholePrice = wholePrice;
    }

    public String getWholePrice() 
    {
        return wholePrice;
    }
    public void setWholeBz(String wholeBz) 
    {
        this.wholeBz = wholeBz;
    }

    public String getWholeBz() 
    {
        return wholeBz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("khName", getKhName())
            .append("khCode", getKhCode())
            .append("wholeNum", getWholeNum())
            .append("wholeDw", getWholeDw())
            .append("wholePrice", getWholePrice())
            .append("wholeBz", getWholeBz())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
