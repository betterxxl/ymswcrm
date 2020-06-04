package com.ymsw.quota.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymsw.quota.mapper.QuotaManagerMapper;
import com.ymsw.quota.domain.QuotaManager;
import com.ymsw.quota.service.IQuotaManagerService;
import com.ymsw.common.core.text.Convert;

/**
 * 配额管理Service业务层处理
 * 
 * @author ymsw
 * @date 2020-06-04
 */
@Service
public class QuotaManagerServiceImpl implements IQuotaManagerService 
{
    @Autowired
    private QuotaManagerMapper quotaManagerMapper;

    /**
     * 查询配额管理
     * 
     * @param quotaId 配额管理ID
     * @return 配额管理
     */
    @Override
    public QuotaManager selectQuotaManagerById(Long quotaId)
    {
        return quotaManagerMapper.selectQuotaManagerById(quotaId);
    }

    /**
     * 查询配额管理列表
     * 
     * @param quotaManager 配额管理
     * @return 配额管理
     */
    @Override
    public List<QuotaManager> selectQuotaManagerList(QuotaManager quotaManager)
    {
        return quotaManagerMapper.selectQuotaManagerList(quotaManager);
    }

    /**
     * 新增配额管理
     * 
     * @param quotaManager 配额管理
     * @return 结果
     */
    @Override
    public int insertQuotaManager(QuotaManager quotaManager)
    {
        return quotaManagerMapper.insertQuotaManager(quotaManager);
    }

    /**
     * 修改配额管理
     * 
     * @param quotaManager 配额管理
     * @return 结果
     */
    @Override
    public int updateQuotaManager(QuotaManager quotaManager)
    {
        return quotaManagerMapper.updateQuotaManager(quotaManager);
    }

    /**
     * 删除配额管理对象
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
     * 删除配额管理信息
     * 
     * @param quotaId 配额管理ID
     * @return 结果
     */
    @Override
    public int deleteQuotaManagerById(Long quotaId)
    {
        return quotaManagerMapper.deleteQuotaManagerById(quotaId);
    }
}
