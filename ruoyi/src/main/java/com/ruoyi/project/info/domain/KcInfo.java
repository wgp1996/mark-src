package com.ruoyi.project.info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 收发存查询对象 kc_info
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
public class KcInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 冷库编码 */
    @Excel(name = "冷库编码")
    private String storeNum;

    /** 单据号 */
    @Excel(name = "单据号")
    private String djNumber;

    /** 流水号 */
    @Excel(name = "流水号")
    private String lsNumber;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 单位 */
    @Excel(name = "单位")
    private String goodsDw;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 数量 */
    @Excel(name = "数量")
    private Double goodsNum;

    /** 单价 */
    @Excel(name = "单价")
    private Double goodsPrice;

    /** 金额 */
    @Excel(name = "金额")
    private Double goodsMoney;

    /** 入库/出库时间 */
    @Excel(name = "入库/出库时间")
    private String djTime;


    /** 0 入库  1 出库 */
    @Excel(name = "0 入库  1 出库")
    private String djType;

    /** 冷库名称 */
    @Excel(name = "冷库名称")
    private String storeName;
    private String goodsMoneys;
    private String cgoodsMoneys;
    private String goodsNums;
    private String cgoodsNums;

    public String getGoodsMoneys() {
        return goodsMoneys;
    }

    public void setGoodsMoneys(String goodsMoneys) {
        this.goodsMoneys = goodsMoneys;
    }

    public String getCgoodsMoneys() {
        return cgoodsMoneys;
    }

    public void setCgoodsMoneys(String cgoodsMoneys) {
        this.cgoodsMoneys = cgoodsMoneys;
    }

    public String getGoodsNums() {
        return goodsNums;
    }

    public void setGoodsNums(String goodsNums) {
        this.goodsNums = goodsNums;
    }

    public String getCgoodsNums() {
        return cgoodsNums;
    }

    public void setCgoodsNums(String cgoodsNums) {
        this.cgoodsNums = cgoodsNums;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setStoreNum(String storeNum) 
    {
        this.storeNum = storeNum;
    }

    public String getStoreNum() 
    {
        return storeNum;
    }
    public void setDjNumber(String djNumber) 
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
    }
    public void setLsNumber(String lsNumber) 
    {
        this.lsNumber = lsNumber;
    }

    public String getLsNumber() 
    {
        return lsNumber;
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
    public void setGoodsDw(String goodsDw) 
    {
        this.goodsDw = goodsDw;
    }

    public String getGoodsDw() 
    {
        return goodsDw;
    }
    public void setGoodsGg(String goodsGg) 
    {
        this.goodsGg = goodsGg;
    }

    public String getGoodsGg() 
    {
        return goodsGg;
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
    public void setGoodsMoney(Double goodsMoney) 
    {
        this.goodsMoney = goodsMoney;
    }

    public Double getGoodsMoney() 
    {
        return goodsMoney;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setDjType(String djType) 
    {
        this.djType = djType;
    }

    public String getDjType() 
    {
        return djType;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeNum", getStoreNum())
            .append("djNumber", getDjNumber())
            .append("lsNumber", getLsNumber())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsDw", getGoodsDw())
            .append("goodsGg", getGoodsGg())
            .append("goodsNum", getGoodsNum())
            .append("goodsPrice", getGoodsPrice())
            .append("goodsMoney", getGoodsMoney())
            .append("djTime", getDjTime())
            .append("djType", getDjType())
            .append("storeName", getStoreName())
            .toString();
    }
}
