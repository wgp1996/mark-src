package com.ruoyi.project.info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 入库单子表对象 rk_info_child
 * 
 * @author ruoyi
 * @date 2020-10-10
 */
public class RkInfoChild extends BaseEntity
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

    /** 名称 */
    @Excel(name = "名称")
    private String goodsName;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 单位 */
    @Excel(name = "单位")
    private String goodsDw;

    /** 单价 */
    @Excel(name = "单价")
    private Double goodsPrice;

    /** 数量 */
    @Excel(name = "数量")
    private Double goodsNum;

    /** 金额 */
    @Excel(name = "金额")
    private Double goodsMoney;

    /** 批次号 */
    @Excel(name = "批次号")
    private String lsNumber;

    /** 批次 */
    @Excel(name = "批次")
    private String rkPc;

    /** 产地 */
    @Excel(name = "产地")
    private String goodsAddress;

    private String syNum;

    private String storeNum;
    private String storeName;

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSyNum() {
        return syNum;
    }

    public void setSyNum(String syNum) {
        this.syNum = syNum;
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
    public void setGoodsPrice(Double goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setGoodsNum(Double goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public Double getGoodsNum() 
    {
        return goodsNum;
    }
    public void setGoodsMoney(Double goodsMoney) 
    {
        this.goodsMoney = goodsMoney;
    }

    public Double getGoodsMoney() 
    {
        return goodsMoney;
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
    public void setGoodsAddress(String goodsAddress) 
    {
        this.goodsAddress = goodsAddress;
    }

    public String getGoodsAddress() 
    {
        return goodsAddress;
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
            .append("goodsPrice", getGoodsPrice())
            .append("goodsNum", getGoodsNum())
            .append("goodsMoney", getGoodsMoney())
            .append("lsNumber", getLsNumber())
            .append("rkPc", getRkPc())
            .append("goodsAddress", getGoodsAddress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
