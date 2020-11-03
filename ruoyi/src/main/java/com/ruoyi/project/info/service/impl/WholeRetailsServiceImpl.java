package com.ruoyi.project.info.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.info.domain.WholeRetails;
import com.ruoyi.project.info.mapper.WholeRetailsMapper;
import com.ruoyi.project.info.service.IWholeRetailsService;
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
public class WholeRetailsServiceImpl implements IWholeRetailsService
{
    @Autowired
    private WholeRetailsMapper WholeRetailMapper;

    /**
     * 查询批发销货一票通
     * 
     * @param id 批发销货一票通ID
     * @return 批发销货一票通
     */
    @Override
    public WholeRetails selectWholeRetailById(String id)
    {
        return WholeRetailMapper.selectWholeRetailById(id);
    }
    /**
     * 查询首页数量信息
     *
     */
    @Override
    public WholeRetails selectNumlList(){
        return WholeRetailMapper.selectNumlList();
    }
    /**
     * 查询批发销货一票通列表
     * 
     * @param WholeRetail 批发销货一票通
     * @return 批发销货一票通
     */
    @Override
    public List<WholeRetails> selectWholeRetailList(WholeRetails WholeRetail)
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
    public int insertWholeRetail(WholeRetails WholeRetail)
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
    public int updateWholeRetail(WholeRetails WholeRetail)
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
