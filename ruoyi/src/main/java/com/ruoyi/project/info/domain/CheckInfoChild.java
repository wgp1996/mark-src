package com.ruoyi.project.info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 盘点单子表对象 check_info_child
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
public class CheckInfoChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 单位 */
    @Excel(name = "单位")
    private String goodsDw;

    /** 盘点数量 */
    @Excel(name = "盘点数量")
    private Double goodsNum;

    /** 单价 */
    @Excel(name = "单价")
    private Double goodsPrice;

    /** 盈亏金额 */
    @Excel(name = "盈亏金额")
    private Double goodsLossMoney;

    /** 盈亏数量 */
    @Excel(name = "盈亏数量")
    private Double goodsLossNum;

    /** 账面数量 */
    @Excel(name = "账面数量")
    private Double goodsPriceNum;

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
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsGg(String goodsGg) 
    {
        this.goodsGg = goodsGg;
    }

    public String getGoodsGg() 
    {
        return goodsGg;
    }
    public void setGoodsDw(String goodsDw) 
    {
        this.goodsDw = goodsDw;
    }

    public String getGoodsDw() 
    {
        return goodsDw;
    }
    public void setGoodsNum(Double goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public Double getGoodsNum() 
    {
        return goodsNum;
    }
    public void setGoodsPrice(Double goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setGoodsLossMoney(Double goodsLossMoney) 
    {
        this.goodsLossMoney = goodsLossMoney;
    }

    public Double getGoodsLossMoney() 
    {
        return goodsLossMoney;
    }
    public void setGoodsLossNum(Double goodsLossNum) 
    {
        this.goodsLossNum = goodsLossNum;
    }

    public Double getGoodsLossNum() 
    {
        return goodsLossNum;
    }
    public void setGoodsPriceNum(Double goodsPriceNum) 
    {
        this.goodsPriceNum = goodsPriceNum;
    }

    public Double getGoodsPriceNum() 
    {
        return goodsPriceNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsGg", getGoodsGg())
            .append("goodsDw", getGoodsDw())
            .append("goodsNum", getGoodsNum())
            .append("goodsPrice", getGoodsPrice())
            .append("goodsLossMoney", getGoodsLossMoney())
            .append("goodsLossNum", getGoodsLossNum())
            .append("goodsPriceNum", getGoodsPriceNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
