package com.ymsw.ranking.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymsw.ranking.mapper.YmswPerformanceRankingMapper;
import com.ymsw.ranking.domain.YmswPerformanceRanking;
import com.ymsw.ranking.service.IYmswPerformanceRankingService;
import com.ymsw.common.core.text.Convert;

/**
 * 业绩排行Service业务层处理
 * 
 * @author ymsw
 * @date 2020-06-22
 */
@Service
public class YmswPerformanceRankingServiceImpl implements IYmswPerformanceRankingService 
{
    @Autowired
    private YmswPerformanceRankingMapper ymswPerformanceRankingMapper;

    /**
     * 查询业绩排行
     * 
     * @param rankingId 业绩排行ID
     * @return 业绩排行
     */
    @Override
    public YmswPerformanceRanking selectYmswPerformanceRankingById(Long rankingId)
    {
        return ymswPerformanceRankingMapper.selectYmswPerformanceRankingById(rankingId);
    }

    /**
     * 查询业绩排行列表
     * 
     * @param ymswPerformanceRanking 业绩排行
     * @return 业绩排行
     */
    @Override
    public List<YmswPerformanceRanking> selectYmswPerformanceRankingList(YmswPerformanceRanking ymswPerformanceRanking)
    {
        return ymswPerformanceRankingMapper.selectYmswPerformanceRankingList(ymswPerformanceRanking);
    }

    /**
     * 新增业绩排行
     * 
     * @param ymswPerformanceRanking 业绩排行
     * @return 结果
     */
    @Override
    public int insertYmswPerformanceRanking(YmswPerformanceRanking ymswPerformanceRanking)
    {
        return ymswPerformanceRankingMapper.insertYmswPerformanceRanking(ymswPerformanceRanking);
    }

    /**
     * 修改业绩排行
     * 
     * @param ymswPerformanceRanking 业绩排行
     * @return 结果
     */
    @Override
    public int updateYmswPerformanceRanking(YmswPerformanceRanking ymswPerformanceRanking)
    {
        return ymswPerformanceRankingMapper.updateYmswPerformanceRanking(ymswPerformanceRanking);
    }

    /**
     * 删除业绩排行对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYmswPerformanceRankingByIds(String ids)
    {
        return ymswPerformanceRankingMapper.deleteYmswPerformanceRankingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除业绩排行信息
     * 
     * @param rankingId 业绩排行ID
     * @return 结果
     */
    @Override
    public int deleteYmswPerformanceRankingById(Long rankingId)
    {
        return ymswPerformanceRankingMapper.deleteYmswPerformanceRankingById(rankingId);
    }
}
