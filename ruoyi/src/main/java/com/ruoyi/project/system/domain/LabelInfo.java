package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 电子价签管理对象 label_info
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public class LabelInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 标签Mac地址 */
    @Excel(name = "标签Mac地址")
    private String mac;

    /** 秘钥固定 */
    @Excel(name = "秘钥固定")
    private String activekey;

    /** 门店编号 */
    @Excel(name = "门店编号")
    private String storeid;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 是否绑定网关 */
    @Excel(name = "是否绑定网关")
    private Integer labelStatus;

    /** 网关MAC地址 */
    @Excel(name = "网关MAC地址")
    private String gatewayMac;

    /** 尺寸 */
    @Excel(name = "尺寸")
    private String inch;

    /** 电量 */
    @Excel(name = "电量")
    private Integer battery;

    /** 模板id */
    @Excel(name = "模板id")
    private String demoid;

    /** 是否在线 */
    @Excel(name = "是否在线")
    private Integer status;

    /** 温度信息 */
    @Excel(name = "温度信息")
    private String temperature;
    /** 价签编码 */
    @Excel(name = "价签编码")
    private String labelCode;
    /** 货位 */
    @Excel(name = "货位")
    private String storeCode;

    private String demoName;

    private String goodsName;

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMac(String mac) 
    {
        this.mac = mac;
    }

    public String getMac() 
    {
        return mac;
    }
    public void setActivekey(String activekey) 
    {
        this.activekey = activekey;
    }

    public String getActivekey() 
    {
        return activekey;
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
    public void setLabelStatus(Integer labelStatus) 
    {
        this.labelStatus = labelStatus;
    }

    public Integer getLabelStatus() 
    {
        return labelStatus;
    }
    public void setGatewayMac(String gatewayMac) 
    {
        this.gatewayMac = gatewayMac;
    }

    public String getGatewayMac() 
    {
        return gatewayMac;
    }
    public void setInch(String inch)
    {
        this.inch = inch;
    }

    public String getInch()
    {
        return inch;
    }
    public void setBattery(Integer battery) 
    {
        this.battery = battery;
    }

    public Integer getBattery() 
    {
        return battery;
    }
    public void setDemoid(String demoid) 
    {
        this.demoid = demoid;
    }

    public String getDemoid() 
    {
        return demoid;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setTemperature(String temperature) 
    {
        this.temperature = temperature;
    }

    public String getTemperature() 
    {
        return temperature;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mac", getMac())
            .append("activekey", getActivekey())
            .append("storeid", getStoreid())
            .append("goodsCode", getGoodsCode())
            .append("labelStatus", getLabelStatus())
            .append("gatewayMac", getGatewayMac())
            .append("inch", getInch())
            .append("battery", getBattery())
            .append("demoid", getDemoid())
            .append("status", getStatus())
            .append("temperature", getTemperature())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
