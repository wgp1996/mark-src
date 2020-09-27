package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 业户信息对象 owner_info
 *
 * @author ruoyi
 * @date 2020-07-29
 */
public class OwnerInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 业主编号
     */
    @Excel(name = "业主编号")
    private String ownerCode;

    /**
     * 业主名称
     */
    @Excel(name = "业主名称")
    private String ownerName;

    /**
     * 组织类型
     */
    @Excel(name = "组织类型")
    private String ownerOrg;

    /**
     * 机构性质
     */
    @Excel(name = "机构性质")
    private String ownerOrgNature;

    /**
     * 信用代码/身份证号
     */
    @Excel(name = "信用代码/身份证号")
    private String ownerPersonId;

    /**
     * 经营方式
     */
    @Excel(name = "经营方式")
    private String ownerMangerType;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String ownerLxr;

    /**
     * 电话
     */
    @Excel(name = "电话")
    private String ownerLxrPhone;

    /**
     * 微信
     */
    @Excel(name = "微信")
    private String ownerLxrWx;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String ownerNote;

    /**
     * 关联帐号
     */
    @Excel(name = "关联帐号")
    private String userName;

    /**
     * $column.columnComment
     */
    @Excel(name = "备注")
    private String createUser;

    /**
     * 图片1
     */
    @Excel(name = "图片1")
    private String fileName1;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String fileTitle1;

    /**
     * 图片2
     */
    @Excel(name = "图片2")
    private String fileName2;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String fileTitle2;

    private String markType;

    @Excel(name = "所属市场")
    private String markTypeName;

    private String stallName;
    private String stallCode;

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getStallCode() {
        return stallCode;
    }

    public void setStallCode(String stallCode) {
        this.stallCode = stallCode;
    }

    public String getMarkType() {
        return markType;
    }

    public void setMarkType(String markType) {
        this.markType = markType;
    }

    public String getMarkTypeName() {
        return markTypeName;
    }

    public void setMarkTypeName(String markTypeName) {
        this.markTypeName = markTypeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerOrg(String ownerOrg) {
        this.ownerOrg = ownerOrg;
    }

    public String getOwnerOrg() {
        return ownerOrg;
    }

    public void setOwnerOrgNature(String ownerOrgNature) {
        this.ownerOrgNature = ownerOrgNature;
    }

    public String getOwnerOrgNature() {
        return ownerOrgNature;
    }

    public void setOwnerPersonId(String ownerPersonId) {
        this.ownerPersonId = ownerPersonId;
    }

    public String getOwnerPersonId() {
        return ownerPersonId;
    }

    public void setOwnerMangerType(String ownerMangerType) {
        this.ownerMangerType = ownerMangerType;
    }

    public String getOwnerMangerType() {
        return ownerMangerType;
    }

    public void setOwnerLxr(String ownerLxr) {
        this.ownerLxr = ownerLxr;
    }

    public String getOwnerLxr() {
        return ownerLxr;
    }

    public void setOwnerLxrPhone(String ownerLxrPhone) {
        this.ownerLxrPhone = ownerLxrPhone;
    }

    public String getOwnerLxrPhone() {
        return ownerLxrPhone;
    }

    public void setOwnerLxrWx(String ownerLxrWx) {
        this.ownerLxrWx = ownerLxrWx;
    }

    public String getOwnerLxrWx() {
        return ownerLxrWx;
    }

    public void setOwnerNote(String ownerNote) {
        this.ownerNote = ownerNote;
    }

    public String getOwnerNote() {
        return ownerNote;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setFileName1(String fileName1) {
        this.fileName1 = fileName1;
    }

    public String getFileName1() {
        return fileName1;
    }

    public void setFileTitle1(String fileTitle1) {
        this.fileTitle1 = fileTitle1;
    }

    public String getFileTitle1() {
        return fileTitle1;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

    public String getFileName2() {
        return fileName2;
    }

    public void setFileTitle2(String fileTitle2) {
        this.fileTitle2 = fileTitle2;
    }

    public String getFileTitle2() {
        return fileTitle2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("ownerCode", getOwnerCode())
                .append("ownerName", getOwnerName())
                .append("ownerOrg", getOwnerOrg())
                .append("ownerOrgNature", getOwnerOrgNature())
                .append("ownerPersonId", getOwnerPersonId())
                .append("ownerMangerType", getOwnerMangerType())
                .append("ownerLxr", getOwnerLxr())
                .append("ownerLxrPhone", getOwnerLxrPhone())
                .append("ownerLxrWx", getOwnerLxrWx())
                .append("ownerNote", getOwnerNote())
                .append("createUser", getCreateUser())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("fileName1", getFileName1())
                .append("fileTitle1", getFileTitle1())
                .append("fileName2", getFileName2())
                .append("fileTitle2", getFileTitle2())
                .toString();
    }
}
