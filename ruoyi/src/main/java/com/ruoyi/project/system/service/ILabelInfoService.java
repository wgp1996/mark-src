package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.LabelInfo;

/**
 * 电子价签管理Service接口
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public interface ILabelInfoService 
{
    /**
     * 查询电子价签管理
     * 
     * @param id 电子价签管理ID
     * @return 电子价签管理
     */
    public LabelInfo selectLabelInfoById(String id);

    /**
     * 查询电子价签管理列表
     * 
     * @param labelInfo 电子价签管理
     * @return 电子价签管理集合
     */
    public List<LabelInfo> selectLabelInfoList(LabelInfo labelInfo);


    /**
     * 根据商品编码查询电子价签
     * @return 电子价签
     */
    public LabelInfo selectLabelInfoByGoodsCode(String goodsCode,String storeId);

    /**
     * 新增电子价签管理
     * 
     * @param labelInfo 电子价签管理
     * @return 结果
     */
    public int insertLabelInfo(LabelInfo labelInfo);



    /**
     * 修改电子价签管理
     * 
     * @param labelInfo 电子价签管理
     * @return 结果
     */
    public int updateLabelInfo(LabelInfo labelInfo);

    /**
     * 修改电子价签
     *
     * @param labelInfo 电子价签管理
     * @return 结果
     */
    public int updateLabelInfoByMac(LabelInfo labelInfo);

    /**
     * 检查电子价签是否重复
     *
     * @param mac 电子价签MACd地址
     * @return 结果
     */
    public int checkLabel(String mac,String id);

    /**
     * 批量删除电子价签管理
     * 
     * @param ids 需要删除的电子价签管理ID
     * @return 结果
     */
    public int deleteLabelInfoByIds(String[] ids);

    /**
     * 批量修改电子价签绑定网关状态
     *
     * @param ids 需要删除的电子价签管理MAC地址
     * @return 结果
     */
    public int bindGateWayMacs(String[] ids);

    /**
     * 批量修改电子价签管理
     *
     * @param goodsCodes 需要删除的电子价签管理编码
     * @return 结果
     */
    public int updateLabelInfoStatus(String[] goodsCodes,String storeId);

    /**
     * 删除电子价签管理信息
     * 
     * @param id 电子价签管理ID
     * @return 结果
     */
    public int deleteLabelInfoById(String id);
}
