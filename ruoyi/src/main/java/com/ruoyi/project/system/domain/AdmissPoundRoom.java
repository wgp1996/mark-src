package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 磅房入场单对象 admiss_pound_room
 * 
 * @author ruoyi
 * @date 2020-08-20
 */
public class AdmissPoundRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNumber;

    /** 业主编号 */
    @Excel(name = "业主编号")
    private String ownerCode;

    /** 业主名称 */
    @Excel(name = "业主名称")
    private String ownerName;

    /** 产地 */
    @Excel(name = "产地")
    private String roomAddress;

    /** 数量 */
    @Excel(name = "数量")
    private String roomNum;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** $column.columnComment */
    @Excel(name = "单号")
    private Integer status;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private String djStatusName;

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    /*
                      子表
                    */
    private List<AdmissPoundRoomChild> childrenList;
    private String rows;

    public List<AdmissPoundRoomChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<AdmissPoundRoomChild> childrenList) {
        this.childrenList = childrenList;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
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
    public void setOwnerCode(String ownerCode) 
    {
        this.ownerCode = ownerCode;
    }

    public String getOwnerCode() 
    {
        return ownerCode;
    }
    public void setOwnerName(String ownerName) 
    {
        this.ownerName = ownerName;
    }

    public String getOwnerName() 
    {
        return ownerName;
    }
    public void setRoomAddress(String roomAddress) 
    {
        this.roomAddress = roomAddress;
    }

    public String getRoomAddress() 
    {
        return roomAddress;
    }
    public void setRoomNum(String roomNum) 
    {
        this.roomNum = roomNum;
    }

    public String getRoomNum() 
    {
        return roomNum;
    }
    public void setDjNumber(String djNumber) 
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carNumber", getCarNumber())
            .append("ownerCode", getOwnerCode())
            .append("ownerName", getOwnerName())
            .append("roomAddress", getRoomAddress())
            .append("roomNum", getRoomNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("djNumber", getDjNumber())
            .append("status", getStatus())
            .toString();
    }
}
