package com.ymsw.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymsw.customer.mapper.YmswCollectionPoolMapper;
import com.ymsw.customer.domain.YmswCollectionPool;
import com.ymsw.customer.service.IYmswCollectionPoolService;
import com.ymsw.common.core.text.Convert;

/**
 * 收藏夹-公共池Service业务层处理
 * 
 * @author ymsw
 * @date 2020-05-18
 */
@Service
public class YmswCollectionPoolServiceImpl implements IYmswCollectionPoolService 
{
    @Autowired
    private YmswCollectionPoolMapper ymswCollectionPoolMapper;

    /**
     * 查询收藏夹-公共池
     * 
     * @param cpId 收藏夹-公共池ID
     * @return 收藏夹-公共池
     */
    @Override
    public YmswCollectionPool selectYmswCollectionPoolById(Long cpId)
    {
        return ymswCollectionPoolMapper.selectYmswCollectionPoolById(cpId);
    }

    /**
     * 查询收藏夹-公共池列表
     * 
     * @param ymswCollectionPool 收藏夹-公共池
     * @return 收藏夹-公共池
     */
    @Override
    public List<YmswCollectionPool> selectYmswCollectionPoolList(YmswCollectionPool ymswCollectionPool)
    {
        return ymswCollectionPoolMapper.selectYmswCollectionPoolList(ymswCollectionPool);
    }

    /**
     * 新增收藏夹-公共池
     * 
     * @param ymswCollectionPool 收藏夹-公共池
     * @return 结果
     */
    @Override
    public int insertYmswCollectionPool(YmswCollectionPool ymswCollectionPool)
    {
        return ymswCollectionPoolMapper.insertYmswCollectionPool(ymswCollectionPool);
    }

    /**
     * 修改收藏夹-公共池
     * 
     * @param ymswCollectionPool 收藏夹-公共池
     * @return 结果
     */
    @Override
    public int updateYmswCollectionPool(YmswCollectionPool ymswCollectionPool)
    {
        return ymswCollectionPoolMapper.updateYmswCollectionPool(ymswCollectionPool);
    }

    /**
     * 删除收藏夹-公共池对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYmswCollectionPoolByIds(String ids)
    {
        return ymswCollectionPoolMapper.deleteYmswCollectionPoolByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收藏夹-公共池信息
     * 
     * @param cpId 收藏夹-公共池ID
     * @return 结果
     */
    @Override
    public int deleteYmswCollectionPoolById(Long cpId)
    {
        return ymswCollectionPoolMapper.deleteYmswCollectionPoolById(cpId);
    }
}
