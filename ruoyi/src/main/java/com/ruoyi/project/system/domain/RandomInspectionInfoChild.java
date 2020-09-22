package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 检测单明细对象 random_inspection_info_child
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public class RandomInspectionInfoChild extends BaseEntity
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

    /** AI值 */
    @Excel(name = "AI值")
    private Float ai;

    /** AF值 */
    @Excel(name = "AF值")
    private Float af;

    /** AF_AI值 */
    @Excel(name = "AF_AI值")
    private Float afAi;

    /** 抑制率 */
    @Excel(name = "抑制率")
    private Float inhibitionNum;

    /** 结论 */
    @Excel(name = "结论")
    private Integer checkResult;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;
    /** 结论 */
    @Excel(name = "结论")
    private String checkResultName;

    public String getCheckResultName() {
        return checkResultName;
    }

    public void setCheckResultName(String checkResultName) {
        this.checkResultName = checkResultName;
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
    public void setAi(Float ai) 
    {
        this.ai = ai;
    }

    public Float getAi() 
    {
        return ai;
    }
    public void setAf(Float af) 
    {
        this.af = af;
    }

    public Float getAf() 
    {
        return af;
    }
    public void setAfAi(Float afAi) 
    {
        this.afAi = afAi;
    }

    public Float getAfAi() 
    {
        return afAi;
    }
    public void setInhibitionNum(Float inhibitionNum) 
    {
        this.inhibitionNum = inhibitionNum;
    }

    public Float getInhibitionNum() 
    {
        return inhibitionNum;
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
            .toString();
    }
}
