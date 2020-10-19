package com.ruoyi.project.info.service;

import java.util.List;
import com.ruoyi.project.info.domain.FeeItem;

/**
 * 费用项目Service接口
 * 
 * @author ruoyi
 * @date 2020-10-16
 */
public interface IFeeItemService 
{
    /**
     * 查询费用项目
     * 
     * @param id 费用项目ID
     * @return 费用项目
     */
    public FeeItem selectFeeItemById(Long id);

    /**
     * 检查费用项目
     *
     * @param id 费用项目ID
     * @return 费用项目
     */
    public int checkFeeItem(Long id,String createBy,String feeName);

    /**
     * 查询费用项目列表
     * 
     * @param feeItem 费用项目
     * @return 费用项目集合
     */
    public List<FeeItem> selectFeeItemList(FeeItem feeItem);

    /**
     * 新增费用项目
     * 
     * @param feeItem 费用项目
     * @return 结果
     */
    public int insertFeeItem(FeeItem feeItem);

    /**
     * 修改费用项目
     * 
     * @param feeItem 费用项目
     * @return 结果
     */
    public int updateFeeItem(FeeItem feeItem);

    /**
     * 批量删除费用项目
     * 
     * @param ids 需要删除的费用项目ID
     * @return 结果
     */
    public int deleteFeeItemByIds(Long[] ids);

    /**
     * 删除费用项目信息
     * 
     * @param id 费用项目ID
     * @return 结果
     */
    public int deleteFeeItemById(Long id);
}
