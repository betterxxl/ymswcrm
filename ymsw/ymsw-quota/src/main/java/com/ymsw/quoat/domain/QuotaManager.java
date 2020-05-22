package com.ymsw.quoat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ymsw.common.annotation.Excel;
import com.ymsw.common.core.domain.BaseEntity;

/**
 * 配额管理表对象 quota_manager
 * 
 * @author ymsw
 * @date 2020-05-22
 */
public class QuotaManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配额管理id */
    private Integer quotaId;

    /** 用户id */
    @Excel(name = "用户id")
    private Integer userId;

    /** 总限额数 */
    @Excel(name = "总限额数")
    private Integer allowTotalCount;

    /** 今日配额数 */
    @Excel(name = "今日配额数")
    private Integer allowTodayCount;

    /** 配额状态 0 关闭 1开启 */
    @Excel(name = "配额状态 0 关闭 1开启")
    private String quotaStatus;

    public void setQuotaId(Integer quotaId) 
    {
        this.quotaId = quotaId;
    }

    public Integer getQuotaId() 
    {
        return quotaId;
    }
    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setAllowTotalCount(Integer allowTotalCount) 
    {
        this.allowTotalCount = allowTotalCount;
    }

    public Integer getAllowTotalCount() 
    {
        return allowTotalCount;
    }
    public void setAllowTodayCount(Integer allowTodayCount) 
    {
        this.allowTodayCount = allowTodayCount;
    }

    public Integer getAllowTodayCount() 
    {
        return allowTodayCount;
    }
    public void setQuotaStatus(String quotaStatus) 
    {
        this.quotaStatus = quotaStatus;
    }

    public String getQuotaStatus() 
    {
        return quotaStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("quotaId", getQuotaId())
            .append("userId", getUserId())
            .append("allowTotalCount", getAllowTotalCount())
            .append("allowTodayCount", getAllowTodayCount())
            .append("quotaStatus", getQuotaStatus())
            .toString();
    }
}
