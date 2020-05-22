package com.ymsw.order.service.impl;

import java.util.List;
import com.ymsw.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymsw.order.mapper.YmswOrderMapper;
import com.ymsw.order.domain.YmswOrder;
import com.ymsw.order.service.IYmswOrderService;
import com.ymsw.common.core.text.Convert;

/**
 * 订单信息表Service业务层处理
 * 
 * @author ymsw
 * @date 2020-05-22
 */
@Service
public class YmswOrderServiceImpl implements IYmswOrderService 
{
    @Autowired
    private YmswOrderMapper ymswOrderMapper;

    /**
     * 查询订单信息表
     * 
     * @param orderId 订单信息表ID
     * @return 订单信息表
     */
    @Override
    public YmswOrder selectYmswOrderById(Long orderId)
    {
        return ymswOrderMapper.selectYmswOrderById(orderId);
    }

    /**
     * 查询订单信息表列表
     * 
     * @param ymswOrder 订单信息表
     * @return 订单信息表
     */
    @Override
    public List<YmswOrder> selectYmswOrderList(YmswOrder ymswOrder)
    {
        return ymswOrderMapper.selectYmswOrderList(ymswOrder);
    }

    /**
     * 新增订单信息表
     * 
     * @param ymswOrder 订单信息表
     * @return 结果
     */
    @Override
    public int insertYmswOrder(YmswOrder ymswOrder)
    {
        return ymswOrderMapper.insertYmswOrder(ymswOrder);
    }

    /**
     * 修改订单信息表
     * 
     * @param ymswOrder 订单信息表
     * @return 结果
     */
    @Override
    public int updateYmswOrder(YmswOrder ymswOrder)
    {
        return ymswOrderMapper.updateYmswOrder(ymswOrder);
    }

    /**
     * 删除订单信息表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYmswOrderByIds(String ids)
    {
        return ymswOrderMapper.deleteYmswOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息表信息
     * 
     * @param orderId 订单信息表ID
     * @return 结果
     */
    @Override
    public int deleteYmswOrderById(Long orderId)
    {
        return ymswOrderMapper.deleteYmswOrderById(orderId);
    }
}
