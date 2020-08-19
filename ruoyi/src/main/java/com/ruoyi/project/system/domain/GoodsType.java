package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类对象 goods_type
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public class GoodsType extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品分类id */
    private Integer goodsTypeId;

    /** 商品分类父id */
    @Excel(name = "商品分类父id")
    private Integer goodsTypePid;

    /** 商品分类名称 */
    @Excel(name = "商品分类名称")
    private String goodsTypeName;

    /** 档案类型 */
    @Excel(name = "档案类型")
    private Integer type;

    public void setGoodsTypeId(Integer goodsTypeId)
    {
        this.goodsTypeId = goodsTypeId;
    }

    public Integer getGoodsTypeId()
    {
        return goodsTypeId;
    }
    public void setGoodsTypePid(Integer goodsTypePid)
    {
        this.goodsTypePid = goodsTypePid;
    }

    public Integer getGoodsTypePid()
    {
        return goodsTypePid;
    }
    public void setGoodsTypeName(String goodsTypeName) 
    {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsTypeName() 
    {
        return goodsTypeName;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    /** 子菜单 */
    private List<GoodsType> children = new ArrayList<GoodsType>();

    @Override
    public List<GoodsType> getChildren() {
        return children;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsTypeId", getGoodsTypeId())
            .append("goodsTypePid", getGoodsTypePid())
            .append("goodsTypeName", getGoodsTypeName())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("type", getType())
            .toString();
    }
}
