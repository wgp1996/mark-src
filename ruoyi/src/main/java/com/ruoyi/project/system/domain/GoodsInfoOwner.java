package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 业户商品建档对象 goods_info_owner
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
public class GoodsInfoOwner extends BaseEntity
{
    private static final Long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;
    /** 商品内部编码 */
    @Excel(name = "商品内部编码")
    private String goodsNbCode;
    /** 商品市平台编码 */
    @Excel(name = "商品市平台编码")
    private String goodsSptCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 副单位 */
    @Excel(name = "副单位")
    private String goodsViceDw;

    /** 主单位 */
    @Excel(name = "主单位")
    private String goodsDw;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String goodsImg;

    /** 图片地址 */
    @Excel(name = "图片编码地址")
    private String goodsCodeImg;

    /** 商品分类 */
    @Excel(name = "商品分类")
    private Integer goodsType;

    /** 主进货地 */
    @Excel(name = "主进货地")
    private String goodsAddress;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 是否推送磅房 */
    @Excel(name = "是否推送磅房")
    private Integer isSend;

    private String createName;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getGoodsNbCode() {
        return goodsNbCode;
    }

    public void setGoodsNbCode(String goodsNbCode) {
        this.goodsNbCode = goodsNbCode;
    }

    public String getGoodsSptCode() {
        return goodsSptCode;
    }

    public void setGoodsSptCode(String goodsSptCode) {
        this.goodsSptCode = goodsSptCode;
    }

    public String getGoodsCodeImg() {
        return goodsCodeImg;
    }

    public void setGoodsCodeImg(String goodsCodeImg) {
        this.goodsCodeImg = goodsCodeImg;
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
    public void setGoodsViceDw(String goodsViceDw) 
    {
        this.goodsViceDw = goodsViceDw;
    }

    public String getGoodsViceDw() 
    {
        return goodsViceDw;
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
    public void setGoodsType(Integer goodsType) 
    {
        this.goodsType = goodsType;
    }

    public Integer getGoodsType() 
    {
        return goodsType;
    }
    public void setGoodsAddress(String goodsAddress) 
    {
        this.goodsAddress = goodsAddress;
    }

    public String getGoodsAddress() 
    {
        return goodsAddress;
    }
    public void setGoodsGg(String goodsGg) 
    {
        this.goodsGg = goodsGg;
    }

    public String getGoodsGg() 
    {
        return goodsGg;
    }
    public void setIsSend(Integer isSend) 
    {
        this.isSend = isSend;
    }

    public Integer getIsSend() 
    {
        return isSend;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsViceDw", getGoodsViceDw())
            .append("goodsDw", getGoodsDw())
            .append("goodsImg", getGoodsImg())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("goodsType", getGoodsType())
            .append("goodsAddress", getGoodsAddress())
            .append("goodsGg", getGoodsGg())
            .append("isSend", getIsSend())
            .toString();
    }
}
