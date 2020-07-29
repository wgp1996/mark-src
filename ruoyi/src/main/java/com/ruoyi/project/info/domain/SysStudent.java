package com.ruoyi.project.info.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 学生信息对象 sys_student
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
@Data
public class SysStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @TableField("student_id")
    private Long studentId;

    /** 学生名称 */
    @Excel(name = "学生名称")
    @TableField("student_name")
    private String studentName;

    /** 年龄 */
    @Excel(name = "年龄")
    @TableField("student_age")
    private Integer studentAge;
    @TableField("student_img")
    private String studentImg;
    @TableField("file_name")
    private String fileName;

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    @TableField("student_sex")
    private String studentSex;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    @TableField("student_status")
    private String studentStatus;

    /** 生日 */
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField("student_birthday")
    private Date studentBirthday;

    private String studentSexName;
//    public String getStudentSexName() {
//        return studentSexName;
//    }
//
//    public void setStudentSexName(String studentSexName) {
//        this.studentSexName = studentSexName;
//    }
//
//    public String getStudentImg() {
//        return studentImg;
//    }
//
//    public void setStudentImg(String studentImg) {
//        this.studentImg = studentImg;
//    }
//
//    public void setStudentId(Long studentId)
//    {
//        this.studentId = studentId;
//    }
//
//    public Long getStudentId()
//    {
//        return studentId;
//    }
//    public void setStudentName(String studentName)
//    {
//        this.studentName = studentName;
//    }
//
//    public String getStudentName()
//    {
//        return studentName;
//    }
//    public void setStudentAge(Integer studentAge)
//    {
//        this.studentAge = studentAge;
//    }
//
//    public Integer getStudentAge()
//    {
//        return studentAge;
//    }
//    public void setStudentSex(String studentSex)
//    {
//        this.studentSex = studentSex;
//    }
//
//    public String getStudentSex()
//    {
//        return studentSex;
//    }
//    public void setStudentStatus(String studentStatus)
//    {
//        this.studentStatus = studentStatus;
//    }
//
//    public String getStudentStatus()
//    {
//        return studentStatus;
//    }
//    public void setStudentBirthday(Date studentBirthday)
//    {
//        this.studentBirthday = studentBirthday;
//    }
//
//    public Date getStudentBirthday()
//    {
//        return studentBirthday;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("studentId", getStudentId())
//            .append("studentName", getStudentName())
//            .append("studentAge", getStudentAge())
//            .append("studentSex", getStudentSex())
//            .append("studentStatus", getStudentStatus())
//            .append("studentBirthday", getStudentBirthday())
//            .append("remark", getRemark())
//            .toString();
//    }
}
