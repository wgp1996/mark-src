package com.ruoyi.project.system.api;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.CgRkd;
import com.ruoyi.project.system.domain.CgRkdChild;
import com.ruoyi.project.system.service.ICgRkdChildService;
import com.ruoyi.project.system.service.ICgRkdService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 进货单Controller
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
@Api("APP接口管理")
@RestController
@RequestMapping("/mark/api")
public class ApiController extends BaseController
{
    

}
