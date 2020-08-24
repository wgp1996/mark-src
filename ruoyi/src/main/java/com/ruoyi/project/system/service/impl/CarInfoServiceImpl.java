package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CarInfoMapper;
import com.ruoyi.project.system.domain.CarInfo;
import com.ruoyi.project.system.service.ICarInfoService;

/**
 * 车辆档案Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
@Service
public class CarInfoServiceImpl implements ICarInfoService 
{
    @Autowired
    private CarInfoMapper carInfoMapper;

    /**
     * 查询车辆档案
     * 
     * @param id 车辆档案ID
     * @return 车辆档案
     */
    @Override
    public CarInfo selectCarInfoById(Integer id)
    {
        return carInfoMapper.selectCarInfoById(id);
    }
    /**
     * 根据车牌查询车辆档案
     *
     * @param id 车辆档案ID
     * @return 车辆档案
     */
    @Override
    public CarInfo selectCarInfoByName(Integer id,String carNumber,String createBy){
        return carInfoMapper.selectCarInfoByName(id,carNumber,createBy);
    }
    /**
     * 查询车辆档案列表
     * 
     * @param carInfo 车辆档案
     * @return 车辆档案
     */
    @Override
    public List<CarInfo> selectCarInfoList(CarInfo carInfo)
    {
        return carInfoMapper.selectCarInfoList(carInfo);
    }

    /**
     * 新增车辆档案
     * 
     * @param carInfo 车辆档案
     * @return 结果
     */
    @Override
    public int insertCarInfo(CarInfo carInfo)
    {
        carInfo.setCreateTime(DateUtils.getNowDate());
        return carInfoMapper.insertCarInfo(carInfo);
    }

    /**
     * 修改车辆档案
     * 
     * @param carInfo 车辆档案
     * @return 结果
     */
    @Override
    public int updateCarInfo(CarInfo carInfo)
    {
        carInfo.setUpdateTime(DateUtils.getNowDate());
        return carInfoMapper.updateCarInfo(carInfo);
    }

    /**
     * 批量删除车辆档案
     * 
     * @param ids 需要删除的车辆档案ID
     * @return 结果
     */
    @Override
    public int deleteCarInfoByIds(Integer[] ids)
    {
        return carInfoMapper.deleteCarInfoByIds(ids);
    }

    /**
     * 删除车辆档案信息
     * 
     * @param id 车辆档案ID
     * @return 结果
     */
    @Override
    public int deleteCarInfoById(Integer id)
    {
        return carInfoMapper.deleteCarInfoById(id);
    }
}
