package com.ruoyi.project.info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 出库单明细对象 lkck_info_child
 * 
 * @author ruoyi
 * @date 2020-10-12
 */
public class LkckInfoChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    
    private String storeNum;

    /** $column.columnComment */
    
    private String djNumber;

    /** $column.columnComment */
    
    private String goodsCode;

    /** $column.columnComment */
    
    private String goodsName;

    /** $column.columnComment */
    
    private String goodsGg;

    /** $column.columnComment */
    
    private String goodsDw;

    /** $column.columnComment */
    
    private Double goodsPrice;

    /** $column.columnComment */
    
    private Double goodsNum;

    /** $column.columnComment */
    
    private Double goodsMoney;

    /** $column.columnComment */
    
    private String goodsBz;

    /** $column.columnComment */
    
    private String rkPc;

    private String rkMxid;

    public String getRkMxid() {
        return rkMxid;
    }

    public void setRkMxid(String rkMxid) {
        this.rkMxid = rkMxid;
    }

    /** 冷库名称 */
    @Excel(name = "冷库名称")
    private String storeName;

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
    public void setGoodsBz(String goodsBz) 
    {
        this.goodsBz = goodsBz;
    }

    public String getGoodsBz() 
    {
        return goodsBz;
    }
    public void setRkPc(String rkPc) 
    {
        this.rkPc = rkPc;
    }

    public String getRkPc() 
    {
        return rkPc;
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
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsGg", getGoodsGg())
            .append("goodsDw", getGoodsDw())
            .append("goodsPrice", getGoodsPrice())
            .append("goodsNum", getGoodsNum())
            .append("goodsMoney", getGoodsMoney())
            .append("goodsBz", getGoodsBz())
            .append("rkPc", getRkPc())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("storeName", getStoreName())
            .toString();
    }
}
