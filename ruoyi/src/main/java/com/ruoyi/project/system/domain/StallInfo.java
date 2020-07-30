package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 市场摊位信息对象 stall_info
 *
 * @author ruoyi
 * @date 2020-07-29
 */
public class StallInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 摊位名称
     */
    @Excel(name = "摊位名称")
    private String stallName;

    /**
     * 摊位编码
     */
    @Excel(name = "摊位编码")
    private String stallCode;

    /**
     * 所属市场
     */
    @Excel(name = "所属市场")
    private String markCode;

    /**
     * 区域面积
     */
    @Excel(name = "区域面积")
    private String regionArea;

    /**
     * 摊位状态
     */
    @Excel(name = "摊位状态")
    private String stallStatus;

    /**
     * 开始期限
     */
    @Excel(name = "开始期限")
    private String stallStartTime;

    /**
     * 结束期限
     */
    @Excel(name = "结束期限")
    private String stallEndTime;

    /**
     * 租金(年)
     */
    @Excel(name = "租金(年)")
    private String stallMoney;

    /**
     * 租赁方
     */
    @Excel(name = "租赁方")
    private String stallLeaseholder;

    /**
     * 摊位说明
     */
    @Excel(name = "摊位说明")
    private String stallNote;

    /**
     * $column.columnComment
     */
    @Excel(name = "摊位说明")
    private String createUser;

    @Excel(name = "所属市场")
    private String markChildName;

    public String getMarkChildName() {
        return markChildName;
    }

    public void setMarkChildName(String markChildName) {
        this.markChildName = markChildName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallCode(String stallCode) {
        this.stallCode = stallCode;
    }

    public String getStallCode() {
        return stallCode;
    }

    public void setMarkCode(String markCode) {
        this.markCode = markCode;
    }

    public String getMarkCode() {
        return markCode;
    }

    public void setRegionArea(String regionArea) {
        this.regionArea = regionArea;
    }

    public String getRegionArea() {
        return regionArea;
    }

    public void setStallStatus(String stallStatus) {
        this.stallStatus = stallStatus;
    }

    public String getStallStatus() {
        return stallStatus;
    }

    public void setStallStartTime(String stallStartTime) {
        this.stallStartTime = stallStartTime;
    }

    public String getStallStartTime() {
        return stallStartTime;
    }

    public void setStallEndTime(String stallEndTime) {
        this.stallEndTime = stallEndTime;
    }

    public String getStallEndTime() {
        return stallEndTime;
    }

    public void setStallMoney(String stallMoney) {
        this.stallMoney = stallMoney;
    }

    public String getStallMoney() {
        return stallMoney;
    }

    public void setStallLeaseholder(String stallLeaseholder) {
        this.stallLeaseholder = stallLeaseholder;
    }

    public String getStallLeaseholder() {
        return stallLeaseholder;
    }

    public void setStallNote(String stallNote) {
        this.stallNote = stallNote;
    }

    public String getStallNote() {
        return stallNote;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("stallName", getStallName())
                .append("stallCode", getStallCode())
                .append("markCode", getMarkCode())
                .append("regionArea", getRegionArea())
                .append("stallStatus", getStallStatus())
                .append("stallStartTime", getStallStartTime())
                .append("stallEndTime", getStallEndTime())
                .append("stallMoney", getStallMoney())
                .append("stallLeaseholder", getStallLeaseholder())
                .append("stallNote", getStallNote())
                .append("createUser", getCreateUser())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
