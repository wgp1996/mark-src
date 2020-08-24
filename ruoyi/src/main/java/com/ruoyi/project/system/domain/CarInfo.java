package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 车辆档案对象 car_info
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
public class CarInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNumber;

    /** 载重 */
    @Excel(name = "载重")
    private String carWeight;

    /** 体积 */
    @Excel(name = "体积")
    private String carVolume;

    /** 发动机号 */
    @Excel(name = "发动机号")
    private String carCode;

    /** 联系人 */
    @Excel(name = "联系人")
    private String carOwner;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String carOwnerPhone;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCarNumber(String carNumber) 
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber() 
    {
        return carNumber;
    }
    public void setCarWeight(String carWeight) 
    {
        this.carWeight = carWeight;
    }

    public String getCarWeight() 
    {
        return carWeight;
    }
    public void setCarVolume(String carVolume) 
    {
        this.carVolume = carVolume;
    }

    public String getCarVolume() 
    {
        return carVolume;
    }
    public void setCarCode(String carCode) 
    {
        this.carCode = carCode;
    }

    public String getCarCode() 
    {
        return carCode;
    }
    public void setCarOwner(String carOwner) 
    {
        this.carOwner = carOwner;
    }

    public String getCarOwner() 
    {
        return carOwner;
    }
    public void setCarOwnerPhone(String carOwnerPhone) 
    {
        this.carOwnerPhone = carOwnerPhone;
    }

    public String getCarOwnerPhone() 
    {
        return carOwnerPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carNumber", getCarNumber())
            .append("carWeight", getCarWeight())
            .append("carVolume", getCarVolume())
            .append("carCode", getCarCode())
            .append("carOwner", getCarOwner())
            .append("carOwnerPhone", getCarOwnerPhone())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
