package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 检测地点建档对象 check_address
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public class CheckAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 检测地点编码 */
    @Excel(name = "检测地点编码")
    private String checkAddressId;

    /** 地点名称 */
    @Excel(name = "地点名称")
    private String checkAddress;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String checkAddressDetail;

    /** 上班时间 */
    @Excel(name = "上班时间")
    private String workStartXtime;

    /** 下班时间 */
    @Excel(name = "下班时间")
    private String workEndXtime;

    /** 检测室 */
    @Excel(name = "检测室")
    private String checkHome;

    /** 休息日 */
    @Excel(name = "休息日")
    private String workDay;

    /** 上班时间 */
    @Excel(name = "上班时间")
    private String workStartTime;

    /** 下班时间 */
    @Excel(name = "下班时间")
    private String workEndTime;

    /** 备注 */
    @Excel(name = "备注")
    private String checkBz;

    /** 经度 */
    @Excel(name = "经度")
    private String checkAddressLat;

    /** 纬度 */
    @Excel(name = "纬度")
    private String checkAddressLng;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String workTel;

    public String getWorkStartXtime() {
        return workStartXtime;
    }

    public void setWorkStartXtime(String workStartXtime) {
        this.workStartXtime = workStartXtime;
    }

    public String getWorkEndXtime() {
        return workEndXtime;
    }

    public void setWorkEndXtime(String workEndXtime) {
        this.workEndXtime = workEndXtime;
    }

    public String getCheckHome() {
        return checkHome;
    }

    public void setCheckHome(String checkHome) {
        this.checkHome = checkHome;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCheckAddressId(String checkAddressId) 
    {
        this.checkAddressId = checkAddressId;
    }

    public String getCheckAddressId() 
    {
        return checkAddressId;
    }
    public void setCheckAddress(String checkAddress) 
    {
        this.checkAddress = checkAddress;
    }

    public String getCheckAddress() 
    {
        return checkAddress;
    }
    public void setCheckAddressDetail(String checkAddressDetail) 
    {
        this.checkAddressDetail = checkAddressDetail;
    }

    public String getCheckAddressDetail() 
    {
        return checkAddressDetail;
    }
    public void setWorkStartTime(String workStartTime) 
    {
        this.workStartTime = workStartTime;
    }

    public String getWorkStartTime() 
    {
        return workStartTime;
    }
    public void setWorkEndTime(String workEndTime) 
    {
        this.workEndTime = workEndTime;
    }

    public String getWorkEndTime() 
    {
        return workEndTime;
    }
    public void setCheckBz(String checkBz) 
    {
        this.checkBz = checkBz;
    }

    public String getCheckBz() 
    {
        return checkBz;
    }
    public void setCheckAddressLat(String checkAddressLat) 
    {
        this.checkAddressLat = checkAddressLat;
    }

    public String getCheckAddressLat() 
    {
        return checkAddressLat;
    }
    public void setCheckAddressLng(String checkAddressLng) 
    {
        this.checkAddressLng = checkAddressLng;
    }

    public String getCheckAddressLng() 
    {
        return checkAddressLng;
    }
    public void setWorkTel(String workTel) 
    {
        this.workTel = workTel;
    }

    public String getWorkTel() 
    {
        return workTel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkAddressId", getCheckAddressId())
            .append("checkAddress", getCheckAddress())
            .append("checkAddressDetail", getCheckAddressDetail())
            .append("workStartTime", getWorkStartTime())
            .append("workEndTime", getWorkEndTime())
            .append("checkBz", getCheckBz())
            .append("createTime", getCreateTime())
            .append("checkAddressLat", getCheckAddressLat())
            .append("checkAddressLng", getCheckAddressLng())
            .append("workTel", getWorkTel())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
