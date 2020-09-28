package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.CheckObject;

/**
 * 监测物体建档Service接口
 * 
 * @author ruoyi
 * @date 2020-09-27
 */
public interface ICheckObjectService 
{
    /**
     * 查询监测物体建档
     * 
     * @param id 监测物体建档ID
     * @return 监测物体建档
     */
    public CheckObject selectCheckObjectById(Integer id);

    /**
     * 查询监测物体建档列表
     * 
     * @param checkObject 监测物体建档
     * @return 监测物体建档集合
     */
    public List<CheckObject> selectCheckObjectList(CheckObject checkObject);

    /**
     * 新增监测物体建档
     * 
     * @param checkObject 监测物体建档
     * @return 结果
     */
    public int insertCheckObject(CheckObject checkObject);

    /**
     * 检测物体是否重复
     *
     * @param checkObject 检测物体
     * @return 结果
     */
    public int checkObjectIsContain(CheckObject checkObject);

    /**
     * 修改监测物体建档
     * 
     * @param checkObject 监测物体建档
     * @return 结果
     */
    public int updateCheckObject(CheckObject checkObject);

    /**
     * 批量删除监测物体建档
     * 
     * @param ids 需要删除的监测物体建档ID
     * @return 结果
     */
    public int deleteCheckObjectByIds(Integer[] ids);

    /**
     * 删除监测物体建档信息
     * 
     * @param id 监测物体建档ID
     * @return 结果
     */
    public int deleteCheckObjectById(Integer id);
}
