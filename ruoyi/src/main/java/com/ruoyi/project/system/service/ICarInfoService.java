package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.CarInfo;

/**
 * 车辆档案Service接口
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
public interface ICarInfoService 
{
    /**
     * 查询车辆档案
     * 
     * @param id 车辆档案ID
     * @return 车辆档案
     */
    public CarInfo selectCarInfoById(Integer id);


    /**
     * 根据车牌查询车辆档案
     *
     * @param id 车辆档案ID
     * @return 车辆档案
     */
    public CarInfo selectCarInfoByName(Integer id,String carNumber,String createBy);

    /**
     * 查询车辆档案列表
     * 
     * @param carInfo 车辆档案
     * @return 车辆档案集合
     */
    public List<CarInfo> selectCarInfoList(CarInfo carInfo);

    /**
     * 新增车辆档案
     * 
     * @param carInfo 车辆档案
     * @return 结果
     */
    public int insertCarInfo(CarInfo carInfo);

    /**
     * 修改车辆档案
     * 
     * @param carInfo 车辆档案
     * @return 结果
     */
    public int updateCarInfo(CarInfo carInfo);

    /**
     * 批量删除车辆档案
     * 
     * @param ids 需要删除的车辆档案ID
     * @return 结果
     */
    public int deleteCarInfoByIds(Integer[] ids);

    /**
     * 删除车辆档案信息
     * 
     * @param id 车辆档案ID
     * @return 结果
     */
    public int deleteCarInfoById(Integer id);
}
