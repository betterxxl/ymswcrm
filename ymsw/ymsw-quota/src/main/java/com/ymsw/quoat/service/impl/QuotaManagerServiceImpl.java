package com.ymsw.quoat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymsw.quoat.mapper.QuotaManagerMapper;
import com.ymsw.quoat.domain.QuotaManager;
import com.ymsw.quoat.service.IQuotaManagerService;
import com.ymsw.common.core.text.Convert;

/**
 * 配额管理表Service业务层处理
 * 
 * @author ymsw
 * @date 2020-05-22
 */
@Service
public class QuotaManagerServiceImpl implements IQuotaManagerService 
{
    @Autowired
    private QuotaManagerMapper quotaManagerMapper;

    /**
     * 查询配额管理表
     * 
     * @param quotaId 配额管理表ID
     * @return 配额管理表
     */
    @Override
    public QuotaManager selectQuotaManagerById(Integer quotaId)
    {
        return quotaManagerMapper.selectQuotaManagerById(quotaId);
    }

    /**
     * 查询配额管理表列表
     * 
     * @param quotaManager 配额管理表
     * @return 配额管理表
     */
    @Override
    public List<QuotaManager> selectQuotaManagerList(QuotaManager quotaManager)
    {
        return quotaManagerMapper.selectQuotaManagerList(quotaManager);
    }

    /**
     * 新增配额管理表
     * 
     * @param quotaManager 配额管理表
     * @return 结果
     */
    @Override
    public int insertQuotaManager(QuotaManager quotaManager)
    {
        return quotaManagerMapper.insertQuotaManager(quotaManager);
    }

    /**
     * 修改配额管理表
     * 
     * @param quotaManager 配额管理表
     * @return 结果
     */
    @Override
    public int updateQuotaManager(QuotaManager quotaManager)
    {
        return quotaManagerMapper.updateQuotaManager(quotaManager);
    }

    /**
     * 删除配额管理表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQuotaManagerByIds(String ids)
    {
        return quotaManagerMapper.deleteQuotaManagerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除配额管理表信息
     * 
     * @param quotaId 配额管理表ID
     * @return 结果
     */
    @Override
    public int deleteQuotaManagerById(Integer quotaId)
    {
        return quotaManagerMapper.deleteQuotaManagerById(quotaId);
    }
}
