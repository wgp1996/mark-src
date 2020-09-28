package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CheckObjectMapper;
import com.ruoyi.project.system.domain.CheckObject;
import com.ruoyi.project.system.service.ICheckObjectService;

/**
 * 监测物体建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
@Service
public class CheckObjectServiceImpl implements ICheckObjectService 
{
    @Autowired
    private CheckObjectMapper checkObjectMapper;

    /**
     * 查询监测物体建档
     * 
     * @param id 监测物体建档ID
     * @return 监测物体建档
     */
    @Override
    public CheckObject selectCheckObjectById(Integer id)
    {
        return checkObjectMapper.selectCheckObjectById(id);
    }

    /**
     * 查询监测物体建档列表
     * 
     * @param checkObject 监测物体建档
     * @return 监测物体建档
     */
    @Override
    public List<CheckObject> selectCheckObjectList(CheckObject checkObject)
    {
        return checkObjectMapper.selectCheckObjectList(checkObject);
    }

    /**
     * 新增监测物体建档
     * 
     * @param checkObject 监测物体建档
     * @return 结果
     */
    @Override
    public int insertCheckObject(CheckObject checkObject)
    {
        checkObject.setCreateTime(DateUtils.getNowDate());
        return checkObjectMapper.insertCheckObject(checkObject);
    }
    /**
     * 检测物体是否重复
     *
     * @param checkObject 检测物体
     * @return 结果
     */
    @Override
    public int checkObjectIsContain(CheckObject checkObject){
        return checkObjectMapper.checkObjectIsContain(checkObject);
    }
    /**
     * 修改监测物体建档
     * 
     * @param checkObject 监测物体建档
     * @return 结果
     */
    @Override
    public int updateCheckObject(CheckObject checkObject)
    {
        checkObject.setUpdateTime(DateUtils.getNowDate());
        return checkObjectMapper.updateCheckObject(checkObject);
    }

    /**
     * 批量删除监测物体建档
     * 
     * @param ids 需要删除的监测物体建档ID
     * @return 结果
     */
    @Override
    public int deleteCheckObjectByIds(Integer[] ids)
    {
        return checkObjectMapper.deleteCheckObjectByIds(ids);
    }

    /**
     * 删除监测物体建档信息
     * 
     * @param id 监测物体建档ID
     * @return 结果
     */
    @Override
    public int deleteCheckObjectById(Integer id)
    {
        return checkObjectMapper.deleteCheckObjectById(id);
    }
}
