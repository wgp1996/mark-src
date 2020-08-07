package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.LeaseContractChildPool;

import java.util.List;

/**
 * 联营合同子表信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface LeaseContractChildPoolMapper {
    /**
     * 查询联营合同子表信息
     *
     * @param id 联营合同子表信息ID
     * @return 联营合同子表信息
     */
    public LeaseContractChildPool selectLeaseContractChildById(String id);

    /**
     * 查询联营合同子表信息
     *
     * @param code 联营合同主表信息编码
     * @return 联营合同子表信息
     */
    public List<LeaseContractChildPool> selectLeaseContractChildByCode(String code);

    /**
     * 查询联营合同子表信息列表
     *
     * @param LeaseContractChildPool 联营合同子表信息
     * @return 联营合同子表信息集合
     */
    public List<LeaseContractChildPool> selectLeaseContractChildList(LeaseContractChildPool LeaseContractChildPool);

    /**
     * 新增联营合同子表信息
     *
     * @param LeaseContractChildPool 联营合同子表信息
     * @return 结果
     */
    public int insertLeaseContractChild(LeaseContractChildPool LeaseContractChildPool);

    /**
     * 修改联营合同子表信息
     *
     * @param LeaseContractChildPool 联营合同子表信息
     * @return 结果
     */
    public int updateLeaseContractChild(LeaseContractChildPool LeaseContractChildPool);

    /**
     * 删除联营合同子表信息
     *
     * @param id 联营合同子表信息ID
     * @return 结果
     */
    public int deleteLeaseContractChildById(String id);

    /**
     * 批量删除联营合同子表信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeaseContractChildByIds(String[] ids);

    /**
     * 根据主表ID批量删除联营合同子表信息
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
