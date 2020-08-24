package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 批发销货一票通对象 whole_sales
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public class WholeSales extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 单据编号 */
    @Excel(name = "单据编号")
    private String goodsCode;

    /** 销售类型 */
    @Excel(name = "销售类型")
    private Long wholeType;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private Integer status;

    /** 来源方式 */
    @Excel(name = "来源方式")
    private Integer from;

    private String rows;

    private String djStatusName;

     /*
       子表
    */
    private List<WholeSalesChild> childrenList;

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public List<WholeSalesChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<WholeSalesChild> childrenList) {
        this.childrenList = childrenList;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
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
    public void setWholeType(Long wholeType) 
    {
        this.wholeType = wholeType;
    }

    public Long getWholeType() 
    {
        return wholeType;
    }
    public void setDjNumber(String djNumber) 
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setFrom(Integer from) 
    {
        this.from = from;
    }

    public Integer getFrom() 
    {
        return from;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsCode", getGoodsCode())
            .append("wholeType", getWholeType())
            .append("djNumber", getDjNumber())
            .append("djTime", getDjTime())
            .append("goodsName", getGoodsName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("from", getFrom())
            .toString();
    }
}
