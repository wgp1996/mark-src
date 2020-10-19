package com.ruoyi.project.info.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.info.mapper.FeeItemMapper;
import com.ruoyi.project.info.domain.FeeItem;
import com.ruoyi.project.info.service.IFeeItemService;

/**
 * 费用项目Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-16
 */
@Service
public class FeeItemServiceImpl implements IFeeItemService 
{
    @Autowired
    private FeeItemMapper feeItemMapper;
    /**
     * 检查费用项目
     *
     * @param id 费用项目ID
     * @return 费用项目
     */
    @Override
    public int checkFeeItem(Long id,String createBy,String feeName){
        return feeItemMapper.checkFeeItem(id,createBy,feeName);
    }

    /**
     * 查询费用项目
     * 
     * @param id 费用项目ID
     * @return 费用项目
     */
    @Override
    public FeeItem selectFeeItemById(Long id)
    {
        return feeItemMapper.selectFeeItemById(id);
    }

    /**
     * 查询费用项目列表
     * 
     * @param feeItem 费用项目
     * @return 费用项目
     */
    @Override
    public List<FeeItem> selectFeeItemList(FeeItem feeItem)
    {
        return feeItemMapper.selectFeeItemList(feeItem);
    }

    /**
     * 新增费用项目
     * 
     * @param feeItem 费用项目
     * @return 结果
     */
    @Override
    public int insertFeeItem(FeeItem feeItem)
    {
        feeItem.setCreateTime(DateUtils.getNowDate());
        return feeItemMapper.insertFeeItem(feeItem);
    }

    /**
     * 修改费用项目
     * 
     * @param feeItem 费用项目
     * @return 结果
     */
    @Override
    public int updateFeeItem(FeeItem feeItem)
    {
        feeItem.setUpdateTime(DateUtils.getNowDate());
        return feeItemMapper.updateFeeItem(feeItem);
    }

    /**
     * 批量删除费用项目
     * 
     * @param ids 需要删除的费用项目ID
     * @return 结果
     */
    @Override
    public int deleteFeeItemByIds(Long[] ids)
    {
        return feeItemMapper.deleteFeeItemByIds(ids);
    }

    /**
     * 删除费用项目信息
     * 
     * @param id 费用项目ID
     * @return 结果
     */
    @Override
    public int deleteFeeItemById(Long id)
    {
        return feeItemMapper.deleteFeeItemById(id);
    }
}
