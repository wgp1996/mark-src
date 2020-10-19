package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 检测申请单子表对象 test_application_form_child
 * 
 * @author ruoyi
 * @date 2020-09-30
 */
public class TestApplicationFormChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** AI */
    @Excel(name = "AI")
    private Float ai;

    /** AF */
    @Excel(name = "AF")
    private Float af;

    /** AF_AI */
    @Excel(name = "AF_AI")
    private Float afAi;

    /** 抑制率 */
    @Excel(name = "抑制率")
    private Float inhibitionNum;

    /** 合格状态 */
    @Excel(name = "合格状态")
    private Integer checkResult;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 业户代码 */
    @Excel(name = "业户代码")
    private String ownerCode;

    /** 业户名称 */
    @Excel(name = "业户名称")
    private String ownerName;

    /** 检测项目 */
    @Excel(name = "检测项目")
    private String checkProject;

    /** 抽样日期 */
    @Excel(name = "抽样日期")
    private String sampTime;

    /** 检验结果 */
    @Excel(name = "检验结果")
    private String testResult;

    private String checkResultName;

    public String getCheckResultName() {
        return checkResultName;
    }

    public void setCheckResultName(String checkResultName) {
        this.checkResultName = checkResultName;
    }

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }

    public Float getAi() {
        return ai;
    }

    public void setAi(Float ai) {
        this.ai = ai;
    }

    public Float getAf() {
        return af;
    }

    public void setAf(Float af) {
        this.af = af;
    }

    public Float getAfAi() {
        return afAi;
    }

    public void setAfAi(Float afAi) {
        this.afAi = afAi;
    }

    public Float getInhibitionNum() {
        return inhibitionNum;
    }

    public void setInhibitionNum(Float inhibitionNum) {
        this.inhibitionNum = inhibitionNum;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public void setDjNumber(String djNumber)
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
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
    public void setCheckProject(String checkProject) 
    {
        this.checkProject = checkProject;
    }

    public String getCheckProject() 
    {
        return checkProject;
    }
    public void setSampTime(String sampTime) 
    {
        this.sampTime = sampTime;
    }

    public String getSampTime() 
    {
        return sampTime;
    }
    public void setTestResult(String testResult) 
    {
        this.testResult = testResult;
    }

    public String getTestResult() 
    {
        return testResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("ai", getAi())
            .append("af", getAf())
            .append("afAi", getAfAi())
            .append("inhibitionNum", getInhibitionNum())
            .append("checkResult", getCheckResult())
            .append("djNumber", getDjNumber())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("ownerCode", getOwnerCode())
            .append("ownerName", getOwnerName())
            .append("checkProject", getCheckProject())
            .append("sampTime", getSampTime())
            .append("testResult", getTestResult())
            .toString();
    }
}
