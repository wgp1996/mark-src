package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 商品建档对象 goods_info
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public class GoodsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品规格 */
    @Excel(name = "商品规格")
    private String goodsGg;

    /** 商品单位 */
    @Excel(name = "商品单位")
    private String goodsDw;
    /** 商品产地 */
    @Excel(name = "商品产地")
    private String goodsAddress;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String goodsImg;

    /** 二维码地址 */
    @Excel(name = "二维码地址")
    private String goodsCodeImg;

    /** 档案类型 0业户 1市场 */
    @Excel(name = "档案类型 0业户 1市场")
    private Integer type;

    /** 商品分类 */
    @Excel(name = "商品分类")
    private Integer goodsType;

    private String goodsTypeName;

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
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
    public void setGoodsImg(String goodsImg) 
    {
        this.goodsImg = goodsImg;
    }

    public String getGoodsImg() 
    {
        return goodsImg;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setGoodsType(Integer goodsType)
    {
        this.goodsType = goodsType;
    }

    public Integer getGoodsType()
    {
        return goodsType;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public String getGoodsCodeImg() {
        return goodsCodeImg;
    }

    public void setGoodsCodeImg(String goodsCodeImg) {
        this.goodsCodeImg = goodsCodeImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsGg", getGoodsGg())
            .append("goodsDw", getGoodsDw())
            .append("goodsImg", getGoodsImg())
            .append("goodsCodeImg", getGoodsCodeImg())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("type", getType())
            .append("goodsType", getGoodsType())
             .append("goodsAddress", getGoodsAddress())
            .toString();
    }
}
