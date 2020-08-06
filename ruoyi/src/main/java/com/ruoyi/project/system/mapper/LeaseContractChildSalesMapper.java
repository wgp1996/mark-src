package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.LeaseContractChildSales;

import java.util.List;

/**
 * 销售合同子表信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface LeaseContractChildSalesMapper {
    /**
     * 查询销售合同子表信息
     *
     * @param id 销售合同子表信息ID
     * @return 销售合同子表信息
     */
    public LeaseContractChildSales selectLeaseContractChildById(String id);

    /**
     * 查询销售合同子表信息
     *
     * @param code 销售合同主表信息编码
     * @return 销售合同子表信息
     */
    public List<LeaseContractChildSales> selectLeaseContractChildByCode(String code);

    /**
     * 查询销售合同子表信息列表
     *
     * @param LeaseContractChildSales 销售合同子表信息
     * @return 销售合同子表信息集合
     */
    public List<LeaseContractChildSales> selectLeaseContractChildList(LeaseContractChildSales LeaseContractChildSales);

    /**
     * 新增销售合同子表信息
     *
     * @param LeaseContractChildSales 销售合同子表信息
     * @return 结果
     */
    public int insertLeaseContractChild(LeaseContractChildSales LeaseContractChildSales);

    /**
     * 修改销售合同子表信息
     *
     * @param LeaseContractChildSales 销售合同子表信息
     * @return 结果
     */
    public int updateLeaseContractChild(LeaseContractChildSales LeaseContractChildSales);

    /**
     * 删除销售合同子表信息
     *
     * @param id 销售合同子表信息ID
     * @return 结果
     */
    public int deleteLeaseContractChildById(String id);

    /**
     * 批量删除销售合同子表信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeaseContractChildByIds(String[] ids);

    /**
     * 根据主表ID批量删除销售合同子表信息
     *
     * @param ids 需要删除的主表数据ID
     * @return 结果
     */
    public int deleteLeaseContractChildPid(String[] ids);
    /**
     * 根据子表ID修改摊位信息
     *
     * @param id 租赁合同子表信息ID
     * @return 结果
     */
    public int updateStallInfoById(String id);


    /**
     * 根据主表ID修改摊位信息
     *
     * @param ids 需要删除的租赁合同主表信息ID
     * @return 结果
     */
    public int updateStallInfoByPids(String[] ids);

}
