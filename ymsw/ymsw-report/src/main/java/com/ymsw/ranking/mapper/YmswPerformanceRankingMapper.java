package com.ymsw.ranking.mapper;

import com.ymsw.ranking.domain.YmswPerformanceRanking;
import java.util.List;

/**
 * 业绩排行Mapper接口
 * 
 * @author ymsw
 * @date 2020-06-22
 */
public interface YmswPerformanceRankingMapper 
{
    /**
     * 查询业绩排行
     * 
     * @param rankingId 业绩排行ID
     * @return 业绩排行
     */
    public YmswPerformanceRanking selectYmswPerformanceRankingById(Long rankingId);

    /**
     * 查询业绩排行列表
     * 
     * @param ymswPerformanceRanking 业绩排行
     * @return 业绩排行集合
     */
    public List<YmswPerformanceRanking> selectYmswPerformanceRankingList(YmswPerformanceRanking ymswPerformanceRanking);

    /**
     * 新增业绩排行
     * 
     * @param ymswPerformanceRanking 业绩排行
     * @return 结果
     */
    public int insertYmswPerformanceRanking(YmswPerformanceRanking ymswPerformanceRanking);

    /**
     * 修改业绩排行
     * 
     * @param ymswPerformanceRanking 业绩排行
     * @return 结果
     */
    public int updateYmswPerformanceRanking(YmswPerformanceRanking ymswPerformanceRanking);

    /**
     * 删除业绩排行
     * 
     * @param rankingId 业绩排行ID
     * @return 结果
     */
    public int deleteYmswPerformanceRankingById(Long rankingId);

    /**
     * 批量删除业绩排行
     * 
     * @param rankingIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYmswPerformanceRankingByIds(String[] rankingIds);
}
