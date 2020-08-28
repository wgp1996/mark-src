package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.SysFile;

/**
 * 附件建档Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-27
 */
public interface SysFileMapper 
{
    /**
     * 查询附件建档
     * 
     * @param id 附件建档ID
     * @return 附件建档
     */
    public SysFile selectSysFileById(String id);

    /**
     * 查询附件建档列表
     * 
     * @param sysFile 附件建档
     * @return 附件建档集合
     */
    public List<SysFile> selectSysFileList(SysFile sysFile);

    /**
     * 新增附件建档
     * 
     * @param sysFile 附件建档
     * @return 结果
     */
    public int insertSysFile(SysFile sysFile);

    /**
     * 修改附件建档
     * 
     * @param sysFile 附件建档
     * @return 结果
     */
    public int updateSysFile(SysFile sysFile);

    /**
     * 删除附件建档
     * 
     * @param id 附件建档ID
     * @return 结果
     */
    public int deleteSysFileById(String id);

    /**
     * 批量删除附件建档
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysFileByIds(String[] ids);
    /**
     * 批量删除附件建档
     *
     * @param ids 需要删除的附件建档DJ_Number
     * @return 结果
     */
    public int deleteSysFileNums(String[] ids);
}
