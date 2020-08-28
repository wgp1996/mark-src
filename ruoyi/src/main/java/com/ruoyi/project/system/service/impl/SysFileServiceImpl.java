package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysFileMapper;
import com.ruoyi.project.system.domain.SysFile;
import com.ruoyi.project.system.service.ISysFileService;

/**
 * 附件建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-27
 */
@Service
public class SysFileServiceImpl implements ISysFileService 
{
    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 查询附件建档
     * 
     * @param id 附件建档ID
     * @return 附件建档
     */
    @Override
    public SysFile selectSysFileById(String id)
    {
        return sysFileMapper.selectSysFileById(id);
    }

    /**
     * 查询附件建档列表
     * 
     * @param sysFile 附件建档
     * @return 附件建档
     */
    @Override
    public List<SysFile> selectSysFileList(SysFile sysFile)
    {
        return sysFileMapper.selectSysFileList(sysFile);
    }

    /**
     * 新增附件建档
     * 
     * @param sysFile 附件建档
     * @return 结果
     */
    @Override
    public int insertSysFile(SysFile sysFile)
    {
        return sysFileMapper.insertSysFile(sysFile);
    }

    /**
     * 修改附件建档
     * 
     * @param sysFile 附件建档
     * @return 结果
     */
    @Override
    public int updateSysFile(SysFile sysFile)
    {
        return sysFileMapper.updateSysFile(sysFile);
    }

    /**
     * 批量删除附件建档
     * 
     * @param ids 需要删除的附件建档ID
     * @return 结果
     */
    @Override
    public int deleteSysFileByIds(String[] ids)
    {
        return sysFileMapper.deleteSysFileByIds(ids);
    }

    /**
     * 删除附件建档信息
     * 
     * @param id 附件建档ID
     * @return 结果
     */
    @Override
    public int deleteSysFileById(String id)
    {
        return sysFileMapper.deleteSysFileById(id);
    }
    /**
     * 批量删除附件建档
     *
     * @param ids 需要删除的附件建档DJ_Number
     * @return 结果
     */
    @Override
    public int deleteSysFileNums(String[] ids){
        return sysFileMapper.deleteSysFileNums(ids);
    }
}
