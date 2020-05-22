package com.ymsw.quoat.mapper;

import com.ymsw.quoat.domain.QuotaManager;
import java.util.List;

/**
 * 配额管理表Mapper接口
 * 
 * @author ymsw
 * @date 2020-05-22
 */
public interface QuotaManagerMapper 
{
    /**
     * 查询配额管理表
     * 
     * @param quotaId 配额管理表ID
     * @return 配额管理表
     */
    public QuotaManager selectQuotaManagerById(Integer quotaId);

    /**
     * 查询配额管理表列表
     * 
     * @param quotaManager 配额管理表
     * @return 配额管理表集合
     */
    public List<QuotaManager> selectQuotaManagerList(QuotaManager quotaManager);

    /**
     * 新增配额管理表
     * 
     * @param quotaManager 配额管理表
     * @return 结果
     */
    public int insertQuotaManager(QuotaManager quotaManager);

    /**
     * 修改配额管理表
     * 
     * @param quotaManager 配额管理表
     * @return 结果
     */
    public int updateQuotaManager(QuotaManager quotaManager);

    /**
     * 删除配额管理表
     * 
     * @param quotaId 配额管理表ID
     * @return 结果
     */
    public int deleteQuotaManagerById(Integer quotaId);

    /**
     * 批量删除配额管理表
     * 
     * @param quotaIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQuotaManagerByIds(String[] quotaIds);
}
