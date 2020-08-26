package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 批发销货一票通子表对象 whole_sales_child
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public class WholeRetailChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 单据号 */
    @Excel(name = "单据号")
    private String djNumber;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

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

    private String khName;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKhName() {
        return khName;
    }

    public void setKhName(String khName) {
        this.khName = khName;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
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
            .append("goodsName", getGoodsName())
            .append("goodsCode", getGoodsCode())
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