package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.LeaseContractPool;

import java.util.List;

/**
 * 联营合同Mapper接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface LeaseContractPoolMapper {
    /**
     * 查询联营合同
     *
     * @param id 联营合同ID
     * @return 联营合同
     */
    public LeaseContractPool selectLeaseContractById(String id);

    /**
     * 查询联营合同
     *
     * @param id   联营合同ID
     * @param code 联营合同编号
     * @return 联营合同
     */
    public LeaseContractPool selectLeaseContractByCode(String code, String id);

    /**
     * 查询联营合同列表
     *
     * @param LeaseContractPool 联营合同
     * @return 联营合同集合
     */
    public List<LeaseContractPool> selectLeaseContractList(LeaseContractPool LeaseContractPool);

    /**
     * 新增联营合同
     *
     * @param LeaseContractPool 联营合同
     * @return 结果
     */
    public int insertLeaseContract(LeaseContractPool LeaseContractPool);

    /**
     * 修改联营合同
     *
     * @param LeaseContractPool 联营合同
     * @return 结果
     */
    public int updateLeaseContract(LeaseContractPool LeaseContractPool);

    /**
     * 删除联营合同
     *
     * @param id 联营合同ID
     * @return 结果
     */
    public int deleteLeaseContractById(String id);

    /**
     * 批量删除联营合同
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeaseContractByIds(String[] ids);
}
