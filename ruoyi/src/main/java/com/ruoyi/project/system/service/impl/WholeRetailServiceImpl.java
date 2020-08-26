package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.WholeRetail;
import com.ruoyi.project.system.mapper.WholeRetailMapper;
import com.ruoyi.project.system.service.IWholeRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批发销货一票通Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
@Service
public class WholeRetailServiceImpl implements IWholeRetailService 
{
    @Autowired
    private WholeRetailMapper WholeRetailMapper;

    /**
     * 查询批发销货一票通
     * 
     * @param id 批发销货一票通ID
     * @return 批发销货一票通
     */
    @Override
    public WholeRetail selectWholeRetailById(String id)
    {
        return WholeRetailMapper.selectWholeRetailById(id);
    }

    /**
     * 查询批发销货一票通列表
     * 
     * @param WholeRetail 批发销货一票通
     * @return 批发销货一票通
     */
    @Override
    public List<WholeRetail> selectWholeRetailList(WholeRetail WholeRetail)
    {
        return WholeRetailMapper.selectWholeRetailList(WholeRetail);
    }

    /**
     * 新增批发销货一票通
     * 
     * @param WholeRetail 批发销货一票通
     * @return 结果
     */
    @Override
    public int insertWholeRetail(WholeRetail WholeRetail)
    {
        WholeRetail.setCreateTime(DateUtils.getNowDate());
        return WholeRetailMapper.insertWholeRetail(WholeRetail);
    }

    /**
     * 修改批发销货一票通
     * 
     * @param WholeRetail 批发销货一票通
     * @return 结果
     */
    @Override
    public int updateWholeRetail(WholeRetail WholeRetail)
    {
        WholeRetail.setUpdateTime(DateUtils.getNowDate());
        return WholeRetailMapper.updateWholeRetail(WholeRetail);
    }

    /**
     * 批量删除批发销货一票通
     * 
     * @param ids 需要删除的批发销货一票通ID
     * @return 结果
     */
    @Override
    public int deleteWholeRetailByIds(String[] ids)
    {
        return WholeRetailMapper.deleteWholeRetailByIds(ids);
    }

    /**
     * 删除批发销货一票通信息
     * 
     * @param id 批发销货一票通ID
     * @return 结果
     */
    @Override
    public int deleteWholeRetailById(String id)
    {
        return WholeRetailMapper.deleteWholeRetailById(id);
    }
    /**
     * 批量生效单据
     *
     * @param ids 需要生效的单据ID
     * @return 结果
     */
    @Override
    public int updateWholeRetailStatus(String[] ids){
        return WholeRetailMapper.updateWholeRetailStatus(ids);
    }
}
