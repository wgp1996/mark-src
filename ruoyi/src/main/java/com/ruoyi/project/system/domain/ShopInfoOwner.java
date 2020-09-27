package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 门店管理对象 shop_info
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public class ShopInfoOwner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 门店ID */
    private String id;

    /** 门店编号 */
    @Excel(name = "门店编号")
    private String storeid;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String shopName;

    /** 门店地址 */
    @Excel(name = "门店地址")
    private String shopAddress;

    /** 门店状态 */
    @Excel(name = "门店状态")
    private Integer shopStatus;

    /** 门店面积 */
    @Excel(name = "门店面积")
    private String shopArea;

    /** 人数 */
    @Excel(name = "人数")
    private String personNum;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String personPhone;
    /** 联系电话 */
    @Excel(name = "关联帐号")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setStoreid(String storeid) 
    {
        this.storeid = storeid;
    }

    public String getStoreid() 
    {
        return storeid;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setShopAddress(String shopAddress) 
    {
        this.shopAddress = shopAddress;
    }

    public String getShopAddress() 
    {
        return shopAddress;
    }
    public void setShopStatus(Integer shopStatus) 
    {
        this.shopStatus = shopStatus;
    }

    public Integer getShopStatus() 
    {
        return shopStatus;
    }
    public void setShopArea(String shopArea) 
    {
        this.shopArea = shopArea;
    }

    public String getShopArea() 
    {
        return shopArea;
    }
    public void setPersonNum(String personNum) 
    {
        this.personNum = personNum;
    }

    public String getPersonNum() 
    {
        return personNum;
    }
    public void setPersonPhone(String personPhone) 
    {
        this.personPhone = personPhone;
    }

    public String getPersonPhone() 
    {
        return personPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeid", getStoreid())
            .append("shopName", getShopName())
            .append("shopAddress", getShopAddress())
            .append("shopStatus", getShopStatus())
            .append("shopArea", getShopArea())
            .append("personNum", getPersonNum())
            .append("personPhone", getPersonPhone())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("userName", getUserName())
            .toString();
    }
}
