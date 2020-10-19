package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 mark_info
 * 
 * @author ruoyi
 * @date 2020-07-28
 */
public class MarkInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 市场名称 */
    @Excel(name = "市场名称")
    private String markName;

    /** 运营主体名称 */
    @Excel(name = "运营主体名称")
    private String perationName;

    /** 主体性质 */
    @Excel(name = "主体性质")
    private String perationNature;

    /** 社会信用代码 */
    @Excel(name = "社会信用代码")
    private String socialCreditCode;

    /** 城市 */
    @Excel(name = "城市")
    private String markAddressCity;

    /** 省份 */
    @Excel(name = "省份")
    private String markAddressProvince;

    /** 区域 */
    @Excel(name = "区域")
    private String markAddressArea;

    /** 详情 */
    @Excel(name = "详情")
    private String markAddressDetail;

    /** 开办日期 */
    @Excel(name = "开办日期")
    private String markCreateTime;

    /** 现有商户数量 */
    @Excel(name = "现有商户数量")
    private String markMerchantsCount;

    /** 说明 */
    @Excel(name = "说明")
    private String markNote;

    /** $column.columnComment */
    @Excel(name = "说明")
    private String createUser;

    /** 市场编码 */
    @Excel(name = "市场编码")
    private String markCode;

    private String addressLat;

    private String addressLng;

    private String fileName;

    public String getAddressLat() {
        return addressLat;
    }

    public void setAddressLat(String addressLat) {
        this.addressLat = addressLat;
    }

    public String getAddressLng() {
        return addressLng;
    }

    public void setAddressLng(String addressLng) {
        this.addressLng = addressLng;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMarkName(String markName) 
    {
        this.markName = markName;
    }

    public String getMarkName() 
    {
        return markName;
    }
    public void setPerationName(String perationName) 
    {
        this.perationName = perationName;
    }

    public String getPerationName() 
    {
        return perationName;
    }
    public void setPerationNature(String perationNature) 
    {
        this.perationNature = perationNature;
    }

    public String getPerationNature() 
    {
        return perationNature;
    }
    public void setSocialCreditCode(String socialCreditCode) 
    {
        this.socialCreditCode = socialCreditCode;
    }

    public String getSocialCreditCode() 
    {
        return socialCreditCode;
    }
    public void setMarkAddressCity(String markAddressCity) 
    {
        this.markAddressCity = markAddressCity;
    }

    public String getMarkAddressCity() 
    {
        return markAddressCity;
    }
    public void setMarkAddressProvince(String markAddressProvince) 
    {
        this.markAddressProvince = markAddressProvince;
    }

    public String getMarkAddressProvince() 
    {
        return markAddressProvince;
    }
    public void setMarkAddressArea(String markAddressArea) 
    {
        this.markAddressArea = markAddressArea;
    }

    public String getMarkAddressArea() 
    {
        return markAddressArea;
    }
    public void setMarkAddressDetail(String markAddressDetail) 
    {
        this.markAddressDetail = markAddressDetail;
    }

    public String getMarkAddressDetail() 
    {
        return markAddressDetail;
    }
    public void setMarkCreateTime(String markCreateTime) 
    {
        this.markCreateTime = markCreateTime;
    }

    public String getMarkCreateTime() 
    {
        return markCreateTime;
    }
    public void setMarkMerchantsCount(String markMerchantsCount) 
    {
        this.markMerchantsCount = markMerchantsCount;
    }

    public String getMarkMerchantsCount() 
    {
        return markMerchantsCount;
    }
    public void setMarkNote(String markNote) 
    {
        this.markNote = markNote;
    }

    public String getMarkNote() 
    {
        return markNote;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setMarkCode(String markCode) 
    {
        this.markCode = markCode;
    }

    public String getMarkCode() 
    {
        return markCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("markName", getMarkName())
            .append("perationName", getPerationName())
            .append("perationNature", getPerationNature())
            .append("socialCreditCode", getSocialCreditCode())
            .append("markAddressCity", getMarkAddressCity())
            .append("markAddressProvince", getMarkAddressProvince())
            .append("markAddressArea", getMarkAddressArea())
            .append("markAddressDetail", getMarkAddressDetail())
            .append("markCreateTime", getMarkCreateTime())
            .append("markMerchantsCount", getMarkMerchantsCount())
            .append("markNote", getMarkNote())
            .append("createUser", getCreateUser())
            .append("markCode", getMarkCode())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
