package com.ruoyi.project.info.controller;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.config.ServerConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.info.domain.SysStudent;
import com.ruoyi.project.info.service.ISysStudentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学生信息Controller
 * 
 * @author ruoyi
 * @date 2020-03-31
 */
@RestController
@RequestMapping("/system/student")
public class SysStudentController extends BaseController
{
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private ISysStudentService sysStudentService;




    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String url = serverConfig.getUrl() + fileName;
        File dest = new File(url);
        try {
            file.transferTo(dest);

            return "上传成功";
        } catch (IOException e) {

        }
        return "上传失败！";
    }

    /**
     * 查询学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStudent sysStudent)
    {
        startPage();
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysStudent sysStudent)
    {
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        return util.exportExcel(list, "student");
    }

    /**
     * 获取学生信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return AjaxResult.success(sysStudentService.selectSysStudentById(studentId));
    }

    /**
     * 新增学生信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.insertSysStudent(sysStudent));
    }

    /**
     * 修改学生信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:edit')")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.updateSysStudent(sysStudent));
    }

    /**
     * 删除学生信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:remove')")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(sysStudentService.deleteSysStudentByIds(studentIds));
    }
}
