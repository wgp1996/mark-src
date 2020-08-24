package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.GoodsInfoOwner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.CarInfo;
import com.ruoyi.project.system.service.ICarInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 车辆档案Controller
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
@RestController
@RequestMapping("/system/car")
public class CarInfoController extends BaseController
{
    @Autowired
    private ICarInfoService carInfoService;

    /**
     * 查询车辆档案列表
     */
    @PreAuthorize("@ss.hasPermi('system:car:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(CarInfo carInfo)
    {
        startPage();
        List<CarInfo> list = carInfoService.selectCarInfoList(carInfo);
        return getDataTable(list);
    }

    /**
     * 根据业主查询车辆
     * @param carInfo
     * @return
     */
    @GetMapping("/getCarlist")
    public TableDataInfo getCarlist(CarInfo carInfo)
    {
        List<CarInfo> list = carInfoService.selectCarInfoList(carInfo);
        return getDataTable(list);
    }

    /**
     * 导出车辆档案列表
     */
    @PreAuthorize("@ss.hasPermi('system:car:export')")
    @Log(title = "车辆档案", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CarInfo carInfo)
    {
        List<CarInfo> list = carInfoService.selectCarInfoList(carInfo);
        ExcelUtil<CarInfo> util = new ExcelUtil<CarInfo>(CarInfo.class);
        return util.exportExcel(list, "car");
    }

    /**
     * 获取车辆档案详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:car:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(carInfoService.selectCarInfoById(id));
    }

    /**
     * 新增车辆档案
     */
    @PreAuthorize("@ss.hasPermi('system:car:add')")
    @Log(title = "车辆档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarInfo carInfo)
    {
        carInfo.setCreateBy(SecurityUtils.getUsername());
        CarInfo info=carInfoService.selectCarInfoByName(-1,carInfo.getCarNumber(),carInfo.getCreateBy());
        if(info!=null) {
            return  toAjaxByError("该车辆在档案中已存在");
        }else{
            return toAjax(carInfoService.insertCarInfo(carInfo));
        }
    }

    /**
     * 修改车辆档案
     */
    @PreAuthorize("@ss.hasPermi('system:car:edit')")
    @Log(title = "车辆档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarInfo carInfo)
    {
        carInfo.setCreateBy(SecurityUtils.getUsername());
        CarInfo info=carInfoService.selectCarInfoByName(carInfo.getId(),carInfo.getCarNumber(),carInfo.getCreateBy());
        if(info!=null) {
            return  toAjaxByError("该车辆在档案中已存在");
        }else {
            return toAjax(carInfoService.updateCarInfo(carInfo));
        }
    }

    /**
     * 删除车辆档案
     */
    @PreAuthorize("@ss.hasPermi('system:car:remove')")
    @Log(title = "车辆档案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(carInfoService.deleteCarInfoByIds(ids));
    }
}
