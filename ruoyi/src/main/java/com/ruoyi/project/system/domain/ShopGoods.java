package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 门店商品管理对象 shop_goods
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public class ShopGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 门店编码 */
    @Excel(name = "门店编码")
    private String storeid;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 标签Mac地址 */
    @Excel(name = "标签Mac地址")
    private String labelMac;

    /** 价格 */
    @Excel(name = "价格")
    private String goodsPrice;

    /** id */
    private String id;

    /** 会员价 */
    @Excel(name = "会员价")
    private String goodsHyPrice;

    private String goodsName;
    private String goodsDw;
    private String goodsAddress;

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

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public void setStoreid(String storeid)
    {
        this.storeid = storeid;
    }

    public String getStoreid() 
    {
        return storeid;
    }
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setLabelMac(String labelMac) 
    {
        this.labelMac = labelMac;
    }

    public String getLabelMac() 
    {
        return labelMac;
    }
    public void setGoodsPrice(String goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setGoodsHyPrice(String goodsHyPrice) 
    {
        this.goodsHyPrice = goodsHyPrice;
    }

    public String getGoodsHyPrice() 
    {
        return goodsHyPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("storeid", getStoreid())
            .append("goodsCode", getGoodsCode())
            .append("labelMac", getLabelMac())
            .append("goodsPrice", getGoodsPrice())
            .append("id", getId())
            .append("goodsHyPrice", getGoodsHyPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
