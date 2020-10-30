package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.LabelInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 电子价签管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public interface LabelInfoMapper 
{
    /**
     * 查询电子价签管理
     * 
     * @param id 电子价签管理ID
     * @return 电子价签管理
     */
    public LabelInfo selectLabelInfoById(String id);
    /**
     * mac查询电子价签管理
     *
     * @param mac 电子价签mac
     * @return 电子价签管理
     */
    public LabelInfo selectLabelInfoByMac(String mac);
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
     * 批量修改电子价签管理
     *
     * @param goodsCodes 需要删除的电子价签管理编码
     * @return 结果
     */
    public int updateLabelInfoStatus(@Param(value = "goodsCodes") String[] goodsCodes,@Param(value = "storeId") String storeId);
    /**
     * 检查电子价签是否重复
     *
     * @param mac 电子价签MACd地址
     * @return 结果
     */
    public int checkLabel(String mac,String id);
    /**
     * 检查电子价签编码是否重复
     *
     * @param labelCode 电子价签编码
     * @return 结果
     */
    public int checkLabelCode(String labelCode,String id);
    /**
     * 新增电子价签管理
     * 
     * @param labelInfo 电子价签管理
     * @return 结果
     */
    public int insertLabelInfo(LabelInfo labelInfo);
    /**
     * 批量修改电子价签绑定网关状态
     *
     * @param ids 需要删除的电子价签管理MAC地址
     * @return 结果
     */
    public int bindGateWayMacs(String[] ids);
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
     * 删除电子价签管理
     * 
     * @param id 电子价签管理ID
     * @return 结果
     */
    public int deleteLabelInfoById(String id);

    /**
     * 批量删除电子价签管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLabelInfoByIds(String[] ids);
}
